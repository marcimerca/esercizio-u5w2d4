package esercizi.eserciziou5w2d2.services;

import com.cloudinary.Cloudinary;
import esercizi.eserciziou5w2d2.dto.AutoreDto;
import esercizi.eserciziou5w2d2.exception.AutoreException;
import esercizi.eserciziou5w2d2.models.Autore;
import esercizi.eserciziou5w2d2.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;





    public String saveAutore(AutoreDto autoreDto) {
        Autore autore = new Autore();
        autore.setNome(autoreDto.getNome());
        autore.setCognome(autoreDto.getCognome());
        autore.setEmail(autoreDto.getEmail());
        autore.setDataDiNascita(autoreDto.getDataDiNascita());
        autore.setAvatar("https://ui-avatars.com/api/?name="+ autoreDto.getNome() +autoreDto.getCognome());
        autoreRepository.save(autore);
        sendMail(autoreDto.getEmail());
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

    //metodo per foto

    public String patchFotoAutore(int id, MultipartFile foto) throws AutoreException, IOException {
        Optional<Autore> autoreOptional = getAutoreById(id);
        if(autoreOptional.isPresent()){
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");
            Autore autore = autoreOptional.get();
            autore.setFoto(url);
            autoreRepository.save(autore);
            return "Foto aggiunta con successo";
        } else {
            throw new AutoreException("L' autore non è presente");
        }

    }

    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }


}
