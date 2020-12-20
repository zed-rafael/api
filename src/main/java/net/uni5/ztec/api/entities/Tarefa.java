package net.uni5.ztec.api.entities;

import java.time.LocalDateTime;

public class Tarefa {
    private Long id;
    private Integer usuarioId;
    private String titulo;
    private String descricao;
    private LocalDateTime dataTarefa;

    public Tarefa() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataTarefa() {
        return dataTarefa;
    }

    public void setDataTarefa(LocalDateTime dataTarefa) {
        this.dataTarefa = dataTarefa;
    }

    @Override
    public String toString() {
        return "Tarefa [Id=" + id + ", Título=" + titulo + ", Descrição=" + descricao + "]";
    }
}
