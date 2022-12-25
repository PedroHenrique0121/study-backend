package com.pedro.study.controllers;

import com.pedro.study.model.Role;
import com.pedro.study.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    @PostMapping()
    public ResponseEntity<Role> salvar(@Valid @RequestBody Role role){
        return new ResponseEntity<>(this.roleService.salvar(role), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> editar(@Valid @RequestBody Role role, @PathVariable Integer id){
        return ResponseEntity.ok(this.roleService.editar(role,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> retornarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(this.roleService.buscarPorId(id));
    }

    @GetMapping()
    public ResponseEntity<List<Role>> retornarTodas(){
        return ResponseEntity.ok(this.roleService.retornarTodas());
    }

}
