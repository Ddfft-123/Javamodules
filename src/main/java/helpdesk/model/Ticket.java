package helpdesk.model;

import jakarta.persistence.*;
        import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.NEW;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 100)
    private String customerName;

    // Пустой конструктор (нужен для JPA)
    public Ticket() {}

    // Конструктор с полями
    public Ticket(String title, String description, String customerName) {
        this.title = title;
        this.description = description;
        this.customerName = customerName;
        this.status = TicketStatus.NEW;
        this.createdAt = LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TicketStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getCustomerName() { return customerName; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(TicketStatus status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}