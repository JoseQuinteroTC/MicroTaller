package com.TallerApi.demo.service.pagos;

import com.TallerApi.demo.dto.clientes.ClientesMapper;
import com.TallerApi.demo.dto.pagos.PagosDto;
import com.TallerApi.demo.dto.pagos.PagosMapper;
import com.TallerApi.demo.dto.pedidos.PedidosMapper;
import com.TallerApi.demo.dto.pagos.PagosToSaveDto;
import com.TallerApi.demo.dto.pedidos.PedidosDto;
import com.TallerApi.demo.dto.pedidos.PedidosToSaveDto;
import com.TallerApi.demo.models.Pagos;
import com.TallerApi.demo.models.Pedidos;
import com.TallerApi.demo.models.enums.MetodoDePago;
import com.TallerApi.demo.repository.ClientesRepository;
import com.TallerApi.demo.repository.PagosRepository;
import com.TallerApi.demo.repository.PedidosRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class PagoServiceImpl implements PagosService{

    private final PagosMapper pagosMapper;
    private final PagosRepository pagosRepository;
    private final PedidosMapper pedidosMapper;

    private final PedidosRepository pedidosRepository;

    public PagoServiceImpl(PagosMapper pagosMapper, PagosRepository pagosRepository, PedidosMapper pedidosMapper, PedidosRepository pedidosRepository) {
        this.pagosMapper = pagosMapper;
        this.pagosRepository = pagosRepository;
        this.pedidosMapper = pedidosMapper;
        this.pedidosRepository = pedidosRepository;
    }


    @Override
    public PagosDto crearPago(PagosToSaveDto pagosToSaveDto) {
        Pedidos pedidos = this.pedidosRepository.findById(pagosToSaveDto.idPedido()).
                orElseThrow(() -> new RuntimeException("No existe el pedido"));

        Pagos pagos = this.pagosMapper.pagosToSaveDtoToPagos(pagosToSaveDto);
        pagos.setPedidos(pedidos);
        this.pagosRepository.save(pagos);
        return this.pagosMapper.pagosToPagosDto(pagos);
    }

    @Override
    public PagosDto actualizarPago(Long id,PagosToSaveDto pagosToSaveDto) {  //
        Pagos pagos = this.pagosRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No existe el cliente"));
        pagos.setMetodoPago(pagosToSaveDto.metodoPago());
        pagos.setTotalPago(pagosToSaveDto.totalPago());
        this.pagosRepository.save(pagos);

        return this.pagosMapper.pagosToPagosDto(pagos);
    }

    @Override
    public PagosDto buscarPorId(Long id) {
        Pagos pagos = this.pagosRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No existe el cliente"));

        return this.pagosMapper.pagosToPagosDto(pagos);

    }

    @Override
    public void eliminarPago(Long id) {
        Pagos pagos = this.pagosRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No existe el pago"));
        this.pagosRepository.delete(pagos);
    }

    @Override
    public List<PagosDto> mostrarTodos() { //
        List<Pagos> pagos = this.pagosRepository.findAll();

        return pagos.stream().map(this.pagosMapper::pagosToPagosDto).toList();
    }

    @Override
    public List<PagosDto> mostrarPagosEntreFechas(String initDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<Pagos> pagos = this.pagosRepository.findByFechaPagoBetween(LocalDateTime.parse(initDate, formatter),
                LocalDateTime.parse(endDate, formatter));

        return pagos.stream().map(this.pagosMapper::pagosToPagosDto).toList();
    }



    @Override
    public List<PagosDto> mostrarPorIdOrdenAndMetodoPago(Long idOrden, MetodoDePago metodoDePago) {
        List<Pagos> pagos = this.pagosRepository.findByIdAndMetodoPago(idOrden,metodoDePago);

        return pagos.stream().map(this.pagosMapper::pagosToPagosDto).toList();
    }
}
