package br.edu.famper.api_automoveis.service;

import br.edu.famper.api_automoveis.dto.CarroDto;
import br.edu.famper.api_automoveis.dto.RevisaoDto;
import br.edu.famper.api_automoveis.exception.ResourceNotFoundException;
import br.edu.famper.api_automoveis.model.Carro;
import br.edu.famper.api_automoveis.model.Revisao;
import br.edu.famper.api_automoveis.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarroService {

    @Autowired
    private final CarroRepository carroRepository;

    public List<CarroDto> getAllCarros() {
        return carroRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CarroDto getCarroById(Long id) throws ResourceNotFoundException {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com ID: " + id));
        return convertToDto(carro);
    }

    public Carro saveCarro(CarroDto carroDto) {
        Carro carro = convertToEntity(carroDto);
        return carroRepository.save(carro);
    }

    public CarroDto editCarro(Long id, CarroDto carroDto) throws ResourceNotFoundException {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com ID: " + id));
        carro.setModelo(carroDto.getModelo());
        carro.setMarca(carroDto.getMarca());
        carro.setAno(carroDto.getAno());
        return convertToDto(carroRepository.save(carro));
    }

    public boolean deleteCarro(Long id) throws ResourceNotFoundException {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado com ID: " + id));
        carroRepository.delete(carro);
        return true;
    }

    private CarroDto convertToDto(Carro carro) {
        return CarroDto.builder()
                .id(carro.getId())
                .modelo(carro.getModelo())
                .marca(carro.getMarca())
                .ano(carro.getAno())
                .revisoes(carro.getRevisoes().stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    private Carro convertToEntity(CarroDto carroDto) {
        Carro carro = new Carro();
        carro.setId(carroDto.getId());
        carro.setModelo(carroDto.getModelo());
        carro.setMarca(carroDto.getMarca());
        carro.setAno(carroDto.getAno());
        return carro;
    }

    private RevisaoDto convertToDto(Revisao revisao) {
        if (revisao == null) return null;

        return RevisaoDto.builder()
                .id(revisao.getId())
                .descricao(revisao.getDescricao())
                .dataRevisao(revisao.getDataRevisao())
                .carroId(revisao.getCarro().getId())
                .build();
    }

    private Revisao convertToEntity(RevisaoDto revisaoDto) {
        if (revisaoDto == null) return null;

        Revisao revisao = new Revisao();
        revisao.setId(revisaoDto.getId());
        revisao.setDescricao(revisaoDto.getDescricao());
        revisao.setDataRevisao(revisaoDto.getDataRevisao());
        return revisao;
    }
}
