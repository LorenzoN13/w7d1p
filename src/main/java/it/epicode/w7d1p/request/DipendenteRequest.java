package it.epicode.w7d1p.request;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class DipendenteRequest {
    @NotBlank(message = "Non può essere vuoto")
    @Size(min=3,max=15,message = "Deve avere un numero di caratteri compreso tra 3 e 15")
    private String nome;

    @NotBlank(message = "Non può essere vuoto")
    @Size(min=3,max=15,message = "Deve avere un numero di caratteri compreso tra 3 e 15")
    private String cognome;

    @NotBlank(message = "Non può essere vuoto")
    @Size(min=3,max=15,message = "Deve avere un numero di caratteri compreso tra 3 e 15")
    private String username;

    @NotBlank(message = "Non può essere vuoto")
    @Email(message = "Deve essere un'email")
    private String email;
}
