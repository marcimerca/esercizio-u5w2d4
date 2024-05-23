package esercizi.eserciziou5w2d2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BlogPostDto {
    @NotNull
    private String categoria;
    @NotNull
    private String titolo;
    @NotBlank
    @Size(min = 10,max = 200)
    private String contenuto;
    private int minutiDiLettura;

    private String cover;

    @NotNull
    private int autoreId;
}




