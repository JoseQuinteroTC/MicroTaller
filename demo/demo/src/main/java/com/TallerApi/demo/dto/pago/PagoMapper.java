package com.TallerApi.demo.dto.pago;

import com.TallerApi.demo.dto.pedido.PedidoMapper;
import com.TallerApi.demo.model.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PedidoMapper.class)
public interface PagoMapper {

    @Mapping(source = "pedido.id", target = "idPedido")
    PagoDto pagosToPagosDto (Pago pago);

   // Pagos pagosDtoToPagos(PagosDto pagosDto);

    @Mapping(target = "fechaPago", expression = "java(java.time.LocalDateTime.now())")
    Pago pagosToSaveDtoToPagos(PagoToSaveDto pagoToSaveDto);
}
