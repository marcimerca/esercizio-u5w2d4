package esercizi.eserciziou5w2d2.exception;

import esercizi.eserciziou5w2d2.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CentralizedException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BlogPostException.class)
    public ResponseEntity<Object> blogPostNonTrovatoHandler(BlogPostException e){
        ErrorModel errore = new ErrorModel();
        errore.setMessage(e.getMessage());
        errore.setDataErrore(LocalDateTime.now());

        return new ResponseEntity<>(errore, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AutoreException.class)
    public ResponseEntity<Object> AutoreNonTrovatoHandler(AutoreException e){
      ErrorModel error = new ErrorModel();
      error.setMessage(e.getMessage());
      error.setDataErrore(LocalDateTime.now());

      return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
