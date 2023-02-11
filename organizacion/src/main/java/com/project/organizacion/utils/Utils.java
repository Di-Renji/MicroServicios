package com.project.organizacion.utils;

import com.project.organizacion.dto.EmpleadoDto;
import com.project.organizacion.dto.EmpresaDto;
import com.project.organizacion.dto.RolDto;
import com.project.organizacion.dto.UsuarioDto;
import com.project.organizacion.entity.Empleado;
import com.project.organizacion.entity.Empresa;
import com.project.organizacion.entity.Rol;
import com.project.organizacion.entity.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {


    /****************************** EMPRESA ***************************************/
    public static Empresa toEmpresa(EmpresaDto empresaDto){
        Empresa empresa = new Empresa();
        empresa.setRazonSocial(empresaDto.getRazonSocial());
        empresa.setRuc(empresaDto.getRuc());
        empresa.setRepresentante(empresaDto.getRepresentante());
        empresa.setFechaCreacion(empresaDto.getFechaCreacion());
        return empresa;
    }

    public static EmpresaDto toEmpresaDto(Empresa empresa){
        final EmpresaDto empresaDto = new EmpresaDto();
        empresaDto.setRazonSocial(empresa.getRazonSocial());
        empresaDto.setRuc(empresa.getRuc());
        empresaDto.setRepresentante(empresa.getRepresentante());
        empresaDto.setFechaCreacion(empresa.getFechaCreacion());
        return empresaDto;

    }

    /****************************** USUARIO ***************************************/
    public static Usuario toUsuario(UsuarioDto usuarioDto, Empleado empleado) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setEmpleado(empleado);
        return usuario;
    }

    public static UsuarioDto toUsuarioDto(Usuario usuario, EmpleadoDto empleadoDto) {
        final UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsername(usuario.getUsername());
        usuarioDto.setPassword(usuario.getPassword());
        usuarioDto.setEmpleado(empleadoDto);
        return usuarioDto;
    }

    /****************************** ROL ***************************************/
    public static RolDto toRolDto(Rol rol) {
        final RolDto rolDto = new RolDto();
        rolDto.setNombre(rol.getNombre());
        return rolDto;
    }

    public static Rol toRol(RolDto rolDto) {
        Rol rol = new Rol();
        rol.setNombre(rolDto.getNombre());
        return rol;
    }



    /****************************** EMPLEADO ***************************************/

    public static Empleado toEmpleado(EmpleadoDto empleadoDto, Empresa empresa, List<Rol> roles) {
        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setSexo(empleadoDto.getSexo());
        empleado.setTelefono(empleado.getTelefono());
        empleado.setEmpresa(empresa);
        empleado.setRoles(roles);
        return empleado;
    }

    public static EmpleadoDto toEmpleadoDto(Empleado empleado,EmpresaDto empresaDto, List<RolDto> rolesDto) {
        final EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setEmpresa(empresaDto);
        empleadoDto.setNombre(empleado.getNombre());
        empleadoDto.setSexo(empleado.getSexo());
        empleadoDto.setRoles(rolesDto);
        empleadoDto.setTelefono(empleado.getTelefono());
        return empleadoDto;
    }






}
