package com.pedro.study.services;

import com.pedro.study.exceptions.exceptionsPersonalizadas.NotFoundExceptionStudy;
import com.pedro.study.model.Assunto;
import com.pedro.study.model.Disciplina;
import com.pedro.study.repositories.AssuntoRepository;
import com.pedro.study.repositories.DisciplinaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@AllArgsConstructor
public class AssuntoService {
    private AssuntoRepository assuntoRepository;
    private DisciplinaService disciplinaService;

    public Assunto salvar(Assunto assunto) {

        disciplinaService.buscarPorId(assunto.getDisciplina().getId());
        return assuntoRepository.save(assunto);
    }

    public Assunto editar(Assunto assunto, Integer id) {
        return assuntoRepository.findById(id).map(
                (model) -> {
                    model.setDescricao(assunto.getDescricao());

                   Disciplina disciplina=  disciplinaService.buscarPorId(assunto.getDisciplina().getId());
                   model.setDisciplina(disciplina);
                    return assuntoRepository.save(model);
                }
        ).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("Assunto não encontrado!");
                }
        );
    }

    public void excluir(Integer id) {
        Assunto assunto = assuntoRepository.findById(id)
                .orElseThrow(
                        () -> {
                            return new NotFoundExceptionStudy("Assunto não foi encontrado!");
                        }
                );
        try {
            assuntoRepository.delete(assunto);
        } catch (Exception e) {
            throw new ConstraintViolationException("Não foi possivel deletar este assunto", null);
        }

    }

    public Page<Assunto> retornarTodos(Pageable pageable) {
        return assuntoRepository.findAll(pageable);
    }

    public List<Assunto> retornarTodos() {
        return assuntoRepository.findAll();
    }

    public Page<Assunto> buscarPorDescricao(String descricao, Pageable pageable) {
        return assuntoRepository.findByDescricaoContaining(descricao, pageable);
    }

    public List<Assunto> buscarPorDescricaoSemPaginacao(String descricao) {
        return assuntoRepository.findByDescricaoNotPagination(descricao);
    }

    public Assunto buscarPorId(Integer id) {
        return assuntoRepository.findById(id).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("Assunto não encontrado!");
                });
    }


    public List<Assunto> findByDisciplinaId(Integer id, String descricao){
        return this.assuntoRepository.findByDescricaoContainingAndDisciplina_Id(descricao,id);
    }


}
