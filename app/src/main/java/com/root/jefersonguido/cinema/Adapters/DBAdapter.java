package com.root.jefersonguido.cinema.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by Jeferson Eduardo on 03/10/2017.
 */

public class DBAdapter {
	private SQLiteDatabase database;
	private DBHelper dbHelper;

    private String[] allColumns = {
			DBHelper.ID,
			DBHelper.NOME,
			DBHelper.TELEFONE,
			DBHelper.DATA_NASCIMENTO,
			DBHelper.NOME_FILME,
			DBHelper.DATA_HORA_FILME,
			DBHelper.SALA,
			DBHelper.ACENTO,
			DBHelper.VALOR};

	public DBAdapter(Context context) {
		dbHelper = new DBHelper(context);
	}

	// Dá permissão de escrita no banco
	public void open() throws SQLiteException{
		database = dbHelper.getWritableDatabase();
	}

	// Fecha o banco de dados
	public void close(){
		dbHelper.close();
	}

	// Método que devolve o ingresso por um cursor
	private Ingresso cursorToIngresso(Cursor cursor){

		// Faz a captura do ingresso como se fosse um array utilizando o cursor
		Ingresso ingresso = new Ingresso(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        Singleton.getIntance().addIdIngresso(ingresso.get_id());

		// Retorna o objeto ingresso
		return ingresso;
	}

	// Método para criar um novo ingresso
	public Ingresso createIngresso (String nome, String telefone, String data_nascimento, String nomeFilme, String data_hora_filme, String sala, String acento, String valor){

		// Faz a captura dos dados do ingresso
		ContentValues values = new ContentValues();
		values.put(DBHelper.NOME, nome);
		values.put(DBHelper.TELEFONE, telefone);
		values.put(DBHelper.DATA_NASCIMENTO, data_nascimento);
        values.put(DBHelper.NOME_FILME, nomeFilme);
        values.put(DBHelper.DATA_HORA_FILME, data_hora_filme);
		values.put(DBHelper.SALA, sala);
		values.put(DBHelper.ACENTO, acento);
		values.put(DBHelper.VALOR, valor);

		long insertId = database.insert(DBHelper.TABLE_NAME, null, values); // ID do item
		Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, DBHelper.ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
		return cursorToIngresso(cursor); // Retorna o ingresso
	}

	// Elimina o ingresso selecionado
	public void eliminarIngresso(long idIngresso){
		database.delete(DBHelper.TABLE_NAME, DBHelper.ID + " = " + idIngresso, null);
	}

	// Retorna todos os ingressos
	public Cursor getIngressos(){
		Cursor cursor = database.rawQuery("select _id, nome, telefone, data_nascimento, nome_filme, data_hora_filme, sala, acento, valor from ingresso", null);
		return cursor;
	}

	// Faz a captura do ingresso selecionado pelo ID
	public Ingresso getIngresso (long idIngresso){
		Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, DBHelper.ID + " = " + idIngresso, null, null, null, null);
		cursor.moveToFirst();
		return cursorToIngresso(cursor);
	}

}
