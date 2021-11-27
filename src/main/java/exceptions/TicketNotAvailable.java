package exceptions;

public class TicketNotAvailable extends IllegalArgumentException{
    public TicketNotAvailable(String s) {
        super(s);
    }

}
