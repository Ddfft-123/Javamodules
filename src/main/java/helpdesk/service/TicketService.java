package helpdesk.service;

import helpdesk.dto.TicketCreateDto;
import helpdesk.model.Ticket;
import java.util.List;

public interface TicketService {
    Ticket createTicket(TicketCreateDto dto);
    List<Ticket> getAllTickets();
    Ticket getTicketById(Long id);
}