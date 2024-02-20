package it.epicode.w7d1p.controller;

import it.epicode.w7d1p.exceptions.BadRequestException;
import it.epicode.w7d1p.exceptions.NotFoundException;
import it.epicode.w7d1p.request.DispositivoPatchRequest;
import it.epicode.w7d1p.request.DispositivoRequest;
import it.epicode.w7d1p.response.DefaultResponse;
import it.epicode.w7d1p.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/dispositivi")
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;
    @GetMapping
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        return DefaultResponse.noMessage(dispositivoService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.customMessage("Trovato",dispositivoService.findById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DefaultResponse> create(@RequestBody @Validated DispositivoRequest d, BindingResult bR) throws NotFoundException {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Creato",dispositivoService.save(d),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> update(@PathVariable int id,@RequestBody @Validated DispositivoRequest d, BindingResult bR) throws NotFoundException {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Aggiornato",dispositivoService.update(id,d),HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<DefaultResponse> patchUpdate(@PathVariable int id, @RequestBody @Validated DispositivoPatchRequest d, BindingResult bR) throws NotFoundException {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Aggiornato",dispositivoService.patchUpdate(id,d),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable int id) throws NotFoundException {
        dispositivoService.delete(id);
        return DefaultResponse.noObject("Dispositivo con id="+id+" eliminato",HttpStatus.OK);
    }
}
