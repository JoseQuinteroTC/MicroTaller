package com.TallerApi.demo.repository;

import com.TallerApi.demo.models.ItemsPedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemsPedidosRepository extends JpaRepository<ItemsPedidos, Long> {
   // Optional<ItemsPedidos> findByIdPedido(Long idPedido);

    //Optional<ItemsPedidos> findByProducto(String producto);

    //Optional<ItemsPedidos> sumSalesByProduct(String producto);
}
