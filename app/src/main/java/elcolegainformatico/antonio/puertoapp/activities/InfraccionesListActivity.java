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
import android.widget.ImageButton;
import android.widget.TextView;
import elcolegainformatico.antonio.puertoapp.R;


/**
 * Created by antonio on 5/4/17.
 */

public class InfraccionesListActivity extends AppCompatActivity{

    private ImageButton home_custom_bar;
    private TextView text_custom_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_infracciones_fragment);

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


        //Float Button
        FltAddSancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfraccion();
            }

        });

/*
        dbInfracciones.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dbInfracciones!=null) {

                    int range=0;
                    int size = (int) dataSnapshot.getChildrenCount();
                    for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                      if(range<size-1){ //Se me sale del Rango ¿Por qué???????????????
                            numInfraccion = childDataSnapshot.getKey();
                            dniMatricula = childDataSnapshot.child("dniMatricula").getValue().toString();
                            DomicilioReferencia = childDataSnapshot.child("domicilioReferencia").getValue().toString();
                            importeSancion = childDataSnapshot.child("importeSancion").getValue().toString();
                            mesInfraccion = childDataSnapshot.child("mesInfraccion").getValue().toString();
                            Ubicacion = childDataSnapshot.child("ubicacion").getValue().toString();
                            myVehicle = childDataSnapshot.child("myVehicle").getValue().toString();
                            nombreMarca = childDataSnapshot.child("nombreMarca").getValue().toString();

                            numUsuario = (long) childDataSnapshot.child("numUsuario").getValue();
                            thisDay = (long) childDataSnapshot.child("thisDay").getValue();
                            thisMonth = (long) childDataSnapshot.child("thisMonth").getValue();
                            thisYear = (long) childDataSnapshot.child("thisYear").getValue();
                            yearInfraccion = (long) childDataSnapshot.child("yearInfraccion").getValue();
                            dayInfraccion = (long) childDataSnapshot.child("dayInfraccion").getValue();
                            hourInfraccion = (long) childDataSnapshot.child("hourInfraccion").getValue();
                            isVehicle = (long) childDataSnapshot.child("isVehicle").getValue();
                            minuteInfraccion = (long) childDataSnapshot.child("minuteInfraccion").getValue();

                            String Articulo_Des = childDataSnapshot.child("articulo_Des").getValue().toString();
                            String Articulo_Num = childDataSnapshot.child("articulo_Num").getValue().toString();
                            String Articulo_Tit = childDataSnapshot.child("articulo_Tit").getValue().toString();

                            imagePath = (ArrayList) childDataSnapshot.child("imagePath").getValue();
                            imageBitmap = (ArrayList) childDataSnapshot.child("imageBitmap").getValue();
                            mArticulo = new Articulo(Articulo_Num, Articulo_Tit, Articulo_Des);

                            sancionesList.add(new Infraccion(mArticulo, (int) hourInfraccion, (int) minuteInfraccion, (int) dayInfraccion, mesInfraccion, (int) yearInfraccion, (int) isVehicle, Double.parseDouble(importeSancion), dniMatricula, nombreMarca, DomicilioReferencia, Ubicacion, myVehicle, imagePath, imageBitmap, (int) thisDay, (int) thisMonth, (int) thisYear, Integer.parseInt(numInfraccion), (int) numUsuario));


                            InfraccionesAdapter myAdaptater = new InfraccionesAdapter(sancionesList, InfraccionesListActivity.this.getApplicationContext());
                            listSanciones.setAdapter(myAdaptater);

                            range++;
                        }

                    }
                }

            }


            @SuppressLint("LongLogTag")
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"Error!",databaseError.toException());
            }
        });


 */
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
