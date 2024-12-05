package hello.term_project.announcement;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Data
public class Announcement {

    @Id
    private Integer id;
    private String title;
    private String content;
    private String announce_date;
    private String writer;
}