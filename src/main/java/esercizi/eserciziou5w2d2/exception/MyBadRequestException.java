package esercizi.eserciziou5w2d2.exception;

public class MyBadRequestException extends RuntimeException{
    public MyBadRequestException(String message) {
        super(message);
    }
}
