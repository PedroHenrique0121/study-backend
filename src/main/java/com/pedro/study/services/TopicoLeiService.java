package com.pedro.study.services;

import com.pedro.study.exceptions.exceptionsPersonalizadas.NotFoundExceptionStudy;
import com.pedro.study.model.Assunto;
import com.pedro.study.model.TopicoLei;
import com.pedro.study.repositories.TopicoLeiRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@AllArgsConstructor
public class TopicoLeiService {

    private TopicoLeiRepository topicoLeiRepository;
    private AssuntoService assuntoService;

    public TopicoLei salvar(TopicoLei topicoLei) {
        this.assuntoService.buscarPorId(topicoLei.getAssunto().getId());
        return this.topicoLeiRepository.save(topicoLei);
    }

    public TopicoLei editar(TopicoLei topicoLei, Integer id) {
        return topicoLeiRepository.findById(id).map(
                (model) -> {
                    Assunto assunto = assuntoService.buscarPorId(topicoLei.getAssunto().getId());
                    model.setDescricao(topicoLei.getDescricao());
                    model.setAssunto(assunto);
                    return topicoLeiRepository.save(model);
                }
        ).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("TopicoLei não encontrado!");
                }
        );
    }

    public void excluir(Integer id) {
        TopicoLei d = topicoLeiRepository.findById(id).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("Não foi possivel concluir a deleção , a topicoLei não foi encontrado!");
                }
        );
        try {
            topicoLeiRepository.delete(d);
        } catch (Exception e) {
            throw new ConstraintViolationException("Não foi possivel deletar este  topico/Lei!", null);
        }

    }

    public Page<TopicoLei> retornarTodas(Pageable pageable) {
        return topicoLeiRepository.findAll(pageable);
    }

    public List<TopicoLei> retornarTodasSemPaginacao() {
        return topicoLeiRepository.findAll();
    }

    public TopicoLei buscarPorId(Integer id) {
        return topicoLeiRepository.findById(id).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("TopicoLei não encontrado!");
                });
    }

    public Page<TopicoLei> buscarPorDescricao(String descricao, Pageable pageable) {
        return topicoLeiRepository.findByDescricaoContaining(descricao, pageable);
    }

    public List<TopicoLei> buscarPorDescricao(String descricao) {
        return topicoLeiRepository.findByDescricaoSemPaginacao(descricao);
    }

    public List<TopicoLei> buscarPorDescricaoOndeExisteAssociacaoComAssunto(String descricao, Integer id){
        return this.topicoLeiRepository.findByDescricaoContainingAndAssunto_Id(descricao, id);
    }



}
