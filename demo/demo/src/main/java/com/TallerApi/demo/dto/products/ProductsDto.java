package com.TallerApi.demo.dto.products;

public record ProductsDto(Long id,
                          String nombre,
                          Integer price,
                          Integer stock) {
}
