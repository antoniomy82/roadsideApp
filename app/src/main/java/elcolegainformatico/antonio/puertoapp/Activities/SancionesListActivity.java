package elcolegainformatico.antonio.puertoapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import elcolegainformatico.antonio.puertoapp.Model.Sancion;
import elcolegainformatico.antonio.puertoapp.R;



/**
 * Created by antonio on 5/4/17.
 */


public class SancionesListActivity extends AppCompatActivity {

    Sancion miSancion;

    ArrayList<Sancion> sancionesList = new ArrayList<>();

    ListView listSanciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sanciones_list);
        listSanciones=(ListView) findViewById(R.id.sanciones_list);

        setTitle("Sanciones Guardadas ");

        final FloatingActionButton FltAddSancion = (FloatingActionButton) findViewById(R.id.FltAddSancion);


        //GetIntent Block
        if(getIntent().getBooleanExtra("isMenu",false) ==false) {
            miSancion = getIntent().getExtras().getParcelable("miSancion");

            sancionesList.add(new Sancion(miSancion.getmArticulo(), miSancion.getHour(), miSancion.getMinute(), miSancion.getDay(), miSancion.getMes(), miSancion.getYear(), miSancion.getIsVehicle(), miSancion.getSancion(), miSancion.getDniMatricula(), miSancion.getNombreMarca(), miSancion.getDomicilioReferencia(), miSancion.getUbicacion(), miSancion.getMyVehicle(), miSancion.getImagePath(), miSancion.getImageBitmap()));
        }

        SancionesAdapter myAdaptater = new SancionesAdapter(sancionesList,SancionesListActivity.this.getApplicationContext());
        listSanciones.setAdapter(myAdaptater);

        FltAddSancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Se presiono Float" ,Snackbar.LENGTH_LONG).show();

                Intent intent = new Intent(SancionesListActivity.this, ArticulosListActivity.class); // Activity Source , Activity Destination

                startActivity(intent); //Start intent
            }

        });

        listSanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Sancion miSancion = sancionesList.get(position);

                /*
                Intent intent = new Intent(SancionesListActivity.this, ValidarActivity.class);

               // intent.putExtra("myArticulo", mArticulo); //Pasamos un objeto articulo Parcelable
                //Pasar el chorreon de intents
                startActivity(intent);*/

                Snackbar.make(view, "Mostrará Validar Activity" ,Snackbar.LENGTH_LONG).show();

            }

        });


        }


}
