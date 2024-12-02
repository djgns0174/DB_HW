package hello.term_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Test {

    public static void main(String[] args){

        test();

    }

    public static void test(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.56.101:4567/db_term_project",
                    "jooyehoon", "ckgusdk1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            while(rs.next())
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+
                        "  "+rs.getString(3) + " " + rs.getInt(4));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

}
