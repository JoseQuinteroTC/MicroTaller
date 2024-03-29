package com.TallerApi.demo.repository;

import com.TallerApi.demo.models.Pagos;
import com.TallerApi.demo.models.enums.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PagosRepository extends JpaRepository<Pagos, Long> {
    List<Pagos> findByFechaPagoBetween(LocalDateTime initDate, LocalDateTime endDate);

    List<Pagos> findByIdAndMetodoPago(Long idOrder, MetodoDePago metodoDePago);
}
