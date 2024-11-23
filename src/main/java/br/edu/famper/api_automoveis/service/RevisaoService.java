package br.edu.famper.api_automoveis.service;

import br.edu.famper.api_automoveis.dto.RevisaoDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Carro;
import br.edu.famper.api_automoveis.model.Revisao;
import br.edu.famper.api_automoveis.repository.CarroRepository;
import br.edu.famper.api_automoveis.repository.RevisaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RevisaoService {

    @Autowired
    private final RevisaoRepository revisaoRepository;

    @Autowired
    private final CarroRepository carroRepository;

    public List<RevisaoDto> getAllRevisoes() {
        return revisaoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RevisaoDto getRevisaoById(Long id) throws ResourceNotFoundException {
        Revisao revisao = revisaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Revisão não encontrada com ID: " + id));
        return convertToDto(revisao);
    }

    public Revisao saveRevisao(RevisaoDto revisaoDto) throws ResourceNotFoundException {
        Carro carro = carroRepository.findById(revisaoDto.getCarroId())
                .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com ID: " + revisaoDto.getCarroId()));
        Revisao revisao = convertToEntity(revisaoDto);
        revisao.setCarro(carro);
        return revisaoRepository.save(revisao);
    }

    public RevisaoDto editRevisao(Long id, RevisaoDto revisaoDto) throws ResourceNotFoundException {
        Revisao revisao = revisaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Revisão não encontrada com ID: " + id));
        revisao.setDescricao(revisaoDto.getDescricao());
        revisao.setDataRevisao(revisaoDto.getDataRevisao());
        return convertToDto(revisaoRepository.save(revisao));
    }

    public boolean deleteRevisao(Long id) throws ResourceNotFoundException {
        Revisao revisao = revisaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Revisão não encontrada com ID: " + id));
        revisaoRepository.delete(revisao);
        return true;
    }

    private RevisaoDto convertToDto(Revisao revisao) {
        return RevisaoDto.builder()
                .id(revisao.getId())
                .descricao(revisao.getDescricao())
                .dataRevisao(revisao.getDataRevisao())
                .carroId(revisao.getCarro().getId())
                .build();
    }

    private Revisao convertToEntity(RevisaoDto revisaoDto) {
        Revisao revisao = new Revisao();
        revisao.setId(revisaoDto.getId());
        revisao.setDescricao(revisaoDto.getDescricao());
        revisao.setDataRevisao(revisaoDto.getDataRevisao());
        return revisao;
    }
}
