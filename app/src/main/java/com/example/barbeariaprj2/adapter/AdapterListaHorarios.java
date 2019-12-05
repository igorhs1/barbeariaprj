package com.example.barbeariaprj2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.activity.MainActivity;
import com.example.barbeariaprj2.model.Agendamento;

import java.util.List;

public class AdapterListaHorarios extends BaseAdapter {

    private Context context;
    private List<Agendamento> agendamentoList;

    public AdapterListaHorarios(Context context, List<Agendamento> agendamentoList) {
        this.context = context;
        this.agendamentoList = agendamentoList;
    }

    @Override
    public int getCount() {

        if (agendamentoList != null && agendamentoList.size() > 0) {
            return agendamentoList.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int posicao) {
        return agendamentoList.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void removerHorario(int posicao){
        this.agendamentoList.remove(posicao);
        notifyDataSetChanged();
    }


    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        View v = View.inflate(this.context, R.layout.adapter_horarios, null);

        ImageView logoHorarios;
        TextView data;
        TextView horario;
        TextView status;

        ImageView imgViewAccept = v.findViewById(R.id.imgViewAceitarAgendamento);
        ImageView imgViewCancel = v.findViewById(R.id.imgViewCancelHorarios);
        TextView setData = v.findViewById(R.id.textDataHorarios);
        TextView setHorario = v.findViewById(R.id.textHorarioHorarios);
        TextView setStatus = v.findViewById(R.id.textStatusHorarios);

        setData.setText(this.agendamentoList.get(posicao).getData());
        setHorario.setText(this.agendamentoList.get(posicao).getHorario());
        setStatus.setText(this.agendamentoList.get(posicao).getStatus());

        if(MainActivity.usuarioLogado.getId() == 1 && agendamentoList.get(posicao).getStatus().equals("Pendente")){
            imgViewCancel.setVisibility(View.VISIBLE);
            imgViewAccept.setVisibility(View.VISIBLE);
        }else
        {
            imgViewCancel.setVisibility(View.VISIBLE);
            imgViewAccept.setVisibility(View.INVISIBLE);
        }

        if(agendamentoList.get(posicao).getStatus().equals("Pendente")){
            setStatus.setTextColor(Color.parseColor("#E42510"));
        }else{
            setStatus.setTextColor(Color.parseColor("#77DD00"));
        }



        return v;
    }

    public void atualizarListViewFeeds(List<Agendamento> agendamentosList){
        this.agendamentoList.clear();
        this.agendamentoList = agendamentosList;
        this.notifyDataSetChanged();
    }
}
