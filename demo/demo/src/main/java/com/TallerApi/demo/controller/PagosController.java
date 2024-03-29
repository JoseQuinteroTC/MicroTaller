package com.TallerApi.demo.controller;


import com.TallerApi.demo.dto.pagos.PagosDto;
import com.TallerApi.demo.dto.pagos.PagosToSaveDto;
import com.TallerApi.demo.dto.pedidos.PedidosDto;
import com.TallerApi.demo.dto.pedidos.PedidosToSaveDto;
import com.TallerApi.demo.models.enums.MetodoDePago;
import com.TallerApi.demo.service.pagos.PagosService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringTokenizer;

@RestController

public class PagosController {

    private final PagosService pagosService;


    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @PostMapping("/payments")
    PagosDto crearPago (@RequestBody PagosToSaveDto pagosToSaveDto) {
        return this.pagosService.crearPago(pagosToSaveDto);
    }

    @PutMapping("/payments/{id}")
    PagosDto actualizarCliente(@PathVariable Long id, @RequestBody PagosToSaveDto pagosToSaveDto) {
        return this.pagosService.actualizarPago(id, pagosToSaveDto);
    }

    @DeleteMapping("/payments/{id}")
    void eliminarPago(@PathVariable Long id){
        this.pagosService.eliminarPago(id);
    }

    @GetMapping("/payments")
    List<PagosDto> mostrarTodos() {
        return this.pagosService.mostrarTodos();
    }

    @GetMapping ("/payment/date-range")
    List<PagosDto> mostrarPagosEntreFechas(@RequestParam String initDate, @RequestParam String endDate) {
        return this.pagosService.mostrarPagosEntreFechas(initDate, endDate);
    }

    @GetMapping ("/payment/order/{orderId}")
    List<PagosDto> mostrarPorIdOrdenAndMetodoPago(@PathVariable Long orderId, @RequestParam MetodoDePago metodoDePago) {
        return this.pagosService.mostrarPorIdOrdenAndMetodoPago(orderId, metodoDePago);
    }



}
