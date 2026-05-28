package helpdesk;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Help Desk");
        model.addAttribute("welcomeMessage", "Добро пожаловать в службу поддержки!");
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "О нас");
        model.addAttribute("description", "Help Desk — учебный проект службы поддержки");
        return "about";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("pageTitle", "Контакты");
        model.addAttribute("email", "support@helpdesk.local");
        model.addAttribute("phone", "+7 (999) 999-99-99");
        return "contacts";
    }
}