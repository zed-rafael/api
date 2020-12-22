package net.uni5.ztec.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import net.uni5.ztec.api.entities.Tarefa;
import net.uni5.ztec.api.responses.Response;
import net.uni5.ztec.api.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    private static final Logger log = LoggerFactory.getLogger(Tarefa.class);

    @Autowired
    private TarefaService tarefaService;

    @GetMapping(value = "")
    public ResponseEntity<Response<List<Tarefa>>> get() throws NoSuchAlgorithmException {
        Response<List<Tarefa>> response = new Response<List<Tarefa>>();
        List<Tarefa> lista = this.tarefaService.listar();
        response.setData(lista);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<Tarefa>> post(@Valid @RequestBody Tarefa tarefa, BindingResult result) throws NoSuchAlgorithmException {
        log.info("Atualizando funcionário: {}", tarefa.toString());
        Response<Tarefa> response = new Response<Tarefa>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Long idTarefa = this.tarefaService.inserirTarefa(tarefa);
        tarefa.setId(idTarefa);
        response.setData(tarefa);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<Tarefa>> update(@PathVariable("id") Long id, @Valid @RequestBody Tarefa tarefaParam, BindingResult result) throws NoSuchAlgorithmException {
        log.info("Atualizando tarefa: {}", tarefaParam.toString());
        Response<Tarefa> response = new Response<Tarefa>();
        tarefaParam.setId(id);
        validarTarefa(tarefaParam, result);

        //tarefaParam.setId(Optional.of(id));

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        //this.tarefaService.atualizarTarefa(tarefaParam);
        response.setData(tarefaParam);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
        log.info("Removendo lançamento: {}", id);
        Response<String> response = new Response<String>();
        //Optional<Tarefa> tarefa = this.tarefaService.buscarPorId(id);

//        if (!tarefa.isPresent() || Objects.isNull(tarefa)) {
//            log.info("Erro ao remover. A tarefa não existe!.", id);
//            response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + id);
//            return ResponseEntity.badRequest().body(response);
//        }
       // this.tarefaService.deletarTarefa(id);
        return ResponseEntity.ok(new Response<String>());
    }

    private void validarTarefa(Tarefa tarefaParam, BindingResult result) {
        //Verifica campos em outras tabelas e etc... quando há relacionamento com outras tabelas
//        if (tarefa.getId() == null) {
//            result.addError(new ObjectError("tarefa", "Tarefa não informado."));
//            return;
//        }

//        log.info("Validando funcionário id {}: ", tarefaParam.getId());
//        Optional<Tarefa> tarefa = this.tarefaService.buscarPorId(tarefaParam.getId());
//        if (!tarefa.isPresent()) {
//            result.addError(new ObjectError("Tarefa", "Tarefa não encontrada. ID inexistente."));
//        }
    }
}

