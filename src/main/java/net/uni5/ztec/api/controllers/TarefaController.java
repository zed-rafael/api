package net.uni5.ztec.api.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {
	
	@GetMapping(value = "")
    public String get() throws NoSuchAlgorithmException {
		
        return "Opa o!";
    }

}


