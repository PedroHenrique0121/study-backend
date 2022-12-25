package com.pedro.study.repositories;

import com.pedro.study.model.Authorization;
import com.pedro.study.model.Role;
import com.pedro.study.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {

    @Query("SELECT a  FROM Authorization a inner join a.user inner join a.role WHERE a.user.id= :id")
    List<Authorization> buscarPorAuthorizaçoesByUser(@Param("id") Integer id);

    @Query("SELECT a FROM Authorization a INNER JOIN a.user inner join a.role WHERE  a.user.id= :idUser AND a.role.id=:idRole ")
    List<Authorization> findByRoleIdAndUserId(@Param("idUser") Integer idUser, @Param("idRole") Integer idRole);

//    List<Authorization> findByRoleIdAndUserId(Integer role, Integer user);
}
