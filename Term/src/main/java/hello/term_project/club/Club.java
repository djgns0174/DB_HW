package hello.term_project.club;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Data
public class Club {

    @Id
    private String name;
    private String description;
    private String create_date;
    private int member_number;
    private String advisor_id;
    private String president_id;
}