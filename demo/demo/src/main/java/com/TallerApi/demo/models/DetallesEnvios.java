package com.TallerApi.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DetallesEnvios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class DetallesEnvios {

    @OneToOne(mappedBy = "detallesEnvios")
    private Pedidos pedidos;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private String direccion;
    private String transportadora;
    private Integer numeroGuia;
}
