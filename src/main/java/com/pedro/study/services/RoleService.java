package com.pedro.study.services;

import com.pedro.study.exceptions.exceptionsPersonalizadas.NotFoundExceptionStudy;
import com.pedro.study.model.Role;
import com.pedro.study.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    public Role salvar(Role role) {
        return this.roleRepository.save(role);
    }

    public Role editar(Role role, Integer id) {
        return this.roleRepository.findById(id)
                .map((model) -> {
                            model.setDescricao(role.getDescricao());
                            return this.roleRepository.save(model);
                        }
                ).orElseThrow(() -> {
                            return new NotFoundExceptionStudy("Role não foi encontrada!");
                        }
                );
    }

    public Role buscarPorId(Integer id) {
        return this.roleRepository.findById(id)
                .orElseThrow(
                        () -> {
                            return new NotFoundExceptionStudy("Role não foi encontrada!");
                        }
                );
    }

    public List<Role> retornarTodas() {
        return this.roleRepository.findAll();
    }

    public void excluir(Integer id) {
        Role role = this.roleRepository.findById(id)
                .orElseThrow(() -> {
                    return new NotFoundExceptionStudy("Não foi possivel excluir esse papel, o papel não Foi encontrada!");
                });

        try {
            this.roleRepository.delete(role);

        } catch (Exception e) {
            throw new ConstraintViolationException("Não foi possivel deletar este papel!", null);
        }

    }
}
