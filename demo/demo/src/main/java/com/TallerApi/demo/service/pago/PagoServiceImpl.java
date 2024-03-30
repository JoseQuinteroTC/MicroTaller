package com.TallerApi.demo.service.pago;

import com.TallerApi.demo.dto.pago.PagoDto;
import com.TallerApi.demo.dto.pago.PagoMapper;
import com.TallerApi.demo.dto.pedido.PedidoMapper;
import com.TallerApi.demo.dto.pago.PagoToSaveDto;
import com.TallerApi.demo.model.Pago;
import com.TallerApi.demo.model.Pedido;
import com.TallerApi.demo.model.enums.MetodoDePago;
import com.TallerApi.demo.repository.PagoRepository;
import com.TallerApi.demo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class PagoServiceImpl implements PagoService {

    private final PagoMapper pagoMapper;
    private final PagoRepository pagoRepository;
    private final PedidoMapper pedidoMapper;
    private final PedidoRepository pedidoRepository;

    public PagoServiceImpl(PagoMapper pagoMapper, PagoRepository pagoRepository, PedidoMapper pedidoMapper, PedidoRepository pedidoRepository) {
        this.pagoMapper = pagoMapper;
        this.pagoRepository = pagoRepository;
        this.pedidoMapper = pedidoMapper;
        this.pedidoRepository = pedidoRepository;
    }


    @Override
    public PagoDto crearPago(PagoToSaveDto pagoToSaveDto) {
        Pedido pedido = this.pedidoRepository.findById(pagoToSaveDto.idPedido()).
                orElseThrow(() -> new RuntimeException("No existe el pedido"));

        Pago pago = this.pagoMapper.pagosToSaveDtoToPagos(pagoToSaveDto);
        pago.setPedido(pedido);
        this.pagoRepository.save(pago);
        return this.pagoMapper.pagosToPagosDto(pago);
    }

    @Override
    public PagoDto actualizarPago(Long id, PagoToSaveDto pagoToSaveDto) {  //
        Pago pago = this.pagoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No existe el cliente"));
        pago.setMetodoPago(pagoToSaveDto.metodoPago());
        pago.setTotalPago(pagoToSaveDto.totalPago());
        this.pagoRepository.save(pago);

        return this.pagoMapper.pagosToPagosDto(pago);
    }

    @Override
    public PagoDto buscarPorId(Long id) {
        Pago pago = this.pagoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No existe el cliente"));

        return this.pagoMapper.pagosToPagosDto(pago);

    }

    @Override
    public void eliminarPago(Long id) {
        Pago pago = this.pagoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No existe el pago"));
        this.pagoRepository.delete(pago);
    }

    @Override
    public List<PagoDto> mostrarTodos() { //
        List<Pago> pagos = this.pagoRepository.findAll();

        return pagos.stream().map(this.pagoMapper::pagosToPagosDto).toList();
    }

    @Override
    public List<PagoDto> mostrarPagosEntreFechas(String initDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<Pago> pagos = this.pagoRepository.findByFechaPagoBetween(LocalDateTime.parse(initDate, formatter),
                LocalDateTime.parse(endDate, formatter));

        return pagos.stream().map(this.pagoMapper::pagosToPagosDto).toList();
    }

    @Override
    public List<PagoDto> mostrarPorIdOrdenAndMetodoPago(Long idOrden, MetodoDePago metodoDePago) {
        List<Pago> pagos = this.pagoRepository.findByIdAndMetodoPago(idOrden,metodoDePago);

        return pagos.stream().map(this.pagoMapper::pagosToPagosDto).toList();
    }
}
