package it.epicode.w7d1p.request;
import it.epicode.w7d1p.enums.Status;
import it.epicode.w7d1p.enums.Tipo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class DispositivoRequest {
    @NotNull(message = "Non può essere null")
    private Status status;
    @NotNull(message = "Non può essere null")
    private Tipo tipo;
    private Integer dipendenteId;
}
