package it.epicode.w7d1p.model;

import it.epicode.w7d1p.enums.Status;
import it.epicode.w7d1p.enums.Tipo;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="dispositivi")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenza_dispositivo")
    @SequenceGenerator(name="sequenza_dispositivo",initialValue = 1,allocationSize = 1)
    private int id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @ManyToOne
    @JoinColumn(name = "dipendente_fk")
    private Dipendente dipendente;
}
