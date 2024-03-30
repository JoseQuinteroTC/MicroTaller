package com.TallerApi.demo.repository;

import com.TallerApi.demo.model.DetalleEnvio;
import com.TallerApi.demo.model.enums.EstadoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long> {

    Optional<DetalleEnvio> findByPedidoId(Long pedido_id);
    List<DetalleEnvio> findByEstadoEnvio(EstadoEnvio estadoEnvio);
    List<DetalleEnvio> findByTransportadora(String transportadora);

}
