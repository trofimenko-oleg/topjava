package ru.javawebinar.topjava;

public class Profiles {
    public static final String
            JDBC = "jdbc",
            JPA = "jpa",
            DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = DATAJPA;

    public static final String
            POSTGRES_DB = "postgres",
            HSQL_DB = "hsqldb",
            HEROKU = "heroku";

    public static final String ACTIVE_DB = POSTGRES_DB;

    public static String getActiveDbProfile() {
        return ACTIVE_DB;
    }
}
