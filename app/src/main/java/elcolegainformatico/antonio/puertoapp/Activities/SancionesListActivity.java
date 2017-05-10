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

    ArrayList<Sancion> sancionesList= new ArrayList<Sancion>();

    ListView listSanciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sanciones_list);
        listSanciones=(ListView) findViewById(R.id.sanciones_list);

        setTitle("Sanciones Guardadas ");

        final FloatingActionButton FltAddSancion = (FloatingActionButton) findViewById(R.id.FltAddSancion);


        //Versión sin FireBase, Aquí cargar BD de FireBase
        if((ArrayList<Sancion>) getIntent().getSerializableExtra("sancionesSaved")!=null) {

            this.sancionesList = new ArrayList<Sancion>((ArrayList<Sancion>) getIntent().getSerializableExtra("sancionesSaved"));
        }


        //GetIntent Block
        if(getIntent().getBooleanExtra("isMenu",false) ==false && getIntent().getExtras().getParcelable("miSancion")!=null) {

            miSancion = getIntent().getExtras().getParcelable("miSancion");
            this.sancionesList.add(new Sancion(miSancion.getmArticulo(), miSancion.getHour(), miSancion.getMinute(), miSancion.getDay(), miSancion.getMes(), miSancion.getYear(), miSancion.getIsVehicle(), miSancion.getSancion(), miSancion.getDniMatricula(), miSancion.getNombreMarca(), miSancion.getDomicilioReferencia(), miSancion.getUbicacion(), miSancion.getMyVehicle(), miSancion.getImagePath(), miSancion.getImageBitmap(), miSancion.getThisDay(), miSancion.getThisMonth(),miSancion.getThisYear(), miSancion.getNumero(), miSancion.getAgente()));
        }

        SancionesAdapter myAdaptater = new SancionesAdapter(sancionesList,SancionesListActivity.this.getApplicationContext());
        listSanciones.setAdapter(myAdaptater);

        //Float Button
        FltAddSancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Se presiono Float" ,Snackbar.LENGTH_LONG).show();

                Intent intent = new Intent(SancionesListActivity.this, ArticulosListActivity.class); // Activity Source , Activity Destination

                //Solo para versión sin Firebase.
                intent.putExtra("sancionesSaved",sancionesList);

                startActivity(intent); //Start intent
            }

        });

        //Selected Item
        listSanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Sancion miSancion = sancionesList.get(position);

                Intent intent = new Intent(SancionesListActivity.this, ValidarActivity.class);

                intent.putExtra("hour",miSancion.getHour());
                intent.putExtra("minute",miSancion.getMinute());
                intent.putExtra("day",miSancion.getDay());
                //intent.putExtra("month",month);
                intent.putExtra("mes",miSancion.getMes());
                intent.putExtra("year",miSancion.getYear());

                intent.putExtra("DniMatricula",miSancion.getDniMatricula());
                intent.putExtra("NombreMarca",miSancion.getNombreMarca());
                intent.putExtra("DomicilioReferencia",miSancion.getDomicilioReferencia()) ;
                intent.putExtra("Ubicacion",miSancion.getUbicacion());

                intent.putExtra("myVehicle", miSancion.getMyVehicle());
                intent.putExtra("myArticulo",miSancion.getmArticulo());
                intent.putExtra("isVehicle", miSancion.getIsVehicle());

                intent.putExtra("ImagePath",miSancion.getImagePath());
                //Bitmap is not necessary , is ineficient, we will use imagePath.


                intent.putExtra("thisDay",miSancion.getThisDay());
                intent.putExtra("thisMonth",miSancion.getThisMonth());
                intent.putExtra("thisYear", miSancion.getThisYear());

                intent.putExtra("numero",miSancion.getNumero());
                intent.putExtra("agente",miSancion.getAgente());

                //Solo para versión sin Firebase.
                intent.putExtra("sancionesSaved",sancionesList);

                intent.putExtra("isSave",false);

                startActivity(intent);

                //Snackbar.make(view, "Mostrará Validar Activity" ,Snackbar.LENGTH_LONG).show();

            }

        });


        }


}
