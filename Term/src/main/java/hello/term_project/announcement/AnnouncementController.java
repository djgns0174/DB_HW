package hello.term_project.announcement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementRepository announcementRepository;

    @GetMapping
    public String getAllAnnouncements(Model model) {
        List<Announcement> announcements = announcementRepository.getAllAnnouncements();
        model.addAttribute("announcements", announcements);
        return "announcements/announcements";
    }

    @GetMapping("/update/{id}")
    public String editAnnouncementForm(@PathVariable Integer id, Model model) {
        Announcement announcement = announcementRepository.getAnnouncement(id).orElseThrow(() -> new IllegalArgumentException("Invalid announcement ID: " + id));
        model.addAttribute("announcement", announcement);
        return "announcements/updateForm";
    }

    @GetMapping("/update")
    public String insertAnnouncementForm(Model model) {
        model.addAttribute("announcement", new Announcement());
        return "announcements/updateForm";
    }

    @PostMapping("/edit")
    public String editAnnouncement(@ModelAttribute Announcement announcement) {
        if (announcement.getWriter() != null && announcement.getWriter().isEmpty()) {
            announcement.setWriter(null);
        }
        announcementRepository.updateAnnouncement(announcement);
        return "redirect:/announcements";
    }

    @PostMapping("/insert")
    public String insertAnnouncement(@ModelAttribute Announcement announcement) {
        if (announcement.getWriter() != null && announcement.getWriter().isEmpty()) {
            announcement.setWriter(null);
        }

        announcementRepository.insertAnnouncement(announcement);
        return "redirect:/announcements";
    }

    @PostMapping("/delete/{id}")
    public String deleteAnnouncement(@PathVariable Integer id) {
        announcementRepository.deleteAnnouncement(id);
        return "redirect:/announcements";
    }
}
