package com.example.mi_agenda.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mi_agenda.persistencia.Tabla;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity(tableName = Tabla.PERSONA)
@NoArgsConstructor

public class Persona {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idPersona")
    private Integer idPersona;
    @ColumnInfo(name = "documento")
    private String documento;
    @ColumnInfo(name = "nombres")
    private String nombres;

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
