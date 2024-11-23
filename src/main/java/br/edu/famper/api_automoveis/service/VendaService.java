package br.edu.famper.api_automoveis.service;

import br.edu.famper.api_automoveis.dto.VendaDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Carro;
import br.edu.famper.api_automoveis.model.Cliente;
import br.edu.famper.api_automoveis.model.Venda;
import br.edu.famper.api_automoveis.model.Vendedor;
import br.edu.famper.api_automoveis.repository.CarroRepository;
import br.edu.famper.api_automoveis.repository.ClienteRepository;
import br.edu.famper.api_automoveis.repository.VendaRepository;
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
public class VendaService {

    @Autowired
    private final VendaRepository vendaRepository;

    @Autowired
    private final CarroRepository carroRepository;

    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final VendedorRepository vendedorRepository;

    public List<VendaDto> getAllVendas() {
        return vendaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public VendaDto getVendaById(Long id) throws ResourceNotFoundException {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com ID: " + id));
        return convertToDto(venda);
    }

    public Venda saveVenda(VendaDto vendaDto) throws ResourceNotFoundException {
        Carro carro = carroRepository.findById(vendaDto.getCarroId())
                .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com ID: " + vendaDto.getCarroId()));
        Cliente cliente = clienteRepository.findById(vendaDto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + vendaDto.getClienteId()));
        Vendedor vendedor = vendedorRepository.findById(vendaDto.getVendedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrado com ID: " + vendaDto.getVendedorId()));

        Venda venda = convertToEntity(vendaDto);
        venda.setCarro(carro);
        venda.setCliente(cliente);
        venda.setVendedor(vendedor);

        return vendaRepository.save(venda);
    }

    public VendaDto editVenda(Long id, VendaDto vendaDto) throws ResourceNotFoundException {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com ID: " + id));
        venda.setDataVenda(vendaDto.getDataVenda());
        venda.setValorVenda(vendaDto.getValorVenda());

        Carro carro = carroRepository.findById(vendaDto.getCarroId())
                .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com ID: " + vendaDto.getCarroId()));
        Cliente cliente = clienteRepository.findById(vendaDto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + vendaDto.getClienteId()));
        Vendedor vendedor = vendedorRepository.findById(vendaDto.getVendedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrado com ID: " + vendaDto.getVendedorId()));

        venda.setCarro(carro);
        venda.setCliente(cliente);
        venda.setVendedor(vendedor);

        return convertToDto(vendaRepository.save(venda));
    }

    public boolean deleteVenda(Long id) throws ResourceNotFoundException {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com ID: " + id));
        vendaRepository.delete(venda);
        return true;
    }

    private VendaDto convertToDto(Venda venda) {
        return VendaDto.builder()
                .id(venda.getId())
                .carroId(venda.getCarro().getId())
                .clienteId(venda.getCliente().getId())
                .vendedorId(venda.getVendedor().getId())
                .dataVenda(venda.getDataVenda())
                .valorVenda(venda.getValorVenda())
                .build();
    }

    private Venda convertToEntity(VendaDto vendaDto) {
        Venda venda = new Venda();
        venda.setId(vendaDto.getId());
        venda.setDataVenda(vendaDto.getDataVenda());
        venda.setValorVenda(vendaDto.getValorVenda());
        return venda;
    }
}
