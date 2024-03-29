package com.TallerApi.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ItemsPedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ItemsPedidos {

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedidos pedidos;

    @ManyToOne @JoinColumn(name = "idProducto")
    private Products products;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private Long productoId;
    private Integer cantidad;
    private Integer precioUnitario;

}
