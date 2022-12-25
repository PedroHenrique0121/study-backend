package com.pedro.study.repositories;

import com.pedro.study.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    @Query("SELECT u FROM User as u WHERE UPPER(u.nome) LIKE CONCAT('%',UPPER(:nome),'%') ")
    Page<User> findByNome(String nome, Pageable pageable);
}
