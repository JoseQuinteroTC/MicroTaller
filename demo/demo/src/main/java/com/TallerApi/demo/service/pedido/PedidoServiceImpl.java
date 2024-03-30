package com.TallerApi.demo.service.pedido;

import com.TallerApi.demo.dto.pedido.*;
import com.TallerApi.demo.model.Cliente;
import com.TallerApi.demo.model.Pedido;
import com.TallerApi.demo.model.enums.EstadoPedido;
import com.TallerApi.demo.repository.ClienteRepository;
import com.TallerApi.demo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            PedidoMapper pedidoMapper,
            ClienteRepository clienteRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
        this.clienteRepository = clienteRepository;
    }
    @Override
    public PedidoDto crearPedido(PedidoToSaveDto pedidoToSaveDto) {
        Cliente cliente = this.clienteRepository.findById(pedidoToSaveDto.idCliente())
                .orElseThrow(()-> new RuntimeException("No existe el cliente"));
        Pedido pedido = this.pedidoMapper.pedidoToSaveDtoToPedido(pedidoToSaveDto);
        pedido.setCliente(cliente);
        this.pedidoRepository.save(pedido);
        return this.pedidoMapper.pedidoToPedidoDto(pedido);
    }

    @Override
    public PedidoDto actualizarPedido(Long id, PedidoActualizarDto pedidoActualizarDto) {
        Pedido pedido = this.pedidoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No existe el pedido"));
        pedido.setEstadoPedido(pedidoActualizarDto.estadoPedido());
        this.pedidoRepository.save(pedido);
        return this.pedidoMapper.pedidoToPedidoDto(pedido);
    }

    @Override
    public PedidoDto buscarPedidoById(Long id) {
        Pedido pedido = this.pedidoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No existe el pedido"));
        return this.pedidoMapper.pedidoToPedidoDto(pedido);
    }

    @Override
    public void removerPedido(Long id) {
        Pedido pedido = this.pedidoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No existe el pedido"));
        this.pedidoRepository.delete(pedido);
    }

    @Override
    public List<PedidoDto> getAllPedidos() {
        List<Pedido> pedidos = this.pedidoRepository.findAll();
        return pedidos.stream().map(this.pedidoMapper::pedidoToPedidoDto).toList();
    }

    @Override
    public List<PedidoDto> buscarPedidosByRangoFechas(String fechaInicio, String fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<Pedido> pedidos = this.pedidoRepository.findByFechaPedidoBetween(
                LocalDateTime.parse(fechaInicio, formatter),
                LocalDateTime.parse(fechaFin, formatter)
        );
        return pedidos.stream().map(this.pedidoMapper::pedidoToPedidoDto).toList();
    }

    @Override
    public List<PedidoDto> buscarPedidoByClienteIdAndEstado(Long clienteId, EstadoPedido estadoPedido) {
        List<Pedido> pedidos = this.pedidoRepository.findByClienteIdAndEstadoPedido(clienteId, estadoPedido);
        return pedidos.stream().map(this.pedidoMapper::pedidoToPedidoDto).toList();
    }

    @Override
    public List<PedidoAndProductDto> buscarPedidosConProductos(Long clienteId) {
        return null;
    }

//    @Override
//    public List<PedidoAndProductoDto> buscarPedidosConProductos(Long clienteId) {
//        List<Pedido> pedidos = this.pedidoRepository.findPedidoConProductosByCliente(clienteId);
//        return this.pedidoMapper.pedidoListToPedidoProductosListDto(pedidos);
//    }
}
