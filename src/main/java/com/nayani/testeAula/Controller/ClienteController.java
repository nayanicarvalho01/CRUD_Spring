package com.nayani.testeAula.Controller;

import com.nayani.testeAula.Entities.Cliente;
import com.nayani.testeAula.Repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que a classe expõe uma API REST
@RequestMapping("/clientes") //Define o caminho base da API (rota base)

public class ClienteController {

    private final ClienteRepository repository;

    /*Aqui ocorre a "injeção de dependência". O SpringBoot injeta a implementação
    concreta do ClienteRepository no constructor do CLienteController*/
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping // Define o método HTTP
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}") //Define o método GET HTTP com parâmetro
    public Cliente buscar(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @PostMapping // Define o método POST
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @PutMapping("/{id}") //Define o método PUT HTTP com paramtro
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente dados){
        Cliente cliente = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(dados.getNome());
        cliente.setEmail(dados.getEmail());
        return repository.save(cliente);
    }

    @DeleteMapping("/{id}")//Define o método DELETE HTTP com prametro
    public void deletetar(@PathVariable Long id){
        repository.deleteById(id);
    }
}
