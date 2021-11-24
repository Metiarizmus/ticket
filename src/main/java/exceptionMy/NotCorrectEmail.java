package exceptionMy;


public class NotCorrectEmail extends IllegalArgumentException {
    public NotCorrectEmail(String s) {
        super(s);
    }

}
