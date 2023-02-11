package com.project.organizacion.service;

import com.project.organizacion.dto.EmpleadoDto;

import java.util.List;

public interface IEmpleadoService {

    List<EmpleadoDto> listarEmpleados();
    List<EmpleadoDto> filtrarEmpleadosPorNombre(String texto);
    EmpleadoDto registrarEmpleado(EmpleadoDto body);
    EmpleadoDto actualizar(EmpleadoDto body, Long idEmpleado);
    String eliminar(Long idEmpleado);

}
