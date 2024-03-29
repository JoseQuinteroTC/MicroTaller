package com.TallerApi.demo.models;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.TallerApi.demo.models.enums.MetodoDePago;
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

public class Pagos {

    @OneToOne(mappedBy = "pagos")
    private Pedidos pedidos;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalPago;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaPago;

    @Enumerated(EnumType.STRING)
    private MetodoDePago metodoPago;
}
