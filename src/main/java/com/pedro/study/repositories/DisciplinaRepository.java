package com.pedro.study.repositories;

import com.pedro.study.model.Disciplina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Integer> {


    @Query("select d from Disciplina  as d where upper(d.descricao) like CONCAT('%',upper(:descricao),'%')  ")
    Page<Disciplina> findByDescricaoContaining(@Param("descricao") String descricao, @Param("pageable") Pageable pageable);

    @Query("select d from Disciplina  as d where upper(d.descricao) like CONCAT('%',upper(:descricao),'%')  ")
    List<Disciplina> findByDescricaoNotPagination(@Param("descricao") String descricao);

//    @Query("SELECT  d from Disciplina as d JOIN FETCH d.assuntos WHERE d.id= :id ")
//    Optional<Disciplina> findById(@Param("id") Integer id);

}
