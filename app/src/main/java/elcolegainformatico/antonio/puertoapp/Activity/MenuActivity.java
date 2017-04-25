package elcolegainformatico.antonio.puertoapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import elcolegainformatico.antonio.puertoapp.R;


public class MenuActivity extends AppCompatActivity {

    private Button mMultasButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        setTitle("Menú Principal");

        //Seek reference to the button
        mMultasButton = (Button) findViewById(R.id.multasButton);

        //Config my button
        mMultasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //launch the second activity
                Intent listIntent = new Intent(MenuActivity.this, ArticulosListActivity.class); // Activity Source , Activity Destination

                startActivity(listIntent); //Start intent
            }
        });


    }


}

