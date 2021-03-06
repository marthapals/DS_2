package session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import rental.CarType;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

@Remote
public interface CarRentalSessionRemote{
    public List<CarType> getAvailableCarTypes(Date start, Date end)throws ReservationException;
    Set<String> getAllRentalCompanies() throws ReservationException;
    public void createQuote(ReservationConstraints constraint, String client) throws ReservationException;
    public List<Quote> getCurrentQuotes() throws ReservationException;
    public List<Reservation> confirmQuotes(List<Quote> quotes) throws ReservationException;
    
}
