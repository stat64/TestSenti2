package com.edc.testsenti2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

/*
 * Esta es la clase para lanzar
 * La interfaz de la recomendación de
 * Ópticas 
 * Autor: Edison Carrillo
 */
public class Especialistas extends Activity implements
		android.widget.AdapterView.OnItemClickListener, OnClickListener {
	// Declaración de los elementos de la interfaz y variables globales	
	GridView gvAbc;
	Button btnSalABC;
	// Arreglo de imagenes de las letras
	Integer[] imagenesID = { R.drawable.opticata, R.drawable.opticatb,
			R.drawable.opticatc, R.drawable.opticatd, R.drawable.opticate,
			R.drawable.opticatf};
	String[] letras = { "a", "b", "c", "d", "e", "f"};
	Integer[] letrasID = { R.drawable.opticata, R.drawable.opticatb,
			R.drawable.opticatc, R.drawable.opticatd, R.drawable.opticate,
			R.drawable.opticatf};
	// Lanzador de la interfaz
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_especialistas);
		// Llamado de los elementos a la clase
		gvAbc = (GridView) findViewById(R.id.gvAbc);
		gvAbc.setAdapter(new ImageAdapter(this));
		gvAbc.setOnItemClickListener(this);
		btnSalABC = (Button) findViewById(R.id.btnSalABC);
		btnSalABC.setOnClickListener(this);
		
	}

	// Clase para la creación de la lista de imágenes
	// O galería
	public class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context c) {
			context = c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return letrasID.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		// Inserción de las imágenes al adaptador
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(context);
				// tamaño de cada imagen
				imageView.setLayoutParams(new GridView.LayoutParams(420, 140));
				imageView.setPadding(5,5, 5, 5);
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				// Espaciado entre imagen e imagen
				
			} else {
				imageView = (ImageView) convertView;
			}
			// inseción de las imagenes al adaptador
			imageView.setImageResource(letrasID[position]);
			return imageView;
		}

	}

	// Acción del evento onItemClick
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		// Creación de un cuadro de dialogo con la imagen y el texto
		Dialog d = new Dialog(this, R.style.Theme_Dialog_Translucent);
		d.setTitle("Ha seleccionado el siguiente especialista '" + letras[position] + "'");
		ImageView im = new ImageView(this);
		im.setImageResource(imagenesID[position]);
		d.setContentView(im);
		d.show();
	}

	// Acciones del evento onClick
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSalABC:
			this.finish();
			break;

		default:
			break;
		}
	}

}
