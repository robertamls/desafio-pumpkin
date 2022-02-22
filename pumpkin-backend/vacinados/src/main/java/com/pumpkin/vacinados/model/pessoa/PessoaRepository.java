package com.pumpkin.vacinados.model.pessoa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

    @Query(value = "SELECT * FROM Pessoa WHERE cpf LIKE ?1", nativeQuery = true)
    Optional<Pessoa> findPessoaByCpf(String cpf);

    @Query(value = "SELECT * FROM Pessoa WHERE email LIKE ?1", nativeQuery = true)
    Optional<Pessoa> findPessoaByEmail(String email);
}
