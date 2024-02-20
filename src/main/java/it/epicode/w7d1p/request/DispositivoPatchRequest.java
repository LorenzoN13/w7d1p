package it.epicode.w7d1p.request;

import it.epicode.w7d1p.enums.Status;
import it.epicode.w7d1p.enums.Tipo;
import lombok.Data;


@Data
public class DispositivoPatchRequest {
    private Status status;
    private Tipo tipo;
    private Integer dipendenteId;
}
