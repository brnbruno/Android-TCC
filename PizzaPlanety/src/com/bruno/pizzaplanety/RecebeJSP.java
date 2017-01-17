package com.bruno.pizzaplanety;

import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/*class  OperacaoAlgumaCoisaWebServiceAsyncTask
           extends AsyncTask<Void, Void, Void> {
}
* Vendo a implementação desta classe percebemos que precisamos definir alguns tipos “Generic”. 
* Para o exemplo acima, colocamos todos como Void. Vamos entender para que serve cada tipo:
* Primeiro Generic(Params)  : o tipo que pode ser enviado para a thread  background desse mecanismo, ou seja, o método doInBackground.
* Segundo  Generic(Progress): o tipo que pode ser usado para usar com finalidade de enviar o progresso de alguma execução da thread background.
* Terceiro Generic(Result)  : o tipo de retorno da thread background. */
public class RecebeJSP extends AsyncTask<Integer, Double, String> {
	private Activity ctx;
	private ProgressDialog progress;

	public RecebeJSP(Activity ctx) {
		this.ctx = ctx;
	}

	//onPreExecute() – É executado antes do doInBackground().
	protected void onPreExecute() {
		progress = ProgressDialog.show(ctx, "Aguarde...", "Baixando dados da web!!!", true);
	}

	// doInBackground(Params…)– A thread background. É chamado quando o onPreExecute finaliza.
	protected String doInBackground(Integer... params) {

		StringBuilder sb = new StringBuilder();
		try {
			HttpClient httpclient = new DefaultHttpClient();
			//String endereco = "http://localhost:8085/ProjetoWeb/listaContatosOra.jsp";
			String endereco = "http://10.0.2.2:8085/ProjetoWeb/listaContatosOra.jsp";
			Log.i("URL", endereco);

			HttpGet httpget = new HttpGet(endereco);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				Scanner s = new Scanner(entity.getContent());
				while (s.hasNext()) {
					sb.append(s.next());
				}
			}
			Log.i("Finalizou... ","Fim");
			
		} catch (ClientProtocolException e) {
			sb.append(e.getMessage());
		} catch (IllegalStateException e) {
			sb.append(e.getMessage());
		} catch (IOException e) {
			sb.append(e.getMessage());
		}	
		return sb.toString();
	}

	//onPostExecute(Result)– Invocado depois que a thread principal finaliza e esta retorna algum valor como parâmetro para este método.
	protected void onPostExecute(String result) {
		progress.dismiss();
		Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

        String[] matrizTela= new String[5];
        String LinhaDados = null;

	    //aqui ele pega a quantidade de carcteres que tem uma determinada variável  
	    //e armazena numa INT para usá-la de contador  
	    int contador = result.length();
	    String total =result.substring(result.indexOf("{")+1,result.indexOf("}"));
	    Log.i("Tot. Reg.:  ", total);
	    Log.i("Tot. Car.:  ", String.valueOf(contador));
	    int PosIni = 0;
	    int PosFin = 0;
	    String Pos1, Pos2, Pos3, Pos4, Pos5,Pos6, Pos7, Pos8, Pos9, Pos10, Pos11, Pos12, Pos13, Pos14;
	        
	    //cria um for( para fazer uma varredura letra por letra até encontrar
	    for(int i = 0;i<contador;i++)
	    {
	    	//usamos substring pra pegar um caractere, passando como parâmetro,  
	    	//o primeiro caractere a ser pega, até a ultima.  
	    	//fiz um if para verificar se o caractere é igual a "?"
	    	if (result.substring(i,i+1).equals("?")){ 
	    		{
	    			Log.i("i     :  ", String.valueOf(i));
	    			Log.i("PosIni:  ", String.valueOf(PosIni));
	    			Log.i("PosFin:  ", String.valueOf(PosFin=i));
	    			LinhaDados = result.substring(PosIni,PosFin).trim();
	    			LinhaDados = LinhaDados.replaceAll("[&]", " ");
	    			Log.i("String:  ", LinhaDados);
	    			PosIni = PosFin+1;
	    			/*Caso utilize a Classe Split.java que realiza a separação dos dados
		            * Split separados;
		            * separados = new Split();
		            * matrizTela = separados.separaDados(LinhaDados, "|"); */
	    			//Utilizando a metodo Split nativo do Java
	    			//Exemplo nomeString.split(limit);
	    			matrizTela = LinhaDados.split("[|]");
	    			//Retorna cada campo em uma posicao
		            Pos1 = matrizTela[0].toString();
		            Pos2 = matrizTela[1].toString();
		            Pos3 = matrizTela[2].toString();
		            Pos4 = matrizTela[3].toString();
		            Pos5 = matrizTela[4].toString();
		            Pos6 = matrizTela[5].toString();
		            Pos7 = matrizTela[6].toString();
		            Pos8 = matrizTela[7].toString();
		            Pos9 = matrizTela[8].toString();
		            Pos10 = matrizTela[9].toString();
		            Pos11 = matrizTela[10].toString();
		            Pos12 = matrizTela[11].toString();
		            Pos13 = matrizTela[12].toString();
		            Pos14 = matrizTela[13].toString();
		            //Exibindo no console (Logcat)
		            Log.i("Pos1  :  ", Pos1);
		            Log.i("Pos2  :  ", Pos2);
		            Log.i("Pos3  :  ", Pos3);
		            Log.i("Pos4  :  ", Pos4);
		            Log.i("Pos5  :  ", Pos5);
		            Log.i("Pos6  :  ", Pos6);
		            Log.i("Pos7  :  ", Pos7);
		            Log.i("Pos8  :  ", Pos8);
		            Log.i("Pos9  :  ", Pos9);
		            Log.i("Pos10 :  ", Pos10);
		            Log.i("Pos11 :  ", Pos11);
		            Log.i("Pos12 :  ", Pos12);
		            Log.i("Pos13 :  ", Pos13);
		            Log.i("Pos14 :  ", Pos14);
		            //Declarando a classe de controle de conexao
                    DatabaseHandler db = new DatabaseHandler(ctx);
			        // Inserting Contacts
			        Log.d("Insert: ", "Inserting ..");
			        db.addContact(Pos2, Pos3, Pos4, Pos5, Pos6, Pos7, Pos8, Pos9, Pos10, Pos11, Pos12, Pos13);
	    		}    		
	    	}
	    } 
	}
}