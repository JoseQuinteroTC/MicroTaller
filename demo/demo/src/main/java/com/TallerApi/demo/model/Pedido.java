package com.TallerApi.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import com.TallerApi.demo.model.enums.EstadoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Pedido {
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemsPedido;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPago", referencedColumnName = "id")
    private Pago pago;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDetallesEnvios", referencedColumnName = "id")
    private DetalleEnvio detalleEnvio;



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaPedido;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;
}
