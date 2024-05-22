package esercizi.eserciziou5w2d2.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue
    private int id;

    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int minutiDiLettura;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

}
