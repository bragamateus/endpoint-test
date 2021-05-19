package com.example.demo.rest;

import com.example.demo.model.entity.Terminal;
import com.example.demo.model.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/terminais")
public class TerminalController {

    private final TerminalRepository repository;

    @Autowired
    public TerminalController(TerminalRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{logic}")
    public Terminal buscar(@PathVariable Integer logic){
        return repository.findByLogic(logic)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto Não encontrado"));
    }

    @PostMapping( consumes = "text/html" , produces = "application/json")
    public Terminal salvar(@RequestBody Terminal terminal){
        return repository.save(terminal);
    }

    @PutMapping("{logic}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@PathVariable Integer logic, @RequestBody @Valid Terminal objetoAtualizado){

        repository
                .findByLogic(logic)
                .map(objeto -> {
                    objeto.setLogic(objeto.getLogic());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto Não encontrado"));

    }
}
