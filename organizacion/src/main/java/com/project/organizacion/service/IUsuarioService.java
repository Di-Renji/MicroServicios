package com.project.organizacion.service;

import com.project.organizacion.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioDto> listarUsuarios();
    UsuarioDto registrarUsuario(UsuarioDto body);
    UsuarioDto actualizar(UsuarioDto body, Long idUsuario);
    String eliminar(Long idUsuario);

}
