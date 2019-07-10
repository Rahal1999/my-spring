package service;

import java.sql.*;

public class DBMysql {
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




    public Statement  connect(){
        Connection connection = null;
        Statement stmt = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "user", "password");

            stmt = connection.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }


}
