package com.pedro.study.repositories;

import com.pedro.study.model.TopicoLei;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoLeiRepository extends JpaRepository<TopicoLei, Integer> {

    @Query("select tl from TopicoLei as tl where  upper(tl.descricao) like concat('%', upper(:descricao),'%')")
    Page<TopicoLei> findByDescricaoContaining(@Param("descricao") String descricao, Pageable pageable);

    @Query("select tl from TopicoLei as tl where  upper(tl.descricao) like concat('%',upper(:descricao),'%')")
    List<TopicoLei> findByDescricaoSemPaginacao(@Param("descricao") String descricao);

    @Query("select t from TopicoLei  as t INNER JOIN t.assunto where upper(t.descricao) like CONCAT('%',upper(:descricao),'%') and t.assunto.id= :id ")
    List<TopicoLei> findByDescricaoContainingAndAssunto_Id(@Param("descricao") String descricao, @Param("id") Integer id);
}
