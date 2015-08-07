package com.edc.testsenti2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/*
 * Esta es la clase para lanzar la
 * Interfaz del test de visión
 * Autor: Edison Carrillo
 */
public class TstView extends Activity implements OnClickListener {
	// Declaración de los elementos de la interfaz y variables globales
	ImageView IMG1;
	Button btnOPt;
	CheckBox cbxIzq, cbxDer, cbxDis;
	ImageButton btnOPT1, btnOPT2, btnOPT3, btnOPT4, btnOPT5;
	LinearLayout LNL1, LNL2, LNL3, LNL4, LNL5, LNL6, LNL7;
	TextView Res3I, Res3D, txtDiag;
	String RESI = "0,0";
	String RESD = "0,0";
	int recoI = 0;
	int recoD = 0;
	int TOJO = 0;
	TextView txtNum;
	int i = 0;
	int cont = 1;

	int[] fotoId = { R.drawable.testva, R.drawable.testvb, R.drawable.testvc,
			R.drawable.testvd, R.drawable.testve, R.drawable.testvf,
			R.drawable.testvg, R.drawable.testvh, R.drawable.testvi,
			R.drawable.testvj };
	int[] fotoOPT1 = { R.drawable.la, R.drawable.lr, R.drawable.lt,
			R.drawable.lb, R.drawable.ld, R.drawable.lx, R.drawable.lr,
			R.drawable.lo, R.drawable.lz, R.drawable.lx };
	int[] fotoOPT2 = { R.drawable.lh, R.drawable.lf, R.drawable.lk,
			R.drawable.le, R.drawable.lq, R.drawable.ld, R.drawable.lz,
			R.drawable.lu, R.drawable.lk, R.drawable.lw };
	int[] fotoOPT3 = { R.drawable.lb, R.drawable.le, R.drawable.lz,
			R.drawable.lf, R.drawable.lr, R.drawable.lb, R.drawable.lk,
			R.drawable.lv, R.drawable.ls, R.drawable.lh };
	int[] fotoOPT4 = { R.drawable.lv, R.drawable.lh, R.drawable.ls,
			R.drawable.lt, R.drawable.lg, R.drawable.lr, R.drawable.lx,
			R.drawable.lj, R.drawable.lt, R.drawable.ly };
	String[] datos = null;
	List<String> Ldatos = new ArrayList<String>();
	List<String> LSEL = new ArrayList<String>();
	List<String> LOPTI = new ArrayList<String>();
	List<String> LOPTD = new ArrayList<String>();
	// Complemento para la captura de imagen
	public ImageView imgScreen;

