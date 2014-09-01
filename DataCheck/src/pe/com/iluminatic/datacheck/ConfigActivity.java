package pe.com.iluminatic.datacheck;

import pe.com.iluminatic.modelo.Configuracion;
import pe.com.iluminatic.sql.BDDataBaseHelper;
import pe.com.iluminatic.sql.BDOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;


public class ConfigActivity extends ActionBarActivity {

	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_config);

		if (savedInstanceState == null) {

			
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment())
					.commit();
			
			

		}

		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.config, menu);
		return true;
	}

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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		

		public PlaceholderFragment() {
			
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_config,
					container, false);

			final BDDataBaseHelper dbHelper = new BDDataBaseHelper(container.getContext());
			Configuracion config = dbHelper.recuperarRegistro();
			
			final EditText editPlanMb = (EditText) rootView
					.findViewById(R.id.editPlanMb);
			final EditText editUsoMb = (EditText) rootView
					.findViewById(R.id.editUsoMb);
			final EditText editDiaInicio = (EditText) rootView
					.findViewById(R.id.editDiaInicio);
			final ToggleButton notificacion50 = (ToggleButton) rootView
					.findViewById(R.id.toggleButton1);
			
			editPlanMb.setText(config.getPlanMb());
			editUsoMb.setText(config.getUsoMB());
			editDiaInicio.setText(config.getDiaInicioFacturacion());
			notificacion50.setText(config.getNotificacion50());
			//config.setNotificacion50(notificacion50.getText().toString());

			Button btnGuardar = (Button) rootView.findViewById(R.id.btnGuardar);

			

			btnGuardar.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Configuracion configuracion = new Configuracion();
					configuracion.setPlanMb(editPlanMb.getText().toString());
					configuracion.setUsoMB(editUsoMb.getText().toString());
					configuracion.setDiaInicioFacturacion(editDiaInicio.getText().toString());
					configuracion.setNotificacion50(notificacion50.getText().toString());
					dbHelper.actualizarConfiguracion(configuracion);
					Intent intent = new Intent(v.getContext(), MainDataActivity.class);
					v.getContext().startActivity(intent);
					
				}
			});
			
			Button btnConfigInicial = (Button) rootView.findViewById(R.id.btnConfigInicial);
			
			btnConfigInicial.setOnClickListener(new OnClickListener() {
				
				
				
				@Override
				public void onClick(View v) {

					//dbHelper.eliminarTabla();
					v.getContext().deleteDatabase("BD.DATACHECK");
					Intent intent = new Intent(v.getContext(), MainDataActivity.class);
					v.getContext().startActivity(intent);
					
					
				}
			});

			return rootView;
		}
	}

}
