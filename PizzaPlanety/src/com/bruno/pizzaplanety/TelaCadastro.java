package com.bruno.pizzaplanety;



import java.util.regex.Pattern;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends Activity{
	
	Button btnCadastrarCadastro;
	EditText txtEmailCadastro;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_actv);
	}
	public void btnCadastrarTelaCadastro(View v){
		EditText txtNomeCadastro        = (EditText) findViewById(R.id.txtNomeCadastro);
		EditText txtEmailCadastro       = (EditText) findViewById(R.id.txtEmailCadastro);
		EditText txtSenhaCadastro       = (EditText) findViewById(R.id.txtSenhaCadastro);
		EditText txtCpfCadastro         = (EditText) findViewById(R.id.txtCpfCadastro);
		EditText txtCelularCadastro     = (EditText) findViewById(R.id.txtCelularCadastro);
		EditText txtCepCadastro         = (EditText) findViewById(R.id.txtCepCadastro);
		EditText txtEnderecoCadastro    = (EditText) findViewById(R.id.txtEnderecoCadastro);
		EditText txtComplementeCadastro = (EditText) findViewById(R.id.txtComplementoCadastro);
		EditText txtNumeroCadastro      = (EditText) findViewById(R.id.txtNumeroCadastro);
		EditText txtBairroCadastro      = (EditText) findViewById(R.id.txtBairroCadastro);
		EditText txtCidadeCadastro      = (EditText) findViewById(R.id.txtCidadeCadastro);
		EditText txtUfCadastro      = (EditText) findViewById(R.id.txtUfCadastro);
		
		//Validaçoes
		
		final String emailText = txtEmailCadastro.getText().toString();
		final String cpf = txtCpfCadastro.getText().toString();
		
		if(txtNomeCadastro.getText().toString().length() <= 0){
			txtNomeCadastro.setError("Preecha o campo Nome!");
			txtNomeCadastro.requestFocus();
		}else if(!EMAIL_ADDRESS_PATTERN.matcher(emailText).matches() 
				|| txtEmailCadastro.getText().toString().length() <= 0){
			txtEmailCadastro.setError("Campo Email Incorreto ou em branco");
			txtEmailCadastro.requestFocus();
		}else if (txtSenhaCadastro.getText().toString().length()<=5){
			txtSenhaCadastro.setError("Senha Muito Curta, mim 6 caracteres ");
			txtSenhaCadastro.requestFocus();
		}else if(!validaCpf.isCPF(cpf)&& cpf.length()!=11){
			txtCpfCadastro.setError("Cpf Inválido");
			txtCpfCadastro.requestFocus();
		}else if(txtCelularCadastro.getText().toString().length()!=11){
			txtCelularCadastro.setError("DDD + numero deve ser preenchido");
			txtCelularCadastro.requestFocus();
		}else if(txtCepCadastro.getText().length() !=8){
			txtCepCadastro.setError("Cep Incorreto");
			txtCepCadastro.requestFocus();
		}else if(txtEnderecoCadastro.getText().toString().length()<=0){
			txtEnderecoCadastro.setError("Endereço não preenchido!");
			txtEnderecoCadastro.requestFocus();
		}else if(txtNumeroCadastro.getText().toString().length()<=0){
			txtNumeroCadastro.setError("Numero não preenchido");
			txtNumeroCadastro.requestFocus();
		}else if(txtBairroCadastro.getText().toString().length() <=0){
			txtBairroCadastro.setError("Bairro não preenchido");
			txtBairroCadastro.requestFocus();
		}else if(txtCidadeCadastro.getText().toString().length()<=0){
			txtCidadeCadastro.setError("Cidade não preenchida");
			txtCidadeCadastro.requestFocus();
		}else if (txtUfCadastro.getText().toString().length()!=2){
			txtUfCadastro.setError("Estado preenchido incorretamente, utilize sua sigla");
			txtUfCadastro.requestFocus();
		}
		else{
			try{
				SQLiteDatabase db = openOrCreateDatabase("Agenda.db",Context.MODE_PRIVATE , null);
				ContentValues cvt = new ContentValues();
				cvt.put("nome", txtNomeCadastro.getText().toString());
				cvt.put("email",txtEmailCadastro.getText().toString());
				cvt.put("senha",txtSenhaCadastro.getText().toString());
				cvt.put("cpf",txtCpfCadastro.getText().toString());
				cvt.put("celular",txtCelularCadastro.getText().toString());
				cvt.put("cep",txtCepCadastro.getText().length());
				cvt.put("endereco",txtEnderecoCadastro.getText().toString());
				cvt.put("complemento",txtComplementeCadastro.getText().toString());
				cvt.put("bairro",txtBairroCadastro.getText().toString());
				cvt.put("nr",txtNumeroCadastro.getText().toString());
				cvt.put("cidade",txtCidadeCadastro.getText().toString());
				cvt.put("estado",txtUfCadastro.getText().toString());
				
				if (db.insert("agenda", "_id", cvt)>0) {
					Toast.makeText(getBaseContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
					Intent it = new Intent (getBaseContext(),TelaPedido.class);
					startActivity(it);
					finish();
				}else{
					Toast.makeText(getBaseContext(),"Erro ao realizar o cadastro!", Toast.LENGTH_SHORT).show();
				}
			}
			catch(Exception ex){
				Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}
		
		
	}
	public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(

            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
        );
	
	
}
	


