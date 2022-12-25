package com.pedro.study.controllers;


import com.pedro.study.dto.input.AuthorizationIDTO;
import com.pedro.study.dto.output.AuthorizationODTO;
import com.pedro.study.model.Authorization;
import com.pedro.study.modelmapper.conversores.AuthorizationConversor;
import com.pedro.study.repositories.AuthorizationRepository;
import com.pedro.study.services.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/authorization")
@AllArgsConstructor
public class AuthorizationController {
    private AuthorizationService authorizationService;
    private AuthorizationConversor authorizationConversor;
    private AuthorizationRepository authorizationRepository;

    @PostMapping()
    public ResponseEntity<AuthorizationODTO> salvar(@Valid @RequestBody AuthorizationIDTO dto) {
        return new ResponseEntity<>(this.authorizationConversor.modelToDTO(this.authorizationService.salvar(this.authorizationConversor.dtoToModel(dto))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorizationODTO>>  retornarTodas(Pageable pageable){
        return ResponseEntity.ok( this.authorizationConversor.pageModelToPageDTO(this.authorizationService.retornarTodas(pageable)));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AuthorizationODTO>>  retornarVinculadasUsuario(@PathVariable Integer id){
        return ResponseEntity.ok( this.authorizationConversor.listModelToListDTO(this.authorizationService.retornarTodasViculadasUsuario(id)));
    }

    @DeleteMapping("/{idUser}/{idRole}")
    @ResponseStatus(value = HttpStatus.OK)
    public void excluir(@PathVariable Integer idUser, @PathVariable Integer idRole) {
          this.authorizationService.deleteByRoleAndByUser(idRole,idUser);
    }






}
