package com.TallerApi.demo.repository;

import com.TallerApi.demo.dto.pagos.PagosDto;
import com.TallerApi.demo.models.Pagos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static com.TallerApi.demo.models.enums.MetodoDePago.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PagosRepositoryTest {


    PagosRepository pagosRepository;


    @Autowired
    public PagosRepositoryTest(PagosRepository pagosRepository) {

        this.pagosRepository = pagosRepository;
    }

    @BeforeEach
    void setUp() {
        pagosRepository.deleteAll();
    }


    //Test crear
    @Test
    @DisplayName("JUnit test para guardad un pago")
    public void givenStudentObject_whenSavingStudent_thenReturnStudentObject() {
        pagosRepository.deleteAll();
        //given - precondition or setup
        Pagos pagos = Pagos.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        // when - action or the behavior we are going to test
        Pagos savedPagos = pagosRepository.save(pagos);

        // then - verify the output
        assertThat(savedPagos).isNotNull();
        assertThat(savedPagos.getId()).isGreaterThan(0);
        assertThat(savedPagos.getMetodoPago()).isEqualTo(PSE);
    }

    //Test updatePago
    @Test
    @DisplayName("JUnit test para actializar un pago")
    public void givenPagosObject_whenUpdatePagos_thenReturnPagosObject() {
        pagosRepository.deleteAll();
        //given - precondition or setup
        Pagos pagos = Pagos.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        this.pagosRepository.save(pagos);
        // when - action or the behavior we are going to test
        Pagos savedPagos = this.pagosRepository.findById(pagos.getId()).
                orElseThrow(() -> new RuntimeException("No existe el cliente"));
        savedPagos.setTotalPago(23000);
        savedPagos.setMetodoPago(NEQUI);
        this.pagosRepository.save(savedPagos);

        // then - verify the output
        assertThat(savedPagos).isNotNull();
        assertThat(savedPagos.getTotalPago()).isEqualTo(23000);
        assertThat(savedPagos.getMetodoPago()).isEqualTo(NEQUI);
    }

    //Test findById
    @Test
    @DisplayName("JUnit test para buscar un pago por el Id")
    public void givenPagoId_whenFindPagoById_thenReturnPagoObject(){
        pagosRepository.deleteAll();
        //given - precondition or setup
        Pagos pagos = Pagos.builder()
                .totalPago(12500)
                .metodoPago(PSE)
                .build();
        // when - action or the behavior we are going to test
        pagosRepository.save(pagos);
        Optional<Pagos> savedPagos = pagosRepository.findById(pagos.getId());

        // then - verify the output
        assertThat(savedPagos).isPresent().hasValueSatisfying(PagosDB->{
            assertThat(PagosDB.getId()).isEqualTo(2l);
            assertThat(PagosDB.getMetodoPago()).isEqualTo(PSE);
            assertThat(PagosDB.getTotalPago()).isEqualTo(12500);

        });

    }




}
