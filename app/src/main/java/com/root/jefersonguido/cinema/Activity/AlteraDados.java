package com.root.jefersonguido.cinema.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.root.jefersonguido.cinema.Adapters.Ingresso;
import com.root.jefersonguido.cinema.Adapters.DBAdapter;
import com.root.jefersonguido.cinema.R;

public class AlteraDados extends AppCompatActivity {
    private TextView nome;
    private EditText telefone;
    private Button alterar;
    private DBAdapter datasource;
    private Ingresso ingresso = new Ingresso();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_dados);

        iniciaObjetos();
        alteraCarro();

    }

    public void iniciaObjetos(){
        nome = (TextView) findViewById(R.id.altera_nome);
        telefone = (EditText) findViewById(R.id.altera_telefone);
        alterar = (Button) findViewById(R.id.btn_alterar);
    }

    // Método para alterar um ingresso no Banco de Dados
    public void alteraCarro(){
        datasource = new DBAdapter(this); // Inicia o Banco

        Intent intent = getIntent(); // Faz a captura dos itens da intent que executou a Activity
        final long idIngresso = intent.getLongExtra("IDDETALHESINGRESSO", 1); // Recebe o código do ingresso selecionado na activity DetalhesActivity

        datasource.open(); // Abre o banco

        ingresso = datasource.getIngresso(idIngresso); // Captura o ingresso selecionado do banco de dados

        // Debug do ID do banco recebido
        //Toast.makeText(getApplicationContext(), "id: " + ingresso.get_id(),Toast.LENGTH_SHORT); // Exibe o Código selecionado, Debug Test

        // Muda os textos dos objetos TextView
        nome.setText(""+ ingresso.getNome());
        telefone.setText(""+ ingresso.getTelefone());
        alterar.setText("Alterar");

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datasource.open(); // Abre o banco de dados

                // Adiciona o ingresso
                ingresso = datasource.createIngresso(
                        nome.getText().toString(),
                        telefone.getText().toString(),
                        ingresso.getData_nascimento(),
                        ingresso.getNomeFilme(),
                        ingresso.getData_Hora_filme(),
                        ingresso.getSala(),
                        ingresso.getAcento(),
                        ingresso.getValor());

                datasource.eliminarIngresso(idIngresso);

                datasource.close(); // Fecha o banco de dados

                // Cria mensagem para o novo ingresso
                AlertDialog.Builder dialogo = new AlertDialog.Builder(AlteraDados.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Alterando Telefone do Proprietário: " + ingresso.getNome());
                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
                dialogo.show(); // Mostra o dialogo
            }
        });
    }
}
