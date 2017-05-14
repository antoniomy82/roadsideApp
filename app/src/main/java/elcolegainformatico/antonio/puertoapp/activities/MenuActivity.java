package elcolegainformatico.antonio.puertoapp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 6/4/17.
 */

public class MenuActivity extends AppCompatActivity {

    private Button mMultasButton = null;

    private ImageButton home_custom_bar;
    private TextView text_custom_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_principal);


        //Custom title bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_title_bar);


        text_custom_title=(TextView)findViewById(R.id.text_custom_title);
        text_custom_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
        text_custom_title.setText(getResources().getString(R.string.MenuPrincipal));

        home_custom_bar=(ImageButton) findViewById(R.id.home_custom_bar);

        home_custom_bar.setImageResource(R.drawable.logo_puerto36);


        //Seek reference to the button
        mMultasButton = (Button) findViewById(R.id.multasButton);

        //Config my button
        mMultasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //launch the second activity
                Intent listIntent = new Intent(MenuActivity.this, InfraccionesListActivity.class); // Activity Source , Activity Destination

                listIntent.putExtra("isMenu",true);

                startActivity(listIntent); //Start intent
            }
        });


    }


}

