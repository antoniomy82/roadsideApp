package elcolegainformatico.antonio.puertoapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.view.View;

import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 6/4/17.
 */

public class MenuActivity extends AppCompatActivity {

    private Button mMultasButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_menu_principal);
        //this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_bar);

       // setTitle("Menú Principal");

        //Seek reference to the button
        mMultasButton = (Button) findViewById(R.id.multasButton);

        //Config my button
        mMultasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //launch the second activity
                Intent listIntent = new Intent(MenuActivity.this, SancionesListActivity.class); // Activity Source , Activity Destination

                listIntent.putExtra("isMenu",true);

                startActivity(listIntent); //Start intent
            }
        });


    }


}

