package br.edu.famper.api_automoveis.service;

import br.edu.famper.api_automoveis.dto.VendedorDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Vendedor;
import br.edu.famper.api_automoveis.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendedorService {

    @Autowired
    private final VendedorRepository vendedorRepository;

    public List<VendedorDto> getAllVendedores() {
        return vendedorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public VendedorDto getVendedorById(Long id) throws ResourceNotFoundException {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrado com ID: " + id));
        return convertToDto(vendedor);
    }

    public Vendedor saveVendedor(VendedorDto vendedorDto) {
        Vendedor vendedor = convertToEntity(vendedorDto);
        return vendedorRepository.save(vendedor);
    }

    public VendedorDto editVendedor(Long id, VendedorDto vendedorDto) throws ResourceNotFoundException {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrado com ID: " + id));
        vendedor.setNome(vendedorDto.getNome());
        vendedor.setCnpj(vendedorDto.getCnpj());
        vendedor.setEmail(vendedorDto.getEmail());
        vendedor.setTelefone(vendedorDto.getTelefone());
        vendedor.setEndereco(vendedorDto.getEndereco());
        return convertToDto(vendedorRepository.save(vendedor));
    }

    public boolean deleteVendedor(Long id) throws ResourceNotFoundException {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrado com ID: " + id));
        vendedorRepository.delete(vendedor);
        return true;
    }

    private VendedorDto convertToDto(Vendedor vendedor) {
        return VendedorDto.builder()
                .id(vendedor.getId())
                .nome(vendedor.getNome())
                .cnpj(vendedor.getCnpj())
                .email(vendedor.getEmail())
                .telefone(vendedor.getTelefone())
                .endereco(vendedor.getEndereco())
                .build();
    }

    private Vendedor convertToEntity(VendedorDto vendedorDto) {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(vendedorDto.getId());
        vendedor.setNome(vendedorDto.getNome());
        vendedor.setCnpj(vendedorDto.getCnpj());
        vendedor.setEmail(vendedorDto.getEmail());
        vendedor.setTelefone(vendedorDto.getTelefone());
        vendedor.setEndereco(vendedorDto.getEndereco());
        return vendedor;
    }
}
