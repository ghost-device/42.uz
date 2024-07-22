package uz.web.domain.exceptions;

public class EmailNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Email not found!";
    }
}
