package hello.term_project.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;

    @GetMapping
    public String getAllEvents(Model model) {
        List<Event> events = eventRepository.getAllEvent();
        model.addAttribute("events", events);
        return "events/events";
    }

    @GetMapping("/update/{id}")
    public String editEventForm(@PathVariable Integer id, Model model) {
        Event event = eventRepository.getEvent(id).orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + id));
        model.addAttribute("event", event);
        return "events/updateForm";
    }

    @GetMapping("/update")
    public String insertEventForm(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "events/updateForm";
    }

    @PostMapping("/edit")
    public String editEvent(@ModelAttribute Event event) {
        eventRepository.updateEvent(event);
        return "redirect:/events";
    }


    @PostMapping("/insert")
    public String insertEvent(@ModelAttribute Event event) {
        eventRepository.insertEvent(event);
        return "redirect:/events";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        eventRepository.deleteEvent(id);
        return "redirect:/events";
    }
}
