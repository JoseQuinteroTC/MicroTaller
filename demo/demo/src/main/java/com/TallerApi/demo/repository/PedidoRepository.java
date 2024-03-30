package com.TallerApi.demo.repository;

import com.TallerApi.demo.model.Pedido;
import com.TallerApi.demo.model.enums.EstadoEnvio;
import com.TallerApi.demo.model.enums.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
   List<Pedido> findByFechaPedidoBetween(LocalDateTime initDate, LocalDateTime endDate);

   List<Pedido> findByClienteIdAndEstadoPedido(Long idCliente, EstadoPedido estadoPedido);

    @Query("SELECT DISTINCT p " +
            "FROM Pedido p " +
            "JOIN FETCH p.itemsPedido " +
            "WHERE p.cliente.id = :idCliente")
    List<Pedido> findPedidoConProductosByCliente(Long idCliente);

}
