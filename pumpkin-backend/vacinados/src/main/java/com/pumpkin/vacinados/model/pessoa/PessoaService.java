package com.pumpkin.vacinados.model.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    /**
     * Método que persiste os dados no banco.
     * @param pessoa
     * @return Objeto pessoa
     */
    public Pessoa persistDados(Pessoa pessoa) {
            repository.save(pessoa);
        return pessoa;
    }

    /**
     * Método que realiza uma busca por ID no banco.
     * @param id
     * @return Objeto seja pessoa ou vazio
     */
    public Optional<Pessoa> findById(Long id){
       return repository.findById(id);
    }

    /**
     * Método que realiza delete por ID.
     * @param id
     */
    public void deleteById(Long id) {
       repository.deleteById(id);
    }

    /**
     * Método que verifica a existencia de um dado por ID no banco.
     * @param id
     * @return Boolean
     */
    public boolean existsById(Long id){
        return repository.existsById(id);
    }

    /**
     * Método que busca todas as pessoas cadastradas.
     * @return Coleção de pessoa
     */
    public Iterable<Pessoa> getPessoas(){
        return repository.findAll();
    }

    /**
     * Método que realiza buscas no banco por cpf e e-mail existentes.
     * @param cpf
     * @param email
     * @return Uma String contendo uma mensagem informativa.
     */
    public String findCpfOrEmailCadastrado(String cpf, String email){
        String mensagem = "";
        Optional<Pessoa> cpfPessoa = repository.findPessoaByCpf(cpf);
        Optional<Pessoa> emailPessoa = repository.findPessoaByEmail(email);

        if(!cpfPessoa.isEmpty() || !emailPessoa.isEmpty()) {
            if (cpfPessoa.isPresent() || emailPessoa.isPresent()) {
                if (cpfPessoa.isPresent() && emailPessoa.isPresent()) {
                    mensagem = "CPF e E-mail já cadastrados no sistema.";
                } else if (cpfPessoa.isEmpty()) {
                    mensagem = "E-mail já cadastrado no sistema.";
                } else {
                    mensagem = "CPF já cadastrado no sistema.";
                }
            }
        }
        return mensagem;
    }
}
