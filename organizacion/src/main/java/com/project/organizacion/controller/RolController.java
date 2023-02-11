package com.project.organizacion.controller;

import com.project.organizacion.dto.RolDto;
import com.project.organizacion.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    IRolService iRolService;

    @GetMapping("/listar")
    public List<RolDto> listarRoles(){
        return iRolService.listarRoles();
    }

    @PostMapping("/registrar")
    public RolDto registrarRol(@RequestBody RolDto body){
        return iRolService.registrarRol(body);
    }

    @PutMapping("/actualizar/{id}")
    public RolDto actualizar(@RequestBody RolDto body, @PathVariable("id") Long idRol) {
        return iRolService.actualizar(body, idRol);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long idRol) {
        return iRolService.eliminar(idRol);
    }




}
