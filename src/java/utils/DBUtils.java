/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author SnoweyMTT
 */
public class DBUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SnoweyMTTVegetables";
        conn= DriverManager.getConnection(url, "sa", "251113");
        return conn;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        if(conn != null){
            System.out.println("Success");
        }
        else{
            System.out.println("Fail");
        }
    }
}
