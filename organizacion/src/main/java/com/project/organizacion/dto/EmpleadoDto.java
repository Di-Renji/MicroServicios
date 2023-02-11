package com.project.organizacion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpleadoDto implements Serializable {

    private String nombre;
    private String sexo;
    private String telefono;
    private Long empresaId;
    private EmpresaDto empresa;
    private List<Long> rolesId;
    private List<RolDto> roles;


}
