package com.bruno.pizzaplanety;

//import java.sql.SQLClientInfoException;

//import com.bruno.pizzaplanety.R.string;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
//import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
//import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaEntrar extends Activity  {
	Button btnEntrarTelaEntrar;
	Button btnCadastrarTelaEntrar;
	public String Email;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_entrar);
		
     }
	public void btnEntraEntrar(View v){
		EditText txtEmailEntrar = (EditText) findViewById(R.id.txtEmailTelaEntrar);
		EditText txtSenhaEntrar = (EditText) findViewById(R.id.txtSenhaTelaEntrar);
		String user =txtEmailEntrar.getText().toString();
		String senha =txtSenhaEntrar.getText().toString();
		
		if(Login(user,senha)!=0){
			Toast.makeText(getBaseContext(), "Bem-vindo!", Toast.LENGTH_SHORT).show();
			Intent it = new Intent (getBaseContext(),TelaPedido.class);
			Email = txtEmailEntrar.getText().toString();
			startActivity(it);
			finish();
		}else{
			Toast.makeText(getBaseContext(), "Senha ou Email invalido", Toast.LENGTH_SHORT).show();
		}
	}
	public void btnCadastrarEntrar(View v){
		Intent it = new Intent (getBaseContext(), TelaCadastro.class);
		startActivity(it);
	}
	
	public int Login(String username,String password)
	{
		SQLiteDatabase db = openOrCreateDatabase("Agenda.db",Context.MODE_PRIVATE , null);
	    String[] selectionArgs = new String[]{username, password};
	    try
	    {
	        int i = 0;
	        Cursor c = null;
	        c = db.rawQuery("select * from agenda where email=? and senha=?", selectionArgs);
	        c.moveToFirst();
	        i = c.getCount(); 
	        c.close(); 
	        return i;
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	
}
