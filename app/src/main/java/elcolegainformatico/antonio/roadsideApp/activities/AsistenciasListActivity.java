package elcolegainformatico.antonio.roadsideApp.activities;

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

import elcolegainformatico.antonio.roadsideApp.R;


/**
 * Created by antonio on 5/4/17.
 */

public class AsistenciasListActivity extends AppCompatActivity {

    private ImageButton home_custom_bar;
    private TextView text_custom_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_asistencia_fragment);

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
                Intent intent = new Intent(AsistenciasListActivity.this, MenuActivity.class);
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

    }


    //Dialog Asistencia
    private void dialogInfraccion(){

        final CharSequence[] options = {"ASEGURADORA", "PARTICULAR"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿QUÉ TIPO DE ASISTENCIAS ES?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                Intent intent = new Intent(AsistenciasListActivity.this, TipoListActivity.class); // Activity Source , Activity Destination

                if (options[item].equals("ASEGURADORA")) {
                    intent.putExtra("isParticular", false);

                }
                if (options[item].equals("PARTICULAR")) {

                    intent.putExtra("isParticular", true);

                }

                startActivity(intent); //Start intent

            }
        });
        builder.show();
    }


}
