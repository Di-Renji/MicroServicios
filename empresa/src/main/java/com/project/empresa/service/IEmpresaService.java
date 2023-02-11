package com.project.empresa.service;

import com.project.empresa.dto.EmpresaDto;

import java.util.List;

public interface IEmpresaService {

    public EmpresaDto registrar(EmpresaDto empresa);
    public EmpresaDto actualizar(Long id, EmpresaDto empresaDto);
    public List<EmpresaDto> listar();
    public EmpresaDto buscar(Long id);
    public String eliminar(Long id);

}
