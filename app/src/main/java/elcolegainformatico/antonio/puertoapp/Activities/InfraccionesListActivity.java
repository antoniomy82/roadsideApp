package elcolegainformatico.antonio.puertoapp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import elcolegainformatico.antonio.puertoapp.Model.Sancion;
import elcolegainformatico.antonio.puertoapp.R;


/**
 * Created by antonio on 5/4/17.
 */


public class InfraccionesListActivity extends AppCompatActivity {

    Sancion miSancion;

    ArrayList<Sancion> sancionesList= new ArrayList<Sancion>();

    ListView listSanciones;

    private ImageButton home_custom_bar;
    private TextView text_custom_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_infracciones_list);
        listSanciones=(ListView) findViewById(R.id.sanciones_list);


        //Custom title bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_title_bar);


        text_custom_title=(TextView)findViewById(R.id.text_custom_title);
        text_custom_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
        text_custom_title.setText("INFRACCIONES GUARDADAS");

        home_custom_bar=(ImageButton) findViewById(R.id.home_custom_bar);

        home_custom_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfraccionesListActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });



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

        InfraccionesAdapter myAdaptater = new InfraccionesAdapter(sancionesList,InfraccionesListActivity.this.getApplicationContext());
        listSanciones.setAdapter(myAdaptater);

        //Float Button
        FltAddSancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*
                Snackbar.make(v, "Se presiono Float" ,Snackbar.LENGTH_LONG).show();

                Intent intent = new Intent(InfraccionesListActivity.this, ArticulosListActivity.class); // Activity Source , Activity Destination

                //Solo para versión sin Firebase.
                intent.putExtra("sancionesSaved",sancionesList);

                startActivity(intent); //Start intent */
               dialogInfraccion();
            }

        });

        //Selected Item
        listSanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Sancion miSancion = sancionesList.get(position);

                Intent intent = new Intent(InfraccionesListActivity.this, ValidarActivity.class);

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

    //Dialog Infraccion
    private void dialogInfraccion(){

        final CharSequence[] options = { "LEY","REGLAMENTO" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿QUÉ TIPO DE INFRACCIÓN DESEA PONER?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                Intent intent = new Intent(InfraccionesListActivity.this, ArticulosListActivity.class); // Activity Source , Activity Destination
                intent.putExtra("sancionesSaved",sancionesList);


                if (options[item].equals("LEY"))
                {
                    intent.putExtra("isReglamento",false);

                }
                if (options[item].equals("REGLAMENTO")) {

                    intent.putExtra("isReglamento",true);

                }

                startActivity(intent); //Start intent

            }
        });
        builder.show();
    }


}
