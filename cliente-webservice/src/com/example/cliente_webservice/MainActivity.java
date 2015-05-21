package com.example.cliente_webservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText camponome, campowebservice;
	static String webservice;
	static ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progress = new ProgressDialog(this);
		progress.setCancelable(false);

		camponome = (EditText) findViewById(R.id.nome);

		campowebservice = (EditText) findViewById(R.id.webservice);

		Button botaoacessar = (Button) findViewById(R.id.acessar);

		botaoacessar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (camponome.getText().toString().length() > 0) {

					if (campowebservice.getText().toString().length() > 0) {
						webservice = campowebservice.getText().toString();

						TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						String imei = telephonyManager.getDeviceId();

						progress();

						Transacao transacao = new Transacao(camponome.getText()
								.toString(), imei);
						transacao.execute();

					} else {

						Toast.makeText(MainActivity.this,
								"digite o webservice", Toast.LENGTH_LONG)
								.show();
						campowebservice.requestFocus();
					}

				} else {

					Toast.makeText(MainActivity.this, "digite o nome",
							Toast.LENGTH_LONG).show();
					camponome.requestFocus();
				}

			}
		});
	}

	public void progress() {

		progress.setMessage("Processando ,Aguarde ... ");
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setIndeterminate(true);
		progress.show();

		final int totalProgressTime = 100;

		final Thread t = new Thread() {

			@Override
			public void run() {

				int jumpTime = 0;
				while (jumpTime < totalProgressTime) {
					try {
						sleep(200);
						jumpTime += 5;
						progress.setProgress(jumpTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		};
		t.start();
	}

	public class Transacao extends AsyncTask<String, Void, Void> {

		private String nome, imei;
		String resultado;
		boolean ok;

		public Transacao(String nome, String imei) {
			super();
			this.nome = nome;
			this.imei = imei;
			ok = false;
		}

		@Override
		protected Void doInBackground(String... params) {
			resultado = WebService.saudacao(nome, imei);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			progress.dismiss();
			Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_LONG)
					.show();

		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected void onProgressUpdate(Void... values) {

		}

	}

}
