package esercizi.eserciziou5w2d2.repository;

import esercizi.eserciziou5w2d2.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
}
