package com.TallerApi.demo.repository;

import com.TallerApi.demo.model.Pago;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;


import static com.TallerApi.demo.model.enums.MetodoDePago.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PagoRepositoryTest {


    PagoRepository pagoRepository;


    @Autowired
    public PagoRepositoryTest(PagoRepository pagoRepository) {

        this.pagoRepository = pagoRepository;
    }

    @BeforeEach
    void setUp() {
        pagoRepository.deleteAll();
    }


    //Test crear
    @Test
    @DisplayName("JUnit test para guardad un pago")
    public void givenStudentObject_whenSavingStudent_thenReturnStudentObject() {

        //given - precondition or setup
        Pago pago = Pago.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        // when - action or the behavior we are going to test
        Pago savedPago = pagoRepository.save(pago);

        // then - verify the output
        assertThat(savedPago).isNotNull();
        assertThat(savedPago.getId()).isGreaterThan(0);
        assertThat(savedPago.getMetodoPago()).isEqualTo(PSE);
        assertThat(savedPago.getTotalPago()).isEqualTo(12500);
    }

    //Test updatePago
    @Test
    @DisplayName("JUnit test para actializar un pago")
    public void givenPagosObject_whenUpdatePagos_thenReturnPagosObject() {

        //given - precondition or setup
        Pago pago = Pago.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        this.pagoRepository.save(pago);
        // when - action or the behavior we are going to test
        Pago savedPago = this.pagoRepository.findById(pago.getId()).
                orElseThrow(() -> new RuntimeException("No existe el pago"));
        savedPago.setTotalPago(23000);
        savedPago.setMetodoPago(NEQUI);
        this.pagoRepository.save(savedPago);

        // then - verify the output
        assertThat(savedPago).isNotNull();
        assertThat(savedPago.getTotalPago()).isEqualTo(23000);
        assertThat(savedPago.getMetodoPago()).isEqualTo(NEQUI);
    }

    //Test findById
    @Test
    @DisplayName("JUnit test para buscar un pago por el Id")
    public void givenPagoId_whenFindPagoById_thenReturnPagoObject(){

        //given - precondition or setup
        Pago pago = Pago.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        // when - action or the behavior we are going to test
        pagoRepository.save(pago);
        Optional<Pago> savedPagos = pagoRepository.findById(pago.getId());

        // then - verify the output
        assertThat(savedPagos).isPresent().hasValueSatisfying(PagosDB->{
            assertThat(PagosDB.getId()).isEqualTo(pago.getId());
            assertThat(PagosDB.getMetodoPago()).isEqualTo(PSE);
            assertThat(PagosDB.getTotalPago()).isEqualTo(12500);

        });

    }
    //Test delete
    @Test
    @DisplayName("JUnit test para mostrar todos un pago por el Id")
    public void givenPagoId_whenMostarTodo_thenReturnPagoObject() {
        //given - precondition or setup
        Pago pago1 = Pago.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        pagoRepository.save(pago1);
        Pago pago2 = Pago.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        pagoRepository.save(pago2);
        // when - action or the behavior we are going to test

        List<Pago> savedPagos = pagoRepository.findAll();

        assertThat(savedPagos).isNotNull();
        assertEquals(2, savedPagos.size());

    }

    @Test
    public void givenPagoId_whenMostarEntre2Fechas_thenReturnPagoObject() {
        //given - precondition or setup
        Pago pago1 = Pago.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        pagoRepository.save(pago1);
        Pago pago2 = Pago.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        pagoRepository.save(pago2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // when - action or the behavior we are going to test
        List<Pago> savedPagos = pagoRepository.findByFechaPagoBetween(pago2.getFechaPago() ,
                                                                        pago1.getFechaPago());


        assertEquals(2, savedPagos.size());
    }
    @Test
    public void givenPagoId_whenMostrarPorIdOrdenAndMetodoPago_thenReturnPagoObject() {
        Pago pago1 = Pago.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        pagoRepository.save(pago1);
        Pago pago2 = Pago.builder()
                .totalPago(12500)
                .metodoPago(NEQUI)
                .build();
        pagoRepository.save(pago2);
        // when - action or the behavior we are going to test
        List<Pago> savedPagos = pagoRepository.findAll();

        assertThat(savedPagos).isNotNull();
        assertThat(savedPagos.get(1).getTotalPago()).isEqualTo(12500);
        assertThat(savedPagos.get(1).getMetodoPago()).isEqualTo(PSE);
        assertThat(savedPagos.get(2).getTotalPago()).isEqualTo(12500);
        assertThat(savedPagos.get(1).getMetodoPago()).isEqualTo(NEQUI);
        assertEquals(2, savedPagos.size());

    }

}
