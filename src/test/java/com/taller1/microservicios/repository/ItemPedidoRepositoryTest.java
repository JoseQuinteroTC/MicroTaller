package com.taller1.microservicios.repository;

import com.taller1.microservicios.AbstractIntegrationDBTest;
import com.taller1.microservicios.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemPedidoRepositoryTest extends AbstractIntegrationDBTest {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        itemPedidoRepository.deleteAll();
    }

    Producto getProductoMock(){
        return  Producto.builder().build();
    }
    Pedido getPedidoMock(){
        Cliente cliente = Cliente.builder().build();
        cliente = clienteRepository.save(cliente);

        return  Pedido.builder().cliente(cliente).build();
    }
    ItemPedido getItemPedidoMock() {
        return ItemPedido.builder()
                .id(1L)
                .cantidad(80)
                .precioUnitario(670d)
                .build();
    }
    List<ItemPedido> getItemPedidoListMock() {

        Producto producto= getProductoMock();
        producto= productoRepository.save(producto);

        Pedido pedido = getPedidoMock();
        pedido= pedidoRepository.save(pedido);

        List<ItemPedido> itemPedidos = new ArrayList<>();
        itemPedidos.add(ItemPedido.builder()
                .cantidad(23)
                .precioUnitario(99000d)
                .pedido(pedido)
                .producto(producto)
                .build()
        );
        itemPedidos.add(ItemPedido.builder()
                .cantidad(24)
                .precioUnitario(8900d)
                .pedido(pedido)
                .producto(producto)
                .build()
        );
        return itemPedidos;
    }



    @Test
    @DisplayName("Save itemPedido")
    void givenItemPedido_saveItemPedido_thenReturnSavedItemPedido() {
        //Given
        ItemPedido itemPedido = getItemPedidoMock();
        //When
        ItemPedido itemPedidoSaved = itemPedidoRepository.save(itemPedido);
        //Then
        Assertions.assertNotNull(itemPedidoSaved);
        Assertions.assertTrue(itemPedidoSaved.getId() > 0);
        Assertions.assertEquals(itemPedidoSaved.getCantidad(), itemPedido.getCantidad());
        Assertions.assertEquals(itemPedidoSaved.getPrecioUnitario(), itemPedido.getPrecioUnitario());
    }

    @Test
    @DisplayName("find all itemPedidos")
    void givenItemPedidoList_whenFindAll_thenItemPedidoList() {
        //Given
        List<ItemPedido> itemPedidos = getItemPedidoListMock();
        itemPedidoRepository.saveAll(itemPedidos);
        //When
        List<ItemPedido> foundItemPedidos = itemPedidoRepository.findAll();
        //Then
        Assertions.assertNotNull(foundItemPedidos);
        Assertions.assertEquals(foundItemPedidos.size(), 2);
    }

    @Test
    @DisplayName("find itemPedido by id")
    void givenItemPedido_whenFindById_thenItemPedido() {
        //Given
        ItemPedido itemPedidoSaved = itemPedidoRepository.save(getItemPedidoMock());
        //When
        Optional<ItemPedido> foundItemPedido = itemPedidoRepository.findById(itemPedidoSaved.getId());
        //Then
        Assertions.assertTrue(foundItemPedido.isPresent());
        Assertions.assertEquals(foundItemPedido.get().getId(), itemPedidoSaved.getId());
    }

    //Estos 2 test dan error al correr
    //todo el archivo, pero si se hacen en individual pasan

    @Test
    @DisplayName("Find ItemPedidos by idpedido")
    void givenIdPedido_whenFindByProductoId_ThenItemPedidos(){
        //Given
        itemPedidoRepository.saveAll(getItemPedidoListMock());
        Long idPedido = 1L;
        //When
        List<ItemPedido> foundItemPedidos = itemPedidoRepository.findByPedidoId(idPedido);
        //Then
        Assertions.assertFalse(foundItemPedidos.isEmpty());
    }

    @Test
    @DisplayName("Find ItemPedidos by idproducto")
    void givenIdProducto_whenFindByProductoId_ThenItemPedidos(){
        //Given
        itemPedidoRepository.saveAll(getItemPedidoListMock());
        Long idProducto = 1L;
        //When
        List<ItemPedido> foundItemPedidos = itemPedidoRepository.findByProductoId(idProducto);
        //Then
        Assertions.assertFalse(foundItemPedidos.isEmpty());
    }

    @Test
    @DisplayName("Delete itemPedido")
    void givenItemPedido_whenDelete_thenRemoveItemPedido() {
        //Given
        ItemPedido itemPedidoSaved = itemPedidoRepository.save(getItemPedidoMock());
        //When
        itemPedidoRepository.delete(itemPedidoSaved);
        Optional<ItemPedido> removedItemPedido = itemPedidoRepository.findById(itemPedidoSaved.getId());
        //Then
        Assertions.assertFalse(removedItemPedido.isPresent());
    }

    @Test
    @DisplayName("Update itemPedido")
    void givenItemPedido_whenUpdate_thenItemPedido() {
        //Given
        ItemPedido itemPedidoSaved = itemPedidoRepository.save(getItemPedidoMock());
        //When
        Integer nuevaCantidad = 2;
        Double nuevoPrecioUnitario = 3000d;
        itemPedidoSaved.setCantidad(nuevaCantidad);
        itemPedidoSaved.setPrecioUnitario(nuevoPrecioUnitario);
        ItemPedido itemPedidoUpdated = itemPedidoRepository.save(itemPedidoSaved);
        //Then
        Assertions.assertNotNull(itemPedidoUpdated);
        Assertions.assertEquals(itemPedidoSaved.getId(), itemPedidoUpdated.getId());
        Assertions.assertEquals(itemPedidoUpdated.getCantidad(), nuevaCantidad);
        Assertions.assertEquals(itemPedidoUpdated.getPrecioUnitario(), nuevoPrecioUnitario);
    }



}
