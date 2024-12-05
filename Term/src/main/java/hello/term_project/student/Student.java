package hello.term_project.student;

import hello.term_project.club.Club;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Data
public class Student {

    @Id
    private String student_id;
    private String name;
    private String major;
    private int year;
    private String club;
}
