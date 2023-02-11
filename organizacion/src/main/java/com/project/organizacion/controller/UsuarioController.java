package com.project.organizacion.controller;

import com.project.organizacion.dto.UsuarioDto;
import com.project.organizacion.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    IUsuarioService iUsuarioService;


    @GetMapping("/listar")
    public List<UsuarioDto> listarUsuarios(){
        return iUsuarioService.listarUsuarios();
    }

    @PostMapping("/registrar")
    public UsuarioDto registrarUsuario(@RequestBody UsuarioDto body){
        return iUsuarioService.registrarUsuario(body);
    }

    @PutMapping("/actualizar/{id}")
    public UsuarioDto actualizar(@RequestBody UsuarioDto body, @PathVariable("id") Long idUsuario){
        return iUsuarioService.actualizar(body, idUsuario);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long idUsuario){
        return iUsuarioService.eliminar(idUsuario);
    }




}
