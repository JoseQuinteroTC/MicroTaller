package com.taller1.microservicios.repository;

import com.taller1.microservicios.AbstractIntegrationDBTest;
import com.taller1.microservicios.model.Cliente;
import com.taller1.microservicios.model.ItemPedido;
import com.taller1.microservicios.model.Pedido;
import com.taller1.microservicios.model.enums.EstadoPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.taller1.microservicios.model.enums.EstadoPedido.PENDIENTE;
import static java.util.Calendar.MARCH;

public class PedidoRepositoryTest extends AbstractIntegrationDBTest {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @BeforeEach
    void setUp() { pedidoRepository.deleteAll(); }

    Pedido getPedidoMock() {
        Cliente cliente = Cliente.builder().build();
        cliente = clienteRepository.save(cliente);

        return Pedido.builder()
                .fechaPedido(LocalDateTime.of(2024, MARCH, 21, 12, 30))
                .estadoPedido(PENDIENTE)
                .cliente(cliente)
                .build();
    }

    List<Pedido> getPedidoListMock() {

        Cliente cliente = Cliente.builder().build();
        cliente = clienteRepository.save(cliente);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(Pedido.builder()
                .fechaPedido(LocalDateTime.of(2024, Month.MARCH, 25, 12, 0))
                .estadoPedido(EstadoPedido.PENDIENTE)
                .cliente(cliente)
                .build()
        );
        pedidos.add(Pedido.builder()
                .fechaPedido(LocalDateTime.of(2024, Month.FEBRUARY, 25, 12, 0))
                .estadoPedido(EstadoPedido.PENDIENTE)
                .cliente(cliente)
                .build()
        );
        pedidos.add(Pedido.builder()
                .fechaPedido(LocalDateTime.of(2024, Month.DECEMBER, 25, 12, 0))
                .estadoPedido(EstadoPedido.ENVIADO)
                .cliente(cliente)
                .build()
        );
        return pedidos;
    }

    @Test
    @DisplayName("Save pedido")
    void givenPedido_savePedido_thenReturnSavedPedido() {
        //Given
        Pedido pedido = getPedidoMock();
        //When
        Pedido pedidoSaved = pedidoRepository.save(pedido);
        //Then
        Assertions.assertNotNull(pedidoSaved);
        Assertions.assertTrue(pedidoSaved.getId() > 0);
        Assertions.assertEquals(pedidoSaved.getFechaPedido(), pedido.getFechaPedido());
        Assertions.assertEquals(pedidoSaved.getEstadoPedido(), pedido.getEstadoPedido());
    }

    @Test
    @DisplayName("Find all pedidos")
    void givenPedidoList_whenFindAll_thenPedidoList() {
        //Given
        List<Pedido> pedidos = getPedidoListMock();
        pedidoRepository.saveAll(pedidos);
        //When
        List<Pedido> foundPedidos = pedidoRepository.findAll();
        //Then
        Assertions.assertNotNull(foundPedidos);
        Assertions.assertEquals(foundPedidos.size(), 3);
    }

    @Test
    @DisplayName("find pedido by id")
    void givenPedido_whenFindById_thenPedido() {
        //Given
        Pedido pedidoSaved = pedidoRepository.save(getPedidoMock());
        //When
        Optional<Pedido> foundPedido = pedidoRepository.findById(pedidoSaved.getId());
        //Then
        Assertions.assertTrue(foundPedido.isPresent());
        Assertions.assertEquals(foundPedido.get().getId(), pedidoSaved.getId());
    }

    @Test
    @DisplayName("Delete pedido")
    void givenPedido_whenDelete_thenRemovePedido() {
        //Given
        Pedido pedidoSaved = pedidoRepository.save(getPedidoMock());
        //When
        pedidoRepository.delete(pedidoSaved);
        Optional<Pedido> removedPedido = pedidoRepository.findById(pedidoSaved.getId());
        //Then
        Assertions.assertFalse(removedPedido.isPresent());
    }

    @Test
    @DisplayName("Update pedido")
    void givenPedido_whenUpdate_thenPedido() {
        //Given
        Pedido pedidoSaved = pedidoRepository.save(getPedidoMock());
        //When
        LocalDateTime nuevaFechaPedido = LocalDateTime.of(2023, Month.MARCH, 23, 11, 13);
        EstadoPedido nuevoEstadoPedido = EstadoPedido.ENTREGADO;
        pedidoSaved.setFechaPedido(nuevaFechaPedido);
        pedidoSaved.setEstadoPedido(nuevoEstadoPedido);
        Pedido pedidoUpdated = pedidoRepository.save(pedidoSaved);
        //Then
        Assertions.assertNotNull(pedidoUpdated);
        Assertions.assertEquals(pedidoSaved.getId(), pedidoUpdated.getId());
        Assertions.assertEquals(pedidoUpdated.getFechaPedido(), nuevaFechaPedido);
        Assertions.assertEquals(pedidoUpdated.getEstadoPedido(), nuevoEstadoPedido);
    }

    @Test
    @DisplayName("Find pedidos Between fecha pedido")
    void givenFechasRango_whenFindBetween_thenReturnPedidos() {
        //Given
        List<Pedido> pedidos = pedidoRepository.saveAll(getPedidoListMock());
        //When
        LocalDateTime fechaInicio = LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2024, Month.JULY, 31, 12, 0);
        List<Pedido> foundPedidos = pedidoRepository.findByFechaPedidoBetween(fechaInicio, fechaFin);
        //Then
        Assertions.assertFalse(foundPedidos.isEmpty());
        for(Pedido pedido : foundPedidos) {
            LocalDateTime fechaPedido = pedido.getFechaPedido();
            Assertions.assertTrue(fechaPedido.isAfter(fechaInicio) && fechaPedido.isBefore(fechaFin));
        }
    }

    //funciona al correrlo individual pero no en grupo

    @Test
    @DisplayName("find pedido by cliente id and estado")
        void givenIdCLienteAndStatus_WhenFindIdClienteAndStatus_thenPedidos(){
        //Given
        pedidoRepository.saveAll(getPedidoListMock());
        Long idCliente = 1L;
        EstadoPedido estado = PENDIENTE;
        //When
        List<Pedido> foundPedidos = pedidoRepository.findByClienteIdAndEstadoPedido(idCliente, estado);
        //Then
        Assertions.assertFalse(foundPedidos.isEmpty());
    }

    @Test
    @DisplayName("Find order with productos  by clienteid")
    void givenIdCLiente_WhenFindIdClienteConProductos_thenPedidos(){
        //Given
        Pedido pedido = getPedidoMock();
        pedido= pedidoRepository.save(pedido);
        List<ItemPedido> itemPedidos = new ArrayList<>();
        itemPedidos.add(ItemPedido.builder().pedido(pedido).build());
        itemPedidos.add(ItemPedido.builder().pedido(pedido).build());
        itemPedidos= itemPedidoRepository.saveAll(itemPedidos);
        ItemPedido item = itemPedidos.get(0);
        item.setPedido(pedido);
        itemPedidoRepository.save(item);
        Long idCliente = 1L;
        //When
        List<Pedido> foundPedidos = pedidoRepository.findPedidoConProductosByCliente(idCliente);
        //Then
        Assertions.assertFalse(foundPedidos.isEmpty());
    }

}
