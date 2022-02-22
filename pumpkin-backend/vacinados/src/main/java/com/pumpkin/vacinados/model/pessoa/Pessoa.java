package com.pumpkin.vacinados.model.pessoa;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    @Size(min = 15, max = 100, message = "O nome deve conter no mínimo 15 caracteres!")
    @NotBlank(message = "Nome obrigatório!")
    @NotNull(message = "O nome não pode ser nulo!")
    private String nome;

    @Column(name = "dataNascimento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Column(name = "cpf", unique = true, nullable = false)
    @NotBlank(message = "CPF obrigatório!")
    @CPF
    private String cpf;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message = "E-mail obrigatório!")
    @Email
    private String email;

    //<editor-fold desc="Getters e Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //</editor-fold>

}
