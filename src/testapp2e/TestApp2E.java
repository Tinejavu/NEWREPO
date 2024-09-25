
package testapp2e;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class TestApp2E {

   //Connection Method to SQLITE
    public static Connection connectDB() {
            Connection con = null;
            try {
                Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver
                con = DriverManager.getConnection("jdbc:sqlite:jstn.db"); // Establish connection
                System.out.println("Connection Successful");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Connection Failed: " + e);
            }
            return con;
        }

    public static void main(String[] args) {
        connectDB();
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.next();
        System.out.print("Enter Status: ");
        String status = sc.next();
        
        String sql = "INSERT INTO Students (s_fname, s_lname, s_email, s_status) VALUES (?, ?, ?, ?)";  
        
        try{
            Connection con = connectDB();
            PreparedStatement state = con.prepareStatement(sql);
            
            state.setString(1, fname);
            state.setString(2, lname);
            state.setString(3, email);
            state.setString(4, status);
            state.executeUpdate();
            
            System.out.println("Insertion Successful.");
        }catch(SQLException e){
            System.out.println("Connection Error: "+e);
        }
    }
}