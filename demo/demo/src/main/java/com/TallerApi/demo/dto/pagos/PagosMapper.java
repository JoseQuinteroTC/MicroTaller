package com.TallerApi.demo.dto.pagos;

import com.TallerApi.demo.dto.detallesEnvios.DetallesEnviosDto;
import com.TallerApi.demo.dto.detallesEnvios.DetallesEnviosToSaveDto;
import com.TallerApi.demo.dto.itemsPedidos.ItemsPedidosDto;
import com.TallerApi.demo.dto.itemsPedidos.ItemsPedidosToSaveDto;
import com.TallerApi.demo.dto.pedidos.PedidosMapper;
import com.TallerApi.demo.models.DetallesEnvios;
import com.TallerApi.demo.models.ItemsPedidos;
import com.TallerApi.demo.models.Pagos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PedidosMapper.class)
public interface PagosMapper {

    @Mapping(source = "pedidos.id", target = "idPedido")
    PagosDto pagosToPagosDto (Pagos pagos);

   // Pagos pagosDtoToPagos(PagosDto pagosDto);

    @Mapping(target = "fechaPago", expression = "java(java.time.LocalDateTime.now())")
    Pagos pagosToSaveDtoToPagos(PagosToSaveDto pagosToSaveDto);
}
