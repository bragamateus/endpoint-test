package com.example.demo.rest;

import com.example.demo.model.entity.Terminal;
import com.example.demo.model.repository.TerminalRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/terminais")
public class TerminalController {

    private final TerminalRepository repository;

    @Autowired
    public TerminalController(TerminalRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{logic}")
    public Optional<Terminal> buscar(@PathVariable Integer logic){
        Optional<Terminal> terminal = repository.findByLogic(logic);
        if(terminal.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto Não encontrado");
        }
        return terminal;
    }

    @PostMapping( consumes = {"text/html"} , produces = "application/json")
    public ResponseEntity<String> salvar(@RequestBody String terminal) {
        var gson = new Gson();

        String[] infos = terminal.split(";");

        Terminal object = new Terminal(
        		
        		Integer.parseInt(infos[0]), 
        		infos[1], 
        		infos[2], 
        		Integer.parseInt(infos[3]), 
        		infos[4], 
        		Integer.parseInt(infos[5]), 
        		infos[6], 
        		Integer.parseInt(infos[7]), 
        		infos[8]);
        
        
        
        System.out.println(gson.toJson(object));

        return ResponseEntity.ok(terminal);
    }

    public void verificarSeExiste(Terminal terminal) {
        buscar(terminal.getLogic());
    }

    @PutMapping("{logic}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@PathVariable Integer logic, @RequestBody @Valid Terminal terminal, Integer id){

        terminal.setId(id);

        if (terminal.getId().equals(id)) {
            terminal.setLogic(logic);
            verificarSeExiste(terminal);

            repository.save(terminal);
        }


    }
}
