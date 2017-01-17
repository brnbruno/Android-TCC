package com.bruno.pizzaplanety;

import android.app.Activity;
import android.os.Bundle;

public class TelaPedido extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pedido_activ);
		
		EnviaJSP env = new EnviaJSP(this);
		env.execute(0);
		
     }

}
