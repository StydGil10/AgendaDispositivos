package com.example.mi_agenda.ui.citas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mi_agenda.R;
import com.example.mi_agenda.databinding.FragmentCitaBinding;
import com.example.mi_agenda.entity.Cita;
import com.example.mi_agenda.persistencia.room.DataBaseHelper;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.eugeniomarletti.kotlin.metadata.shadow.resolve.scopes.MemberScope;


public class CitaFragment extends Fragment {

    private CitaViewModel citaViewModel;
    private FragmentCitaBinding binding;
    private Cita cita;
    private EditText identificacion;
    private EditText nombre;
    private EditText fecha;
    private EditText observacion;
    private EditText estado;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        citaViewModel =
                new ViewModelProvider(this).get(CitaViewModel.class);

        binding = FragmentCitaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        identificacion = binding.txtIdentificacion;
        nombre = binding.txtNombres;
        fecha = binding.txtFecha;
        observacion = binding.txtObservacion;
        estado = binding.txtEstado;
        final Button btnCitar = binding.btnCitar;
        cita = new Cita();



        citaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnCitar.setOnClickListener(v -> {
            cita.setC_idPersona(identificacion.getText().toString());
            cita.setName(nombre.getText().toString());
            cita.setFecha(fecha.getText().toString());
            cita.setObservacion(observacion.getText().toString());
            cita.setEstado(estado.getText().toString());
            Cita fechaIngreso= DataBaseHelper.getDBMainThread(getContext()).getCitaDAO().findByFecha(cita.getFecha());
            Cita personaNew= DataBaseHelper.getDBMainThread(getContext()).getCitaDAO().findByC_idPersona(cita.getC_idPersona());
            if(fechaIngreso==null && personaNew==null){
                insertarInformacion();
                Toast.makeText(getContext(),getText(R.string.cita_exitosa), Toast.LENGTH_LONG).show();
            }
            else if(nombre.getText().toString().isEmpty()|identificacion.getText().toString().isEmpty()|fecha.getText().toString().isEmpty()|estado.getText().toString().isEmpty()){
                Toast.makeText(getContext(),getText(R.string.llene_campos), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getContext(),getText(R.string.no_se_puede), Toast.LENGTH_LONG).show();
            }



        });
        return root;
    }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
        private void insertarInformacion () {
            Observable.fromCallable(() -> {
                DataBaseHelper.getSimpleDB(getContext()).getCitaDAO().crear(cita);
                return cita;
        }).subscribeOn(Schedulers.computation()).subscribe();


    }




}