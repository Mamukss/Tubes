/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
     public static Connection CON;
    public static final String URL = "jdbc:mysql://";
    public static final String DBNAME = "tubes";
    public static final String PATH = "localhost:3306/" + DBNAME;

    public Connection makeConnection() {
        System.out.println("Opening database...");
        try {
            CON = DriverManager.getConnection(URL + PATH, "root", "");
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println("error opening database");
            System.out.println(e);
        }
        return CON;
    }

    public void closeConnection() {
        System.out.println("closing database...");
        try {
            CON.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println("error closing database");
            System.out.println(e);
        }
    }
}