	private ServiceConnection aslServiceConn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			aslProvider = com.edc.complementos.IScreenshotProvider.Stub
					.asInterface(service);
		}
	};
	private com.edc.complementos.IScreenshotProvider aslProvider = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tst_view);
		// Mapeo de objetos de la interfaz		
		IMG1 = (ImageView) findViewById(R.id.IMG1);
		btnOPt = (Button) findViewById(R.id.btnok);
		btnOPT1 = (ImageButton) findViewById(R.id.btnOPT1);
		btnOPT2 = (ImageButton) findViewById(R.id.btnOPT2);
		btnOPT3 = (ImageButton) findViewById(R.id.btnOPT3);
		btnOPT4 = (ImageButton) findViewById(R.id.btnOPT4);
		btnOPT5 = (ImageButton) findViewById(R.id.btnOPT5);
		cbxIzq = (CheckBox) findViewById(R.id.cbxIzq);
		cbxDer = (CheckBox) findViewById(R.id.cbxDer);
		cbxDis = (CheckBox) findViewById(R.id.cbxDis);
		txtNum = (TextView) findViewById(R.id.txtNum);
		LNL1 = (LinearLayout) findViewById(R.id.LNL1);
		LNL2 = (LinearLayout) findViewById(R.id.LNL2);
		LNL3 = (LinearLayout) findViewById(R.id.LNL3);
		LNL4 = (LinearLayout) findViewById(R.id.LNL4);
		LNL5 = (LinearLayout) findViewById(R.id.LNL5);
		LNL6 = (LinearLayout) findViewById(R.id.LNL6);
		LNL7 = (LinearLayout) findViewById(R.id.LNL7);
		Res3I = (TextView) findViewById(R.id.Res3I);
		Res3D = (TextView) findViewById(R.id.Res3D);
		txtDiag = (TextView) findViewById(R.id.txtDiag);
		// Aplicación del método para todos los botones
		// IMG1.setOnClickListener(this);
		btnOPt.setOnClickListener(this);
		btnOPT1.setOnClickListener(this);
		btnOPT2.setOnClickListener(this);
		btnOPT3.setOnClickListener(this);
		btnOPT4.setOnClickListener(this);
		btnOPT5.setOnClickListener(this);
		// Llamado del intento para capturar la pantalla
		Intent intent = new Intent();
		intent.setClass(this, com.edc.complementos.ScreenshotService.class);
		bindService(intent, aslServiceConn, Context.BIND_AUTO_CREATE);
	}
	// Método para fijar las acciones a los botones
	// Mapeados anteriormente
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.btnok) {

			LNL1.setVisibility(View.GONE);
			LNL2.setVisibility(View.VISIBLE);

		} else {
			if (i < fotoId.length - 1) {

				btnOPT1.setBackgroundResource(fotoOPT1[i + 1]);
				btnOPT2.setBackgroundResource(fotoOPT2[i + 1]);
				btnOPT3.setBackgroundResource(fotoOPT3[i + 1]);
				btnOPT4.setBackgroundResource(fotoOPT4[i + 1]);
				IMG1.setImageResource(fotoId[i + 1]);
			}
			int opt = 1;
			if (i < fotoId.length) {
				switch (v.getId()) {
				case R.id.btnOPT1:
					opt = 1;
					break;
				case R.id.btnOPT2:
					opt = 2;
					break;
				case R.id.btnOPT3:
					opt = 3;
					break;
				case R.id.btnOPT4:
					opt = 4;
					break;
				case R.id.btnOPT5:
					opt = 5;
					break;
				default:

					break;
				}

				if (TOJO == 0) {
					LOPTI.add("" + opt);
					int contador = 0;
					contador = i + 2;
					txtNum.setText(" " + contador);
				}
				if (TOJO == 1) {
					LOPTD.add("" + opt);
					int contador = 0;
					contador = i + 2;
					txtNum.setText(" " + contador);
					
				}

			}
			if (i < fotoId.length - 1) {

				LNL1.setVisibility(View.VISIBLE);
				LNL2.setVisibility(View.GONE);

				i++;
				cont++;
			} else {

				LNL1.setVisibility(View.GONE);
				LNL2.setVisibility(View.GONE);

				if (TOJO == 0) {
					RESI = Resultados() + "";
					Res3I.setText(RESI + "");
					if (RESI.equals("100") || RESI.equals("90")) {

					} else {
						recoI += 1;
					}
					cont = 1;
					LNL4.setVisibility(View.VISIBLE);

				} else {
					if (TOJO == 1) {

						RESD = Resultados() + "";
						Res3D.setText(RESD + "");
						if (RESD.equals("100") || RESD.equals("90")) {
						} else {
							recoD += 1;

						}
						TOJO++;
						Recomendar(Res3I, Res3D);
						Res3D.setText(RESD + "%");
						Res3I.setText(RESI + "%");
						LNL3.setVisibility(View.VISIBLE);

					} else {
						Toast t = Toast.makeText(TstView.this, "" + recoI + " "
								+ recoD, Toast.LENGTH_LONG);
						t.show();

						txtDiag.setText(recoI + " " + recoD);
						Recomendar(Res3I, Res3D);
						Res3D.setText(RESD + "%");
						Res3I.setText(RESI + "%");
						LNL5.setVisibility(View.GONE);
						LNL4.setVisibility(View.GONE);
						LNL3.setVisibility(View.VISIBLE);

					}
				}
			}

		}

	}
	// Método para calificar los resultados del test
	public void Recomendar(TextView res3i, TextView res3d) {
		// TODO Auto-generated method stub
		String res3i2 = res3i.getText() + "";
		String res3d2 = res3d.getText() + "";

		int izq = Integer.parseInt(res3i2);
		int der = Integer.parseInt(res3d2);
		if (izq > 10 || der > 10) {
			txtDiag.setText("Ud. tiene discapacidad visual y requiere una cita con un especialista, si desea consultar un especialista presione el botón 'Observar recomendación'");
		} else {
			if (izq <= 10 || der <= 10) {
				txtDiag.setText("Ud. no tiene discapacidad visual, si desea consultar un especialista presione el botón 'Observar recomendación'");
			}
		}

	}
	// Método para convertir los datos obtenidos del test
	public String Resultados() {
		int res = 100;
		if (TOJO == 0) {
			Ldatos = LOPTI;
		} else {
			Ldatos = LOPTD;
		}
		if (Ldatos.get(0).toString().equals("1")) {
			res = res - 10;

		} 
		if (Ldatos.get(1).toString().equals("3")) {
			res = res - 10;

		}
		if (Ldatos.get(2).toString().equals("2")) {
			res = res - 10;

		}
		if (Ldatos.get(3).toString().equals("3")) {
			res = res - 10;

		}
		if (Ldatos.get(4).toString().equals("1")) {
			res = res - 10;

		}
		if (Ldatos.get(5).toString().equals("4")) {
			res = res - 10;

		}
		if (Ldatos.get(6).toString().equals("3")) {
			res = res - 10;

		}
		if (Ldatos.get(7).toString().equals("2")) {
			res = res - 10;

		}
		if (Ldatos.get(8).toString().equals("1")) {
			res = res - 10;

		}
		if (Ldatos.get(9).toString().equals("3")) {
			res = res - 10;

		}

		return "" + res;
	}
	// Método de validación de indicaciones 1
	// Del ojo izquierdo y llamado de la interfaz
	// Del test
	public void BTNIZQ(View v) {
		if (cbxIzq.isChecked()) {
			i = 0;
			TOJO = 0;
			IMG1.setImageResource(fotoId[0]);
			btnOPT1.setBackgroundResource(fotoOPT1[0]);
			btnOPT2.setBackgroundResource(fotoOPT2[0]);
			btnOPT3.setBackgroundResource(fotoOPT3[0]);
			btnOPT4.setBackgroundResource(fotoOPT4[0]);
			LNL5.setVisibility(View.GONE);
			LNL1.setVisibility(View.VISIBLE);
		}
	}
	// Método de validación de indicaciones 2
	public void BTNDIS(View v) {
		if (cbxDis.isChecked()) {
			LNL6.setVisibility(View.GONE);
			LNL5.setVisibility(View.VISIBLE);
		}
	}
	// Método de validación de indicaciones 1
	// Del ojo derecho y llamado de la interfaz
	// Del test
	public void BTNDRC(View v) {
		if (cbxDer.isChecked()) {
			i = 0;
			TOJO = 1;
			cont = 1;
			txtNum.setText(" 1");
			IMG1.setImageResource(fotoId[0]);
			btnOPT1.setBackgroundResource(fotoOPT1[0]);
			btnOPT2.setBackgroundResource(fotoOPT2[0]);
			btnOPT3.setBackgroundResource(fotoOPT3[0]);
			btnOPT4.setBackgroundResource(fotoOPT4[0]);
			LNL4.setVisibility(View.GONE);
			LNL1.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void onDestroy() {
		unbindService(aslServiceConn);
		super.onDestroy();
	}
	//Método para retroceder del menú recomendación
	public void AtrasReco(View v) {
		LNL7.setVisibility(View.GONE);
		LNL3.setVisibility(View.VISIBLE);
	}
	// Método para lanzar la lista de ópticas
	public void Recomendacion(View v) {
		Intent i = new Intent(TstView.this, Especialistas.class);
		startActivity(i);
	}
	//Método para tomar fotos y guardarlas
	public void Foto(View v) {
		Bitmap bitmap = takeScreenshot();
		saveBitmap(bitmap);
	}
	// Método para salir del test
	public void SalirT(View v) {
		finish();
	}
	// Método para tomar captura de pantall
	public Bitmap takeScreenshot() {
		View rootView = findViewById(android.R.id.content).getRootView();
		rootView.setDrawingCacheEnabled(true);
		return rootView.getDrawingCache();
	}
	//Método para guardar la interfaz capturada
	public void saveBitmap(Bitmap bitmap) {
		Toast t = Toast.makeText(this, "Almacenando la imagen en Galería",
				Toast.LENGTH_LONG);
		t.show();
		Random rnd = new Random();
		int di = (int) (rnd.nextDouble() * 999999 + 1);
		File imagePath = new File(Environment.getExternalStorageDirectory()
				+ "/TestVision" + di + ".png");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(imagePath);
			bitmap.compress(CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			Log.e("GREC", e.getMessage(), e);
		} catch (IOException e) {
			Log.e("GREC", e.getMessage(), e);
		}
	}
}
