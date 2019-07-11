package service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBMysql {


//    @Configuration
//    @PropertySource("file:connection.properties")
    public Statement  connect(){
        Connection connection = null;
        Statement stmt = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "user", "password");

            stmt = connection.createStatement();

//            FileInputStream fis=new FileInputStream("connection.properties");
//
//            Properties p=new Properties ();
//            p.load (fis);
//            String dname= (String) p.get ("Dname");
//            String url= (String) p.get ("URL");
//            String username= (String) p.get ("Uname");
//            String password= (String) p.get ("password");
//            Class.forName(dname);
//            Connection con = DriverManager.getConnection(
//                    url, username, password);
//             stmt = con.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }


    public void add(String ID,String title,String price){

        Statement stmt = connect();
        try
        {
            stmt.execute("INSERT INTO items (ID,Title,Price) "
                    + "VALUES (('"+ID+"'),('"+title+"'),('"+price+"'))");
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(String ID){

        Statement stmt = connect();
        try
        {
            stmt.execute("DELETE FROM items WHERE ID=('"+ID+"')");
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(String ID,String title,String price){

        Statement stmt = connect();
        try
        {
            stmt.execute("UPDATE items SET Title = ('"+title+"'), Price=('"+price+"') WHERE ID = ('"+ID+"') ");
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void display(){
        Statement stmt = connect();
        try
        {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM items ");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {

                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));

                }
                System.out.println("");
            }
            System.out.println();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }






}
