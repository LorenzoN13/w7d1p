package it.epicode.w7d1p.controller;

import com.example.progetto.entities.Utente;
import com.example.progetto.exceptions.BadRequestException;
import com.example.progetto.exceptions.NotFoundException;
import com.example.progetto.requests.LoginRequest;
import com.example.progetto.requests.UtenteRequest;
import com.example.progetto.security.JwtTools;
import com.example.progetto.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtTools jwtTools;

    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw  new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.save(utenteRequest);

    }
    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw  new BadRequestException(bindingResult.getAllErrors().toString());
        }

        Utente utente = utenteService.getUtenteByUsername(loginRequest.getUsername());

        return jwtTools.createToken(utente);
    }
}
