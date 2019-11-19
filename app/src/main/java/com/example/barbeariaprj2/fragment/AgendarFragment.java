package com.example.barbeariaprj2.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbeariaprj2.DAO.AgendamentoDAO;
import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.activity.MainActivity;
import com.example.barbeariaprj2.model.Agendamento;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgendarFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private AgendamentoDAO agendamentoDAO;

    private Context context;
    private EditText dataText;
    private TextView tvSelectedDate;

    private Spinner spinServicos;
    private Spinner spinHorarios;
    private Button btnEfetuarAgendamento;



    public AgendarFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_agendar, container, false);

        agendamentoDAO = new AgendamentoDAO(context);


        btnEfetuarAgendamento = rootView.findViewById(R.id.btnEfetuarAgendamento);

        dataText = rootView.findViewById(R.id.tvSelectedDate);


        rootView.findViewById(R.id.btnData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });


        spinServicos = rootView.findViewById(R.id.spinServicos);
        spinHorarios = rootView.findViewById(R.id.spinHorarios);

        spinHorarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                liberarBtnAgendar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinServicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                liberarBtnAgendar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnEfetuarAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarCadastroAgendamento();

                btnEfetuarAgendamento.setBackgroundColor(Color.parseColor("#808080"));
                btnEfetuarAgendamento.setClickable(false);
                btnEfetuarAgendamento.setEnabled(false);


            }
        });

        // Inflate the layout for this fragment
        return rootView;

    }

    private void showDatePickerDialog(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                R.style.DialogTheme,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }


    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        i1 = i1 + 1;
        String date = i2 + "/" + i1 + "/" + i;
        dataText.setText(date);
        dataText.setBackgroundColor(Color.parseColor("#EB6E00"));
        liberarBtnAgendar();
    }

    public void liberarBtnAgendar(){


        if( (spinServicos.getSelectedItemPosition() != 0) && (spinHorarios.getSelectedItemPosition() != 0) && (!dataText.getText().toString().equals("")) ){

            btnEfetuarAgendamento.setBackgroundColor(Color.parseColor("#ED9301"));
            btnEfetuarAgendamento.setClickable(true);
            btnEfetuarAgendamento.setEnabled(true);

        }else{
            btnEfetuarAgendamento.setBackgroundColor(Color.parseColor("#808080"));
            btnEfetuarAgendamento.setClickable(false);
            btnEfetuarAgendamento.setEnabled(false);
        }


    }

    public void validarCadastroAgendamento(){



        //recuperando texto dos campos
        String dataAgendamento = dataText.getText().toString();
        String horario = spinHorarios.getSelectedItem().toString();
        String status = "Pendente";


        Agendamento agendamento = new Agendamento();
        agendamento.setData(dataAgendamento);
        agendamento.setHorario(horario);
        agendamento.setStatus(status);
        agendamento.setIdUsuario(MainActivity.usuarioLogado.getId().toString());

        long id = agendamentoDAO.inserirAgendamento(agendamento);
        Toast.makeText(context, "Agendamento efetuado com sucesso!", Toast.LENGTH_SHORT).show();



    }

}
