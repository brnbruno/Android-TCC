package com.bruno.pizzaplanety;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class EnviaJSP extends AsyncTask<Integer, Double, String> {
	private Activity ctx;
	private ProgressDialog progress;
	int totalReplicado = 0;

	public EnviaJSP(Activity ctx) {
		this.ctx = ctx;
	}

	//onPreExecute() – É executado antes do doInBackground().
	protected void onPreExecute() {
		Log.i("onPreExecute...  ", " ");
		progress = ProgressDialog.show(ctx, "Aguarde...", "Enviando dados para web!!!", true);
		Log.i("onPreExecute...  ", "..OK..");
	}

	// doInBackground(Params…)– A thread background. É chamado quando o onPreExecute finaliza.
	protected String doInBackground(Integer... params) {
		Log.i("doInBackground...  ", " Abrindo BD ");
        //Abrir/Criar o Banco de Dados
        //Declarando a classe de controle de conexao
        DatabaseHandler db = new DatabaseHandler(ctx);
        // Inserting Contacts
        Log.d("Reading: ", "Reading all contacts.."); 
        List<Agenda> contatos = db.getAllContacts();       
         
        for (Agenda cn : contatos) {
            //String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            //String.valueOf(contador)
        	Log.d("ID:    ", String.valueOf(cn.getID()));
        	Log.d("Nome:  ", cn.getNome());
        	Log.d("Email: ", cn.getEmail());
        	Log.d("Fone:  ", cn.getFone());
            Log.d("hyuyhe","hh");
    

        	StringBuilder strURL = new StringBuilder();
			//strURL.append("http://localhost:8085/ProjetoWeb/insereContatosOra.jsp?nome=");
			strURL.append("http://10.0.2.2:8085/ProjetoWeb/insereContatosOra.jsp?NOME=");
			//strURL.append("http://192.168.0.100:8085/projetoWeb/insereContatosOra.jsp?nome=");
			//strURL.append(cn.getNome());
			//Retirar Espacos do Nome
			String nomeConv=cn.getNome();
			while(nomeConv.indexOf(' ') != -1 ) 
			{
			    System.out.println("Nome: " + nomeConv);
			    nomeConv = nomeConv.substring(0, nomeConv.indexOf(' ')) + "%20" +
			               nomeConv.substring(nomeConv.indexOf(' ') + 1);
			}
			System.out.println("Nome: " + nomeConv);
			strURL.append(nomeConv); 
			strURL.append(cn.getNome());
			strURL.append("&CPF=");
			strURL.append(cn.getCpf());
			strURL.append("&CEP=");
			strURL.append(cn.getCep());
			strURL.append("&CIDADE=");
			strURL.append(cn.getCidade());
			strURL.append("&ENDERECO=");
			strURL.append(cn.getEndereco());
			strURL.append("&BAIRRO=");
			strURL.append(cn.getBairro());
			strURL.append("&UF=");
			strURL.append(cn.getUf());
			strURL.append("&CELULAR=");
			strURL.append(cn.getFone());
			strURL.append("&NR=");
			strURL.append(cn.getNr());
			strURL.append("&ATIVO=");
			strURL.append(1);
			strURL.append("&EMAIL=");
			strURL.append(cn.getEmail());
			strURL.append("&SENHA=");
			strURL.append(cn.getSenha());
			Log.d("enviaJSP... ", strURL.toString());
		 
			try{
				URL url = new URL(strURL.toString());
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				InputStreamReader ips = new InputStreamReader(http.getInputStream());
				BufferedReader line = new BufferedReader(ips);
			 
				String linhaRetorno = line.readLine();
				totalReplicado++;
				Log.d("Verificando Exportacao ", linhaRetorno); 
				if(linhaRetorno.equals("Y")){
					Log.i("linhaRetorno.equals(Y)", " OK ");
					db.deleteContact(cn.getID());
					totalReplicado++;
					Log.d("ExportarAgendaService...", linhaRetorno);
				} 
				else
				{
					Log.i("linhaRetorno.equals(N)", " Fail ");
				}
			} catch(Exception ex){
				Log.d("ExportarAgendaService", ex.getMessage());
			}
        }
		return null;
	}

	//onPostExecute(Result)– Invocado depois que a thread principal finaliza e esta retorna algum valor como parâmetro para este método.
	protected void onPostExecute(String result) {
		progress.dismiss();
		Toast.makeText(ctx, totalReplicado + " Registro(s) Enviado(s) com sucesso!", Toast.LENGTH_LONG).show(); 
	} 
}