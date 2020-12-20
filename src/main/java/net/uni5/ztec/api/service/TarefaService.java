package net.uni5.ztec.api.service;

import java.util.List;
import java.util.Optional;

import net.uni5.ztec.api.entities.Tarefa;

public interface TarefaService {

    public List<Tarefa> listar();

    public Long inserirTarefa(Tarefa tarefa);

    public Tarefa atualizarTarefa(Tarefa tarefa);

    public int deletarTarefa(Long id);

    public Optional<Tarefa> buscarPorId(Long id);
	
}
