package com.nayani.testeAula.Service;

import com.nayani.testeAula.DTO.ClienteResponseDTO;
import com.nayani.testeAula.DTO.ClienteRequestDTO;
import com.nayani.testeAula.Entities.Cliente;
import com.nayani.testeAula.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service


public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    public List<ClienteResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscar(Long id){
        Cliente cliente = repository.findById(id)
                .orElseThrow(()->new RuntimeException("Cliente não encontrado"));
        return toResponseDTO(cliente);
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        Cliente salvo = repository.save(cliente);
        return toResponseDTO(salvo);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto){
        Cliente cliente = repository.findById(id)
        .orElseThrow(()->new RuntimeException("Cliente não encontrado"));
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        if (dto.getSenha()!= null && dto.getSenha().isBlank()){
            cliente.setSenha(dto.getSenha());
        }
        Cliente atualizado = repository.save(cliente);
        return toResponseDTO(atualizado);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente){
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        return dto;
    }



}
