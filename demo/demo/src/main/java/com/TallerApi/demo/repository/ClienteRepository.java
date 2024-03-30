package com.TallerApi.demo.repository;

import com.TallerApi.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByDireccion(String Direccion);

    List<Cliente> findByNombreStartingWithIgnoreCase(String nombre);



}
