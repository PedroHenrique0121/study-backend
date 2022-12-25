package com.pedro.study.controllers;


import com.pedro.study.dto.input.AssuntoIDTO;
import com.pedro.study.dto.output.AssuntoODTO;
import com.pedro.study.model.Assunto;
import com.pedro.study.modelmapper.conversores.AssuntoConversor;
import com.pedro.study.services.AssuntoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@RestController
@RequestMapping("/api/assuntos")
public class AssuntoController {

    private AssuntoService assusntoService;
    private AssuntoConversor assuntoConversor;

    @PostMapping("/cadastrar")
    public ResponseEntity<AssuntoODTO> salvar(@RequestBody @Valid AssuntoIDTO dto) {
        return new ResponseEntity<>(
                assuntoConversor.modelToODTO(assusntoService.salvar(assuntoConversor.iDTOToModel(dto))),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssuntoODTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assuntoConversor.modelToODTO(assusntoService.buscarPorId(id)));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<AssuntoODTO>> retornarTodos(Pageable pageable) {
        Page<Assunto> pageModel = assusntoService.retornarTodos(pageable);
        Page<AssuntoODTO> pageODTO = pageModel.map(model -> assuntoConversor.modelToODTO(model));
        return ResponseEntity.ok(pageODTO);
    }

    @GetMapping()
    public ResponseEntity<List<AssuntoODTO>> retornarTodosSemPaginacao() {
        List<Assunto> listaModel = assusntoService.retornarTodos();
        List<AssuntoODTO> pageODTO = listaModel.stream().map(model -> assuntoConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pageODTO);
    }

    @GetMapping("/search/pagination/{descricao}")
    public ResponseEntity<Page<AssuntoODTO>> buscarPorDescricao(@PathVariable String descricao, Pageable pageable) {
        Page<Assunto> pageModel = assusntoService.buscarPorDescricao(descricao, pageable);
        Page<AssuntoODTO> pageODTO = pageModel.map(model -> assuntoConversor.modelToODTO(model));
        return ResponseEntity.ok(pageODTO);
    }

    @GetMapping("/search/{descricao}")
    public ResponseEntity<List<AssuntoODTO>> buscarPorDescricaoSemPaginacao(@PathVariable String descricao) {
        List<Assunto> listaModel = assusntoService.buscarPorDescricaoSemPaginacao(descricao);
        List<AssuntoODTO> pageODTO = listaModel.stream().map(model -> assuntoConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pageODTO);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<AssuntoODTO> editar(@RequestBody @Valid AssuntoIDTO dto, @PathVariable Integer id) {
        return new ResponseEntity<>(
                assuntoConversor.modelToODTO(assusntoService.editar(assuntoConversor.iDTOToModel(dto), id)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{descricao}/disciplina/{id}")
    public ResponseEntity<List<AssuntoODTO>>  findByDisciplinaId(@PathVariable Integer id,@PathVariable String descricao){
        List<AssuntoODTO> listaODTO=  this.assusntoService.findByDisciplinaId(id,descricao)
                .stream().map(model-> this.assuntoConversor.modelToODTO(model)).collect(Collectors.toList());
                return ResponseEntity.ok(listaODTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletar(@PathVariable Integer id) {
        assusntoService.excluir(id);
    }

}
