package elcolegainformatico.antonio.puertoapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 8/4/17.
 */

public class ArticuloEditActivity extends AppCompatActivity {

    private TextView mtxtNumArticulo;
    private TextView mTxtTitulo;
    private TextView mTxtDescripcion;
    private Button   mBtnSelecArticulo;
    Articulo mArticulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_articulo_edit);

        setTitle("Articulo seleccionado");

        mtxtNumArticulo = (TextView) findViewById(R.id.txtNumArticulo);
        mTxtTitulo = (TextView) findViewById(R.id.txtTitulo);
        mTxtDescripcion=(TextView) findViewById(R.id.txtDescripcion);
        mBtnSelecArticulo = (Button) findViewById(R.id.btnSelecArticulo);

        //Cojo el intent parcelable
        mArticulo = getIntent().getExtras().getParcelable("myArticulo");


        mtxtNumArticulo.setText(mArticulo.getNumArticulo());
        mTxtTitulo.setText(mArticulo.getTitulo());
        mTxtDescripcion.setText(mArticulo.getDescripcion());

        //Config my button
        mBtnSelecArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //launch the second activity
                Intent intent = new Intent(ArticuloEditActivity.this, EntidadVehiculoActivity.class); // Activity Source , Activity Destination

                //Aquí tengo que pasar el articulo.
                intent.putExtra("myArticulo", mArticulo); //Pasamos un objeto articulo Parcelable

                startActivity(intent); //Start intent
            }
        });

    }
}
