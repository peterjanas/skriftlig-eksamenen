package app;

import app.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        //EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();


        AppConfig.startServer();

    }
}