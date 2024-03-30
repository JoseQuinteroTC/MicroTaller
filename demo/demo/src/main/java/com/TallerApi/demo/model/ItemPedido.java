package com.TallerApi.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itemPedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ItemPedido {

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne @JoinColumn(name = "idProducto")
    private Product product;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private Long productoId;
    private Integer cantidad;
    private Integer precioUnitario;

}
