import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StartProgram {
    public static void main(String[] args) throws ParseException {
        Type targetClassType = new TypeToken<Ticket>() { }.getType();
        Ticket ticket = new Gson().fromJson(utils.readFile("tickets.json"), targetClassType);
        ArrayList<TicketValue> ticketValues = new ArrayList<>(ticket.tickets);

        ArrayList<Long> vlTtel = convertDate(ticketValues);

        Double averTime = vlTtel.stream()
                .mapToDouble(n->n)
                .average()
                .orElse(0.0);

        Date average = new SimpleDateFormat("mm").parse(String.valueOf(averTime));
        SimpleDateFormat hours = new SimpleDateFormat("HH");
        SimpleDateFormat minutes = new SimpleDateFormat("mm");

        System.out.println("Среднее время полета между городами Владивосток и Тель-Авив: "
                        + hours.format(average) + " часов "
                        + minutes.format(average) + " минуты");

        System.out.println("90-й процентиль времени полета между городами Владивосток и Тель-Авив: "
                        + calculatePercentile(vlTtel, 90));
    }

    public static long calculatePercentile(List<Long> list, double percentile) {
        Collections.sort(list);
        return list.get((int) Math.round(percentile/100 * (list.size() - 1)));
    }

    public static ArrayList<Long> convertDate(ArrayList<TicketValue> ticketValues) throws ParseException {

        ArrayList<Long> vlTtel = new ArrayList<>();

        for(TicketValue value: ticketValues) {
            Date departure = new SimpleDateFormat("dd.mm.yy HH:mm").
                    parse(value.departure_date + " " + value.departure_time);
            Date arrival = new SimpleDateFormat("dd.mm.yy HH:mm").
                    parse(value.departure_date + " " + value.arrival_time);
            vlTtel.add(TimeUnit.MINUTES.convert(arrival.getTime() - departure.getTime(), TimeUnit.MILLISECONDS));
        }

        return vlTtel;
    }

}
