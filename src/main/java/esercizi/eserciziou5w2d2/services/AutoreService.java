package esercizi.eserciziou5w2d2.services;

import esercizi.eserciziou5w2d2.dto.AutoreDto;
import esercizi.eserciziou5w2d2.exception.AutoreException;
import esercizi.eserciziou5w2d2.exception.BlogPostException;
import esercizi.eserciziou5w2d2.models.Autore;
import esercizi.eserciziou5w2d2.models.BlogPost;
import esercizi.eserciziou5w2d2.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    public String saveAutore(AutoreDto autoreDto) {
        Autore autore = new Autore();
        autore.setNome(autoreDto.getNome());
        autore.setCognome(autoreDto.getCognome());
        autore.setEmail(autoreDto.getEmail());
        autore.setDataDiNascita(autoreDto.getDataDiNascita());
        autoreRepository.save(autore);
        return "Autore inserito con successo!";

    }

    /*public List<Autore> getAllAutori() {
        return autoreRepository.findAll();
    }
*/

    public Page<Autore> getAllAutori(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return autoreRepository.findAll(pageable);
    }
    public Optional<Autore> getAutoreById(int id) {
        return autoreRepository.findById(id);
    }

    public Autore updateAutore(int id, AutoreDto autoreDto) throws AutoreException {
        Optional<Autore> autoreOpt = getAutoreById(id);
        if (autoreOpt.isPresent()) {
            Autore autore = autoreOpt.get();
            autore.setNome(autoreDto.getNome());
            autore.setCognome(autoreDto.getCognome());
            autore.setEmail(autoreDto.getEmail());
            autore.setDataDiNascita(autoreDto.getDataDiNascita());
            autoreRepository.save(autore);
            return autore;
        } else {
            throw new AutoreException("L' autore da modificare non è presente");
        }
    }

    public String deleteAutore(int id) throws AutoreException {
        Optional<Autore> autoreOpt = getAutoreById(id);
        if (autoreOpt.isPresent()) {
            autoreRepository.delete(autoreOpt.get());
            return "L'autore è stato eliminato";
        } else {
            throw new AutoreException("L' autore da eliminare non è presente");
        }
    }


}
