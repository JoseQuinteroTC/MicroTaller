package com.taller1.microservicios.repository;

import com.taller1.microservicios.AbstractIntegrationDBTest;
import com.taller1.microservicios.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepositoryTest extends AbstractIntegrationDBTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    Cliente getClienteMock() {
        return Cliente.builder()
                .id(1L)
                .nombre("Camilo")
                .email("Camilo@gmail.com")
                .direccion("Carrera 23")
                .build();
    }

    List<Cliente> getClienteListMock() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(
                Cliente.builder()
                        .id(1L)
                        .nombre("Camilo")
                        .email("Camilo@gmail.com")
                        .direccion("Carrera 23")
                        .build()
        );
        clientes.add(
                Cliente.builder()
                        .id(2L)
                        .nombre("Juan")
                        .email("Juan@gmail.com")
                        .direccion("Cll 23")
                        .build()
        );
        return clientes;
    }
    @Test
    @DisplayName("Guardar un objeto cliente")
    void givenCliente_saveCliente_thenReturnSavedCliente() {
        //Given
        Cliente cliente = getClienteMock();
        //When
        Cliente clienteSaved = clienteRepository.save(cliente);
        //Then
        Assertions.assertNotNull(clienteSaved);
        Assertions.assertTrue(clienteSaved.getId() > 0);
        Assertions.assertEquals(clienteSaved.getNombre(), cliente.getNombre());
        Assertions.assertEquals(clienteSaved.getDireccion(), cliente.getDireccion());
        Assertions.assertEquals(clienteSaved.getEmail(), cliente.getEmail());
    }

    @Test
    @DisplayName("find all clientes")
    void givenClienteList_whenFindAll_thenClienteList() {
        //Given
        List<Cliente> clientes = getClienteListMock();
        clienteRepository.saveAll(clientes);
        //When
        List<Cliente> foundClientes = clienteRepository.findAll();
        //Then
        Assertions.assertNotNull(foundClientes);
        Assertions.assertEquals(foundClientes.size(), 2);
    }

    @Test
    @DisplayName("find cliente by id")
    void givenCliente_whenFindById_thenCliente() {
        //Given
        Cliente clienteSaved = clienteRepository.save(getClienteMock());
        //When
        Optional<Cliente> foundCliente = clienteRepository.findById(clienteSaved.getId());
        //Then
        Assertions.assertTrue(foundCliente.isPresent());
        Assertions.assertEquals(foundCliente.get().getId(), clienteSaved.getId());
    }

    @Test
    @DisplayName("delete  cliente")
    void givenCliente_whenDelete_thenRemoveCliente() {
        //Given
        Cliente clienteSaved = clienteRepository.save(getClienteMock());
        //When
        clienteRepository.delete(clienteSaved);
        Optional<Cliente> removedCliente = clienteRepository.findById(clienteSaved.getId());
        //Then
        Assertions.assertFalse(removedCliente.isPresent());
    }

    @Test
    @DisplayName("update cliente")
    void givenCliente_whenUpdate_thenCliente() {
        //Given
        Cliente clienteSaved = clienteRepository.save(getClienteMock());
        //When
        String nuevoNombre = "Lorena";
        String nuevoEmail = "Lorena@gmail.com";
        String nuevaDireccion = "mnanzana 12";
        clienteSaved.setNombre(nuevoNombre);
        clienteSaved.setEmail(nuevoEmail);
        clienteSaved.setDireccion(nuevaDireccion);
        Cliente clienteUpdated = clienteRepository.save(clienteSaved);
        //Then
        Assertions.assertNotNull(clienteUpdated);
        Assertions.assertEquals(clienteSaved.getId(), clienteUpdated.getId());
        Assertions.assertEquals(clienteUpdated.getNombre(), nuevoNombre);
        Assertions.assertEquals(clienteUpdated.getEmail(), nuevoEmail);
        Assertions.assertEquals(clienteUpdated.getDireccion(), nuevaDireccion);
    }

    @Test
    @DisplayName("find cliente by email")
    void givenClienteEmail_whenBuscarCliente_thenCliente() {
        //Given
        Cliente clienteSaved = clienteRepository.save(getClienteMock());
        String email = "Camilo@gmail.com";
        //When
        Optional<Cliente> clienteFound = clienteRepository.findByEmail(email);
        //Then
        Assertions.assertTrue(clienteFound.isPresent());
        Assertions.assertEquals(clienteFound.get().getEmail(), email);
    }

    @Test
    @DisplayName("find cliente by direccion")
    void givenClienteDireccion_whenBuscarCliente_thenCliente() {
        //Given
        Cliente clienteSaved = clienteRepository.save(getClienteMock());
        String direccion = "Carrera 23";
        //When
        Optional<Cliente> clienteFound = clienteRepository.findByDireccion(direccion);
        //Then
        Assertions.assertTrue(clienteFound.isPresent());
        Assertions.assertEquals(clienteFound.get().getDireccion(), direccion);
    }

    @Test
    @DisplayName("Buscar cliente donde el nombre coincida con el unicio")
    void givenClienteNombre_whenBuscarClienteNombreEmpiezaPor_thenCliente() {
        //Given
        clienteRepository.saveAll(this.getClienteListMock());
        String nombre = "cami";
        //When
        List<Cliente> clientesFound = clienteRepository.findByNombreStartingWithIgnoreCase(nombre);
        //Then
        Assertions.assertFalse(clientesFound.isEmpty());
        for (Cliente cliente : clientesFound) {
            Assertions.assertTrue(cliente.getNombre()
                    .toLowerCase().startsWith(nombre)
            );
        }
    }
}
