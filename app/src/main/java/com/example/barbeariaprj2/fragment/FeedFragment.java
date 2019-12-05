package com.example.barbeariaprj2.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.barbeariaprj2.DAO.FeedDAO;
import com.example.barbeariaprj2.R;
import com.example.barbeariaprj2.RecyclerItemClickListener;
import com.example.barbeariaprj2.activity.MainActivity;
import com.example.barbeariaprj2.adapter.AdapterFeeds;
import com.example.barbeariaprj2.adapter.AdapterListaFeeds;
import com.example.barbeariaprj2.model.Feed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    private Context context;
    private FeedDAO feedDAO;

    private RecyclerView recyclerFeeds;
    private ListView listViewFeeds;
    private List<Feed> listaFeeds;
    private AdapterListaFeeds adapterListaFeeds;


    private Button btnEnviarNovoFeed;
    private EditText etMensagemFeed;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        this.listViewFeeds = rootView.findViewById(R.id.listViewFeeds);

        feedDAO = new FeedDAO(context);
        listaFeeds = feedDAO.listAllFeed();

        btnEnviarNovoFeed = rootView.findViewById(R.id.btnEnviarNovoFeed);
        etMensagemFeed = rootView.findViewById(R.id.etNovoFeed);

        //recyclerFeeds = rootView.findViewById(R.id.recyclerFeeds);


        if(MainActivity.usuarioLogado.getId() == 1){

        }else{
            btnEnviarNovoFeed.setVisibility(View.GONE);
            etMensagemFeed.setVisibility(View.GONE);
        }

        //configurar adapter do listview
        this.adapterListaFeeds = new AdapterListaFeeds(this.context, this.listaFeeds);
        this.listViewFeeds.setAdapter(this.adapterListaFeeds);


        btnEnviarNovoFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mensagemFeed = etMensagemFeed.getText().toString();


                if (!mensagemFeed.isEmpty()) {

                    Date dataHoraAtual = new Date();
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                    String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

                    Feed feed = new Feed();
                    feed.setMensagem(mensagemFeed);
                    feed.setData(data.toString());
                    feed.setHora(hora.toString());

                    long id = feedDAO.inserirFeed(feed);

                    if (id > 0) {
                        adapterListaFeeds.atualizarListViewFeeds(feedDAO.listAllFeed());
                        Toast.makeText(context, "Feed postado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Erro ao postar o feed", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "Por favor, preencha com uma mensagem", Toast.LENGTH_SHORT).show();
                }

                etMensagemFeed.setText("");
            }

        });

        //somente administrador pode clicar
        if(MainActivity.usuarioLogado.getId() == 1){

            this.listViewFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long l) {

                    final Feed feedSelecionado = (Feed) adapterListaFeeds.getItem(posicao);

                    AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(context, R.style.DialogTheme);

                    janelaDeEscolha.setTitle("Excluir Feed: ");
                    janelaDeEscolha.setMessage("Deseja excluir o feed selecionado?");

                    janelaDeEscolha.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    janelaDeEscolha.setNegativeButton("Excluir Feed", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            boolean excluiu = feedDAO.excluirFeed(feedSelecionado.getId());
                            dialogInterface.cancel();

                            if (excluiu == true) {
                                adapterListaFeeds.removerFeed(posicao);
                                Toast.makeText(context, "Feed excluido!", Toast.LENGTH_SHORT);

                            } else {
                                Toast.makeText(context, "Erro ao excluir feed!", Toast.LENGTH_SHORT);
                            }
                        }

                    });

                    janelaDeEscolha.create().show();


                }
            });


        }else{

            this.listViewFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long l) {

                    final Feed feedSelecionado = (Feed) adapterListaFeeds.getItem(posicao);

                    AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(context, R.style.DialogTheme);

                    janelaDeEscolha.setTitle("Feed selecionado: ");
                    janelaDeEscolha.setMessage(((Feed) adapterListaFeeds.getItem(posicao)).getMensagem());

                    janelaDeEscolha.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    janelaDeEscolha.create().show();


                }
            });

        }


        /*
        //configuar adapter
        final AdapterFeeds adapterFeeds = new AdapterFeeds(context, listaFeeds);

        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerFeeds.setLayoutManager(layoutManager);
        recyclerFeeds.setHasFixedSize(true);
        recyclerFeeds.setAdapter(adapterFeeds);


        btnEnviarNovoFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mensagemFeed = etMensagemFeed.getText().toString();


                if (!mensagemFeed.isEmpty()) {

                    Date dataHoraAtual = new Date();
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                    String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

                    Feed feed = new Feed();
                    feed.setMensagem(mensagemFeed);
                    feed.setData(data.toString());
                    feed.setHora(hora.toString());

                    long id = feedDAO.inserirFeed(feed);
                    if (id > 0) {
                        adapterFeeds.atualizarRecycler(feedDAO.listAllFeed());
                        Toast.makeText(context, "Feed postado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Erro ao postar o feed", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(context, "Por favor, preencha com uma mensagem", Toast.LENGTH_SHORT).show();
                }

                etMensagemFeed.setText("");
            }

        });


        if(MainActivity.usuarioLogado.getId() == 1) {

            //evento de click na lista
            recyclerFeeds.addOnItemTouchListener(
                    new RecyclerItemClickListener(context, recyclerFeeds, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, final int position) {

                            final Feed feedSelecionado = listaFeeds.get(position);

                            AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(context, R.style.DialogTheme);

                            janelaDeEscolha.setTitle("Excluir Feed: ");
                            janelaDeEscolha.setMessage("Deseja excluir o feed selecionado?");

                            janelaDeEscolha.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                            janelaDeEscolha.setNegativeButton("Excluir Feed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    boolean excluiu = feedDAO.excluirFeed(feedSelecionado.getId());
                                    dialogInterface.cancel();

                                    if (excluiu == true) {
                                        adapterFeeds.removerHorario(position);
                                        Toast.makeText(context, "Feed excluido!", Toast.LENGTH_SHORT);

                                    } else {
                                        Toast.makeText(context, "Erro ao excluir feed!", Toast.LENGTH_SHORT);
                                    }
                                }
                            });

                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                        }

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        }
                    })
            );

        }

        */

        return rootView;
    }



    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

}
