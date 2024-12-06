package hello.term_project.professor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorRepository professorRepository;

    @GetMapping
    public String getAllProfessors(Model model) {
        List<Professor> professors = professorRepository.getAllprofessor();
        model.addAttribute("professors", professors);
        return "professors/professors";
    }

    @GetMapping("/update/{id}")
    public String editProfessorForm(@PathVariable String id, Model model) {
        Professor professor = professorRepository.getprofessor(id).orElseThrow(() -> new IllegalArgumentException("Invalid professor ID: " + id));
        model.addAttribute("professor", professor);
        return "professors/updateForm";
    }

    @GetMapping("/update")
    public String insertProfessorForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "professors/updateForm";
    }

    @PostMapping("/edit")
    public String editProfessor(@ModelAttribute Professor professor) {
        professorRepository.updateProfessor(professor);
        return "redirect:/professors";
    }


    @PostMapping("/insert")
    public String insertProfessor(@ModelAttribute Professor professor) {
        professorRepository.insertprofessor(professor);
        return "redirect:/professors";
    }

    @PostMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable String id) {
        professorRepository.deleteProfessor(id);
        return "redirect:/professors";
    }
}