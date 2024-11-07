package app.controller;


import app.config.ApplicationConfig;
import app.config.HibernateConfig;
import app.config.Populate;
import app.daos.impl.TripDAO;
import app.dtos.TripDTO;
import app.security.controllers.SecurityController;
import app.security.daos.SecurityDAO;
import app.security.exceptions.ValidationException;
import dk.bugelhartmann.UserDTO;
import io.javalin.Javalin;
import io.restassured.common.mapper.TypeRef;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.Matchers.not;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TripControllerTest
{
    private final static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private final static SecurityController securityController = SecurityController.getInstance();
    private final static SecurityDAO securityDAO = new SecurityDAO(emf);
    private final static TripDAO tripDAO = new TripDAO(emf);
    private static Javalin app;

    private static UserDTO userDTO, adminDTO;
    private static String userToken, adminToken;
    private static final String BASE_URL = "http://localhost:7007/api";

    @BeforeAll
    static void setUpAll()
    {
        HibernateConfig.setTest(true);

        app = ApplicationConfig.startServer(7007);
    }


    @BeforeEach
    void setUp()
    {
        Populate.populateDB(emf);
        UserDTO[] users = Populate.populateUsers(emf);
        userDTO = users[0];
        adminDTO = users[1];

        try
        {
            UserDTO verifiedUser = securityDAO.getVerifiedUser(userDTO.getUsername(), userDTO.getPassword());
            UserDTO verifiedAdmin = securityDAO.getVerifiedUser(adminDTO.getUsername(), adminDTO.getPassword());
            userToken = "Bearer " + securityController.createToken(verifiedUser);
            adminToken = "Bearer " + securityController.createToken(verifiedAdmin);
        } catch (ValidationException e)
        {
            throw new RuntimeException(e);
        }

    }

    @AfterEach
    void tearDown()
    {
        Populate.cleanDB(emf);
    }

    @AfterAll
    static void tearDownAll()
    {
        ApplicationConfig.stopServer(app);
    }


    @Test
    void readAll()
    {
        List<TripDTO> tripDTO =
                given()
                        .when()
                        .header("Authorization", userToken)
                        .get(BASE_URL + "/trips")
                        .then()
                        .statusCode(200)
                        .body("size()", is(6))
                        .log().all()
                        .extract()
                        .as(new TypeRef<List<TripDTO>>()
                        {
                        });

        assertThat(tripDTO.size(), is(6));
    }
}

