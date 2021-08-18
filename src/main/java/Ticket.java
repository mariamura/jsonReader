import java.util.Date;
import java.util.List;

public class Ticket {
    public List<TicketValue> tickets;

    public List<TicketValue> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "tickets=" + tickets +
                '}';
    }
}
