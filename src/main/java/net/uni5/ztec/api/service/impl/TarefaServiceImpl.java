package net.uni5.ztec.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.uni5.ztec.api.dao.TarefaDAO;
import net.uni5.ztec.api.entities.Tarefa;
import net.uni5.ztec.api.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	 @Autowired
	    private TarefaDAO tarefaDAO;

	    public List<Tarefa> listar() {
	        return tarefaDAO.listar();
	    }

	    public Long inserirTarefa(Tarefa tarefa) {
	        return tarefaDAO.inserirTarefa(tarefa);
	    }

	    public Tarefa atualizarTarefa(Tarefa tarefa) {
	        return tarefaDAO.atualizarTarefa(tarefa);
	    }

	    public int deletarTarefa(Long id) {
	        return tarefaDAO.deletarTarefa(id);
	    }

	    public Optional<Tarefa> buscarPorId(Long id) {
	        return Optional.ofNullable(tarefaDAO.buscarPorId(id));
	    }

}
