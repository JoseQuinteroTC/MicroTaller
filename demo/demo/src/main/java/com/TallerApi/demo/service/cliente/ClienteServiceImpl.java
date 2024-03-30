package com.TallerApi.demo.service.cliente;

import com.TallerApi.demo.dto.cliente.ClienteDto;
import com.TallerApi.demo.dto.cliente.ClienteMapper;
import com.TallerApi.demo.dto.cliente.ClienteToSaveDto;
import com.TallerApi.demo.model.Cliente;
import com.TallerApi.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteMapper clienteMapper;

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteMapper clienteMapper, ClienteRepository clienteRepository) {
        this.clienteMapper = clienteMapper;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDto crearCliente(ClienteToSaveDto clienteToSaveDto) {
        Cliente cliente = this.clienteMapper.clienteToSaveDtoToCliente(clienteToSaveDto);
        this.clienteRepository.save(cliente);
        return this.clienteMapper.clienteToClienteDto(cliente);
    }

    @Override
    public ClienteDto actualizarCliente(Long id, ClienteToSaveDto clienteToSaveDto) {
        Cliente clienteInDb = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El cliente no existe"));
        clienteInDb.setNombre(clienteToSaveDto.nombre());
        clienteInDb.setEmail(clienteToSaveDto.email());
        clienteInDb.setDireccion(clienteToSaveDto.direccion());
        clienteRepository.save(clienteInDb);
        return this.clienteMapper.clienteToClienteDto(clienteInDb);
    }

    @Override
    public ClienteDto buscarClienteById(Long id) {
        Cliente cliente = this.clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El cliente no existe"));
        return this.clienteMapper.clienteToClienteDto(cliente);
    }

    @Override
    public void removerCliente(Long id) {
        Cliente cliente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El cliente no existe"));
        this.clienteRepository.delete(cliente);
    }

    @Override
    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return clientes.stream().map(this.clienteMapper::clienteToClienteDto).toList();
    }

    @Override
    public ClienteDto buscarClienteByEmail(String email) {
        Cliente cliente = this.clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("El cliente no existe"));
        return this.clienteMapper.clienteToClienteDto(cliente);
    }

    @Override
    public ClienteDto buscarClienteByDireccion(String direccion) {
        Cliente cliente = this.clienteRepository.findByDireccion(direccion)
                .orElseThrow(() -> new RuntimeException("El cliente no existe"));
        return this.clienteMapper.clienteToClienteDto(cliente);
    }

    @Override
    public List<ClienteDto> buscarClientesNombreEmpiezaPor(String nombre) {
        List<Cliente> clientes = this.clienteRepository.findByNombreStartingWithIgnoreCase(nombre);
        return clientes.stream().map(this.clienteMapper::clienteToClienteDto).toList();
    }
}
