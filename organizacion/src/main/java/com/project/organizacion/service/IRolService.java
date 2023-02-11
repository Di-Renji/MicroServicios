package com.project.organizacion.service;

import com.project.organizacion.dto.RolDto;

import java.util.List;

public interface IRolService {

    List<RolDto> listarRoles();
    RolDto registrarRol(RolDto body);
    RolDto actualizar(RolDto body, Long idRol);
    String eliminar(Long idRol);


}
