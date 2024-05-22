package esercizi.eserciziou5w2d2.models;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ErrorModel {
    private String message;
    private LocalDateTime dataErrore;
}
