package com.TallerApi.demo.service.producto;

import com.TallerApi.demo.dto.producto.ProductDto;
import com.TallerApi.demo.dto.producto.ProductToSaveDto;

import java.util.List;


public interface ProductService {

    ProductDto crearProducto (ProductToSaveDto productoToSaveDto);
    ProductDto actualizarProducto(Long id, ProductToSaveDto productoToSaveDto);
    ProductDto buscarProductoById(Long id);
    void removerProducto(Long id);
    List<ProductDto> getAllProductos();
    List<ProductDto> buscarProductoByTermino(String termino);
    List<ProductDto> buscarProductosEnStock();
    List<ProductDto> buscarProductoMenoresByPrecioAndStock(Double precio, Integer Stock);
}
