package com.pedro.study.services;

import com.pedro.study.exceptions.exceptionsPersonalizadas.NotFoundExceptionStudy;
import com.pedro.study.model.Disciplina;
import com.pedro.study.repositories.DisciplinaRepository;
import com.pedro.study.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@AllArgsConstructor
public class DisciplinaService {

    private DisciplinaRepository disciplinaRepository;
    private UserRepository userRepository;

    public Disciplina salvar(Disciplina disciplina) {
        userRepository.findById(disciplina.
                getUser().
                getId()).
                orElseThrow(() -> {
                            return new NotFoundExceptionStudy(
                                    "Não foi possivel Realizar o cadastro, o usuario não foi encontrado!");
                        }
                );
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina editar(Disciplina disciplina, Integer id) {
        return disciplinaRepository.findById(id).map(
                (model) -> {
                    model.setDescricao(disciplina.getDescricao());
                    return disciplinaRepository.save(model);
                }
        ).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("Disciplina não encontrada!");
                }
        );
    }

    public void excluir(Integer id) {
        Disciplina d = disciplinaRepository.findById(id).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("Não foi possivel concluir a deleção , a disciplina não foi encontrado!");
                }
        );
        try {
            disciplinaRepository.delete(d);
        } catch (Exception e) {
            throw new ConstraintViolationException("Não foi possivel deletar esta disciplina!", null);
        }

    }

    public Page<Disciplina> retornarTodas(Pageable pageable) {
        return disciplinaRepository.findAll(pageable);
    }

    public List<Disciplina> retornarTodasSemPaginacao() {
        return disciplinaRepository.findAll();
    }

    public Disciplina buscarPorId(Integer id) {
        return disciplinaRepository.findById(id).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("Disciplina não encontrada!");
                });
    }

    public Page<Disciplina> buscarPorDescricao(String descricao, Pageable pageable) {
        return disciplinaRepository.findByDescricaoContaining(descricao, pageable);
    }

    public List<Disciplina> buscarPorDescricaoNotPagination(String descricao) {
        return disciplinaRepository.findByDescricaoNotPagination(descricao);
    }


}
