package com.example.mi_agenda.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mi_agenda.entity.Cita;

import java.util.List;

@Dao
public interface CitaDAO {
    @Insert
    void crear(Cita cita);

    @Query("SELECT * FROM cita")
    List<Cita> findAll();


    @Query("SELECT * FROM cita where estado=:estado")
    Cita findByEstado(String estado);

    @Query("SELECT * FROM cita where fecha=:fecha")
    Cita findByFecha(String fecha);

    @Query("SELECT * FROM cita where Nombres=:name")
    Cita findByName(String name);

    @Query("SELECT * FROM cita where c_idPersona=:c_idPersona")
    Cita findByC_idPersona(String c_idPersona);
}
