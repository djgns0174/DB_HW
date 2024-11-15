package hello.hw4_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Test {

    public static void main(String[] args){

        selectBook();

        insertCustomer();
        selectCustomer();

        deleteCustomer();
        selectCustomer();
    }

    public static void selectBook(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.56.101:4567/madang",
                    "jooyehoon", "ckgusdk1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Book");

            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+
                        "  "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public static void selectCustomer(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.56.101:4567/madang",
                    "jooyehoon", "ckgusdk1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");

            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+
                        "  "+rs.getString(3)+" "+rs.getString(4));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }


    public static void insertCustomer(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.56.101:4567/madang",
                    "jooyehoon", "ckgusdk1234");
            Statement stmt=con.createStatement();
            String sql = "insert into Customer (custid, name, address, phone) values (6, '주예훈', '대한민국 충청북도 청주', 12345678)";
            stmt.executeUpdate(sql);
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void deleteCustomer(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.56.101:4567/madang",
                    "jooyehoon", "ckgusdk1234");
            Statement stmt=con.createStatement();
            String sql = "delete from Customer where custid = 6";
            stmt.executeUpdate(sql);
            con.close();
        }catch (Exception e){System.out.println(e);}
    }
}
