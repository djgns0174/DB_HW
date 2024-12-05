package hello.term_project.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentRepository.getAllStudent();
        model.addAttribute("students", students);
        return "students/students";
    }

    @GetMapping("/update/{id}")
    public String editStudentForm(@PathVariable String id, Model model) {
        Student student = studentRepository.getStudent(id).orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "students/updateForm";
    }

    @GetMapping("/update")
    public String insertStudentForm(Model model) {
        Student student = new Student();
        student.setClub(null);
        model.addAttribute("student", student);
        return "students/updateForm";
    }

    @PostMapping("/edit")
    public String editStudent(@ModelAttribute Student student) {
        studentRepository.updateStudent(student);
        return "redirect:/students";
    }


    @PostMapping("/insert")
    public String insertStudent(@ModelAttribute Student student) {
        System.out.println(student.getClub());  //test
        studentRepository.insertStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentRepository.deleteStudent(id);
        return "redirect:/students";
    }
}
