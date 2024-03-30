package com.TallerApi.demo.service.detalleEnvio;

import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioActualizarDto;
import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioDto;
import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioMapper;
import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioToSaveDto;
import com.TallerApi.demo.model.DetalleEnvio;
import com.TallerApi.demo.model.Pedido;
import com.TallerApi.demo.model.enums.EstadoEnvio;
import com.TallerApi.demo.repository.DetalleEnvioRepository;
import com.TallerApi.demo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class DetalleEnvioServiceImpl implements DetalleEnvioService {

    private final PedidoRepository pedidoRepository;
    private final DetalleEnvioRepository detalleEnvioRepository;
    private final DetalleEnvioMapper detalleEnvioMapper;

    public DetalleEnvioServiceImpl(
            DetalleEnvioRepository detalleEnvioRepository,
            DetalleEnvioMapper detalleEnvioMapper,
            PedidoRepository pedidoRepository)
    {
        this.detalleEnvioRepository = detalleEnvioRepository;
        this.detalleEnvioMapper = detalleEnvioMapper;
        this.pedidoRepository = pedidoRepository;
    }
    @Override
    public DetalleEnvioDto crearDetalleEnvio(DetalleEnvioToSaveDto detalleEnvioToSaveDto) {

        Random random = new Random();

        Pedido pedido = this.pedidoRepository.findById(detalleEnvioToSaveDto.idPedido())
                .orElseThrow(() -> new RuntimeException("No existe"));
        DetalleEnvio detalleEnvio = this.detalleEnvioMapper.detalleEnvioToSaveDtoToDetalleEnvio(detalleEnvioToSaveDto);

        String numeroGuia = "ENV-" + pedido.getId() + random.nextInt(1000000 - 9999999 + 1) + 1000000;
        detalleEnvio.setNumeroGuia(numeroGuia);
        detalleEnvio.setPedido(pedido);
        detalleEnvio.setEstadoEnvio(EstadoEnvio.ENVIADO);
        this.detalleEnvioRepository.save(detalleEnvio);
        pedido.setDetalleEnvio(detalleEnvio);
        this.pedidoRepository.save(pedido);
        return this.detalleEnvioMapper.detalleEnvioToDetalleEnvioDto(detalleEnvio);
    }

    @Override
    public DetalleEnvioDto actualizarDetalleEnvio(Long id, DetalleEnvioActualizarDto detalleEnvioActualizarDto) {
        DetalleEnvio detalleEnvio = this.detalleEnvioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El envio no existe"));
        detalleEnvio.setEstadoEnvio(detalleEnvioActualizarDto.estadoEnvio());
        detalleEnvio.setDireccion(detalleEnvioActualizarDto.direccion());
        detalleEnvio.setTransportadora(detalleEnvioActualizarDto.transportadora());

        this.detalleEnvioRepository.save(detalleEnvio);
        return this.detalleEnvioMapper.detalleEnvioToDetalleEnvioDto(detalleEnvio);
    }

    @Override
    public DetalleEnvioDto buscarDetalleEnvioById(Long id) {
        DetalleEnvio detalleEnvio = this.detalleEnvioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No hay detalles del envio"));
        return this.detalleEnvioMapper.detalleEnvioToDetalleEnvioDto(detalleEnvio);
    }

    @Override
    public void removerDetalleEnvio(Long id) {
        DetalleEnvio detalleEnvio = this.detalleEnvioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No hay detalles del envio"));
        this.detalleEnvioRepository.delete(detalleEnvio);
    }

    @Override
    public List<DetalleEnvioDto> getAllDetalleEnvio() {
        List<DetalleEnvio> detalleEnvios = this.detalleEnvioRepository.findAll();
        return detalleEnvios.stream().map(this.detalleEnvioMapper::detalleEnvioToDetalleEnvioDto).toList();

    }

    @Override
    public DetalleEnvioDto getDetalleEnvioByPedidoId(Long id) {
        DetalleEnvio detalleEnvio = this.detalleEnvioRepository.findByPedidoId(id)
                .orElseThrow(() -> new RuntimeException("No hay detalles del envio"));
        return this.detalleEnvioMapper.detalleEnvioToDetalleEnvioDto(detalleEnvio);
    }

    @Override
    public List<DetalleEnvioDto> getDetalleEnviosByTransportadora(String transportadora) {
        List<DetalleEnvio> detalleEnvios = this.detalleEnvioRepository.findByTransportadora(transportadora);
        return detalleEnvios.stream().map(this.detalleEnvioMapper::detalleEnvioToDetalleEnvioDto).toList();
    }

    @Override
    public List<DetalleEnvioDto> getDetalleEnviosByEstado(EstadoEnvio estadoEnvio) {
        List<DetalleEnvio> detalleEnvios = this.detalleEnvioRepository.findByEstadoEnvio(estadoEnvio);
        return detalleEnvios.stream().map(this.detalleEnvioMapper::detalleEnvioToDetalleEnvioDto).toList();
    }
}
