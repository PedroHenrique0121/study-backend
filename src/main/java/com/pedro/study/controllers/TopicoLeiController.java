package com.pedro.study.controllers;

import com.pedro.study.config.permissoes.MyAdmin;
import com.pedro.study.config.permissoes.MyUser;
import com.pedro.study.dto.input.TopicoLeiIDTO;
import com.pedro.study.dto.output.TopicoLeiODTO;
import com.pedro.study.model.TopicoLei;
import com.pedro.study.modelmapper.conversores.TopicoLeiConversor;
import com.pedro.study.services.TopicoLeiService;
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
@RequestMapping("/api/topicosLeis")
@AllArgsConstructor
public class TopicoLeiController {

    private TopicoLeiService topicoLeiService;
    private TopicoLeiConversor topicoLeiConversor;

    @MyAdmin
    @MyUser
    @PostMapping("/cadastrar")
    public ResponseEntity<TopicoLeiODTO> salvar(@RequestBody @Valid TopicoLeiIDTO dto) {
        return new ResponseEntity<>(this.topicoLeiConversor.modelToODTO(this.topicoLeiService.salvar(this.topicoLeiConversor.iDTOToModel(dto))), HttpStatus.CREATED);
    }

    @MyAdmin
    @MyUser
    @PutMapping("/{id}")
    public ResponseEntity<TopicoLeiODTO> editar(@PathVariable Integer id, @RequestBody TopicoLeiIDTO dto) {
        return new ResponseEntity<>(this.topicoLeiConversor.modelToODTO(this.topicoLeiService.editar(this.topicoLeiConversor.iDTOToModel(dto), id)), HttpStatus.OK);
    }

    @MyAdmin
    @MyUser
    @GetMapping("/{id}")
    public ResponseEntity<TopicoLeiODTO> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(this.topicoLeiConversor.modelToODTO(this.topicoLeiService.buscarPorId(id)), HttpStatus.OK);
    }

    @MyAdmin
    @MyUser
    @GetMapping("/search/pagination/{descricao}")
    public ResponseEntity<Page<TopicoLeiODTO>> buscarPorDescricao(@PathVariable String descricao, Pageable pageable) {
        Page<TopicoLei> pageModel = this.topicoLeiService.buscarPorDescricao(descricao, pageable);
        Page<TopicoLeiODTO> page = pageModel.map(model -> this.topicoLeiConversor.modelToODTO(model));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @MyAdmin
    @MyUser
    @GetMapping("/search/{descricao}")
    public ResponseEntity<List<TopicoLeiODTO>> buscarPorDescricaoSemPaginacao(@PathVariable String descricao, Pageable pageable) {
        List<TopicoLei> listaModel = this.topicoLeiService.buscarPorDescricao(descricao);
        List<TopicoLeiODTO> page = listaModel.stream().map(model -> this.topicoLeiConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @MyAdmin
    @MyUser
    @GetMapping("/pagination")
    public ResponseEntity<Page<TopicoLeiODTO>> retornarTodos(Pageable pageable) {
        Page<TopicoLei> pageModel = this.topicoLeiService.retornarTodas(pageable);
        Page<TopicoLeiODTO> page = pageModel.map(model -> this.topicoLeiConversor.modelToODTO(model));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @MyAdmin
    @MyUser
    @GetMapping()
    public ResponseEntity<List<TopicoLeiODTO>> retornarTodos() {
        List<TopicoLei> listaModel = this.topicoLeiService.retornarTodasSemPaginacao();
        List<TopicoLeiODTO> lista = listaModel.stream().map(model -> this.topicoLeiConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @MyAdmin
    @MyUser
    @GetMapping("/{descricao}/assunto/{id}")
    public ResponseEntity<List<TopicoLeiODTO>> retornarPorDescricaoAssociacaoComAssunto(@PathVariable String descricao, @PathVariable Integer id) {
        List<TopicoLeiODTO> listaODTO = this.topicoLeiService.buscarPorDescricaoOndeExisteAssociacaoComAssunto(descricao, id)
                .stream().map(model -> this.topicoLeiConversor.modelToODTO(model)).collect(Collectors.toList());
        return ResponseEntity.ok(listaODTO);
    }

    @MyAdmin
    @MyUser
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void excluir(@PathVariable Integer id) {
        this.topicoLeiService.excluir(id);
    }


}
