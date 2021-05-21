package com.example.demo.rest;

import com.example.demo.model.entity.Terminal;
import com.example.demo.model.repository.TerminalRepository;
import com.google.gson.GsonBuilder;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/terminais")
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
    public ResponseEntity<String> salvar(@Valid @RequestBody String entrada) {
        var gson = new GsonBuilder().setPrettyPrinting().create();

        String[] infos = entrada.split(";");

        var terminal = new Terminal(
        		
        		Integer.parseInt(infos[0]), 
        		infos[1], 
        		infos[2], 
        		Integer.parseInt(infos[3]), 
        		infos[4], 
        		Integer.parseInt(infos[5]), 
        		infos[6], 
        		Integer.parseInt(infos[7]),
        		Integer.parseInt(infos[8]),
        		infos[9]);
        
        
        repository.save(terminal);
        
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{logic}").buildAndExpand(terminal.getLogic()).toUri();

        return ResponseEntity.created(uri).body(gson.toJson(terminal));
    }


    @PutMapping("{logic}")
    public ResponseEntity<Terminal> editar(@PathVariable Integer logic, @RequestBody @Valid Terminal terminal){

        if (!repository.existsTerminalByLogic(logic)){
                return ResponseEntity.notFound().build();
        }

        terminal.setLogic(logic);

        terminal = repository.save(terminal);
            return ResponseEntity.ok(terminal);


    }
}
