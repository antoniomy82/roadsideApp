package elcolegainformatico.antonio.roadsideApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import elcolegainformatico.antonio.roadsideApp.R;
import elcolegainformatico.antonio.roadsideApp.model.Tipo;

/**
 * Created by antonio on 7/4/17.
 */

public class TipoListActivity extends AppCompatActivity {

    private ListView listAsistencias;
    private ArrayList<Tipo> asistenciaArrayList;

    private boolean isParticular;

    private ImageButton home_custom_bar;
    private TextView text_custom_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Custom title bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_title_bar);


        text_custom_title = (TextView) findViewById(R.id.text_custom_title);
        text_custom_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
        text_custom_title.setText(getResources().getString(R.string.ListaArticulos));

        home_custom_bar = (ImageButton) findViewById(R.id.home_custom_bar);

        home_custom_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TipoListActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


        isParticular = getIntent().getBooleanExtra("isParticular", true);

        setContentView(R.layout.activity_tipos_list); //le asigno su layout
        listAsistencias = (ListView) findViewById(R.id.tipos_list); //Busco mi listView activity_articulos_list

        //Meto a manubrio los articulos


        asistenciaArrayList = new ArrayList<>();

        if (isParticular) {
            loadParticulares();
        } else {
            loadAseguradoras();
        }

        AsistenciasAdapter myAdaptater = new AsistenciasAdapter(asistenciaArrayList, TipoListActivity.this.getApplicationContext(), isParticular);
        listAsistencias.setAdapter(myAdaptater);

        //Cuando seleccione algún item de la lista
        listAsistencias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Tipo mTipo = asistenciaArrayList.get(position);

                Intent intent = new Intent(TipoListActivity.this, EntidadVehiculoActivity.class);

                intent.putExtra("myAssistance", mTipo); //Pasamos un objeto articulo Parcelable


                startActivity(intent);
            }

        });
    }

    private void loadAseguradoras() {
        asistenciaArrayList.add(new Tipo("1", "ASEGURADORA - TARIFA BASE 30", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 30 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("2", "ASEGURADORA - TARIFA 60", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 60 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("3", "ASEGURADORA - TARIFA 150", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 150 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("4", "ASEGURADORA - TARIFA VIAJE 300", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 300 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("5", "ASEGURADORA - TARIFA LARGA DISTANCIA", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 1000 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("6", "ASEGURADORA - TARIFA PROFESIONAL 30", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 30 KM (Vehículos Industriales, Camiones, trenes de carretera"));
        asistenciaArrayList.add(new Tipo("7", "ASEGURADORA - TARIFA PROFESIONAL 60", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 60 KM (Vehículos Industriales, Camiones, trenes de carretera"));
        asistenciaArrayList.add(new Tipo("8", "ASEGURADORA - TARIFA PROFESIONAL 150", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 150 KM (Vehículos Industriales, Camiones, trenes de carretera"));
        asistenciaArrayList.add(new Tipo("9", "ASEGURADORA - TARIFA PROFESIONAL VIAJE", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 300 KM (Vehículos Industriales, Camiones, trenes de carretera"));
        asistenciaArrayList.add(new Tipo("10", "ASEGURADORA - TARIFA PROFESIONAL LARGA DISTANCIA", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 1000 KM (Vehículos Industriales, Camiones, trenes de carretera"));
    }

    private void loadParticulares() {

        asistenciaArrayList.add(new Tipo("11", "PARTICULAR - TARIFA BASE 30", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 30 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("12", "PARTICULAR  - TARIFA 60", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 60 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("13", "PARTICULAR  - TARIFA 150", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 150 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("14", "PARTICULAR  - TARIFA VIAJE 300", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 300 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("15", "PARTICULAR  - TARIFA LARGA DISTANCIA", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 1000 KM (Turismos, Motocicletas,Furgonetas ligeras, Cuatriciclos"));
        asistenciaArrayList.add(new Tipo("16", "PARTICULAR  - TARIFA PROFESIONAL 30", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 30 KM (Vehículos Industriales, Camiones, trenes de carretera"));
        asistenciaArrayList.add(new Tipo("17", "PARTICULAR  - TARIFA PROFESIONAL 60", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 60 KM (Vehículos Industriales, Camiones, trenes de carretera"));
        asistenciaArrayList.add(new Tipo("18", "PARTICULAR  - TARIFA PROFESIONAL 150", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 150 KM (Vehículos Industriales, Camiones, trenes de carretera"));
        asistenciaArrayList.add(new Tipo("19", "PARTICULAR  - TARIFA PROFESIONAL VIAJE", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 300 KM (Vehículos Industriales, Camiones, trenes de carretera"));
        asistenciaArrayList.add(new Tipo("20", "PARTICULAR  - TARIFA PROFESIONAL LARGA DISTANCIA", "Remolque de vehículo averiado desde el lugar de avería hasta taller autorizado , hasta 1000 KM (Vehículos Industriales, Camiones, trenes de carretera"));
    }
}
