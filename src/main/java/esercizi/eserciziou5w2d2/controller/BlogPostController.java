package esercizi.eserciziou5w2d2.controller;

import esercizi.eserciziou5w2d2.dto.BlogPostDto;
import esercizi.eserciziou5w2d2.exception.AutoreException;
import esercizi.eserciziou5w2d2.exception.BlogPostException;
import esercizi.eserciziou5w2d2.exception.MyBadRequestException;
import esercizi.eserciziou5w2d2.models.BlogPost;
import esercizi.eserciziou5w2d2.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BlogPostController {

    @Autowired
    public BlogPostService blogPostService;

    @PostMapping("/blogPosts")
    public String saveBlogPost(@RequestBody @Validated BlogPostDto blogPostDto, BindingResult bindingResult) throws AutoreException {
        if (bindingResult.hasErrors()) {
            throw new MyBadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).reduce("", (s, s2) -> s + s2));
        }
        return blogPostService.salvaPost(blogPostDto);
    }

    @GetMapping("/blogPosts")
    public List<BlogPost> getallPosts() {
        return blogPostService.getAllPost();
    }

    @GetMapping("blogPosts/{id}")
    public BlogPost getPostById(@PathVariable int id) throws BlogPostException {
        Optional<BlogPost> blogPostOpt = blogPostService.getPostById(id);

        if (blogPostOpt.isPresent()) {
            return blogPostOpt.get();
        } else {
            throw new BlogPostException("blog post non trovato");
        }
    }


    @PutMapping("/blogPosts/{id}")
    public BlogPost updateBlogPost(@PathVariable int id, @RequestBody @Validated BlogPostDto blogPostDto, BindingResult bindingResult) throws BlogPostException, AutoreException {
        if (bindingResult.hasErrors()) {
            throw new MyBadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).reduce("", (s, s2) -> s + s2));
        }
        return blogPostService.updatePost(id, blogPostDto);
    }

    @DeleteMapping("/blogPosts/{id}")
    public String deletePost(@PathVariable int id) throws BlogPostException {
        return blogPostService.deletePost(id);
    }


}
