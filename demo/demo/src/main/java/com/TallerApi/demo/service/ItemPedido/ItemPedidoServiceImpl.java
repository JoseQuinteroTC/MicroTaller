package com.TallerApi.demo.service.ItemPedido;

import com.TallerApi.demo.dto.itemPedido.ItemPedidoActualizarDto;
import com.TallerApi.demo.dto.itemPedido.ItemPedidoDto;
import com.TallerApi.demo.dto.itemPedido.ItemPedidoMapper;
import com.TallerApi.demo.dto.itemPedido.ItemPedidoToSaveDto;

import com.TallerApi.demo.model.ItemPedido;
import com.TallerApi.demo.model.Pedido;
import com.TallerApi.demo.model.Product;
import com.TallerApi.demo.repository.ItemPedidoRepository;
import com.TallerApi.demo.repository.PedidoRepository;
import com.TallerApi.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService{
    private final ItemPedidoMapper itemPedidoMapper;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductRepository productRepository;

    public ItemPedidoServiceImpl(
            ItemPedidoMapper itemPedidoMapper,
            ItemPedidoRepository itemPedidoRepository,
            ProductRepository productRepository,
            PedidoRepository pedidoRepository
    ) {
        this.itemPedidoMapper = itemPedidoMapper;
        this.itemPedidoRepository = itemPedidoRepository;
        this.productRepository = productRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public ItemPedidoDto crearItemPedido(ItemPedidoToSaveDto itemPedidoToSaveDto) {
        boolean itemPedidoExists = this.itemPedidoRepository.findByProductoIdAndPedidoId(
                itemPedidoToSaveDto.idProducto(),
                itemPedidoToSaveDto.idProducto()).isPresent();
        if (itemPedidoExists) throw new RuntimeException("El item pedido ya existe");
        Pedido pedido = this.pedidoRepository.findById(itemPedidoToSaveDto.idProducto())
                .orElseThrow(() -> new RuntimeException("El pedido no existe"));
        Product product = this.productRepository.findById(itemPedidoToSaveDto.idProducto())
                .orElseThrow(() -> new RuntimeException("El producto no existe"));
        ItemPedido itemPedido = this.itemPedidoMapper.itemPedidoToSaveDtoToItemPedido(itemPedidoToSaveDto);
        itemPedido.setPedido(pedido);
        itemPedido.setProduct(product);
        itemPedido.setPrecioUnitario(product.getPrecio());
        itemPedidoRepository.save(itemPedido);
        return this.itemPedidoMapper.itemPedidoToItemPedidoDto(itemPedido);
    }

    @Override
    public List<ItemPedidoDto> getAllItemPedidos() {
        List<ItemPedido> pedidos = this.itemPedidoRepository.findAll();
        return pedidos.stream().map(this.itemPedidoMapper::itemPedidoToItemPedidoDto).toList();
    }

    @Override
    public ItemPedidoDto buscarItemPedidoById(Long id) {
        ItemPedido itemPedido = this.itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido no existe"));
        return this.itemPedidoMapper.itemPedidoToItemPedidoDto(itemPedido);
    }

    @Override
    public ItemPedidoDto actualizarItemPedido(Long id, ItemPedidoActualizarDto itemPedidoActualizarDto) {
        ItemPedido itemPedido = this.itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido no existe"));
        itemPedido.setCantidad(itemPedidoActualizarDto.cantidad());
        itemPedidoRepository.save(itemPedido);
        return this.itemPedidoMapper.itemPedidoToItemPedidoDto(itemPedido);
    }

    @Override
    public void removerItemPedido(Long id) {
        ItemPedido itemPedido = this.itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El itemPedido no existe"));
        itemPedidoRepository.delete(itemPedido);
    }

    @Override
    public List<ItemPedidoDto> buscarItemPedidoByidPedido(Long idPedido) {
        Optional<ItemPedido> pedidos = this.itemPedidoRepository.findByPedidoId(idPedido);
        return pedidos.stream().map(this.itemPedidoMapper::itemPedidoToItemPedidoDto).toList();
    }

    @Override
    public List<ItemPedidoDto> buscarItemPedidoByidProducto(Long idProducto) {
        List<ItemPedido> pedidos = this.itemPedidoRepository.findByProductoId(idProducto);
        return pedidos.stream().map(this.itemPedidoMapper::itemPedidoToItemPedidoDto).toList();
    }

    @Override
    public Double sumaTotalVentasDeidProducto(Long idProducto) {
        return this.itemPedidoRepository.findTotalVentasByProduct(idProducto)
                .orElseThrow(() -> new RuntimeException("Error al calcular el total"));
    }
}
