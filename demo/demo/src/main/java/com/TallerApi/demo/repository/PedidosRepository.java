package com.TallerApi.demo.repository;

import com.TallerApi.demo.models.Pedidos;
import com.TallerApi.demo.models.enums.EstadoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
   // Optional<Pedidos> findBetweenDate(LocalDate initDate, LocalDate endDate);

   // Optional<Pedidos> findByClientAndState(String nombre, EstadoEnvio estadoEnvio);

  //  Optional<Pedidos> findPedidosByCliente(String nombre);

}
