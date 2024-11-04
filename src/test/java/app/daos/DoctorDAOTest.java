package app.daos;

import app.config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
class DoctorDAOTest
{
    private static EntityManagerFactory emf;
    private static DoctorDAO doctorDAO;

    @BeforeAll
    static void setUpAll()
    {
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactoryConfigTest();
        doctorDAO = new DoctorDAO(emf);
    }

    @BeforeEach
    void setUp()
    {
        PopulateOLD.populateDB(emf);
    }

    @AfterEach
    void tearDown()
    {
        PopulateOLD.CleanDB(emf);
    }

    @AfterAll
    static void tearDownAll()
    {
        if (emf != null)
        {
            emf.close();
        }
    }

    @Test
    void readAll()
    {
        List<Doctor> doctors = doctorDAO.readAll();
        assertNotNull(doctors);
        assertEquals(2, doctors.size());
    }


    @Test
    void create() {
        Doctor doctor = new Doctor("Dr. Emily White", LocalDate.of(1985, 2, 15), 2010, "City Health Clinic", Speciality.PEDIATRICS);
        Doctor createdDoctor = doctorDAO.create(doctor);

        assertNotNull(createdDoctor);
        assertEquals("Dr. Emily White", createdDoctor.getName());
        assertEquals(Speciality.PEDIATRICS, createdDoctor.getSpeciality());
    }

    @Test
    void update()
    {
        // Read an existing doctor (assume first entry for simplicity)
        Doctor doctor = doctorDAO.readAll().get(0);
        assertNotNull(doctor);

        // Modify details
        doctor.setDateOfBirth(LocalDate.of(1980, 1, 1));
        doctor.setYearOfGraduation(2005);
        doctor.setNameOfClinic("City Health Clinic");
        doctor.setSpeciality(Speciality.PEDIATRICS);

        // Update in database
        Doctor updatedDoctor = doctorDAO.update(doctor.getId(), doctor);

        assertNotNull(updatedDoctor);
        assertEquals(LocalDate.of(1980, 1, 1),updatedDoctor.getDateOfBirth());
        assertEquals(2005, updatedDoctor.getYearOfGraduation());
        assertEquals("City Health Clinic", updatedDoctor.getNameOfClinic());
        assertEquals(Speciality.PEDIATRICS, updatedDoctor.getSpeciality());
    }

    @Test
    void doctorBySpeciality() {
        List<Doctor> doctors = doctorDAO.doctorBySpeciality(Speciality.FAMILY_MEDICINE);
        assertNotNull(doctors);
        assertEquals(1,doctors.size());
        assertTrue(doctors.stream().allMatch(d -> d.getSpeciality() == Speciality.FAMILY_MEDICINE));
    }

    @Test
    void doctorByBirthdateRange() {
        LocalDate from = LocalDate.of(1975, 1, 1);
        LocalDate to = LocalDate.of(1985, 12, 31);

        List<Doctor> doctors = doctorDAO.doctorByBirthdateRange(from, to);
        assertNotNull(doctors);
        assertEquals(2,doctors.size());
        assertTrue(doctors.stream().allMatch(d -> !d.getDateOfBirth().isBefore(from) && !d.getDateOfBirth().isAfter(to)));
    }
}

 */