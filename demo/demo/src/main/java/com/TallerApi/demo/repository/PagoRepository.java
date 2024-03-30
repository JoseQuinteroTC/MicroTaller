package com.TallerApi.demo.repository;

import com.TallerApi.demo.model.Pago;
import com.TallerApi.demo.model.enums.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByFechaPagoBetween(LocalDateTime initDate, LocalDateTime endDate);

    List<Pago> findByIdAndMetodoPago(Long idOrder, MetodoDePago metodoDePago);
}
