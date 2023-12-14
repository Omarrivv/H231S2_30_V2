
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samuel
 */
public class conexion {
    Connection cn;
    public Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost/registro","root","admin");
            System.out.println("conexion exitosa");
        } catch (Exception e) {
            System.out.println("error");
        }return cn;
    }
}
