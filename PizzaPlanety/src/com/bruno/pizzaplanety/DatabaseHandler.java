package com.bruno.pizzaplanety;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("DefaultLocale") 
public class DatabaseHandler extends SQLiteOpenHelper {
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;
	// Database Name
	private static final String DATABASE_NAME = "Agenda.db";
	// Agenda table name
	private static final String TABLE_AGENDA = "agenda";
	// ContAgenda Table Columns names
	private static final String KEY_ID     = "_id";
	private static final String KEY_NOME   = "nome";
	private static final String KEY_EMAIL  = "email";
	private static final String KEY_FONE   = "celular";
	private static final String KEY_CPF    =  "cpf";
	private static final String KEY_CEP    = "cep";
	private static final String KEY_ENDERECO = "endereco";
	private static final String KEY_COMPLEMENTO = "complemento";
	private static final String KEY_BAIRRO = "bairro";
	private static final String KEY_NR = "nr";
	private static final String KEY_CIDADE = "cidade";
	private static final String KEY_ESTADO = "estado";

	// Produto table name
	private static final String TABLE_PROD = "produto";
	// Produto Table Columns names
	private static final String KEY_PRO_ID    = "_id";
	private static final String KEY_PRO_DESC  = "descricao";
	private static final String KEY_PRO_CAT   = "categoria";
	private static final String KEY_PRO_VL    = "valor";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	public void onCreate(SQLiteDatabase db) {
		//Criar Tabela agenda
		String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_AGENDA + " ("
				+ KEY_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+ KEY_NOME  + " VARCHAR(40), "
				+ KEY_EMAIL + " VARCHAR(30), "
				+ KEY_FONE  + " VARCHAR(11), "
				+ KEY_CPF   + " VARCHAR(14), "
				+ KEY_CEP   + " VARCHAR(10), "
			+ KEY_ENDERECO  + " VARCHAR(50), "
			+KEY_COMPLEMENTO + "VARCHAR(50), "
				+ KEY_BAIRRO + " VARCHAR(50), "
				+ KEY_NR     + " VARCHAR(5), "
				+ KEY_CIDADE + "VARCHAR(50), "
				+ KEY_ESTADO + "VARCHAR(2) )";
		db.execSQL(CREATE_TABLE);
		
		//Criar Tabela produto
		CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PROD + " ("
				+ KEY_PRO_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+ KEY_PRO_DESC + " VARCHAR(40), "
				+ KEY_PRO_CAT  + " VARCHAR(20), "
				+ KEY_PRO_VL   + " NUMERIC(6,2));";
		db.execSQL(CREATE_TABLE);	
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);
		// Create tables again
		onCreate(db);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROD);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	@SuppressLint("DefaultLocale") // Adding new contact
	void addContact(String nome, String email, String fone,
			String cep,String cidade,String endereco,String bairro,
			String complemento,String cpf,String uf, String nr,String senha) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NOME,  nome);                 
		values.put(KEY_EMAIL, email);                 
		values.put(KEY_FONE,  fone);
		values.put(KEY_CPF, cpf);
		values.put(KEY_CEP,   cep);
		values.put(KEY_ENDERECO, endereco);
		values.put(KEY_COMPLEMENTO, complemento);
		values.put(KEY_BAIRRO, bairro);
		values.put(KEY_NR, nr);
		values.put(KEY_CIDADE, cidade);
		values.put(KEY_ESTADO, uf);
		

		// Inserting Row
		db.insert(TABLE_AGENDA, null, values);
		db.close(); // Closing database connecting
	}


	// Updating single contact
	public int updateContact(int id, String nome, String email, String fone, String sexo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NOME,  nome.toUpperCase());                 
		values.put(KEY_EMAIL, email.toLowerCase());                 
		values.put(KEY_FONE,  fone);


		// updating row
		return db.update(TABLE_AGENDA, values, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	// Deleting single contact
	public void deleteContact(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_AGENDA, KEY_ID + " = ?",
				new String[] { String.valueOf(id)});
		db.close();
	}

	// Deleting all contact
	public void deleteAllContact() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_AGENDA);
		db.close();
	}
	

	// Getting contacts Count
	public int getContactsCount() {

		   String countQuery = "SELECT * FROM " + TABLE_AGENDA;
		   SQLiteDatabase db = this.getReadableDatabase();
		   Cursor cursor = db.rawQuery(countQuery, null);
		   int count = 0;
		   try {
		      if (cursor.moveToFirst()) {
		         count = cursor.getCount();
		      }
		      return count;
		   }
		   finally {
		      if (cursor != null) {
		         cursor.close();
		      }
		   }
	}

	
	// Getting All Contacts
	public List<Agenda> getAllContacts() {

		List<Agenda> contactList = new ArrayList<Agenda>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_AGENDA;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Agenda agenda = new Agenda();
				//agenda.setID(Integer.parseInt(cursor.getString(0)));
				agenda.setNome(cursor.getString(1));
				agenda.setEmail(cursor.getString(2));
				agenda.setSenha(cursor.getString(3));
				agenda.setCpf(cursor.getString(4));
				//agenda.setSexo(cursor.getString(4));
				agenda.setFone(cursor.getString(5));
				agenda.setCep(cursor.getString(6));
				agenda.setEndereco(cursor.getString(7));
				agenda.setComplemento(cursor.getString(8));
				agenda.setBairro(cursor.getString(9));
				agenda.setNr(cursor.getString(10));
				agenda.setCidade(cursor.getString(11));
				agenda.setUf(cursor.getString(12));
				// Adding contact to list
				contactList.add(agenda);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}
	
	
}
