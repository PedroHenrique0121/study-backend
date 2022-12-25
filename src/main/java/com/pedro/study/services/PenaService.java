package com.pedro.study.services;

import com.pedro.study.exceptions.exceptionsPersonalizadas.NotFoundExceptionStudy;
import com.pedro.study.model.Artigo;
import com.pedro.study.model.Pena;
import com.pedro.study.repositories.PenaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@AllArgsConstructor
public class PenaService {

    private PenaRepository penaRepository;
    private ArtigoService artigoService;

    public Pena salvar(Pena pena) {
        Artigo artigo = artigoService.buscarPorId(pena.getArtigo().getId());
        pena.setArtigo(artigo);
        return penaRepository.save(pena);
    }

    public Pena editar(Pena pena) {
        Artigo artigo = artigoService.buscarPorId(pena.getArtigo().getId());
        return penaRepository.findById(pena.getId())
                .map(model -> {

                            model.setDescricao(pena.getDescricao());
                            model.setCategoria(pena.getCategoria());
                            model.setArtigo(artigo);
                            return penaRepository.save(model);
                        }
                ).orElseThrow(() -> {
                    return new NotFoundExceptionStudy("Não foi possivel editar a pena! Pena não foi encontrada");
                });
    }

    public Pena buscarPorId(Integer id) {
        return penaRepository.findById(id)
                .orElseThrow(() -> {
                            return new NotFoundExceptionStudy("Artigo não encontrado!");
                        }
                );
    }

    public List<Pena> buscarTodasSemPginacao() {
        return penaRepository.findAll();
    }

    public Page<Pena> buscarTodas(Pageable pageable) {
        return penaRepository.findAll(pageable);
    }

    public Page<Pena> buscarPorDescricao(String descricao, Pageable pageable) {
        return penaRepository.buscarPenaPorDescricao(descricao, pageable);
    }

    public List<Pena> buscarPorDescricaoSempaginacao(String descricao, Pageable pageable) {
        return penaRepository.buscarPenaPorDescricaoSemPaginacao(descricao, pageable);
    }

    public void excluir(Integer id) {
        Pena pena = this.buscarPorId(id);
        try {
            penaRepository.delete(pena);
        } catch (Exception e) {
            throw new ConstraintViolationException("Por motivos de violação de constraints, não foi possivel excluir a pena", null);
        }

    }

}
