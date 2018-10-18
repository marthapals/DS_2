package session;

import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import rental.Quote;
import rental.ReservationConstraints;
import rental.ReservationException;

@Remote
public interface CarRentalSessionRemote {

    Set<String> getAllRentalCompanies();
    public void createQuote(ReservationConstraints constraint, String client) throws ReservationException;
    public List<Quote> getCurrentQuotes() throws ReservationException;
    public void confirmQuotes(List<Quote> quotes) throws ReservationException;
    
}
