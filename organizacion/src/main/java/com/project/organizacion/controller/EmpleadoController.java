package com.project.organizacion.controller;

import com.project.organizacion.dto.EmpleadoDto;
import com.project.organizacion.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    IEmpleadoService iEmpleadoService;

    @GetMapping("/listar")
    public List<EmpleadoDto> listarEmpleados(){
        return iEmpleadoService.listarEmpleados();
    }

    @GetMapping("/filtrar")
    public List<EmpleadoDto> filtrarEmpleadosPorNombre(@RequestParam(name = "nombre", required = false) String texto){
        return iEmpleadoService.filtrarEmpleadosPorNombre(texto);
    }

    @PutMapping("/actualizar/{id}")
    public EmpleadoDto actualizar(@RequestBody EmpleadoDto body, @PathVariable("id") Long idEmpleado){
        return iEmpleadoService.actualizar(body, idEmpleado);
    }
    @PostMapping("/registrar")
    public EmpleadoDto registrarEmpleado(@RequestBody EmpleadoDto body){
        return iEmpleadoService.registrarEmpleado(body);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long idEmpleado) {
        return iEmpleadoService.eliminar(idEmpleado);
    }


}
