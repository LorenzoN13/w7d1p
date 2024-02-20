package it.epicode.w7d1p.service;
import it.epicode.w7d1p.exceptions.NotFoundException;
import it.epicode.w7d1p.model.Utente;
import it.epicode.w7d1p.request.UtenteRequest;
import it.epicode.w7d1p.response.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente save(UtenteRequest utenteRequest){
        Utente utente = new Utente();
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setUsername(utenteRequest.getUsername());
        utente.setPassword(utenteRequest.getPassword());

        return utenteRepository.save(utente);
    }

    public Utente getUtenteByUsername(String username) throws NotFoundException {
        return utenteRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Username non trovato"));
    }

}
