package com.project.organizacion.repository;

import com.project.organizacion.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findByNombreContaining(String text);

    @Query(value = "select new Empleado(p.id,p.nombre) from Empleado p")
    List<Empleado> listarEmpleadosJpa();

    @Query(value = "select * from Empleados", nativeQuery = true)
    List<Empleado> listarEmpleadosSql();


}
