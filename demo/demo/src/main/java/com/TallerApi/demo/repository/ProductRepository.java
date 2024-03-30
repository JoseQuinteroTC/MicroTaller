package com.TallerApi.demo.repository;

import com.TallerApi.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNombreContaining(String nombre);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.stock >= 1")
    List<Product> findByInStock();
    List<Product> findByPrecioLessThanEqualAndStockLessThanEqual(Double precio, Integer stock);


}
