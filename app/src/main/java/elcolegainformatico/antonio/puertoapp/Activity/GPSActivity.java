package elcolegainformatico.antonio.puertoapp.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.R;

public class GPSActivity extends AppCompatActivity {

	private TextView tvAddress;
	private Button btnGetLocation;
	private EditText edAddress;
	private TextView tvSelectedArt;
	private EditText edZona;
	private Button btnNext;


	Articulo mArticulo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_gps);
		setTitle(" Obtener dirección GPS");

		tvAddress = (TextView) findViewById(R.id.tvAddress);
		btnGetLocation = (Button) findViewById(R.id.btnGetLocation);
		edAddress = (EditText) findViewById(R.id.edAddress);
		tvSelectedArt = (TextView) findViewById(R.id.tvSelectedArt);
		edZona =(EditText) findViewById(R.id.edZone);
		btnNext = (Button) findViewById(R.id.btnNext);

		//Cojo el intent parcelable
		mArticulo = getIntent().getExtras().getParcelable("myArticulo");

		tvSelectedArt.setText(mArticulo.getNumArticulo()+ ": " +mArticulo.getTitulo());
        tvAddress.setText(""); //Clear
		edAddress.setText("");
		edZona.setText("");

		btnGetLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String address = ""; //Use to paint tvAddress
				GPSTracker gps = new GPSTracker(getApplicationContext(), GPSActivity.this);
				tvAddress.setText(" "); //Clear

                //Check GPS Permissions
				if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
						ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(GPSActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

				}
				else  //I have permissions
					{

					if (gps.canGetLocation()) { //I have GPS enable

						double latitude = gps.getLatitude();
						double longitude = gps.getLongitude();

						// \n is for new line
						Toast.makeText(getApplicationContext(), "Ubicación - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

						address = gps.getLocationAddress();

						if(gps.getAddress()){
							edAddress.setText(address); //I Paint my address with geodecoder function (GPSTracker)
						}
						else //No geodecoder location
						 {
							tvAddress.setText(address);
						}

					}

					else { //I have GPS disable
						dialogNoGPS();
					}

				}
			}//onClick

		});



		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String myAddress = edAddress.getText().toString();
				int lengthAdd = myAddress.length();

				//Check that address not null
			    if(lengthAdd>0){
				Intent i = new Intent(GPSActivity.this,EntidadVehiculoActivity.class);

				i.putExtra("selected_art", mArticulo);
				i.putExtra("myAddress",edAddress.getText().toString());
				i.putExtra("myZone",edZona.getText().toString());

					Log.d("Entra en intent",edAddress.getText().toString());
				startActivity(i);
				}

				else{
					dialogNoAddress();
				}
			}
		});
	}


	/**
	 *  Dialog to Enable GPS
	 */

	public void dialogNoGPS(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(GPSActivity.this);
		dialog.setCancelable(false);
		dialog.setTitle("GPS DESACTIVADO");
		dialog.setMessage("¿Desea activar el GPS?" );
		dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {

				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				GPSActivity.this.startActivity(intent);
			}
		})
				.setNegativeButton("Cancelar ", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Action for "Cancel".
						dialog.cancel();
					}
				});

		final AlertDialog alert = dialog.create();
		alert.show();
	}

	/**
	 * Dialog No Address
	 */
	public void dialogNoAddress(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(GPSActivity.this);
		dialog.setCancelable(false);
		dialog.setTitle("DIRECCION VACIA");
		dialog.setMessage("Debe rellenar o obtener la dirección" );
		dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {

			}
		});

		final AlertDialog alert = dialog.create();
		alert.show();
	}
}

