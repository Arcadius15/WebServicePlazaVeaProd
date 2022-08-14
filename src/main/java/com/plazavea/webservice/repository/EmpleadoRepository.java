package com.plazavea.webservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.plazavea.webservice.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,String> {

    @Query(value = "SELECT e.* FROM empleado e INNER JOIN rol_usuario ru ON e.id_usuario = ru.id_usuario WHERE ru.id_rol = 2",nativeQuery = true)
    List<Empleado> findAdmins();
}
