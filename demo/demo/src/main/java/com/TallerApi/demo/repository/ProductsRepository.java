package com.TallerApi.demo.repository;

import com.TallerApi.demo.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {

  //  Optional <Products> findByTermino(String termino);

  //  Optional <Products> showByInStock();

 //   Optional <Products>  findByPriceAndStock(Integer precio, Integer stock);

}
