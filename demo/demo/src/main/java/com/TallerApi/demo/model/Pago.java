package com.TallerApi.demo.model;


import java.time.LocalDateTime;

import com.TallerApi.demo.model.enums.MetodoDePago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Pago {

    @OneToOne(mappedBy = "pago")
    private Pedido pedido;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalPago;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaPago;

    @Enumerated(EnumType.STRING)
    private MetodoDePago metodoPago;
}
