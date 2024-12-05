package hello.term_project.club;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clubs")
public class ClubController {

    private final ClubRepository clubRepository;

    @GetMapping
    public String getAllClubs(Model model) {
        List<Club> clubs = clubRepository.getAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs/clubs";
    }

    @GetMapping("/update/{name}")
    public String updateClubForm(@PathVariable String name, Model model) {
        Club club = clubRepository.getClub(name).orElseThrow(() -> new IllegalArgumentException("Invalid Club name: " + name));
        model.addAttribute("club", club);
        return "clubs/updateForm";
    }

    @GetMapping("/update")
    public String newClubForm(Model model) {
        model.addAttribute("club", new Club());
        return "clubs/updateForm";
    }

    @PostMapping("/edit")
    public String editClub(@ModelAttribute Club club, @RequestParam String oldName) {
        clubRepository.updateClub(club, oldName);
        return "redirect:/clubs";
    }

    @PostMapping("/insert")
    public String insertClub(@ModelAttribute Club club) {
        clubRepository.insertClub(club);
        return "redirect:/clubs";
    }

    @PostMapping("/delete/{name}")
    public String deleteClub(@PathVariable String name) {
        clubRepository.deleteClub(name);
        return "redirect:/clubs";
    }
}
