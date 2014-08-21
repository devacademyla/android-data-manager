package pe.com.iluminatic.datacheck;

import pe.com.iluminatic.modelo.Configuracion;
import pe.com.iluminatic.sql.BDDataBaseHelper;
import android.support.v7.app.ActionBarActivity;

import android.support.v4.app.Fragment;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainDataActivity extends ActionBarActivity {

	private BDDataBaseHelper dbHelper;
	private static final String DATABASE_NAME = "BD.DATACHECK";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_data);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();

			//deleteDatabase(DATABASE_NAME);
			
			dbHelper = new BDDataBaseHelper(this);
			

			Configuracion config = dbHelper.recuperarRegistro();

			if ( null == config.getId() ) {

				dbHelper.insertarRegistroConfig();

			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*
		 * int id = item.getItemId(); if (id == R.id.action_settings) { return
		 * true; } return super.onOptionsItemSelected(item);
		 */

		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, MainDataActivity.class);
			this.startActivity(intent);
			return true;
		case R.id.action_ayuda:
			Intent intent2 = new Intent(this, AyudaActivity.class);
			this.startActivity(intent2);
			return true;
		case R.id.action_config:
			Intent intent3 = new Intent(this, ConfigActivity.class);
			this.startActivity(intent3);
			return true;

			/*
			 * case R.id.help: showHelp(); return true;
			 */
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_data,
					container, false);
			return rootView;
		}
	}

}
