package com.pedro.study.controllers;


import com.pedro.study.config.permissoes.MyAdmin;
import com.pedro.study.config.permissoes.MySuperAdmin;
import com.pedro.study.config.permissoes.PermitAll;
import com.pedro.study.dto.input.UserIDTO;
import com.pedro.study.dto.output.UserODTO;
import com.pedro.study.model.User;
import com.pedro.study.modelmapper.conversores.UserConversor;
import com.pedro.study.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserConversor userConversor;


    @MySuperAdmin
    @PostMapping("/cadastrar")
    public ResponseEntity<UserODTO> salvar(@RequestBody @Valid UserIDTO iDTO) {
        return new ResponseEntity<>(userConversor.modelToODTO(userService.salvar(userConversor.dTOToUser(iDTO))), HttpStatus.CREATED);
    }

    @MySuperAdmin
    @GetMapping
    public ResponseEntity<Page<UserODTO>> listarTodos(Pageable pageable) {
        Page<User> pageUser = userService.listarTodos(pageable);
        Page<UserODTO> page = pageUser.map(user -> userConversor.modelToODTO(user));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @MySuperAdmin
    @GetMapping("/{id}")
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<UserODTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(userConversor.modelToODTO(userService.buscarPOrId(id)));
    }

    @MySuperAdmin
    @GetMapping("pagination/{nome}")
    public ResponseEntity<Page<UserODTO>> retornarPorNome(@PathVariable String nome, Pageable pageable) {
        Page<UserODTO> page = this.userService.retornarPorNome(nome, pageable).map(user -> userConversor.modelToODTO(user));
        return ResponseEntity.ok(page);
    }

    @MySuperAdmin
    @GetMapping("/login/{login}")
    public ResponseEntity<UserODTO> buscarPorLogin(@PathVariable String login) {
        return ResponseEntity.ok(userConversor.modelToODTO(userService.retornarPorLogin(login)));
    }

    @MySuperAdmin
    @PutMapping("/editar/{id}")
    public ResponseEntity<UserODTO> editar(@RequestBody @Valid UserIDTO dto, @PathVariable Integer id) {
        return ResponseEntity.ok(userConversor.modelToODTO(userService.editar(userConversor.dTOToUser(dto), id)));
    }

    @MySuperAdmin
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        userService.deletar(id);

    }

}
