package com.google.sps.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.google.cloud.sql.mysql.SocketFactory;

/** Handles requests sent to the /cloudsql URL. Try running a server and navigating to /cloudsql! */
@WebServlet("/cloudsql")
public class CloudSQLServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    // The configuration object specifies behaviors for the connection pool.
    HikariConfig config = new HikariConfig();
   
    
    config.setJdbcUrl("jdbc:mysql:///test");
    config.setUsername("anyone");
    config.setPassword("cdobno");
    config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
    config.addDataSourceProperty("cloudSqlInstance", "rwasim-sps-summer22:us-central1:practice553");
    config.addDataSourceProperty("ipTypes", "PUBLIC");

    DataSource pool = new HikariDataSource(config);
  
    Connection conn; // Cloud SQL connection

    // Cloud SQL table creation commands
    final String createContentTableSql =
        "CREATE TABLE IF NOT EXISTS posts ( post_id INT NOT NULL "
            + "AUTO_INCREMENT, author_id INT NOT NULL, timestamp DATETIME NOT NULL, "
            + "title VARCHAR(256) NOT NULL, "
            + "body VARCHAR(1337) NOT NULL, PRIMARY KEY (post_id) )";

    final String createUserTableSql =
        "CREATE TABLE IF NOT EXISTS users ( user_id INT NOT NULL "
            + "AUTO_INCREMENT, user_fullname VARCHAR(64) NOT NULL, "
            + "PRIMARY KEY (user_id) )";
      try {
        String url = System.getProperty("cloudsql");

        try {
          conn = DriverManager.getConnection(url);

          // Create the tables so that the SELECT query doesn't throw an exception
          // if the user visits the page before any posts have been added

          conn.createStatement().executeUpdate(createContentTableSql); // create content table
          conn.createStatement().executeUpdate(createUserTableSql); // create user table

          // Create a test user
          //conn.createStatement().executeUpdate(createTestUserSql);
        } catch (SQLException e) {
          throw new ServletException("Unable to connect to SQL server", e);
        }

      } finally {
        // Nothing really to do here.
      }
  }
}