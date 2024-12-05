package hello.term_project.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Update {

    public static void update(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.56.101:4567/db_term_project",
                    "jooyehoon", "ckgusdk1234");
            Statement stmt = con.createStatement();
            int rowAffected = stmt.executeUpdate("update club set name = 'update_nestNet' where name='nestNet'");

            if(rowAffected > 0){

                System.out.println("정상적으로 수정되었습니다.");
            } else{

                System.out.println("수정된 데이터가 없습니다.");
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
