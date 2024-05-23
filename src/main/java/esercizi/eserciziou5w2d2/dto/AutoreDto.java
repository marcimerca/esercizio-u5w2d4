package esercizi.eserciziou5w2d2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoreDto {
    @NotNull
    @NotBlank
    @Size(max = 20)
    private String nome;
    @NotNull
    @NotBlank
    @Size(max = 30)
    private String cognome;

    @Email
    private String email;
    private LocalDate dataDiNascita;
}


