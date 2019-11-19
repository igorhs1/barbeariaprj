package com.example.barbeariaprj2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.activity.MainActivity;
import com.example.barbeariaprj2.model.Feed;

import java.util.List;

public class AdapterFeeds extends RecyclerView.Adapter<AdapterFeeds.MyViewHolder> {

    private Context context;
    private List<Feed> feeds;
    private ImageView imgExcluirFeed;

    public AdapterFeeds(Context context, List<Feed> feeds){
        this.context = context;
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemFeeds = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_feeds, parent, false);

        imgExcluirFeed = itemFeeds.findViewById(R.id.imgViewCancelFeeds);

        if(MainActivity.usuarioLogado.getId() > 1){
            imgExcluirFeed.setVisibility(View.GONE);
        }

        return new MyViewHolder(itemFeeds);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (feeds != null) {
            holder.setMensagem.setText(feeds.get(position).getMensagem());
            holder.setData.setText(feeds.get(position).getData());
            holder.setHora.setText(feeds.get(position).getHora());
        }else {

            holder.setMensagem.setText("TESTE");
            holder.setData.setText("07/11/2019");
            holder.setHora.setText("07:00");
        }



    }

    @Override
    public int getItemCount() {
        if(feeds != null && feeds.size() >= 1){
            return feeds.size();
        }
        return 0;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        EditText setMensagem;
        TextView setData;
        TextView setHora;
        ImageView imgExcluir;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            setMensagem =  itemView.findViewById(R.id.etMensagemFeed);
            setData = itemView.findViewById(R.id.txtDataFeed);
            setHora = itemView.findViewById(R.id.txtHoraFeed);

        }


    }

    public void removerHorario(int position){
        this.feeds.remove(position);
        notifyDataSetChanged();
    }

    public void atualizarRecycler(List<Feed> feedsList){
        this.feeds.clear();
        this.feeds = feedsList;
        this.notifyDataSetChanged();
    }

}
