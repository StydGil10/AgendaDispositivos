package com.example.mi_agenda.ui.personas;

import android.os.AsyncTask;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mi_agenda.R;
import com.example.mi_agenda.databinding.FragmentPersonaBinding;
import com.example.mi_agenda.databinding.FragmentPersonaBinding;
import com.example.mi_agenda.entity.Persona;
import com.example.mi_agenda.persistencia.room.DataBaseHelper;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PersonaFragment extends Fragment {

    private PersonaViewModel personaViewModel;
    private FragmentPersonaBinding binding;
    private Persona persona;
    private EditText documento;
    private EditText nombres;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personaViewModel =
                new ViewModelProvider(this).get(PersonaViewModel.class);

        binding = FragmentPersonaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        documento = binding.txtDocumento;
        nombres = binding.txtNombre;
        final Button btnAceptar = binding.btnAceptar;
        persona = new Persona();


        personaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnAceptar.setOnClickListener(v -> {
            persona.setNombres(nombres.getText().toString());
            persona.setDocumento(documento.getText().toString());
            Persona personaConsultada= DataBaseHelper.getDBMainThread(getContext()).getPersonaDAO().findByDocumento(persona.getDocumento());
            if(personaConsultada==null){
                insertarInformacion();
                Toast.makeText(getContext(),getText(R.string.informacion_exitosa), Toast.LENGTH_SHORT).show();
                borrarInformacion();
            }else{
                Toast.makeText(getContext(),getText(R.string.ya_existe), Toast.LENGTH_LONG).show();

            }


        });
        return root;
    }

    private void borrarInformacion(){
        persona=new Persona();
        documento.setText("");
        nombres.setText("");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void insertarInformacion() {
        Observable.fromCallable(() -> {
            DataBaseHelper.getSimpleDB(getContext()).getPersonaDAO().crear(persona);
            return persona;
        }).subscribeOn(Schedulers.computation()).subscribe();


    }
}