package com.pedro.study.repositories;

import com.pedro.study.model.Artigo;
import com.pedro.study.model.Pena;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Integer> {

    @Query("SELECT a FROM Artigo AS a WHERE UPPER(a.descricao) LIKE(CONCAT('%',UPPER(:descricao),'%') ) ")
    Page<Artigo> findByDescricaoContaining(@Param("descricao") String descricao, Pageable page);

    @Query("SELECT a FROM Artigo AS a  INNER JOIN a.topicoLei WHERE UPPER(a.descricao) LIKE(CONCAT('%',UPPER(:descricao),'%'))  AND a.topicoLei.id= :topicoLeiId ")
    Page<Artigo> findByDescricaoContainingAndTopicoLei(@Param("descricao") String descricao, @Param("topicoLeiId") Integer topicoLeiId, Pageable page);

    @Query("SELECT a FROM Artigo AS a WHERE UPPER(a.descricao) LIKE(CONCAT('%',UPPER(:descricao),'%') ) ")
    List<Artigo> findByDescricaoNotPagination(@Param("descricao") String descricao);

    @Query("SELECT a  FROM Artigo a INNER JOIN a.topicoLei WHERE a.topicoLei.id= :id")
    Page<Artigo> findByIdTopicoLei(@Param("id") Integer id, Pageable pageable );

    List<Artigo> findByTopicoLei_Id( Integer id );

    Page<Artigo> findArtigosByCategoriaAndTopicoLei_Id(String categoria, Integer id, Pageable pageable);

    @Query("SELECT p.artigo FROM Pena AS p  INNER JOIN p.artigo INNER JOIN p.artigo.topicoLei WHERE UPPER(p.categoria) LIKE(CONCAT('%',UPPER(:categoria),'%'))   AND p.artigo.topicoLei.id= :topicoLeiId ")
    Page<Artigo> findByFromPenaCategoriaETopicoLei(@Param("categoria") String categoria, @Param("topicoLeiId") Integer topicoLeiId, Pageable page);

    @Query("SELECT p.artigo FROM Pena AS p  INNER JOIN p.artigo INNER JOIN p.artigo.topicoLei WHERE UPPER(p.categoria) LIKE(CONCAT('%',UPPER(:categoriaPena),'%'))   AND UPPER(p.artigo.categoria) LIKE(CONCAT('%',UPPER(:categoriaArtigo),'%'))  AND p.artigo.topicoLei.id= :topicoLeiId ")
    Page<Artigo> findByCategoriaPenaCategoriaETopicoLei(@Param("categoriaArtigo") String categoriaArtigo, @Param("topicoLeiId") Integer topicoLeiId, @Param("categoriaPena") String categoriaPena, Pageable page);

}
