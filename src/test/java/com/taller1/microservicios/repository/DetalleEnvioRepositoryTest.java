package com.taller1.microservicios.repository;

import com.taller1.microservicios.AbstractIntegrationDBTest;
import com.taller1.microservicios.model.Cliente;
import com.taller1.microservicios.model.DetalleEnvio;
import com.taller1.microservicios.model.Pago;
import com.taller1.microservicios.model.Pedido;
import com.taller1.microservicios.model.enums.EstadoEnvio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetalleEnvioRepositoryTest extends AbstractIntegrationDBTest {

    @Autowired
    private DetalleEnvioRepository detalleEnvioRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        detalleEnvioRepository.deleteAll();
    }

    DetalleEnvio getDetalleEnvioMock() {

        Cliente cliente = Cliente.builder().build();
        cliente = clienteRepository.save(cliente);

        Pedido pedido = Pedido.builder().cliente(cliente).build();
        pedido = pedidoRepository.save(pedido);

        return DetalleEnvio.builder()
                .id(1L)
                .direccion("Kra 234")
                .transportadora("Envia")
                .estadoEnvio(EstadoEnvio.ENVIADO)
                .numeroGuia("ENV-2024789")
                .pedido(pedido)
                .build();
    }

    List<DetalleEnvio> getDetalleEnvioListMock() {
        List<DetalleEnvio> detalleEnvios = new ArrayList<>();
        detalleEnvios.add(DetalleEnvio.builder()
                .id(1L)
                .direccion("Kra 234")
                .transportadora("Envia")
                .estadoEnvio(EstadoEnvio.ENVIADO)
                .numeroGuia("ENV-2024789")
                .build()
        );
        detalleEnvios.add(DetalleEnvio.builder()
                .id(2L)
                .direccion("Trv 21c")
                .transportadora("Coordinadora")
                .estadoEnvio(EstadoEnvio.EN_REPARTO)
                .numeroGuia("ENV-20249987")
                .build()
        );
        return detalleEnvios;
    }

    @Test
    @DisplayName("save detalleEnvio")
    void givenDetalleEnvio_saveDetalleEnvio_thenReturnSavedDetalleEnvio() {
        //Given
        DetalleEnvio detalleEnvio = getDetalleEnvioMock();
        //When
        DetalleEnvio detalleEnvioSaved = detalleEnvioRepository.save(detalleEnvio);
        //Then
        Assertions.assertNotNull(detalleEnvioSaved);
        Assertions.assertTrue(detalleEnvioSaved.getId() > 0);
        Assertions.assertEquals(detalleEnvioSaved.getDireccion(), detalleEnvio.getDireccion());
        Assertions.assertEquals(detalleEnvioSaved.getEstadoEnvio(), detalleEnvio.getEstadoEnvio());
        Assertions.assertEquals(detalleEnvioSaved.getTransportadora(), detalleEnvio.getTransportadora());
        Assertions.assertEquals(detalleEnvioSaved.getNumeroGuia(), detalleEnvio.getNumeroGuia());
    }

    @Test
    @DisplayName("find all detalleEnvios")
    void givenDetalleEnvioList_whenFindAll_thenDetalleEnvioList() {
        //Given
        List<DetalleEnvio> detalleEnvios = getDetalleEnvioListMock();
        detalleEnvioRepository.saveAll(detalleEnvios);
        //When
        List<DetalleEnvio> foundDetalleEnvios = detalleEnvioRepository.findAll();
        //Then
        Assertions.assertNotNull(foundDetalleEnvios);
        Assertions.assertEquals(foundDetalleEnvios.size(), 2);
    }

    @Test
    @DisplayName("find detalleEnvio by id")
    void givenDetalleEnvio_whenFindById_thenDetalleEnvio() {
        //Given
        DetalleEnvio detalleEnvioSaved = detalleEnvioRepository.save(getDetalleEnvioMock());
        //When
        Optional<DetalleEnvio> foundDetalleEnvio = detalleEnvioRepository.findById(detalleEnvioSaved.getId());
        //Then
        Assertions.assertTrue(foundDetalleEnvio.isPresent());
        Assertions.assertEquals(foundDetalleEnvio.get().getId(), detalleEnvioSaved.getId());
    }

    @Test
    @DisplayName("delete detalleEnvio")
    void givenDetalleEnvio_whenDelete_thenRemoveDetalleEnvio() {
        //Given
        DetalleEnvio detalleEnvioSaved = detalleEnvioRepository.save(getDetalleEnvioMock());
        //When
        detalleEnvioRepository.delete(detalleEnvioSaved);
        Optional<DetalleEnvio> removedDetalleEnvio = detalleEnvioRepository.findById(detalleEnvioSaved.getId());
        //Then
        Assertions.assertFalse(removedDetalleEnvio.isPresent());
    }

    @Test
    @DisplayName("update detalleEnvio")
    void givenDetalleEnvio_whenUpdate_thenDetalleEnvio() {
        //Given
        DetalleEnvio detalleEnvioSaved = detalleEnvioRepository.save(getDetalleEnvioMock());
        //When
        String nuevaDireccion = "Trnv 1b";
        String nuevaTransportadora = "Interrapidisimo";
        String nuevoNumeroGuia = "ENV-20248900";
        EstadoEnvio nuevoEstadoEnvio = EstadoEnvio.ENTREGADO;
        detalleEnvioSaved.setDireccion(nuevaDireccion);
        detalleEnvioSaved.setTransportadora(nuevaTransportadora);
        detalleEnvioSaved.setNumeroGuia(nuevoNumeroGuia);
        detalleEnvioSaved.setEstadoEnvio(nuevoEstadoEnvio);
        DetalleEnvio detalleEnvioUpdated = detalleEnvioRepository.save(detalleEnvioSaved);
        //Then
        Assertions.assertNotNull(detalleEnvioUpdated);
        Assertions.assertEquals(detalleEnvioSaved.getId(), detalleEnvioUpdated.getId());
        Assertions.assertEquals(detalleEnvioUpdated.getDireccion(), nuevaDireccion);
        Assertions.assertEquals(detalleEnvioUpdated.getTransportadora(), nuevaTransportadora);
        Assertions.assertEquals(detalleEnvioUpdated.getNumeroGuia(), nuevoNumeroGuia);
        Assertions.assertEquals(detalleEnvioUpdated.getEstadoEnvio(), nuevoEstadoEnvio);
    }

    @Test
    @DisplayName("find detalleEnvio by transportadora")
    void givenDetalleEnvioList_whenFindByTransportadora_thenDetalleEnvioList() {
        //Given
        detalleEnvioRepository.saveAll(getDetalleEnvioListMock());
        //When
        String transportadora = "Envia";
        List<DetalleEnvio> foundDetalleEnvios = detalleEnvioRepository.findByTransportadora(transportadora);
        //Then
        Assertions.assertFalse(foundDetalleEnvios.isEmpty());
        for (DetalleEnvio detalleEnvio : foundDetalleEnvios) {
            Assertions.assertEquals(detalleEnvio.getTransportadora(), transportadora);
        }
    }

    @Test
    @DisplayName("find detalleEnvio by estado envio")
    void givenDetalleEnvioList_whenFindByEstadoEnvio_thenDetalleEnvioList() {
        //Given
        detalleEnvioRepository.saveAll(getDetalleEnvioListMock());
        //When
        EstadoEnvio estadoEnvio = EstadoEnvio.ENVIADO;
        List<DetalleEnvio> foundDetalleEnvios = detalleEnvioRepository.findByEstadoEnvio(estadoEnvio);
        //Then
        Assertions.assertFalse(foundDetalleEnvios.isEmpty());
        for (DetalleEnvio detalleEnvio : foundDetalleEnvios) {
            Assertions.assertEquals(detalleEnvio.getEstadoEnvio(), estadoEnvio);
        }
    }

    @Test
    @DisplayName("find detalleEnvio by idpedido")
    void givenIdPedido_whenfindByPedidoId_thenDetalleEnvio(){
        //Given
        DetalleEnvio detalleEnvio = detalleEnvioRepository.save(getDetalleEnvioMock());
        Long idPedido = detalleEnvio.getPedido().getId();
        //When
        Optional<DetalleEnvio> foundDetalleEnvio = detalleEnvioRepository.findByPedidoId(idPedido);
        //Then
        Assertions.assertTrue(foundDetalleEnvio.isPresent());
    }

}
