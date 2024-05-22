package esercizi.eserciziou5w2d2.services;

import esercizi.eserciziou5w2d2.dto.BlogPostDto;
import esercizi.eserciziou5w2d2.exception.AutoreException;
import esercizi.eserciziou5w2d2.exception.BlogPostException;
import esercizi.eserciziou5w2d2.models.Autore;
import esercizi.eserciziou5w2d2.models.BlogPost;
import esercizi.eserciziou5w2d2.repository.AutoreRepository;
import esercizi.eserciziou5w2d2.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreRepository autoreRepository;

    public String salvaPost(BlogPostDto blogPostDto) throws AutoreException {
        Optional<Autore> autoreOpt = autoreRepository.findById(blogPostDto.getAutoreId());
        if (autoreOpt.isPresent()) {
            BlogPost blogPost = new BlogPost();
            blogPost.setTitolo(blogPostDto.getTitolo());
            blogPost.setCategoria(blogPostDto.getCategoria());
            blogPost.setMinutiDiLettura(blogPostDto.getMinutiDiLettura());
            blogPost.setContenuto(blogPostDto.getContenuto());
            blogPost.setAutore(autoreOpt.get());
            blogPost.setCover(blogPostDto.getCover());

            blogPostRepository.save(blogPost);
            return "Blog post salvato con successo";

        } else {
            throw new AutoreException("L'autore con id indicato non è stato trovato");
        }
    }

    public List<BlogPost> getAllPost() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> getPostById(int id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost updatePost(int id, BlogPostDto blogPostDto) throws BlogPostException, AutoreException {
        Optional<BlogPost> blogPostOpt = getPostById(id);
        if (blogPostOpt.isPresent()) {
            BlogPost blogPost = blogPostOpt.get();
            blogPost.setTitolo(blogPostDto.getTitolo());
            blogPost.setCategoria(blogPostDto.getCategoria());
            blogPost.setMinutiDiLettura(blogPostDto.getMinutiDiLettura());
            blogPost.setContenuto(blogPostDto.getContenuto());

            Optional<Autore> autoreOpt = autoreRepository.findById(blogPostDto.getAutoreId());

            if (autoreOpt.isPresent()) {
                Autore autore = autoreOpt.get();
                blogPost.setAutore(autore);
                blogPostRepository.save(blogPost);
                return blogPost;
            } else {
                throw new AutoreException("L'autore con id indicato non è stato trovato");
            }
        } else {
            throw new BlogPostException("Post non trovato");
        }
    }

    public String deletePost(int id) throws BlogPostException{
        Optional<BlogPost> blogPostOpt = getPostById(id);
        if(blogPostOpt.isPresent()){
            blogPostRepository.delete(blogPostOpt.get());
            return "Post eliminato con successo";
        } else {
            throw new BlogPostException("Post da eliminare non trovato");
        }
    }

}
