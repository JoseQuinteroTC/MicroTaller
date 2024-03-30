package com.TallerApi.demo.service.pedido;
import com.TallerApi.demo.dto.pedido.PedidoActualizarDto;
import com.TallerApi.demo.dto.pedido.PedidoAndProductDto;
import com.TallerApi.demo.dto.pedido.PedidoDto;
import com.TallerApi.demo.dto.pedido.PedidoToSaveDto;
import com.TallerApi.demo.model.enums.EstadoPedido;

import java.util.List;

public interface PedidoService {

    PedidoDto crearPedido(PedidoToSaveDto pedidoToSaveDto);

    PedidoDto actualizarPedido(Long id, PedidoActualizarDto pedidoActualizarDto);

    PedidoDto buscarPedidoById(Long id);

    void removerPedido(Long id);

    List<PedidoDto> getAllPedidos();

    List<PedidoDto> buscarPedidosByRangoFechas(String fechaInicio, String fechaFin);

    List<PedidoDto> buscarPedidoByClienteIdAndEstado(Long clienteId, EstadoPedido estadoPedido);

    List<PedidoAndProductDto> buscarPedidosConProductos(Long clienteId);
}
