package com.edc.testsenti2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Esta es la clase para realizar el Test de audición
 * Autor: Leonardo Criollo
 */
public class Audicion extends Activity implements OnClickListener,
		android.widget.AdapterView.OnItemClickListener {
	// Declaración de los elementos de la interfaz y variables globales
	LinearLayout relative1, LNL7, LNL_IND1;
	RelativeLayout relative2, relative3, relative4;
	Button btnsiguiente, btnrigth, btncenter, btnleft, btncont, btnSPO, btnNPO,
			btnMOM, btnSPOI, btnNPOI, btnMOMI;
	TextView PorcDER, PorcIZQ, txtDiag;
	GridView gvAbc;
	CheckBox cbxaud, cbxIND1, cbxIND2;

	Integer[] imagenesID = { R.drawable.espauda, R.drawable.espaudb,
			R.drawable.espaudc, R.drawable.espaudd };
	String[] dotor = { "doctA", "doctB", "doctC", "doctD" };
	Integer[] doctorID = { R.drawable.espauda, R.drawable.espaudb,
			R.drawable.espaudc, R.drawable.espaudd };
	ArrayList<String> ArrayDer = new ArrayList<String>();
	public MusicIntentReceiver myReceiver;
	ProgressBar pBar, pBarIzq;
	MediaPlayer sp;
	int res = 0;
	int lado = 0;
	int total = 0;
	int tests = 0;
	int posPB = 2;
	int pos = 1;
	int[] audDer = { R.raw.atstder, R.raw.btstder, R.raw.ctstder,
			R.raw.dtstder, R.raw.etstder, R.raw.atstizq, R.raw.btstizq,
			R.raw.ctstizq, R.raw.dtstizq, R.raw.etstizq };

	int[] Rdatos = new int[6];
	int[] RdatosIzq = new int[6];
	private ImageView imgScreen;
	// Declaración del complemento para capturar la pantalla
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
	// Declaración del complemento para capturar la pantalla
	private com.edc.complementos.IScreenshotProvider aslProvider = null;

	// inicializacion de las variables y objetos en la interfaz
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_audicion);
		// Mapeo de objetos de la interfaz
		relative1 = (LinearLayout) findViewById(R.id.Relative1);
		relative2 = (RelativeLayout) findViewById(R.id.Relative2);
		relative3 = (RelativeLayout) findViewById(R.id.Relative3);
		relative4 = (RelativeLayout) findViewById(R.id.Relative4);

		cbxaud = (CheckBox) findViewById(R.id.cbxaud);
		cbxIND1 = (CheckBox) findViewById(R.id.cbxIND1);

		LNL7 = (LinearLayout) findViewById(R.id.LNL7);
		LNL_IND1 = (LinearLayout) findViewById(R.id.LNL_IND1);

		btnsiguiente = (Button) findViewById(R.id.btn_siguiente);
		btnrigth = (Button) findViewById(R.id.btn_rigth);
		btncenter = (Button) findViewById(R.id.btn_center);
		btnleft = (Button) findViewById(R.id.btn_left);
		btncont = (Button) findViewById(R.id.btn_cont);
		btnSPO = (Button) findViewById(R.id.btnSPO);
		btnNPO = (Button) findViewById(R.id.btnNPO);
		btnMOM = (Button) findViewById(R.id.btnMOM);

		PorcDER = (TextView) findViewById(R.id.PorcDER);
		PorcIZQ = (TextView) findViewById(R.id.PorcIZQ);
		txtDiag = (TextView) findViewById(R.id.txtDiag);
		gvAbc = (GridView) findViewById(R.id.gvAbc);
		// Aplicación del método para todos los botones
		btnleft.setOnClickListener(this);
		btnrigth.setOnClickListener(this);
		btncenter.setOnClickListener(this);
		btncont.setOnClickListener(this);
		btnSPO.setOnClickListener(this);
		btnNPO.setOnClickListener(this);
		btnMOM.setOnClickListener(this);

		// Configuración de la barra del estado de finalización del test
		pBar = (ProgressBar) findViewById(R.id.pBar);
		pBar.getProgressDrawable().setColorFilter(Color.DKGRAY, Mode.SRC_IN);
		pBar.setProgress(1 * 100 / 5);
		// Selección de interfaz a lanzarse en la interfaz
		relative1.setVisibility(View.VISIBLE);
		relative2.setVisibility(View.GONE);
		relative3.setVisibility(View.GONE);
		relative4.setVisibility(View.GONE);

		LNL_IND1.setVisibility(View.GONE);

		LNL7.setVisibility(View.GONE);
		// Almacenando las imágenes en el objeto de recomendación
		gvAbc.setAdapter(new ImageAdapter(this));
		gvAbc.setOnItemClickListener(this);
		// Llamado del intento para capturar la pantalla
		Intent intent = new Intent();
		intent.setClass(this, com.edc.complementos.ScreenshotService.class);
		bindService(intent, aslServiceConn, Context.BIND_AUTO_CREATE);

	}

	// Método de definición del resultado del test
	// Fijación de datos en la interfaz y el llamado
	// De la interfaz en la pantalla del dispositivo
	public void DarDatos() {
		// TODO Auto-generated method stub
		int DiscDer = 0;
		int DiscIzq = 0;
		String COment = "";

		for (int i = 0; i < 5; i++) {
			if (ArrayDer.get(i).equals("0")) {
				DiscDer += 20;
			}
			if (ArrayDer.get(i).equals("1")) {
				DiscDer += 10;
			}
		}
		for (int i = 5; i < 10; i++) {
			if (ArrayDer.get(i).equals("0")) {
				DiscIzq += 20;
			}
			if (ArrayDer.get(i).equals("1")) {
				DiscIzq += 10;
			}
		}

		if (DiscIzq > 20 || DiscDer > 20) {
			COment = "Se ha diagnosticado que tiene una grave discapacidad para poder escuchar, usted requiere un especialista urgente";
		} else {
			COment = "Usted cuenta con una buena audición, lo recomendable es que no escuche música con el volumen muy alto";
		}
		PorcDER.setText(DiscDer + "%");
		PorcIZQ.setText(DiscIzq + "%");
		txtDiag.setText("" + COment);
		relative3.setVisibility(View.GONE);
		relative4.setVisibility(View.VISIBLE);
	}

	// Método de intercambio de y validación interfaz
	// Valida si el usuario conecta los auriculares al
	// Dispositivo y si el usuario está listo para realizar
	// el test
	public void siguiente(View v) {
		if (cbxaud.isChecked()) {
			AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
			
			  if (am.isWiredHeadsetOn()) {
			
			relative1.setVisibility(View.GONE);
			relative2.setVisibility(View.VISIBLE);
			relative3.setVisibility(View.GONE);
			
			  } else { Toast toast = Toast .makeText( this,
			  "Por favor conecte los auriculares para realizar la prueba de audición"
			  , Toast.LENGTH_SHORT); toast.show();
			  
			  }
			 
		}
	}

	// Método para testear el estado del auricular izquierdo
	public void izquierdo() {
		sp = MediaPlayer.create(this, R.raw.audizqr);
		sp.start();
		btnleft.setEnabled(false);
		btnrigth.setEnabled(true);
		btnleft.setEnabled(false);
	}

	// Método para testear el estado del auricular Derecho
	public void derecho() {
		if (sp.isPlaying()) {
			sp.stop();
			sp = MediaPlayer.create(this, R.raw.audder);
			sp.start();
			btnleft.setEnabled(false);
			btnrigth.setEnabled(false);
			btncenter.setEnabled(true);
		} else {
			sp.stop();
			sp = MediaPlayer.create(this, R.raw.audder);
			sp.start();
			btnleft.setEnabled(false);
			btnrigth.setEnabled(false);
			btncenter.setEnabled(true);
		}
	}

	// Método para testear el estado los dos auriculares
	public void center() {
		if (sp.isPlaying()) {
			sp.stop();
			sp = MediaPlayer.create(this, R.raw.audcen);
			sp.start();
			btnleft.setEnabled(false);
			btnrigth.setEnabled(false);
			btncenter.setEnabled(false);
			btncont.setEnabled(true);
		} else {
			sp = MediaPlayer.create(this, R.raw.audcen);
			sp.start();
			btnleft.setEnabled(false);
			btnrigth.setEnabled(false);
			btncenter.setEnabled(false);
			btncont.setEnabled(true);
		}

	}

	// Método para reproducir los sonidos
	public class MusicIntentReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
				int state = intent.getIntExtra("state", -1);
				switch (state) {
				case 0:
					Log.d("Headset is unplugged", null);
					break;
				case 1:
					Log.d("Headset is plugged", null);
					break;
				default:
					Log.d("I have no idea what the headset state is", null);
				}
			}
		}
	}

	// Método para pausar los sonidos
	@Override
	public void onPause() {
		if (myReceiver != null) {
			unregisterReceiver(myReceiver);
		}

		super.onPause();
	}

	// Método para que al seleccionar la imagen del especialista
	// Muestre la descripción del especialista.
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
	}

	// Método para fijar las acciones a los botones
	// Mapeados anteriomente
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.btn_left) {
			izquierdo();

		}
		if (v.getId() == R.id.btn_rigth) {
			derecho();

		}
		if (v.getId() == R.id.btn_center) {
			center();
		}
		if (v.getId() == R.id.btn_cont) {
			relative1.setVisibility(View.GONE);
			relative2.setVisibility(View.GONE);
			LNL_IND1.setVisibility(View.VISIBLE);
			sp.stop();
		}
		if (v.getId() == R.id.btnNPO) {
			if (pos < 11) {
				sp.stop();
				res = 0;
				ArrayDer.add("" + res);
				cambioSon();
			}

		}
		if (v.getId() == R.id.btnSPO) {
			if (pos < 11) {
				sp.stop();
				res = 2;
				ArrayDer.add("" + res);
				cambioSon();
			}
		}
		if (v.getId() == R.id.btnMOM) {
			if (pos < 11) {
				sp.stop();
				res = 1;
				ArrayDer.add("" + res);
				cambioSon();

			}
		}

	}

	// Método para cambiar los sonidos entre cada prueba
	public void cambioSon() {
		if (pos < 10) {

			sp = MediaPlayer.create(this, audDer[pos]);
			sp.start();
			pBar.setProgress(posPB * 100 / 10);

		} else {
			DarDatos();
		}
		posPB++;
		pos++;

	}

	// Método para cambiar de interfaz y lanzar
	// La lista de los especialistas
	public void Recomendacion(View v) {

		relative4.setVisibility(View.GONE);
		LNL7.setVisibility(View.VISIBLE);

	}

	// Método para salir del test
	public void SalirT(View v) {
		finish();
	}

	// Método para salir del test
	public void SalirRE(View v) {
		LNL7.setVisibility(View.GONE);
		relative4.setVisibility(View.VISIBLE);
		
		
	}

	// Método para manejar el contenedor de los especialistas cuando lo
	// presiona
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Dialog d = new Dialog(this, R.style.Theme_Dialog_Translucent);
		d.setTitle("Ha seleccionado el siguiente especialista");
		ImageView im = new ImageView(this);
		im.setImageResource(imagenesID[position]);
		d.setContentView(im);
		d.show();

	}

	// Método para manejar el contenedor de los especialistas
	public class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context c) {
			context = c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return doctorID.length;
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
				imageView.setPadding(5, 5, 5, 5);
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				// Espaciado entre imagen e imagen

			} else {
				imageView = (ImageView) convertView;
			}
			// inseción de las imagenes al adaptador
			imageView.setImageResource(doctorID[position]);
			return imageView;
		}

	}

	@Override
	public void onDestroy() {
		unbindService(aslServiceConn);
		super.onDestroy();
	}

	// Método para llamar a la captura de pantalla
	public void Foto(View v) {
		Bitmap bitmap = takeScreenshot();
		saveBitmap(bitmap);
	}

	// Método para capturar la pantalla
	public Bitmap takeScreenshot() {
		View rootView = findViewById(android.R.id.content).getRootView();
		rootView.setDrawingCacheEnabled(true);
		return rootView.getDrawingCache();
	}

	// Método para almacenar imagen en la galería
	public void saveBitmap(Bitmap bitmap) {
		Toast t = Toast.makeText(this, "Almacenando imagen en Galería",
				Toast.LENGTH_LONG);
		t.show();

		Random rnd = new Random();
		int di = (int) (rnd.nextDouble() * 999999 + 1);
		File imagePath = new File(Environment.getExternalStorageDirectory()
				+ "/TestAudicion" + di + ".png");
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

	// Método para cambiar de interfaz y realizar el testa
	public void sig_ind1(View v) {
		if (cbxIND1.isChecked()) {
			LNL_IND1.setVisibility(View.GONE);
			relative3.setVisibility(View.VISIBLE);
			sp.stop();
			sp = MediaPlayer.create(this, R.raw.atstder);
			sp.start();
		}
	}

}
