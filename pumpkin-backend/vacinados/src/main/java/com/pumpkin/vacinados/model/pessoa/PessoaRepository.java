package com.pumpkin.vacinados.model.pessoa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

    /**
     * Busca por cpf existente no banco
     * @param cpf
     * @return Objeto seja pessoa ou vazio
     */
    @Query(value = "SELECT * FROM Pessoa WHERE cpf LIKE ?1", nativeQuery = true)
    Optional<Pessoa> findPessoaByCpf(String cpf);

    /**
     * Busca por e-mail existente no banco
     * @param email
     * @return Objeto seja pessoa ou vazio
     */
    @Query(value = "SELECT * FROM Pessoa WHERE email LIKE ?1", nativeQuery = true)
    Optional<Pessoa> findPessoaByEmail(String email);
}
