package elcolegainformatico.antonio.puertoapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 5/4/17.
 */

public class SancionesListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sanciones_list);

        setTitle("Listado de multas ");
    }
}
