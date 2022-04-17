package br.com.hrworker.resources;

import br.com.hrworker.entities.Worker;
import br.com.hrworker.repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping (value = "/v1/workers")
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class WorkerResource {

    private final WorkerRepository repository;

    @Value(value = "${test.config}")
    private String configuration;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll () {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Worker> findById (@PathVariable Long id) {
        return ResponseEntity.ok(this.repository.findById(id).get());
    }

    @GetMapping(value = "/configuration")
    public ResponseEntity<Void> configuration () {
        System.out.println(configuration);
        return ResponseEntity.noContent().build();
    }
}