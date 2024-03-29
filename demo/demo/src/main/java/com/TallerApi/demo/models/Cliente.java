package com.TallerApi.demo.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Cliente {

    @OneToMany(mappedBy = "cliente")
    private List<Pedidos> pedidos;


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String direccion;

}
