package com.root.jefersonguido.cinema.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.root.jefersonguido.cinema.Adapters.Ingresso;
import com.root.jefersonguido.cinema.Adapters.DBAdapter;
import com.root.jefersonguido.cinema.Adapters.Singleton;
import com.root.jefersonguido.cinema.R;

public class DetalhesActivity extends Activity {
    static final int EDITAR_INGRESSO = 2;
    static final String IDDETALHESINGRESSO = "IDDETALHESINGRESSO";

    private DBAdapter datasource;
    private TextView txtnome, txttelefone, txtdata_nascimento, txtnome_filme, txtdata_hora_filme, txtsala, txtacento, txtvalor;
    private Button voltar, editar, excluir;
    private long idIngresso;
    private Ingresso ingresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtnome = (TextView) findViewById(R.id.detalhes_nome);
        txttelefone = (TextView) findViewById(R.id.detalhes_telefone);
        txtdata_nascimento = (TextView) findViewById(R.id.detalhes_data_nascimento);
        txtnome_filme = (TextView) findViewById(R.id.detalhes_nome_filme);
        txtdata_hora_filme = (TextView) findViewById(R.id.detalhes_data_hora_filme);
        txtsala = (TextView) findViewById(R.id.detalhes_sala);
        txtacento = (TextView) findViewById(R.id.detalhes_acento);
        txtvalor = (TextView) findViewById(R.id.detalhes_valor);

        iniciaObjetos(); // Inicia os objetos
        datasource = new DBAdapter(this); // Inicia o Banco

        Intent intent = getIntent();
        idIngresso = intent.getLongExtra("idIngresso", 1); // Recebe o código do carro selecionado na activity anterior
        datasource.open(); // Abre o banco

        ingresso = new Ingresso();
        ingresso = datasource.getIngresso(idIngresso); // Captura o carro selecionado do banco de dados

        // Debug do ID do banco recebido
        //Toast.makeText(getApplicationContext(), "id: " + carro.get_id(),Toast.LENGTH_SHORT); // Exibe o Código selecionado, Debug Test

        // Muda os textos dos objetos TextView
        txtnome.setText(""+ingresso.getNome().toString());
        txttelefone.setText(""+ingresso.getTelefone().toString());
        txtdata_nascimento.setText(""+ingresso.getData_nascimento().toString());
        txtnome_filme.setText(""+ingresso.getNomeFilme().toString());
        txtdata_hora_filme.setText(""+ingresso.getData_Hora_filme().toString());
        txtsala.setText(""+ingresso.getSala().toString());
        txtacento.setText(""+ingresso.getAcento().toString());
        txtvalor.setText(""+ingresso.getValor().toString());

        datasource.close(); // Fecha o banco

        // Volta à activity anterior
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Finaliza a activity
            }
        });

        // Inicia a activity que irá possibilitar a edição do carro
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlteraDados.class);
                intent.putExtra("IDDETALHESINGRESSO", idIngresso); // Envia a posção seleionada para a proxima activity
                startActivityForResult(intent, EDITAR_INGRESSO); // Inicia a proxima Activity
                finish(); // Finaliza a Activity
            }
        });

        // Exclui o carro com o id selecionado pelo usuário
        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datasource.open(); // Abre o banco de dados
                datasource.eliminarIngresso(idIngresso); // Elimina o carro do banco
                Singleton.getIntance().removeIdIngresso(idIngresso); // Elimina o carro da lista
                datasource.close(); // Fecha o banco de dados

                // Cria mensagem para o novo carro
                AlertDialog.Builder dialogo = new AlertDialog.Builder(DetalhesActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Exluindo dados referentes ao ingresso de: " + ingresso.getNome());
                dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish(); // Finaliza a activity
                    }
                });
                dialogo.show(); // Exibe o dialogo a cima que é executado pelo botão de excluir
            }
        });

    }

    private void iniciaObjetos() {
        voltar = (Button) findViewById(R.id.detalhes_btnvoltar);
        editar = (Button) findViewById(R.id.detalhes_btnEditar);
        excluir = (Button) findViewById(R.id.detalhes_btnExcluir);
    }

}
