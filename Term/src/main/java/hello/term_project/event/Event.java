package hello.term_project.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Data
public class Event {

    @Id
    private Integer id;
    private String description;
    private String event_date;
    private String name;
    private int participant_number;
    private String location;
    private String host_club_name;
}