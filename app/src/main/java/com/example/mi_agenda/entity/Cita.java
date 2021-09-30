package com.example.mi_agenda.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mi_agenda.persistencia.Tabla;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity(tableName = Tabla.CITA)
@NoArgsConstructor

public class Cita {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idCita")
    private Integer idCita;
    @ColumnInfo(name="c_idPersona")
    private String c_idPersona;
    @ColumnInfo(name="Nombres")
    private String name;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "estado")
    private String estado;
    @ColumnInfo(name = "observacion")
    private String observacion;


    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getC_idPersona() {
        return c_idPersona;
    }

    public void setC_idPersona(String c_idPersona) {
        this.c_idPersona = c_idPersona;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
