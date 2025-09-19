package com.nayani.testeAula.Controller;

import com.nayani.testeAula.DTO.ClienteRequestDTO;
import com.nayani.testeAula.DTO.ClienteResponseDTO;
import com.nayani.testeAula.Entities.Cliente;
import com.nayani.testeAula.Repository.ClienteRepository;
import com.nayani.testeAula.Service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que a classe expõe uma API REST
@RequestMapping("/clientes") //Define o caminho base da API (rota base)

public class ClienteController {

    private final ClienteService service;

    /*Aqui ocorre a "injeção de dependência". O SpringBoot injeta a implementação
    concreta do ClienteRepository no constructor do CLienteController*/
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping // Define o método HTTP
    public List<ClienteResponseDTO> listar() {
        return service.listar();
    }

    //teste github

    @GetMapping("/{id}") //Define o método GET HTTP com parâmetro
    public ClienteResponseDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping // Define o método POST
    public ClienteResponseDTO salvar(@RequestBody ClienteRequestDTO dto){
        return service.salvar(dto);
    }

    @PutMapping("/{id}") //Define o método PUT HTTP com paramtro
    public ClienteResponseDTO atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO dto){
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")//Define o método DELETE HTTP com prametro
    public void deletetar(@PathVariable Long id){
        service.deletar(id);
    }
}
