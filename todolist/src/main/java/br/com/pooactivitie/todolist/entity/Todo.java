package br.com.pooactivitie.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    private String dataEntrega;
    
    @Column(name = "data_criacao")
    private String dataCriacao;
    
    private boolean importancia;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    // Construtor padrão OBRIGATÓRIO para JPA
    public Todo() {
        this.dataCriacao = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.status = Status.A_FAZER;
    }

    public Todo(String nome, String descricao, String dataEntrega, boolean importancia, Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.importancia = importancia;
        this.status = status != null ? status : Status.A_FAZER;
        this.dataCriacao = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // Getters e Setters
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDataEntrega() {
        return dataEntrega;
    }
    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    public String getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public boolean isImportancia() {
        return importancia;
    }
    public void setImportancia(boolean importancia) {
        this.importancia = importancia;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}