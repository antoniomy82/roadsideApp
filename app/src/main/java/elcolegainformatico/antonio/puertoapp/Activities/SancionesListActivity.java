package elcolegainformatico.antonio.puertoapp.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        //GetIntent Block
        miSancion = getIntent().getExtras().getParcelable("miSancion");

        sancionesList.add(new Sancion(miSancion.getmArticulo(),miSancion.getHour(),miSancion.getMinute(),miSancion.getDay(),miSancion.getMes(),miSancion.getYear(),miSancion.getIsVehicle(),miSancion.getSancion(),miSancion.getDniMatricula(),miSancion.getNombreMarca(),miSancion.getDomicilioReferencia(),miSancion.getUbicacion(),miSancion.getMyVehicle(),miSancion.getImagePath(),miSancion.getImageBitmap()));


        SancionesAdapter myAdaptater = new SancionesAdapter(sancionesList,SancionesListActivity.this.getApplicationContext());
        listSanciones.setAdapter(myAdaptater);


        }


}
