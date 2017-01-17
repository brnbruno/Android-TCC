package com.bruno.pizzaplanety;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.provider.ContactsContract.CommonDataKinds.Email;
/*import android.view.Menu;
import android.view.MenuItem;*/
import android.view.View;
import android.widget.Button;

public class TelaInicial extends Activity {

	Button btnEntrarTelaInicial;
	Button btnCadastrarTelaInicial;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_inicial);
		//Realizando a cria do BD
		SQLiteDatabase db = openOrCreateDatabase("Agenda.db",Context.MODE_PRIVATE , null);
	
		//Criado a tabela Banco
		StringBuilder sqlPizza = new StringBuilder();
		sqlPizza.append("CREATE TABLE IF NOT EXISTS [agenda] (");
		sqlPizza.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sqlPizza.append("nome VARCHAR(30) ,");
		sqlPizza.append("email VARCHAR(40) ,");
		sqlPizza.append("senha VARCHAR(16) ,");
		sqlPizza.append("cpf VARCHAR(14) ,");
		sqlPizza.append("celular VARCHAR(11) ,");
		sqlPizza.append("cep VARCHAR(10) ,");
		sqlPizza.append("endereco VARCHAR(50) ,");
		sqlPizza.append("complemento VARCHAR(20) ,");
		sqlPizza.append("bairro VARCHAR(50) ,");
		sqlPizza.append("nr VARCHAR(5) ,");
		sqlPizza.append("cidade VARCHAR(50) ,");
		sqlPizza.append("estado VARCHAR(2) );");	
		db.execSQL(sqlPizza.toString());
		
		
		
	
	}
	
	
	
	public void TelaEntrar(View v){
		Intent it = new Intent (getBaseContext(),TelaEntrar.class);
		RecebeJSP sinc = new RecebeJSP(this);
		sinc.execute(0);
		startActivity(it);
		finish();
	}
	
	public void TelaCadastrar(View v){
		Intent it = new Intent (getBaseContext(), TelaCadastro.class);
		startActivity(it);
		
	}
	
	

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_inicial, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
