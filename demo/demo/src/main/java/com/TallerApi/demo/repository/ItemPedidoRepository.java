package com.TallerApi.demo.repository;

import com.TallerApi.demo.model.ItemPedido;
import com.TallerApi.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    Optional<ItemPedido> findByPedidoId(Long idPedido);

    List<ItemPedido> findByProductoId(Long idProduct);

    Optional<ItemPedido> findByProductoIdAndPedidoId(Long idProduct, Long idPedido);

    @Query("SELECT SUM( ip.precioUnitario * ip.cantidad)" +
            " FROM ItemPedido ip" +
            " WHERE ip.product.id = :idProducto")
    Optional<Double> findTotalVentasByProduct(Long idProduct);
}
