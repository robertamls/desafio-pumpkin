package com.pumpkin.vacinados.controller;

import com.pumpkin.vacinados.model.pessoa.Pessoa;
import com.pumpkin.vacinados.model.pessoa.PessoaService;
import com.pumpkin.vacinados.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/vacinados")
public class PessoaController {

    @Autowired
    private PessoaService service;


    @GetMapping
    public ResponseEntity<Iterable<Pessoa>> pessoas() {
        Iterable<Pessoa> pessoas = service.getPessoas();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Object> insertPessoa(@Valid @RequestBody Pessoa pessoa) {
        String responsePessoa;
        responsePessoa = service.findCpfOrEmailCadastrado(pessoa.getCpf(), pessoa.getEmail());

        if (!responsePessoa.equals("")) {
            return ResponseUtil.warningMessage(responsePessoa, HttpStatus.BAD_REQUEST);
        } else {
            Pessoa novosDados = service.persistDados(pessoa);
            responsePessoa = "Dados cadastrados.";
            return ResponseUtil.sucessMessage(responsePessoa, novosDados, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getPessoaById(@PathVariable Long id) {
        String responsePessoa;
        Optional<Pessoa> pessoa = service.findById(id);
        if (pessoa.isPresent()) {
            responsePessoa = "Dados encontrados.";
            Pessoa dadosencontrados = pessoa.get();
            return ResponseUtil.sucessMessage(responsePessoa, dadosencontrados, HttpStatus.OK);
        }
        responsePessoa = "Dados não encontrados.";
        return ResponseUtil.warningMessage(responsePessoa, HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updatePessoa(@RequestBody Pessoa pessoa, @PathVariable Long id) {
        String responsePessoa;
        Optional<Pessoa> dadosEncontrados = service.findById(id);
        if (dadosEncontrados.isPresent()) {
            Optional<Pessoa> verificacaoUnicaCPF = service.findPessoaByCpf(pessoa.getCpf());
            Optional<Pessoa> verificacaoUnicaEmail = service.findPessoaByEmail(pessoa.getEmail());
            if (!verificacaoUnicaCPF.isPresent() || !verificacaoUnicaEmail.isPresent()) {
                pessoa.setId(id);
                Pessoa dadosAlterados = service.persistDados(pessoa);
                responsePessoa = "Dados alterados.";
                return ResponseUtil.sucessMessage(responsePessoa, dadosAlterados, HttpStatus.OK);
            } else {
                if (!verificacaoUnicaCPF.get().getCpf().equals(pessoa.getCpf()) && !verificacaoUnicaCPF.get().getId().equals(id) ||
                        !verificacaoUnicaEmail.get().getEmail().equals(pessoa.getEmail()) && !verificacaoUnicaEmail.get().getId().equals(id)) {
                    pessoa.setId(id);
                    Pessoa dadosAlterados = service.persistDados(pessoa);
                    responsePessoa = "Dados alterados.";
                    return ResponseUtil.sucessMessage(responsePessoa, dadosAlterados, HttpStatus.OK);
                } else {
                    responsePessoa = "Você está tentando inserir um dado já cadastrado.";
                    return ResponseUtil.warningMessage(responsePessoa, HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            responsePessoa = "Dados não encontrados.";
            return ResponseUtil.warningMessage(responsePessoa, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable Long id) {
        String responsePessoa;
        Optional<Pessoa> pessoa = service.findById(id);
        if (pessoa.isPresent()) {
            Pessoa dadosExcluidos = pessoa.get();
            service.deleteById(pessoa.get().getId());
            responsePessoa = "Dados excluídos.";
            return ResponseUtil.sucessMessage(responsePessoa, dadosExcluidos, HttpStatus.OK);
        } else {
            responsePessoa = "Dados não encontrados.";
            return ResponseUtil.warningMessage(responsePessoa, HttpStatus.NO_CONTENT);
        }
    }

}
