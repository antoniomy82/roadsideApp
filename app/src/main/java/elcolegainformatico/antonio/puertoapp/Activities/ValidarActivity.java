package elcolegainformatico.antonio.puertoapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import elcolegainformatico.antonio.puertoapp.GalleryListPhotos.Gallery_MainActivity;
import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 18/4/17.
 */

public class ValidarActivity extends AppCompatActivity{

    Articulo mArticulo;

    private int hour,minute;
    private int day;
    private int month;
    private int year;
    private String DniMatricula,NombreMarca,DomicilioReferencia,Ubicacion,myVehicle;

    private TextView txtZona,txtDate,txtArticle,txtDatos;
    private Button btnGetPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_validar);

        //GetIntent Block
        mArticulo = getIntent().getExtras().getParcelable("myArticulo");
        myVehicle = getIntent().getStringExtra("myVehicle");

        hour=getIntent().getIntExtra("hour",0);
        minute=getIntent().getIntExtra("minute",0);
        day=getIntent().getIntExtra("day",0);
        month=getIntent().getIntExtra("month",0);
        year=getIntent().getIntExtra("year",0);

        DniMatricula=getIntent().getStringExtra("DniMatricula");
        NombreMarca = getIntent().getStringExtra("NombreMarca");
        DomicilioReferencia=getIntent().getStringExtra("DomicilioReferencia");
        Ubicacion=getIntent().getStringExtra("Ubicacion");


        //TextView link
        txtZona=(TextView)findViewById(R.id.txtZona);
        txtDate=(TextView)findViewById(R.id.txtDate);
        txtArticle = (TextView)findViewById(R.id.txtArticle);
        txtDatos = (TextView)findViewById(R.id.txtDatos);

        txtZona.setText("Diligencia para hacer constar que en la zona del puerto "+Ubicacion);
        txtDate.setText("Siendo las "+hour+":"+minute+" horas del día "+day+" de "+month+ "de" +year+ "del año en curso");
        txtArticle.setText("Ocurrió el siguiente hecho: "+mArticulo.getDescripcion()+ "estando estipulado en el"+mArticulo.getNumArticulo());
        txtDatos.setText(DniMatricula+" "+NombreMarca+" "+myVehicle+" "+DomicilioReferencia+" "+DomicilioReferencia);

        btnGetPhotos=(Button)findViewById(R.id.btnGetPhotos);


        btnGetPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ValidarActivity.this, Gallery_MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
