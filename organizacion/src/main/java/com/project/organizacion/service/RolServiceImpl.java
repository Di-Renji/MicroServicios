package com.project.organizacion.service;

import com.project.organizacion.dto.RolDto;
import com.project.organizacion.entity.Rol;
import com.project.organizacion.repository.EmpleadoRepository;
import com.project.organizacion.repository.RolRepository;
import com.project.organizacion.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    RolRepository rolRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public List<RolDto> listarRoles() {
        return rolRepository.findAll().stream().map(Utils::toRolDto).collect(Collectors.toList());
    }

    @Override
    public RolDto registrarRol(RolDto body) {
        Rol rolRegister = new Rol(null, body.getNombre());
        Rol rolRegistered = rolRepository.save(rolRegister);
        return Utils.toRolDto(rolRegistered);
    }

    @Override
    public RolDto actualizar(RolDto bodyDto, Long idRol) {
        Optional<Rol> encontrado = rolRepository.findById(idRol);
        if (encontrado.isPresent()) {
            Rol rolUpdate = encontrado.get();
            rolUpdate.setId(idRol);
            rolUpdate.setNombre(bodyDto.getNombre());
            Rol rolUpdated = rolRepository.save(rolUpdate);
            return Utils.toRolDto(rolUpdated);
        }
        return null;

    }

    @Override
    public String eliminar(Long idRol) {
        Optional<Rol> encontrado = rolRepository.findById(idRol);
        if (encontrado.isPresent()) {
            rolRepository.deleteById(idRol);
            return "Rol eliminado correctamente";
        }
        return "Rol no se encuentra registrado";

    }
}
