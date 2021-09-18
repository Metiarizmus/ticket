package myException;

public class EmailYetExist extends IllegalArgumentException{
    public EmailYetExist(String s) {
        super(s);
    }
}
