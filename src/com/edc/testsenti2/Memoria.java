package com.edc.testsenti2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
/*
 * Esta es la clase para lanzar
 * la interfaz del test de memoria
 * Autor: Dennis Cajas
 */
public class Memoria extends Activity implements OnClickListener {
	// Declaración de los elementos de la interfaz y variables globales
	TextView txtCon, txtResMen;
	LinearLayout LNL1, LNL2, LNL3;
	ImageButton imaa, imab, imac, imad, imae, imaf, imag, imah, imai, imaj,
			imak, imal;
	ImageButton imba, imbb, imbc, imbd, imbe, imbf, imbg, imbh, imbi, imbj,
			imbk, imbl;
	int opt = 0;
	int optA = 10;
	int optB = 0;
	int contSeri = 1;
	int res = 0;
	int porres = 0;
	// Inicialización de las variables y objetos en la interfaz
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_memoria);
		// Mapeo de objetos de la interfaz
		txtCon = (TextView) findViewById(R.id.txtCon);
		txtResMen = (TextView) findViewById(R.id.TxtResMen);
		LNL1 = (LinearLayout) findViewById(R.id.LNL1);
		LNL2 = (LinearLayout) findViewById(R.id.LNL2);
		LNL3 = (LinearLayout) findViewById(R.id.LNL3);
		lanzarAlarma();
		imaa = (ImageButton) findViewById(R.id.imgaaf);
		imab = (ImageButton) findViewById(R.id.imgbbf);
		imac = (ImageButton) findViewById(R.id.imgcbf);
		imad = (ImageButton) findViewById(R.id.imgdbf);
		imae = (ImageButton) findViewById(R.id.imgecf);
		imaf = (ImageButton) findViewById(R.id.imgfef);
		imag = (ImageButton) findViewById(R.id.imggff);
		imah = (ImageButton) findViewById(R.id.imghdf);
		imai = (ImageButton) findViewById(R.id.imgief);
		imaj = (ImageButton) findViewById(R.id.imgjcf);
		imak = (ImageButton) findViewById(R.id.imgkff);
		imal = (ImageButton) findViewById(R.id.imgldf);

		imba = (ImageButton) findViewById(R.id.imghaaf);
		imbb = (ImageButton) findViewById(R.id.imghbbf);
		imbc = (ImageButton) findViewById(R.id.imghcaf);
		imbd = (ImageButton) findViewById(R.id.imghdbf);
		imbe = (ImageButton) findViewById(R.id.imghecf);
		imbf = (ImageButton) findViewById(R.id.imghfef);
		imbg = (ImageButton) findViewById(R.id.imghgff);
		imbh = (ImageButton) findViewById(R.id.imghhdf);
		imbi = (ImageButton) findViewById(R.id.imghief);
		imbj = (ImageButton) findViewById(R.id.imghjcf);
		imbk = (ImageButton) findViewById(R.id.imghkff);
		imbl = (ImageButton) findViewById(R.id.imghldf);
		// aplicación del método para todos los botones
		imba.setOnClickListener(this);
		imbb.setOnClickListener(this);
		imbc.setOnClickListener(this);
		imbd.setOnClickListener(this);
		imbe.setOnClickListener(this);
		imbf.setOnClickListener(this);
		imbg.setOnClickListener(this);
		imbh.setOnClickListener(this);
		imbi.setOnClickListener(this);
		imbj.setOnClickListener(this);
		imbk.setOnClickListener(this);
		imbl.setOnClickListener(this);

	}
	// Método para lanzar un contador en la interfaz
	private void lanzarAlarma() {
		int i = 5;
		CountDownTimer timer = new CountDownTimer(i * 1000, 100) {

			@Override
			public void onTick(long millisUntilFinished) {
				txtCon.setText("-----------Tiempo Restante: "
						+ String.valueOf(millisUntilFinished / 1000)
						+ " s -----------");
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				LNL1.setVisibility(View.GONE);
				LNL2.setVisibility(View.VISIBLE);
			}

		};
		timer.start();
	}
	// Método para fijar las acciones a los botones
	// mapeados anteriomente, se revisa si la opcions
	// seleccionada es igual a la que seleciono anteriomente
	// Si esta incorrecta deshace la selección y le da otra
	// Oportunidad, al momento que falla los intentos
	// La aplicación le muestra el resultado del test
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (contSeri < 12) {
			if (opt < 2) {
				contSeri++;
				if (opt == 0) {
					if (v.getId() == R.id.imghaaf) {
						imba.setImageResource(R.drawable.af);
						optA = 1;
					}
					if (v.getId() == R.id.imghbbf) {
						imbb.setImageResource(R.drawable.bf);
						optA = 2;
					}
					if (v.getId() == R.id.imghcaf) {
						imbc.setImageResource(R.drawable.af);
						optA = 1;
					}
					if (v.getId() == R.id.imghdbf) {
						imbd.setImageResource(R.drawable.bf);
						optA = 2;
					}
					if (v.getId() == R.id.imghecf) {
						imbe.setImageResource(R.drawable.cf);
						optA = 3;
					}
					if (v.getId() == R.id.imghfef) {
						imbf.setImageResource(R.drawable.ef);
						optA = 5;
					}
					if (v.getId() == R.id.imghgff) {
						imbg.setImageResource(R.drawable.ff);
						optA = 6;
					}
					if (v.getId() == R.id.imghhdf) {
						imbh.setImageResource(R.drawable.df);
						optA = 4;
					}
					if (v.getId() == R.id.imghief) {
						imbi.setImageResource(R.drawable.ef);
						optA = 5;
					}
					if (v.getId() == R.id.imghjcf) {
						imbj.setImageResource(R.drawable.cf);
						optA = 3;
					}
					if (v.getId() == R.id.imghkff) {
						imbk.setImageResource(R.drawable.ff);
						optA = 6;
					}
					if (v.getId() == R.id.imghldf) {
						imbl.setImageResource(R.drawable.df);
						optA = 4;
					}

				} else {

					if (v.getId() == R.id.imghaaf) {
						imba.setImageResource(R.drawable.af);
						optB = 1;
					}
					if (v.getId() == R.id.imghbbf) {
						imbb.setImageResource(R.drawable.bf);
						optB = 2;
					}
					if (v.getId() == R.id.imghcaf) {
						imbc.setImageResource(R.drawable.af);
						optB = 1;
					}
					if (v.getId() == R.id.imghdbf) {
						imbd.setImageResource(R.drawable.bf);
						optB = 2;
					}
					if (v.getId() == R.id.imghecf) {
						imbe.setImageResource(R.drawable.cf);
						optB = 3;
					}
					if (v.getId() == R.id.imghfef) {
						imbf.setImageResource(R.drawable.ef);
						optB = 5;
					}
					if (v.getId() == R.id.imghgff) {
						imbg.setImageResource(R.drawable.ff);
						optB = 6;
					}
					if (v.getId() == R.id.imghhdf) {
						imbh.setImageResource(R.drawable.df);
						optB = 4;
					}
					if (v.getId() == R.id.imghief) {
						imbi.setImageResource(R.drawable.ef);
						optB = 5;
					}
					if (v.getId() == R.id.imghjcf) {
						imbj.setImageResource(R.drawable.cf);
						optB = 3;
					}
					if (v.getId() == R.id.imghkff) {
						imbk.setImageResource(R.drawable.ff);
						optB = 6;
					}
					if (v.getId() == R.id.imghldf) {
						imbl.setImageResource(R.drawable.df);

						optB = 4;
					}

				}
				opt++;
			}

			if (opt == 2) {
				if (optA == optB) {
					opt = 0;
					optA = 12;
					optA = 13;
					res++;
				}
				else {
					if (optA == 1 || optB == 1) {
						imba.setImageResource(R.drawable.abc_btn_radio_material);
						imbc.setImageResource(R.drawable.abc_btn_radio_material);
					}
					if (optA == 2 || optB == 2) {
						imbd.setImageResource(R.drawable.abc_btn_radio_material);
						imbb.setImageResource(R.drawable.abc_btn_radio_material);
					}
					if (optA == 3 || optB == 3) {
						imbj.setImageResource(R.drawable.abc_btn_radio_material);
						imbe.setImageResource(R.drawable.abc_btn_radio_material);
					}
					if (optA == 4 || optB == 4) {
						imbl.setImageResource(R.drawable.abc_btn_radio_material);
						imbh.setImageResource(R.drawable.abc_btn_radio_material);
					}
					if (optA == 5 || optB == 5) {
						imbi.setImageResource(R.drawable.abc_btn_radio_material);
						imbf.setImageResource(R.drawable.abc_btn_radio_material);
					}
					if (optA == 6 || optB == 6) {
						imbk.setImageResource(R.drawable.abc_btn_radio_material);
						imbg.setImageResource(R.drawable.abc_btn_radio_material);
					}
					opt = 0;
				}
			}
		} 
		//cálculo del porcentaje de capacidad de aprendizaje
		else {
			porres = (res * 100 / 5);
			LNL2.setVisibility(View.GONE);
			LNL3.setVisibility(View.VISIBLE);
			txtResMen.setText(porres + "%");
		}

		txtResMen.setText(porres + "%");
	}
	// Método para salir del test
	public void SalirTMen(View v) {
		finish();
	}
	// Método para avanzar y realizar el test
	public void contTMem(View v) {
		LNL1.setVisibility(View.GONE);
		LNL2.setVisibility(View.VISIBLE);
	}

}
