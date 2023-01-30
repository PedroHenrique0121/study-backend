package com.pedro.study.services;

import com.pedro.study.dto.output.UserODTO;
import com.pedro.study.exceptions.exceptionsPersonalizadas.NotFoundExceptionStudy;
import com.pedro.study.exceptions.exceptionsPersonalizadas.UniqueExceptionStudy;
import com.pedro.study.model.Authorization;
import com.pedro.study.model.Role;
import com.pedro.study.model.User;
import com.pedro.study.repositories.AuthorizationRepository;
import com.pedro.study.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.*;
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private AuthorizationRepository authorizationRepository;

    public User salvar(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UniqueExceptionStudy("usuario já está cadastrado!");
        }
    }

    public Page<User> listarTodos(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User buscarPOrId(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundExceptionStudy("Usario com id: " + id + " não foi encontrado")
        );
    }

    public User editar(User user, Integer id) {
        return userRepository.findById(id).
                map((model) -> {
                    model.setLogin(user.getLogin());
                    model.setNome(user.getNome());
                    model.setSenha(user.getSenha());
                    return userRepository.save(model);
                }).
                orElseThrow(() -> {
                    return new NotFoundExceptionStudy("Usuario, não foi encontrado!");
                });
    }

    public void deletar(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> {
                    return new NotFoundExceptionStudy("Não foi possivel concluir a deleção , o usuario não foi encontrado!");
                }
        );
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            throw new ConstraintViolationException("Não foi possivel deletar este ususario!", null);
        }

    }

    public Page<User> retornarPorNome(String nome, Pageable pageable) {
        return this.userRepository.findByNome(nome, pageable);
    }

    public User retornarPorLogin(String login) {
        return this.userRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundExceptionStudy("Usuario inexistente!"));
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository
                .findByLogin(userName)
                .orElseThrow(() -> new NotFoundExceptionStudy("Usuario inexistente!"));

        List<Authorization> authorizations = this.authorizationRepository
                .buscarPorAuthorizaçoesByUser(user.getId());

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Role> r = new ArrayList<>();
        for (Authorization a : authorizations) {
           authorities.add( new SimpleGrantedAuthority("ROLE_"+a.getRole().getDescricao()));
        }
//
//        String[] v = new String[r.size()];
//
//        for (int i = 0; i < r.size(); i++) {
//            v[i] = r.get(i).getDescricao();
//        }
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getSenha(),
                authorities
        );
    }
}
