package com.pedro.study.controllers;

import com.pedro.study.config.permissoes.MyAdmin;
import com.pedro.study.config.permissoes.MyUser;
import com.pedro.study.dto.input.ArtigoIDTO;
import com.pedro.study.dto.output.ArtigoODTO;
import com.pedro.study.model.Artigo;
import com.pedro.study.modelmapper.conversores.ArtigoConversor;
import com.pedro.study.services.ArtigoService;
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
@RequestMapping("/api/artigos")
public class ArtigoController {

    private ArtigoService artigoService;
    private ArtigoConversor artigoConversor;

    @MyUser
    @MyAdmin
    @PostMapping("/cadastrar")
    public ResponseEntity<ArtigoODTO> salvar(@RequestBody @Valid ArtigoIDTO dto) {
        return new ResponseEntity<>(
                artigoConversor.modelToODTO(artigoService.salvar(artigoConversor.iDTOToModel(dto))), HttpStatus.CREATED);
    }

    @MyUser
    @MyAdmin
    @GetMapping("/{id}")
    public ResponseEntity<ArtigoODTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(artigoConversor.modelToODTO(artigoService.buscarPorId(id)));
    }

    @MyUser
    @MyAdmin
    @GetMapping("/pagination/topicoLei/{id}")
    public ResponseEntity<Page<ArtigoODTO>> buscarTodosPorRelcaoComTopicoLeiPaginado(@PathVariable Integer id, Pageable pageable) {
        Page<Artigo> pageModel = this.artigoService.buscarTodosPorRelacaoComTopicoLei(id, pageable);
        Page<ArtigoODTO> pageDTO = pageModel.map(model -> this.artigoConversor.modelToODTO(model));
        return ResponseEntity.ok(pageDTO);
    }

    @MyUser
    @MyAdmin
    @GetMapping("/topicoLei/{id}")
    public ResponseEntity<List<ArtigoODTO>> buscarTodosPorRelcaoComTopicoLei(@PathVariable Integer id) {
        List<ArtigoODTO> listODTO = this.artigoService.buscarTodosPorRelacaoComTopicoLei(id)
                .stream().map(model -> this.artigoConversor.modelToODTO(model)).collect(Collectors.toList());
        return ResponseEntity.ok(listODTO);
    }

    @MyUser
    @MyAdmin
    @GetMapping("/pagination/topicoLei/{topicoLeiId}/pena/{categoria}")
    public ResponseEntity<Page<ArtigoODTO>> buscarPorRelacaoTopicoLeiECategoriaDePena(@PathVariable String categoria, @PathVariable Integer topicoLeiId, Pageable pageable) {
        Page<Artigo> pageModel = artigoService.buscarTodosPorRelacaoComTopicoLeiECategoriaPena(categoria, topicoLeiId, pageable);
        Page<ArtigoODTO> pageODTO = pageModel.map(model -> artigoConversor.modelToODTO(model));
        return ResponseEntity.ok(pageODTO);
    }

    @MyUser
    @MyAdmin
    @GetMapping("/pagination/categoria/{categoria}/topicoLei/{topicoLeiId}")
    public ResponseEntity<Page<ArtigoODTO>> buscarPorCategoriaERelacaoTopicoLei(@PathVariable String categoria, @PathVariable Integer topicoLeiId, Pageable pageable) {
        Page<Artigo> pageModel = artigoService.buscarTodosPorCategoriaERelacaoComTopicoLei(categoria, topicoLeiId, pageable);
        Page<ArtigoODTO> pageODTO = pageModel.map(model -> artigoConversor.modelToODTO(model));
        return ResponseEntity.ok(pageODTO);
    }

    @MyUser
    @MyAdmin
    @GetMapping("/pagination/categoria/{categoriaArtigo}/topicoLei/{topicoLeiId}/pena/{categoriaPena}")
    public ResponseEntity<Page<ArtigoODTO>> buscarPorCategoriaRelacaoTopicoLeiEPenaCategoria(@PathVariable String categoriaArtigo, @PathVariable Integer topicoLeiId,@PathVariable String categoriaPena, Pageable pageable) {
        Page<Artigo> pageModel = artigoService.buscarTodosPorCategoriaRelacaoComTopicoLeiEPenaCategoria(categoriaArtigo, topicoLeiId, categoriaPena, pageable);
        Page<ArtigoODTO> pageODTO = pageModel.map(model -> artigoConversor.modelToODTO(model));
        return ResponseEntity.ok(pageODTO);
    }

    @MyUser
    @MyAdmin
    @GetMapping("/pagination")
    public ResponseEntity<Page<ArtigoODTO>> retornarTodos(Pageable pageable) {
        Page<Artigo> pageModel = artigoService.retornarTodos(pageable);
        Page<ArtigoODTO> pageODTO = pageModel.map(model -> artigoConversor.modelToODTO(model));
        return ResponseEntity.ok(pageODTO);
    }

    @MyUser
    @MyAdmin
    @GetMapping()
    public ResponseEntity<List<ArtigoODTO>> retornarTodosSemPaginacao() {
        List<Artigo> listaModel = artigoService.retornarTodos();
        List<ArtigoODTO> pageODTO = listaModel.stream().map(model -> artigoConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pageODTO);
    }

    @MyUser
    @MyAdmin
    @GetMapping("/search/pagination/{descricao}")
    public ResponseEntity<Page<ArtigoODTO>> buscarPorDescricao(@PathVariable String descricao, Pageable pageable) {
        Page<Artigo> pageModel = artigoService.buscarPorDescricao(descricao, pageable);
        Page<ArtigoODTO> pageODTO = pageModel.map(model -> artigoConversor.modelToODTO(model));
        return ResponseEntity.ok(pageODTO);
    }

    @MyUser
    @MyAdmin
    @GetMapping("/search/pagination/{descricao}/{topicoLeiId}")
    public ResponseEntity<Page<ArtigoODTO>> buscarPorDescricaoComRelacaoTopicoLei(@PathVariable String descricao, @PathVariable Integer topicoLeiId, Pageable pageable) {
        Page<Artigo> pageModel = artigoService.buscarPorDescricaoAssociadoTopicoLei(descricao, topicoLeiId, pageable);
        Page<ArtigoODTO> pageODTO = pageModel.map(model -> artigoConversor.modelToODTO(model));
        return ResponseEntity.ok(pageODTO);
    }

    @MyUser
    @MyAdmin
    @GetMapping("/search/{descricao}")
    public ResponseEntity<List<ArtigoODTO>> buscarPorDescricaoSemPaginacao(@PathVariable String descricao) {
        List<Artigo> listaModel = artigoService.buscarPorDescricaoSemPaginacao(descricao);
        List<ArtigoODTO> pageODTO = listaModel.stream().map(model -> artigoConversor.modelToODTO(model))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pageODTO);
    }

    @MyUser
    @MyAdmin
    @PutMapping("/editar/{id}")
    public ResponseEntity<ArtigoODTO> editar(@RequestBody @Valid ArtigoIDTO dto, @PathVariable Integer id) {
        return new ResponseEntity<>(
                artigoConversor.modelToODTO(artigoService.editar(artigoConversor.iDTOToModel(dto), id)),
                HttpStatus.OK
        );
    }

    @MyUser
    @MyAdmin
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletar(@PathVariable Integer id) {
        artigoService.excluir(id);
    }
}
