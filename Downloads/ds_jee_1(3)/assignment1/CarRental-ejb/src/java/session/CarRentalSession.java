package session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import rental.CarRentalCompany;
import rental.Quote;
import rental.RentalStore;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

@Stateful
public class CarRentalSession implements CarRentalSessionRemote {
    private List<Quote> quotes = new ArrayList();
    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }
    public void createQuote(ReservationConstraints constraint, String client){
      for (String s: getAllRentalCompanies()){
           try{
               CarRentalCompany crc = RentalStore.getRental(s);
               Quote quote = crc.createQuote(constraint, client);
               quotes.add(quote);
               break;
           }
           catch (ReservationException e){}
           
      }
    }
    public List<Quote> getCurrentQuotes(){
        return quotes;
    }
    public void confirmQuotes(List<Quote> quotes) throws ReservationException{
        List<Reservation> res = new ArrayList();
        for (Quote q: quotes){
            try{
                CarRentalCompany crc = RentalStore.getRental(q.getRentalCompany());
                Reservation reservation = crc.confirmQuote(q);
                res.add(reservation);
            }
            catch(ReservationException e){
                for(Reservation r : res ){
                    CarRentalCompany crc = RentalStore.getRental(r.getRentalCompany());
                    crc.cancelReservation(r);
                }
                
                throw new ReservationException("All reservations cancelled");
            }
    }
    
    }
}
