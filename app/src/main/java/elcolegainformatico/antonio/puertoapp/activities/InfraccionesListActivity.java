package elcolegainformatico.antonio.puertoapp.activities;

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

import elcolegainformatico.antonio.puertoapp.model.Infraccion;
import elcolegainformatico.antonio.puertoapp.R;


/**
 * Created by antonio on 5/4/17.
 */


public class InfraccionesListActivity extends AppCompatActivity {

    private Infraccion miInfraccion;

    private ArrayList<Infraccion> sancionesList= new ArrayList<>();

    private ListView listSanciones;

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
        text_custom_title.setText(getResources().getString(R.string.Infracciones));

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
        if(getIntent().getSerializableExtra("sancionesSaved") !=null) {

            this.sancionesList = new ArrayList<>((ArrayList<Infraccion>) getIntent().getSerializableExtra("sancionesSaved"));
        }


        //GetIntent Block
        if(!getIntent().getBooleanExtra("isMenu",false)  && getIntent().getExtras().getParcelable("miInfraccion")!=null) {

            miInfraccion = getIntent().getExtras().getParcelable("miInfraccion");
            this.sancionesList.add(new Infraccion(miInfraccion.getmArticulo(), miInfraccion.getHour(), miInfraccion.getMinute(), miInfraccion.getDay(), miInfraccion.getMes(), miInfraccion.getYear(), miInfraccion.getIsVehicle(), miInfraccion.getSancion(), miInfraccion.getDniMatricula(), miInfraccion.getNombreMarca(), miInfraccion.getDomicilioReferencia(), miInfraccion.getUbicacion(), miInfraccion.getMyVehicle(), miInfraccion.getImagePath(), miInfraccion.getImageBitmap(), miInfraccion.getThisDay(), miInfraccion.getThisMonth(), miInfraccion.getThisYear(), miInfraccion.getNumero(), miInfraccion.getAgente()));
        }

        InfraccionesAdapter myAdaptater = new InfraccionesAdapter(sancionesList,InfraccionesListActivity.this.getApplicationContext());
        listSanciones.setAdapter(myAdaptater);

        //Float Button
        FltAddSancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       dialogInfraccion();
            }

        });

        //Selected Item
        listSanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Infraccion miInfraccion = sancionesList.get(position);

                Intent intent = new Intent(InfraccionesListActivity.this, ValidarActivity.class);

                intent.putExtra("hour", miInfraccion.getHour());
                intent.putExtra("minute", miInfraccion.getMinute());
                intent.putExtra("day", miInfraccion.getDay());
                //intent.putExtra("month",month);
                intent.putExtra("mes", miInfraccion.getMes());
                intent.putExtra("year", miInfraccion.getYear());

                intent.putExtra("DniMatricula", miInfraccion.getDniMatricula());
                intent.putExtra("NombreMarca", miInfraccion.getNombreMarca());
                intent.putExtra("DomicilioReferencia", miInfraccion.getDomicilioReferencia()) ;
                intent.putExtra("Ubicacion", miInfraccion.getUbicacion());

                intent.putExtra("myVehicle", miInfraccion.getMyVehicle());
                intent.putExtra("myArticulo", miInfraccion.getmArticulo());
                intent.putExtra("isVehicle", miInfraccion.getIsVehicle());

                intent.putExtra("ImagePath", miInfraccion.getImagePath());
                //Bitmap is not necessary , is ineficient, we will use imagePath.


                intent.putExtra("thisDay", miInfraccion.getThisDay());
                intent.putExtra("thisMonth", miInfraccion.getThisMonth());
                intent.putExtra("thisYear", miInfraccion.getThisYear());

                intent.putExtra("numero", miInfraccion.getNumero());
                intent.putExtra("agente", miInfraccion.getAgente());

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
        builder.setTitle("¿QUÉ TIPO DE INFRACCIÓN ES?");
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
