package esercizi.eserciziou5w2d2.controller;

import esercizi.eserciziou5w2d2.dto.AutoreDto;
import esercizi.eserciziou5w2d2.exception.AutoreException;
import esercizi.eserciziou5w2d2.exception.BlogPostException;
import esercizi.eserciziou5w2d2.models.Autore;
import esercizi.eserciziou5w2d2.models.BlogPost;
import esercizi.eserciziou5w2d2.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @PostMapping("/authors")
    public String salvaAutore(@RequestBody AutoreDto autore) {
        return autoreService.saveAutore(autore);
    }

   /* @GetMapping("/authors")
    public List<Autore> getAllAutori(){
        return autoreService.getAllAutori();
    }*/

    @GetMapping("/authors")
    public Page<Autore> getAllAutori(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return autoreService.getAllAutori(page, size, sortBy);
    }


    @GetMapping("authors/{id}")
    public Autore getAutoreById(@PathVariable int id) throws AutoreException {
        Optional<Autore> autoreOpt = autoreService.getAutoreById(id);

        if (autoreOpt.isPresent()) {
            return autoreOpt.get();
        } else {
            throw new AutoreException("autore non trovato");
        }
    }

    @PutMapping("/authors/{id}")
    public Autore updateAutore(@PathVariable int id, @RequestBody AutoreDto autore) throws AutoreException {
        return autoreService.updateAutore(id, autore);
    }

    @DeleteMapping("/authors/{id}")
    public String deleteAutore(@PathVariable int id) throws AutoreException {
        return autoreService.deleteAutore(id);
    }


}
