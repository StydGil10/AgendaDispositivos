package com.example.mi_agenda.persistencia.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mi_agenda.entity.Cita;
import com.example.mi_agenda.entity.Persona;
import com.example.mi_agenda.persistencia.dao.CitaDAO;
import com.example.mi_agenda.persistencia.dao.PersonaDAO;


@Database(entities =
        {Cita.class, Persona.class

        }, version = DataBaseHelper.VERSION_BASE_DATOS,exportSchema = false)


public abstract class DataBaseHelper extends RoomDatabase{
    public static final int VERSION_BASE_DATOS=2; //Final: inicializado no puede ser cambiado
    public static final String NOMBRE_BASE_DATOS="Agenda de citas";
    private static DataBaseHelper intance;

    public static DataBaseHelper getSimpleDB(Context context){
        if(intance==null) {
            intance = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).build();
        }
        return intance;
    }

    public static DataBaseHelper getDBMainThread(Context context){
        if(intance==null) {
            intance = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).allowMainThreadQueries().build();
        }
        return intance;
    }
    public abstract PersonaDAO getPersonaDAO();
    public abstract CitaDAO getCitaDAO();
}
