package com.edc.testsenti2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
/*
 * Esta es la clase para lanzar
 * la interfaz principal de las opciones
 * de cada test
 * Autor: Edison Carrillo
 */
public class MainActivity extends Activity {
	// Declaraci�n de los elementos de la interfaz y variables globales	
	ImageButton btnTstView, btnTstAud, btnTstMem, btnSalir;

	// Lanzador de la interfaz
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		//Mapeo de los elementos a la clase
		btnTstView = (ImageButton) findViewById(R.id.btnTstView);
		btnTstAud = (ImageButton) findViewById(R.id.btnTstAud);
		btnTstMem = (ImageButton) findViewById(R.id.btnTstMem);
		btnSalir = (ImageButton) findViewById(R.id.btnSalir);
		
	}
	// Selecci�n del test de visi�n 
	public void TstView(View v){
		Intent tstv = new Intent(MainActivity.this,TstView.class);
		startActivity(tstv);
	}
	// Selecci�n del test de audici�n	
	public void TstAud(View v){
		Intent tstaud = new Intent(MainActivity.this,Audicion.class);
		startActivity(tstaud);
	}
	// Selecci�n del test de Memoria
	public void TstMem(View v){
		Intent rcmem = new Intent(MainActivity.this,RecomMemoria.class);
		startActivity(rcmem);
	}
	// Selecci�n para salir de la aplicaci�n
	public void Salir(View v){		
		this.finish();
	}
	
}
