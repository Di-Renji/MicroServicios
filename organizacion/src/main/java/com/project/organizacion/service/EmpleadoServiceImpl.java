package com.project.organizacion.service;

import com.project.organizacion.dto.EmpleadoDto;
import com.project.organizacion.dto.EmpresaDto;
import com.project.organizacion.entity.Empleado;
import com.project.organizacion.entity.Empresa;
import com.project.organizacion.entity.Rol;
import com.project.organizacion.repository.EmpleadoRepository;
import com.project.organizacion.repository.RolRepository;
import com.project.organizacion.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<EmpleadoDto> listarEmpleados() {
        return empleadoRepository.findAll().stream().map(empleado -> {
            return Utils.toEmpleadoDto(empleado, Utils.toEmpresaDto(empleado.getEmpresa()), Optional.ofNullable(empleado.getRoles()).map(roles -> { return roles.stream().map(Utils::toRolDto).collect(Collectors.toList());}).orElse(null));
        }).collect(Collectors.toList());

    }

    @Override
    public List<EmpleadoDto> filtrarEmpleadosPorNombre(String texto) {
        return empleadoRepository.findByNombreContaining(texto).stream().map(empleado -> Utils.toEmpleadoDto(empleado, Utils.toEmpresaDto(empleado.getEmpresa()), Optional.ofNullable(empleado.getRoles()).map(roles -> roles.stream().map(Utils::toRolDto).collect(Collectors.toList())).orElse(null))).collect(Collectors.toList());
    }

    @Override
    public EmpleadoDto registrarEmpleado(EmpleadoDto body) {

        HttpHeaders headers = new HttpHeaders();
        headers.put("cabecera1", List.of("value1"));
        HttpEntity<EmpresaDto> request = new HttpEntity<>(headers);

        ResponseEntity<EmpresaDto> responseEmpresa = restTemplate.exchange("http://SERVICIO-EMPRESA/empresa/buscar/"+body.getEmpresaId(), HttpMethod.GET, request, EmpresaDto.class);

        if (Objects.nonNull(responseEmpresa.getBody()) && Objects.isNull(responseEmpresa.getBody().getMensaje())) {
            Empleado empleadoRegister = new Empleado(null, body.getNombre(), body.getSexo(), body.getTelefono(), new Empresa(body.getEmpresaId()), null);
            Empleado empleadoRegistered = empleadoRepository.save(empleadoRegister);
            return Utils.toEmpleadoDto(empleadoRegistered, Utils.toEmpresaDto(empleadoRegistered.getEmpresa()), Optional.ofNullable(empleadoRegistered.getRoles()).map(roles -> roles.stream().map(Utils::toRolDto).collect(Collectors.toList())).orElse(null));
        }
        return null;
    }

    @Override
    public EmpleadoDto actualizar(EmpleadoDto body, Long idEmpleado) {
        Optional<Empleado> encontrado = empleadoRepository.findById(idEmpleado);
        /*Optional<Empresa> empresa = empresaRepository.findById(body.getEmpresaId());
        List<Rol> rolList = rolRepository.findAllById(body.getRolesId());
        if (encontrado.isPresent() && empresa.isPresent() && rolList.size() == body.getRolesId().size()) {
            Empleado empleadoUpdate = encontrado.get();
            empleadoUpdate.setId(idEmpleado);
            empleadoUpdate.setNombre(body.getNombre());
            empleadoUpdate.setSexo(body.getSexo());
            empleadoUpdate.setTelefono(body.getTelefono());
            empleadoUpdate.setEmpresa(empresa.get());
            empleadoUpdate.setRoles(rolList);
            Empleado empleadoUpdated = empleadoRepository.save(empleadoUpdate);
            return Utils.toEmpleadoDto(empleadoUpdated, Utils.toEmpresaDto(empleadoUpdated.getEmpresa()),
                    Optional.ofNullable(empleadoUpdated.getRoles())
                            .map(roles -> roles.stream().map(Utils::toRolDto).collect(Collectors.toList())).orElse(null));
        }*/
        return null;
    }

    @Override
    public String eliminar(Long idEmpleado) {
        Optional<Empleado> encontrado = empleadoRepository.findById(idEmpleado);
        if (encontrado.isPresent()) {
            empleadoRepository.deleteById(idEmpleado);
            return "Empleado eliminado correctamente";
        }
        return "Empleado no se encuentra registrado";

    }
}
