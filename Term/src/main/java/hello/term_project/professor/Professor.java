package hello.term_project.professor;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Data
public class Professor {
    @Id
    private String professor_id;
    private String name;
    private String major;
}
