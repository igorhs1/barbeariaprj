package com.example.barbeariaprj2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbeariaprj2.DAO.AgendamentoDAO;
import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.activity.MainActivity;
import com.example.barbeariaprj2.activity.TelaCadastro;
import com.example.barbeariaprj2.fragment.HorariosFragment;
import com.example.barbeariaprj2.model.Agendamento;

import java.util.List;

public class AdapterHorarios extends RecyclerView.Adapter<AdapterHorarios.MyViewHolder> {


    private Context context;
    private List<Agendamento> agendamentos;
    private ImageView imgAceitar;
    private TextView txtStatus;

    public AdapterHorarios(Context context, List<Agendamento> agendamentos) {
        this.context = context;
        this.agendamentos = agendamentos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemHorarios = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_horarios, parent, false);

        imgAceitar = itemHorarios.findViewById(R.id.imgViewAceitarAgendamento);
        txtStatus = itemHorarios.findViewById(R.id.textStatusHorarios);


        return new MyViewHolder(itemHorarios);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        if(agendamentos.get(position).getStatus().equals("Aprovado")){
            txtStatus.setTextColor(Color.parseColor("#77DD00"));
            imgAceitar.setVisibility(View.INVISIBLE);
        }else{
            imgAceitar.setVisibility(View.VISIBLE);
            txtStatus.setTextColor(Color.parseColor("#E42510"));
        }


        if (agendamentos != null) {
            holder.setData.setText(agendamentos.get(position).getData());
            holder.setHorario.setText(agendamentos.get(position).getHorario());
            holder.setStatus.setText(agendamentos.get(position).getStatus());
        }else {

            holder.setData.setText("07/11/2019");
            holder.setHorario.setText("07:00");
            holder.setStatus.setText("Pendente");
        }



    }

    @Override
    public int getItemCount() {

        if(agendamentos != null && agendamentos.size() >= 1){
            return agendamentos.size();
        }
        return 0;

        //return agendamentos.size();



    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView logoHorarios;
        TextView data;
        TextView horario;
        TextView status;

        ImageView imgViewAccept;
        ImageView imgViewCancel;
        TextView setData;
        TextView setHorario;
        TextView setStatus;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            setData = itemView.findViewById(R.id.textDataHorarios);
            setHorario = itemView.findViewById(R.id.textHorarioHorarios);
            setStatus = itemView.findViewById(R.id.textStatusHorarios);

        }
    }

    public void removerHorario(int position){
        this.agendamentos.remove(position);
        notifyDataSetChanged();
    }


    //atualiza lista de produtos do adapter
    public void atualizarRecycler(List<Agendamento> agendamentosList){
        this.agendamentos.clear();
        this.agendamentos = agendamentosList;
        this.notifyDataSetChanged();
    }

}
