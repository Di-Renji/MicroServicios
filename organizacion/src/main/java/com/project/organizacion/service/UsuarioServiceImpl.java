package com.project.organizacion.service;

import com.project.organizacion.dto.UsuarioDto;
import com.project.organizacion.entity.Empleado;
import com.project.organizacion.entity.Usuario;
import com.project.organizacion.repository.EmpleadoRepository;
import com.project.organizacion.repository.UsuarioRepository;
import com.project.organizacion.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;


    @Override
    public List<UsuarioDto> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(usuario -> Utils.toUsuarioDto(usuario, Utils.toEmpleadoDto(usuario.getEmpleado(), Utils.toEmpresaDto(usuario.getEmpleado().getEmpresa()), Optional.ofNullable(usuario.getEmpleado().getRoles()).map(roles -> roles.stream().map(Utils::toRolDto).collect(Collectors.toList())).orElse(null)))).collect(Collectors.toList());
    }

    @Override
    public UsuarioDto registrarUsuario(UsuarioDto body) {
        Optional<Empleado> encontrado = empleadoRepository.findById(body.getEmpleadoId());
        if (encontrado.isPresent()) {
            Usuario usuarioRegister = new Usuario(null, body.getUsername(), body.getPassword(), encontrado.get());
            Usuario usuarioRegistered = usuarioRepository.save(usuarioRegister);
            return Utils.toUsuarioDto(usuarioRegistered,
                    Utils.toEmpleadoDto(usuarioRegistered.getEmpleado(), Utils.toEmpresaDto(usuarioRegistered.getEmpleado().getEmpresa()),
                            Optional.ofNullable(usuarioRegistered.getEmpleado().getRoles()).map(roles -> roles.stream().map(Utils::toRolDto)
                                    .collect(Collectors.toList())).orElse(null)));
        }
        return null;
    }

    @Override
    public UsuarioDto actualizar(UsuarioDto body, Long idUsuario) {
        Optional<Empleado> encontradoEmpleado = empleadoRepository.findById(body.getEmpleadoId());
        Optional<Usuario> encontrado = usuarioRepository.findById(idUsuario);
        if (encontradoEmpleado.isPresent() && encontrado.isPresent()) {
            Usuario usuarioUpdate = encontrado.get();
            usuarioUpdate.setId(idUsuario);
            usuarioUpdate.setUsername(body.getUsername());
            usuarioUpdate.setPassword(body.getPassword());
            usuarioUpdate.setEmpleado(encontradoEmpleado.get());
            Usuario usuarioUpdated = usuarioRepository.save(usuarioUpdate);
            return Utils.toUsuarioDto(usuarioUpdated, Utils.toEmpleadoDto(usuarioUpdated.getEmpleado(),
                    Utils.toEmpresaDto(usuarioUpdated.getEmpleado().getEmpresa()),
                    Optional.ofNullable(usuarioUpdated.getEmpleado().getRoles()).map(roles -> roles.stream().map(Utils::toRolDto)
                            .collect(Collectors.toList())).orElse(null)));
        }
        return null;

    }

    @Override
    public String eliminar(Long idUsuario) {
        Optional<Usuario> encontrado = usuarioRepository.findById(idUsuario);
        if (encontrado.isPresent()) {
            usuarioRepository.deleteById(idUsuario);
            return "Usuario eliminado correctamente";
        }
        return "Usuario no se encuentra registrado";

    }
}
