package it.epicode.w7d1p.service;
import it.epicode.w7d1p.enums.Status;
import it.epicode.w7d1p.exceptions.BadRequestException;
import it.epicode.w7d1p.exceptions.NotFoundException;
import it.epicode.w7d1p.model.Dipendente;
import it.epicode.w7d1p.model.Dispositivo;
import it.epicode.w7d1p.repository.DispositivoRepository;
import it.epicode.w7d1p.request.DispositivoPatchRequest;
import it.epicode.w7d1p.request.DispositivoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private DipendenteService dipendenteService;
    public Dispositivo save(DispositivoRequest d) throws NotFoundException {
        Dispositivo dispositivo=new Dispositivo();
        return dispositivoRepository.save(put(d,dispositivo));
    }
    public Dispositivo update(int id, DispositivoRequest d) throws NotFoundException {
        Dispositivo dispositivo=findById(id);
        return dispositivoRepository.save(put(d,dispositivo));
    }
    public Dispositivo patchUpdate(int id, DispositivoPatchRequest d) throws NotFoundException {
        Dispositivo dispositivo=findById(id);

        if(d.getTipo()!=null)dispositivo.setTipo(d.getTipo());
        if(d.getStatus()!=null)dispositivo.setStatus(d.getStatus());
        if(d.getStatus()==Status.DISPONIBILE) dispositivo.setDipendente(null);

        if(d.getDipendenteId()!=null){
            if(dispositivo.getStatus()== Status.MANUTENZIONE) throw new BadRequestException("Il dispositivo è in manutenzione");
            Dipendente dipendente=dipendenteService.findById(d.getDipendenteId());
            dispositivo.setStatus(Status.ASSEGNATO);
            dispositivo.setDipendente(dipendente);
        }
        return dispositivoRepository.save(dispositivo);
    }
    public Dispositivo findById(int id) throws NotFoundException {
        Optional<Dispositivo> dispositivo=dispositivoRepository.findById(id);
        if(dispositivo.isEmpty()) throw new NotFoundException("Non esiste un dispositivo con id="+id);
        return dispositivo.get();
    }
    public Page<Dispositivo> findAll(Pageable pageable){
        return dispositivoRepository.findAll(pageable);
    }
    private Dispositivo put(DispositivoRequest d, Dispositivo dispositivo) throws NotFoundException {
        dispositivo.setTipo(d.getTipo());
        dispositivo.setStatus(d.getStatus());
        if(d.getStatus()==Status.DISPONIBILE) dispositivo.setDipendente(null);
        if (d.getDipendenteId()!=null){
            if(dispositivo.getStatus()==Status.MANUTENZIONE) throw new BadRequestException("Il dispositivo è in manutenzione");
            Dipendente dipendente=dipendenteService.findById(d.getDipendenteId());
            dispositivo.setStatus(Status.ASSEGNATO);
            dispositivo.setDipendente(dipendente);
        }
        return dispositivo;
    }
    public void delete(int id) throws NotFoundException {
        Dispositivo dispositivo=findById(id);
        dispositivoRepository.delete(dispositivo);
    }
}
