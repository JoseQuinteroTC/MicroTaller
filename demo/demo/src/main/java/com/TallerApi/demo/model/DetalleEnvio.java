package com.TallerApi.demo.model;

import com.TallerApi.demo.model.enums.EstadoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalleEnvios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class DetalleEnvio {

    @OneToOne(mappedBy = "detalleEnvio")
    private Pedido pedido;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;

    private String transportadora;

    private EstadoEnvio estadoEnvio;

    @Column(unique = true)
    private String numeroGuia;

}
