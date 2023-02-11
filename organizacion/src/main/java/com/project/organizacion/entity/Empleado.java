package com.project.organizacion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", length = 120)
    private String nombre;

    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    private String telefono;

    @ManyToOne
    private Empresa empresa;

    @ManyToMany
    @JoinTable(name = "EmpleadoRoles", joinColumns = @JoinColumn(name = "empleado_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> roles;

    public Empleado(Long id, String mombre){
        this.id = id;
        this.nombre = nombre;
    }

}
