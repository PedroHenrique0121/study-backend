package com.pedro.study.services;

import com.pedro.study.exceptions.exceptionsPersonalizadas.NotFoundExceptionStudy;
import com.pedro.study.model.Authorization;
import com.pedro.study.model.Role;
import com.pedro.study.model.User;
import com.pedro.study.repositories.AuthorizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private AuthorizationRepository authorizationRepository;
    private RoleService roleService;
    private UserService userService;

    public Authorization salvar(Authorization authorization){
        Role role = roleService.buscarPorId(authorization.getRole().getId());
        User user = userService.buscarPOrId(authorization.getUser().getId());
        authorization.setRole(role);
        authorization.setUser(user);
        return this.authorizationRepository.save(authorization);
    }

    public Page<Authorization> retornarTodas(Pageable pageable){
        return  this.authorizationRepository.findAll(pageable);
    }

    public List<Authorization> retornarTodasViculadasUsuario(Integer id){
        return  this.authorizationRepository.buscarPorAuthorizaçoesByUser(id);
    }

    public void deleteByRoleAndByUser(Integer idRole, Integer idUser){
        Role role = roleService.buscarPorId(idRole);
        User user = userService.buscarPOrId(idUser);
        List<Authorization> authorizations = this.authorizationRepository.findByRoleIdAndUserId(idUser,idRole);
        System.out.println(authorizations.size());
        if(authorizations.size()>=1){
            this.authorizationRepository.deleteAll(authorizations);
        }else{
            throw  new NotFoundExceptionStudy("Nenhuma authorização encontrada!");
        }

    }

}
