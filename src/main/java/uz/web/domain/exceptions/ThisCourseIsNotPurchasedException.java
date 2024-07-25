package uz.web.domain.exceptions;

public class ThisCourseIsNotPurchasedException extends RuntimeException {
    public ThisCourseIsNotPurchasedException(String message) {
        super(message);
    }
}
