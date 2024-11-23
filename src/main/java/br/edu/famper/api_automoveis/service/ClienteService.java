package br.edu.famper.api_automoveis.service;

import br.edu.famper.api_automoveis.dto.ClienteDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Cliente;
import br.edu.famper.api_automoveis.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public List<ClienteDto> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ClienteDto getClienteById(Long id) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        return convertToDto(cliente);
    }

    public Cliente saveCliente(ClienteDto clienteDto) {
        Cliente cliente = convertToEntity(clienteDto);
        return clienteRepository.save(cliente);
    }

    public ClienteDto editCliente(Long id, ClienteDto clienteDto) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setEndereco(clienteDto.getEndereco());
        return convertToDto(clienteRepository.save(cliente));
    }

    public boolean deleteCliente(Long id) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        clienteRepository.delete(cliente);
        return true;
    }

    private ClienteDto convertToDto(Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .endereco(cliente.getEndereco())
                .build();
    }

    private Cliente convertToEntity(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setEndereco(clienteDto.getEndereco());
        return cliente;
    }
}
