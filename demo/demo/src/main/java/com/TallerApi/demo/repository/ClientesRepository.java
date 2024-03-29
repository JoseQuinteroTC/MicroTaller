package com.TallerApi.demo.repository;

import com.TallerApi.demo.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {

    //Optional<Cliente> findByEmail(String email);

    //List<Cliente> findByDireccion(String Direccion);

    //List<Cliente> findByInitName(String nombre);



}
