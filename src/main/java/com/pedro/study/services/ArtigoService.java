package com.pedro.study.services;

import com.pedro.study.exceptions.exceptionsPersonalizadas.NotFoundExceptionStudy;
import com.pedro.study.model.Artigo;
import com.pedro.study.model.TopicoLei;
import com.pedro.study.repositories.ArtigoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@AllArgsConstructor
public class ArtigoService {

    private ArtigoRepository artigoRepository;
    private TopicoLeiService topicoLeiService;

    public Artigo salvar(Artigo artigo) {
        topicoLeiService.buscarPorId(artigo.getTopicoLei().getId());
        return artigoRepository.save(artigo);
    }

    public Artigo editar(Artigo artigo, Integer id) {
        return artigoRepository.findById(id).map(
                (model) -> {
                    model.setDescricao(artigo.getDescricao());
                    model.setCategoria(artigo.getCategoria());
                    model.setNumero(artigo.getNumero());
                    TopicoLei topicoLei = topicoLeiService.buscarPorId(artigo.getTopicoLei().getId());
                    model.setTopicoLei(topicoLei);
                    return artigoRepository.save(model);
                }
        ).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("Artigo não encontrado!");
                }
        );
    }

    public void excluir(Integer id) {
        Artigo artigo = artigoRepository.findById(id)
                .orElseThrow(
                        () -> {
                            return new NotFoundExceptionStudy("Artigo não foi encontrado!");
                        }
                );
        try {
            artigoRepository.delete(artigo);
        } catch (Exception e) {
            throw new ConstraintViolationException("Não foi possivel deletar este artigo", null);
        }

    }

    public Page<Artigo> retornarTodos(Pageable pageable) {
        return artigoRepository.findAll(pageable);
    }

    public List<Artigo> retornarTodos() {
        return artigoRepository.findAll();
    }

    public Page<Artigo> buscarPorDescricao(String descricao, Pageable pageable) {
        return artigoRepository.findByDescricaoContaining(descricao, pageable);
    }

    public Page<Artigo> buscarPorDescricaoAssociadoTopicoLei(String descricao, Integer topicoLeiId, Pageable pageable) {
        return artigoRepository.findByDescricaoContainingAndTopicoLei(descricao, topicoLeiId, pageable);
    }

    public List<Artigo> buscarPorDescricaoSemPaginacao(String descricao) {
        return artigoRepository.findByDescricaoNotPagination(descricao);
    }

    public Artigo buscarPorId(Integer id) {
        return artigoRepository.findById(id).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("Artigo não encontrado!");
                });
    }

    public Page<Artigo> buscarTodosPorRelacaoComTopicoLei(Integer id, Pageable pageable) {
        return this.artigoRepository.findByIdTopicoLei(id, pageable);
    }

    public Page<Artigo> buscarTodosPorCategoriaERelacaoComTopicoLei(String categoria, Integer id, Pageable pageable) {
        return this.artigoRepository.findArtigosByCategoriaAndTopicoLei_Id(categoria,id,  pageable);
    }

    public Page<Artigo> buscarTodosPorCategoriaRelacaoComTopicoLeiEPenaCategoria(String categoriaArtigo, Integer id, String categoriaPena, Pageable pageable) {
        return this.artigoRepository.findByCategoriaPenaCategoriaETopicoLei(categoriaArtigo,id, categoriaPena,  pageable);
    }

    public Page<Artigo> buscarTodosPorRelacaoComTopicoLeiECategoriaPena(String categoria,Integer topicoLeiId, Pageable pageable) {
        return this.artigoRepository.findByFromPenaCategoriaETopicoLei(categoria, topicoLeiId, pageable);
    }

    public List<Artigo> buscarTodosPorRelacaoComTopicoLei(Integer id) {
        return this.artigoRepository.findByTopicoLei_Id(id);
    }


}
