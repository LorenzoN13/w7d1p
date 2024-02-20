package it.epicode.w7d1p.request;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class DipendentePatchRequest {
    @Pattern(regexp = ".*[^ ].*",message = "Nome non valido!")
    @Size(min=3,max=15,message = "Deve avere un numero di caratteri compreso tra 3 e 15")
    private String nome;

    @Pattern(regexp = ".*[^ ].*",message = "Nome non valido!")
    @Size(min=3,max=15,message = "Deve avere un numero di caratteri compreso tra 3 e 15")
    private String cognome;

    @Pattern(regexp = ".*[^ ].*",message = "Nome non valido!")
    @Size(min=3,max=15,message = "Deve avere un numero di caratteri compreso tra 3 e 15")
    private String username;

    @Pattern(regexp = ".*[^ ].*",message = "Nome non valido!")
    @Email(message = "Deve essere un'email")
    private String email;
}
