package com.pedro.study.controllers;

import com.pedro.study.dto.input.PenaIDTO;
import com.pedro.study.dto.output.PenaODTO;
import com.pedro.study.model.Pena;
import com.pedro.study.modelmapper.conversores.PenaConversor;
import com.pedro.study.services.PenaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/penas")
@AllArgsConstructor
public class PenaController {
    private PenaService penaService;
    private PenaConversor penaConversor;

    @PostMapping
    public ResponseEntity<PenaODTO> salvar(@RequestBody PenaIDTO dto) {
        return new ResponseEntity<>(penaConversor.modelToODTO(penaService.salvar(penaConversor.iDTOToModel(dto))), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<PenaODTO> editar(@PathVariable PenaIDTO dto, @PathVariable Integer id) {
        return new ResponseEntity<>(penaConversor.modelToODTO(penaService.salvar(penaConversor.iDTOToModel(dto))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenaODTO> retornarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(penaConversor.modelToODTO(this.penaService.buscarPorId(id)));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<PenaODTO>> retornarTodas(Pageable pageable) {
        Page<Pena> pageModel = penaService.buscarTodas(pageable);
        Page<PenaODTO> pageDTO = pageModel.map((model -> penaConversor.modelToODTO(model)));
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping()
    public ResponseEntity<List<PenaODTO>> buscarTodasSemPaginacao() {
        List<Pena> listaModel = penaService.buscarTodasSemPginacao();
        List<PenaODTO> listaDTO = listaModel.stream().map(model -> penaConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/pagination/search/{descricao}")
    public ResponseEntity<Page<PenaODTO>> retornarPordescricao(@PathVariable String descricao, Pageable pageable) {
        Page<Pena> pageModel = penaService.buscarPorDescricao(descricao, pageable);
        Page<PenaODTO> pageDTO = pageModel.map(model -> penaConversor.modelToODTO(model));
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/search/{descricao}")
    public ResponseEntity<List<PenaODTO>> retornarPordescricaoSemPaginacao(@PathVariable String descricao, Pageable pageable) {
        List<Pena> pageModel = penaService.buscarPorDescricaoSempaginacao(descricao, pageable);
        List<PenaODTO> pageDTO = pageModel.stream().map(model -> penaConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pageDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletar(@PathVariable Integer id) {
        penaService.excluir(id);
    }


}
