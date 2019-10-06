package com.example.barbeariaprj2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbeariaprj2.R;

public class AdapterHorarios extends RecyclerView.Adapter<AdapterHorarios.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemHorarios = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_horarios, parent, false);

        return new MyViewHolder(itemHorarios);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData.setText("28/10/2019");
        holder.setHorario.setText("07:00");
        holder.setStatus.setText("Pendente");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView logoHorarios;
        TextView data;
        TextView horario;
        TextView status;

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



}
