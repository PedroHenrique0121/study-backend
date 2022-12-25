package com.pedro.study.repositories;

import com.pedro.study.model.Assunto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto,Integer> {

    @Query("select a from Assunto  as a where upper(a.descricao) like CONCAT('%',upper(:descricao),'%')  ")
    Page<Assunto> findByDescricaoContaining(@Param("descricao") String descricao, Pageable pageable);

    @Query("select a from Assunto  as a where upper(a.descricao) like CONCAT('%',upper(:descricao),'%')  ")
    List<Assunto> findByDescricaoNotPagination(@Param("descricao") String descricao);

    @Query("select a from Assunto  as a INNER JOIN a.disciplina where upper(a.descricao) like CONCAT('%',upper(:descricao),'%') and a.disciplina.id= :id ")
    List<Assunto> findByDescricaoContainingAndDisciplina_Id(@Param("descricao") String descricao,@Param("id") Integer id);
}
