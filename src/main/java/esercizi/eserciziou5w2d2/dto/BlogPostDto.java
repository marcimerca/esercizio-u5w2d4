package esercizi.eserciziou5w2d2.dto;

import lombok.Data;

@Data
public class BlogPostDto {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int minutiDiLettura;

    private String cover;

    private int autoreId;
}




