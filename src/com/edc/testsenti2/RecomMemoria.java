package com.edc.testsenti2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
/*
 * Esta es la clase para lanzar
 * La interfaz de confirmación de estado
 * Del usuario -- si está listo o no
 * Autor: Dennis Cajas
 */
public class RecomMemoria extends Activity {
	// Declaración de los elementos de la interfaz y variables globales	
	CheckBox cbxmen;
	// Declaración del complemento para capturar la pantalla
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recom_memoria);
		cbxmen = (CheckBox) findViewById(R.id.cbxmem);

	}
	// Lanzamiento de interfaz del test de memoria
	public void BTNMEN(View v) {
		if (cbxmen.isChecked()) {
			Intent intent = new Intent(RecomMemoria.this, Memoria.class);
			startActivity(intent);
			finish();
		}
	}
}
