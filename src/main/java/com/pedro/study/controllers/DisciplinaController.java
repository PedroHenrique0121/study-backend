package com.pedro.study.controllers;

import com.pedro.study.config.permissoes.MyAdmin;
import com.pedro.study.config.permissoes.MyUser;
import com.pedro.study.dto.input.DisciplinaIDTO;
import com.pedro.study.dto.output.DisciplinaODTO;
import com.pedro.study.model.Disciplina;
import com.pedro.study.modelmapper.conversores.DisciplinaConversor;
import com.pedro.study.services.DisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/disciplinas")
@AllArgsConstructor
public class DisciplinaController {

    private DisciplinaService disciplinaService;
    private DisciplinaConversor disciplinaConversor;

    @MyAdmin
    @MyUser
    @PostMapping("/cadastrar")
    public ResponseEntity<DisciplinaODTO> salvar(@RequestBody @Valid DisciplinaIDTO dto) {
        return new ResponseEntity<>(
                disciplinaConversor.modelToODTO(disciplinaService.salvar(disciplinaConversor.iDTOToModel(dto))),
                HttpStatus.CREATED);
    }

    @MyAdmin
    @MyUser
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaODTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(disciplinaConversor.modelToODTO(disciplinaService.buscarPorId(id)));
    }

    @MyAdmin
    @MyUser
    @GetMapping("/pagination")
    public ResponseEntity<Page<DisciplinaODTO>> retornarTodas(Pageable pageable) {
        Page<Disciplina> pageModel = disciplinaService.retornarTodas(pageable);
        Page<DisciplinaODTO> pageODTO = pageModel.map(model -> disciplinaConversor.modelToODTO(model));
        return ResponseEntity.ok(pageODTO);
    }

    @MyAdmin
    @MyUser
    @GetMapping()
    public ResponseEntity<List<DisciplinaODTO>> retornarTodasSemPaginacao() {
        List<Disciplina> listaModel = disciplinaService.retornarTodasSemPaginacao();
        List<DisciplinaODTO> listaODTO = listaModel.stream().map(model -> disciplinaConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaODTO);
    }

    @MyAdmin
    @MyUser
    @GetMapping("/search/pagination/{descricao}")
    public ResponseEntity<Page<DisciplinaODTO>> buscarPorDescricao(@PathVariable String descricao, Pageable pageable) {
        Page<Disciplina> pageModel = disciplinaService.buscarPorDescricao(descricao, pageable);
        Page<DisciplinaODTO> page = pageModel.map(model -> disciplinaConversor.modelToODTO(model));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @MyAdmin
    @MyUser
    @GetMapping("/search/{descricao}")
    public ResponseEntity<List<DisciplinaODTO>> buscarPorDescricaoSemPaginacao(@PathVariable String descricao) {
        List<Disciplina> listaModel = disciplinaService.buscarPorDescricaoNotPagination(descricao);
        List<DisciplinaODTO> lista = listaModel
                .stream()
                .map(model -> disciplinaConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @MyAdmin
    @MyUser
    @PutMapping("/editar/{id}")
    public ResponseEntity<DisciplinaODTO> editar(@RequestBody @Valid DisciplinaIDTO dto, @PathVariable Integer id) {
        return new ResponseEntity<>(
                disciplinaConversor.modelToODTO(disciplinaService.editar(disciplinaConversor.iDTOToModel(dto), id)),
                HttpStatus.OK
        );
    }

    @MyAdmin
    @MyUser
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void excluir(@PathVariable Integer id) {
        this.disciplinaService.excluir(id);
    }


}
