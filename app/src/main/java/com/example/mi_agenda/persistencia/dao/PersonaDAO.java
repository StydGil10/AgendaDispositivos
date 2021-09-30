package com.example.mi_agenda.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mi_agenda.entity.Persona;

import java.util.List;

@Dao
public interface PersonaDAO {
    @Insert
    void crear(Persona persona);

    @Query("SELECT * FROM persona")
    List<Persona> findAll();


    @Query("SELECT * FROM persona where documento=:documento")
    Persona findByDocumento(String documento);
}
