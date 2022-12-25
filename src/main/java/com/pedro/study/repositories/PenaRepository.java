package com.pedro.study.repositories;

import com.pedro.study.model.Pena;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaRepository extends JpaRepository<Pena,Integer> {

    @Query("SELECT p FROM Pena AS p WHERE UPPER(p.descricao) like CONCAT('%',UPPER(:descricao),'%')")
    Page<Pena> buscarPenaPorDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Query("SELECT p FROM Pena AS p WHERE UPPER(p.descricao) like CONCAT('%',UPPER(:descricao),'%')")
    List<Pena> buscarPenaPorDescricaoSemPaginacao(@Param("descricao") String descricao, Pageable pageable);


}
