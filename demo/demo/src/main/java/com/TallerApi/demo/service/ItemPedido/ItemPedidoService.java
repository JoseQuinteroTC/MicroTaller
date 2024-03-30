package com.TallerApi.demo.service.ItemPedido;

import com.TallerApi.demo.dto.itemPedido.ItemPedidoActualizarDto;
import com.TallerApi.demo.dto.itemPedido.ItemPedidoDto;
import com.TallerApi.demo.dto.itemPedido.ItemPedidoToSaveDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ItemPedidoService {
    ItemPedidoDto crearItemPedido (ItemPedidoToSaveDto itemPedidoToSaveDto);
    ItemPedidoDto actualizarItemPedido(Long id, ItemPedidoActualizarDto itemPedidoActualizarDto);
    ItemPedidoDto buscarItemPedidoById(Long id);
    void removerItemPedido(Long id);
    List<ItemPedidoDto> getAllItemPedidos();
    List<ItemPedidoDto> buscarItemPedidoByidPedido(Long idPedido);
    List<ItemPedidoDto> buscarItemPedidoByidProducto(Long idProducto);
    Double sumaTotalVentasDeidProducto(Long idProducto);
}
