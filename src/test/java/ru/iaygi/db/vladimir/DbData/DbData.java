package ru.iaygi.db.vladimir.DbData;

public class DbData {
    public static final String DB_URL = System.getProperty("db_url", "jdbc:postgresql://localhost:5432/postgres");
    public static final String DB_USER = System.getProperty("db_user", "postgres");
    public static final String DB_PASSWORD = System.getProperty("db_password", "postgres");
}