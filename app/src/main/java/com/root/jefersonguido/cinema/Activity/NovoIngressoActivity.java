package com.root.jefersonguido.cinema.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.root.jefersonguido.cinema.Adapters.Ingresso;
import com.root.jefersonguido.cinema.Adapters.DBAdapter;
import com.root.jefersonguido.cinema.R;

public class NovoIngressoActivity extends Activity{
    private EditText nome, telefone, data_nascimento;
    private Spinner nome_filme, valor, acento;
    private TextView data_hora_filme, sala;
    private String fil = "", val = "", acen = "";

    private Ingresso ingresso;

    private Button cadastrar;
    private DBAdapter datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_ingresso);

        iniciaObjetos();
        cadastraIngresso();

        }

    public void iniciaObjetos(){
        ingresso = new Ingresso();

        nome = (EditText) findViewById(R.id.novoIngresso_edt_nome);
        telefone = (EditText) findViewById(R.id.novoIngresso_edt_telefone);
        data_nascimento = (EditText) findViewById(R.id.novoIngresso_edt_data_nasc);

        nome_filme = (Spinner) findViewById(R.id.novoIngresso_spinner_nome_filme);
        ArrayAdapter adapter_nome_filme = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.filmes));
        nome_filme.setAdapter(adapter_nome_filme);

        data_hora_filme = (TextView) findViewById(R.id.novoIngresso_txt_data_hora_filme);
        sala = (TextView) findViewById(R.id.novoIngresso_txt_sala);
        acento = (Spinner) findViewById(R.id.novoIngresso_spinner_acento);
        ArrayAdapter adapter_acento = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.acento));
        acento.setAdapter(adapter_acento);

        valor = (Spinner) findViewById(R.id.novoIngresso_spinner_valor);
        ArrayAdapter adapter_valor = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.valor));
        valor.setAdapter(adapter_valor);

        cadastrar = (Button) findViewById(R.id.btn_cadastrar);
    }
    public void capturaDados(){

        nome_filme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long id) {

                String filme = (String) adapterView.getItemAtPosition(posicao); // Captura o item selecionado

                switch (filme){
                    case "Wolverine: Origens - 2D":
                        data_hora_filme.setText("Quarta 22/11/17, 18:30Hr");
                        sala.setText("Sala 1");
                        fil = filme.toString();
                        Toast.makeText(getApplicationContext(), "Filme: " + filme +"\nHora: " + data_hora_filme.getText().toString() + "\nSala: " + sala.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case "A Origem - 3D":
                        data_hora_filme.setText("Quarta 22/11/17, 20:00Hr");
                        sala.setText("Sala 2");
                        fil = filme.toString();
                        Toast.makeText(getApplicationContext(), "Filme: " + filme +"\nHora: " + data_hora_filme.getText().toString() + "\nSala: " + sala.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case "Meu malvado Favorito - 2D":
                        data_hora_filme.setText("Quarta 22/11/17, 21:30Hr");
                        sala.setText("Sala 1");
                        fil = filme.toString();
                        Toast.makeText(getApplicationContext(), "Filme: " + filme +"\nHora: " + data_hora_filme.getText().toString() + "\nSala: " + sala.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        valor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long id) {
                String value = (String) adapterView.getItemAtPosition(posicao); // Captura o item selecionado

                switch (value){
                    case "Inteira R$30.00":
                        val = value.toString();
                        Toast.makeText(getApplicationContext(), "Valor: " + value, Toast.LENGTH_LONG).show();
                        break;
                    case "Estudante R$15.00":
                        val = value.toString();
                        Toast.makeText(getApplicationContext(), "Valor: " + value, Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        acento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long id) {
                String acent = (String) adapterView.getItemAtPosition(posicao);
                acen = acent.toString();
                Toast.makeText(getApplicationContext(), "Acento: " + acent, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    // Método para cadastrar um novo Ingresso no Banco de Dados
    public void cadastraIngresso(){
        datasource = new DBAdapter(this); // Inicia o Banco

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                capturaDados();

                // Cria mensagem para o novo carro
                AlertDialog.Builder dialogo = new AlertDialog.Builder(NovoIngressoActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage(
                        "Proprietário: " + nome.getText().toString()
                        + "\nTelefone: " + telefone.getText().toString()
                        + "\nData Nasc.: " + data_nascimento.getText().toString()
                        + "\nFilme: " + fil.toString()
                        + "\nHorario do Filme:\n" + data_hora_filme.getText().toString()
                        + "\nSala: " + sala.getText().toString()
                        + "\nAcento: " + acen.toString()
                        + "\nValor: " + val.toString());

                dialogo.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        datasource.open(); // Abre o banco de dados

                        // Adiciona o Ingresso
                        ingresso = datasource.createIngresso(
                                nome.getText().toString(),
                                telefone.getText().toString(),
                                data_nascimento.getText().toString(),
                                fil.toString(),
                                data_hora_filme.getText().toString(),
                                sala.getText().toString(),
                                acen.toString(),
                                val.toString());
                        datasource.close(); // Fecha o banco de dados

                        finish(); // Finaliza a classe, voltando para a tela principal
                    }
                });
                dialogo.show(); // Mostra o dialogo
            }
        });
    }

}
