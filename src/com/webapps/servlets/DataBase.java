package com.webapps.servlets;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by nick on 20.11.16.
 */
public class DataBase {

    public static ArrayList getProducts() {
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://mysql303.1gb.ua/gbua_pivasik";

        //  Database credentials
        final String USER = "gbua_pivasik";
        final String PASS = "86aa4a52cnm1";

        ArrayList<Product> products = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT `ID`,`TITLE`,`DESCRIPTION`,`PRICE`,`IMG_URL` FROM products";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                Double price = rs.getDouble("PRICE");
                String imgUrl = rs.getString("IMG_URL");

                products.add(new Product(id, title, description, price, imgUrl));

//                    //Display values
//                    System.out.print("ID: " + id);
//                    //System.out.print(", Age: " + age);
//                    System.out.print(", First: " + first);
//                    System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

        return products;
    }


}
