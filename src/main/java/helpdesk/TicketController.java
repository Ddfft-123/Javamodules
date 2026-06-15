package helpdesk;

import helpdesk.dto.TicketCreateDto;
import helpdesk.model.Ticket;
import helpdesk.service.TicketService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

//    @GetMapping
//    public String listTickets(Model model) {
//        model.addAttribute("tickets", ticketService.getAllTickets());
//        return "tickets";
//    }


    @GetMapping("/new")
    public String showCreateForm(@Nonnull Model model) {
        model.addAttribute("ticket", new TicketCreateDto());
        return "ticket-form";
    }

    @PostMapping
    public String createTicket(@Valid @ModelAttribute("ticket") TicketCreateDto dto,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "ticket-form";
        }
        Ticket saved = ticketService.createTicket(dto);
        return "redirect:/tickets/" + saved.getId() + "/success";
    }

    @GetMapping("/{id}/success")
    public String showSuccess(@PathVariable Long id, Model model) {
        model.addAttribute("ticket", ticketService.getTicketById(id));
        return "ticket-success";
    }
}