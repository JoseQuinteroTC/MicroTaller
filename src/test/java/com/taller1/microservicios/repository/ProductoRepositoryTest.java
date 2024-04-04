package com.taller1.microservicios.repository;

import com.taller1.microservicios.AbstractIntegrationDBTest;
import com.taller1.microservicios.model.Producto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoRepositoryTest extends AbstractIntegrationDBTest {

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
    }

    Producto getProductoMock() {
        return Producto.builder()
                .nombre("Manzana")
                .stock(12)
                .precio(12300d)
                .build();
    }
    List<Producto> getProductoListMock() {
        List<Producto> productos = new ArrayList<>();
        productos.add(Producto.builder()
                .nombre("Manzana")
                .stock(150)
                .precio(12300d)
                .build()
        );
        productos.add(Producto.builder()
                .nombre("Pera")
                .precio(15000d)
                .stock(24)
                .build()
        );
        return productos;
    }

    @Test
    @DisplayName("Save producto")
    void givenProducto_saveProducto_thenReturnSavedProducto() {
        //Given
        Producto producto = getProductoMock();
        //When
        Producto productoSaved = productoRepository.save(producto);
        //Then
        Assertions.assertNotNull(productoSaved);
        Assertions.assertTrue(productoSaved.getId() > 0);
        Assertions.assertEquals(productoSaved.getNombre(), producto.getNombre());
        Assertions.assertEquals(productoSaved.getPrecio(), producto.getPrecio());
        Assertions.assertEquals(productoSaved.getStock(), producto.getStock());
    }

    @Test
    @DisplayName("Find all productos")
    void givenProductoList_whenFindAll_thenProductoList() {
        //Given
        List<Producto> productos = getProductoListMock();
        productoRepository.saveAll(productos);
        //When
        List<Producto> foundProductos = productoRepository.findAll();
        //Then
        Assertions.assertNotNull(foundProductos);
        Assertions.assertEquals(foundProductos.size(), 2);
    }

    @Test
    @DisplayName("Find producto by id")
    void givenProducto_whenFindById_thenProducto() {
        //Given
        Producto productoSaved = productoRepository.save(getProductoMock());
        //When
        Optional<Producto> foundProducto = productoRepository.findById(productoSaved.getId());
        //Then
        Assertions.assertTrue(foundProducto.isPresent());
        Assertions.assertEquals(foundProducto.get().getId(), productoSaved.getId());
    }

    @Test
    @DisplayName("Delete producto")
    void givenProducto_whenDelete_thenRemoveProducto() {
        //Given
        Producto productoSaved = productoRepository.save(getProductoMock());
        //When
        productoRepository.delete(productoSaved);
        Optional<Producto> removedProducto = productoRepository.findById(productoSaved.getId());
        //Then
        Assertions.assertFalse(removedProducto.isPresent());
    }

    @Test
    @DisplayName("Update producto")
    void givenProducto_whenUpdate_thenProducto() {
        //Given
        Producto productoSaved = productoRepository.save(getProductoMock());
        //When
        String nuevoNombre = "Cereza";
        Double nuevoPrecio = 1250d;
        Integer nuevoStock = 80;
        productoSaved.setNombre(nuevoNombre);
        productoSaved.setPrecio(nuevoPrecio);
        productoSaved.setStock(nuevoStock);
        Producto productoUpdated = productoRepository.save(productoSaved);
        //Then
        Assertions.assertNotNull(productoUpdated);
        Assertions.assertEquals(productoSaved.getId(), productoUpdated.getId());
        Assertions.assertEquals(productoUpdated.getNombre(), nuevoNombre);
        Assertions.assertEquals(productoUpdated.getPrecio(), nuevoPrecio);
        Assertions.assertEquals(productoUpdated.getStock(), nuevoStock);
    }

    @Test
    @DisplayName("Find by name containing the given word")
    void givenNombre_whenFindContainingNombre_thenProducto() {
        //Given
        List<Producto> productos = productoRepository.saveAll(getProductoListMock());
        String palabra = "per";
        //When
        List<Producto> foundProductos = productoRepository.findByNombreContainingIgnoreCase(palabra);
        //Then
        Assertions.assertFalse(foundProductos.isEmpty());
        for(Producto producto : foundProductos) {
            Assertions.assertTrue(producto.getNombre().toLowerCase().contains(palabra));
        }
    }

    @Test
    @DisplayName("Find for products that have stock greater than 0")
    void givenProductoList_whenFindInStock_thenProductoList() {
        //Given
        List<Producto> productos = getProductoListMock();
        productos.add(Producto.builder()
                .nombre("Limon")
                .precio(700d)
                .stock(0)
                .build()
        );
        productoRepository.saveAll(productos);
        //When
        List<Producto> foundProductos = productoRepository.findByInStock();
        //Then
        Assertions.assertFalse(foundProductos.isEmpty());
        for(Producto producto : foundProductos) {
            Assertions.assertTrue(producto.getStock() > 0);
        }
    }

    @Test
    @DisplayName("find for products that have stock and a price lower than the one provided")
    void givenPrecioAndStock_whenFindProductoLessThanEqual_thenProductoList() {
        //Given
        List<Producto> productos = productoRepository.saveAll(getProductoListMock());
        Double precioMaximo = 25000d;
        Integer stockMaximo = 50;
        //When
        List<Producto> foundProductos = productoRepository
                .findByPrecioLessThanEqualAndStockLessThanEqual(precioMaximo, stockMaximo);
        //Then
        Assertions.assertFalse(foundProductos.isEmpty());
        for(Producto producto : foundProductos) {
            Assertions.assertTrue(producto.getStock() <= stockMaximo);
            Assertions.assertTrue(producto.getPrecio() <= precioMaximo);
        }
    }



}
