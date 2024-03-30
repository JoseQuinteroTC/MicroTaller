package com.TallerApi.demo.controller;


import com.TallerApi.demo.dto.pago.PagoDto;
import com.TallerApi.demo.dto.pago.PagoToSaveDto;
import com.TallerApi.demo.model.enums.MetodoDePago;
import com.TallerApi.demo.service.pago.PagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PagoController {

    private final PagoService pagoService;


    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping("/payments")
    PagoDto crearPago (@RequestBody PagoToSaveDto pagoToSaveDto) {
        return this.pagoService.crearPago(pagoToSaveDto);
    }

    @PutMapping("/payments/{id}")
    PagoDto actualizarCliente(@PathVariable Long id, @RequestBody PagoToSaveDto pagoToSaveDto) {
        return this.pagoService.actualizarPago(id, pagoToSaveDto);
    }

    @DeleteMapping("/payments/{id}")
    void eliminarPago(@PathVariable Long id){
        this.pagoService.eliminarPago(id);
    }

    @GetMapping("/payments")
    List<PagoDto> mostrarTodos() {
        return this.pagoService.mostrarTodos();
    }

    @GetMapping ("/payment/date-range")
    List<PagoDto> mostrarPagosEntreFechas(@RequestParam String initDate, @RequestParam String endDate) {
        return this.pagoService.mostrarPagosEntreFechas(initDate, endDate);
    }

    @GetMapping ("/payment/order/{orderId}")
    List<PagoDto> mostrarPorIdOrdenAndMetodoPago(@PathVariable Long orderId, @RequestParam MetodoDePago metodoDePago) {
        return this.pagoService.mostrarPorIdOrdenAndMetodoPago(orderId, metodoDePago);
    }



}
