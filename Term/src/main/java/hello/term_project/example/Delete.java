package hello.term_project.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Delete {

    public static void delete(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.56.101:4567/db_term_project",
                    "jooyehoon", "ckgusdk1234");
            Statement stmt = con.createStatement();
            int rowAffected = stmt.executeUpdate("delete from club where name = 'sammaru'");

            if(rowAffected > 0){

                System.out.println("정상적으로 삭제되었습니다.");
            } else{

                System.out.println("삭제된 데이터가 없습니다.");
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
