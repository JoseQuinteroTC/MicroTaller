package com.TallerApi.demo.service.clientes;

import com.TallerApi.demo.dto.clientes.ClientesDto;
import com.TallerApi.demo.dto.clientes.ClientesToSaveDto;
import com.TallerApi.demo.dto.clientes.ClientesMapper;
import com.TallerApi.demo.models.Cliente;
import com.TallerApi.demo.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImp implements ClienteService{


    private final ClientesMapper clientesMapper;
    private final ClientesRepository clientesRepository;

    public ClienteServiceImp(ClientesMapper clientesMapper, ClientesRepository clientesRepository) {
        this.clientesMapper = clientesMapper;
        this.clientesRepository = clientesRepository;
    }

    @Override
    public ClientesDto crearCliente(ClientesToSaveDto clientesToSaveDto) {
        Cliente cliente = this.clientesMapper.clientesToSaveDtoToClientes(clientesToSaveDto);
        this.clientesRepository.save(cliente);
        return this.clientesMapper.clientesToClientesDto(cliente);
    }

    @Override
    public ClientesDto actualizarCliente(ClientesToSaveDto clientesToSaveDto) {

        Cliente cliente = this.clientesMapper.clientesToSaveDtoToClientes(clientesToSaveDto);
        this.clientesRepository.save(cliente);
        return this.clientesMapper.clientesToClientesDto(cliente);
    }

    @Override
    public ClientesDto buscarPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarProducto(Long id) {

    }
}
