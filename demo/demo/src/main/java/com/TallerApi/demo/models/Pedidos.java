package com.TallerApi.demo.models;

import java.time.LocalDate;
import java.util.List;

import com.TallerApi.demo.models.enums.EstadoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Pedidos {
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedidos")
    private List<ItemsPedidos> itemsPedidos;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPago", referencedColumnName = "id")
    private Pagos pagos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDetallesEnvios", referencedColumnName = "id")
    private DetallesEnvios detallesEnvios;



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private Long clienteId;
    //private LocalDate fechaPedido;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;
}
