package com.example.barbeariaprj2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.activity.MainActivity;
import com.example.barbeariaprj2.model.Feed;

import java.util.List;

public class AdapterListaFeeds extends BaseAdapter {

    private Context context;
    private List<Feed> feedsList;

    public AdapterListaFeeds(Context context, List<Feed> feedsList) {
        this.context = context;
        this.feedsList = feedsList;
    }

    @Override
    public int getCount() {

        if (feedsList != null && feedsList.size() > 0) {
            return feedsList.size();
        }else{
            return 0;
        }

    }

    @Override
    public Object getItem(int posicao) {
        return feedsList.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void removerFeed(int posicao){
        this.feedsList.remove(posicao);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        View v = View.inflate(this.context, R.layout.adapter_feeds, null);

        EditText setMensagem = v.findViewById(R.id.etMensagemFeed);
        TextView setData = v.findViewById(R.id.txtDataFeed);
        TextView setHora = v.findViewById(R.id.txtHoraFeed);
        ImageView imgExcluir = v.findViewById(R.id.imgViewCancelFeeds);


        setMensagem.setText(this.feedsList.get(posicao).getMensagem());
        setData.setText(this.feedsList.get(posicao).getData());
        setHora.setText(this.feedsList.get(posicao).getHora());


        if(MainActivity.usuarioLogado.getId() == 1){
            imgExcluir.setVisibility(View.VISIBLE);
        }else
        {
            imgExcluir.setVisibility(View.GONE);
        }


        return v;
    }

    public void atualizarListViewFeeds(List<Feed> feedsList){
        this.feedsList.clear();
        this.feedsList = feedsList;
        this.notifyDataSetChanged();
    }
}
