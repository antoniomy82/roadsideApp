package elcolegainformatico.antonio.puertoapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    private String mes;
    private int year;
    private int isVehicle;

    private String DniMatricula,NombreMarca,DomicilioReferencia,Ubicacion,myVehicle;

    private TextView txtZona,txtDate,txtArticle,txtDatos,lblEntidadVehiculo;
    private Button btnGetPhotos;

    ArrayList<String> imagePath; //Array List of path

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_validar);

        imagePath = new ArrayList<String>(8);

        setTitle("Validar Sanción");

        //GetIntent Block
        mArticulo = getIntent().getExtras().getParcelable("myArticulo");
        myVehicle = getIntent().getStringExtra("myVehicle");

        hour=getIntent().getIntExtra("hour",0);
        minute=getIntent().getIntExtra("minute",0);
        day=getIntent().getIntExtra("day",0);
        month=getIntent().getIntExtra("month",0);
        mes=getIntent().getStringExtra("mes");
        year=getIntent().getIntExtra("year",0);

        DniMatricula=getIntent().getStringExtra("DniMatricula");
        NombreMarca = getIntent().getStringExtra("NombreMarca");
        DomicilioReferencia=getIntent().getStringExtra("DomicilioReferencia");
        Ubicacion=getIntent().getStringExtra("Ubicacion");

        isVehicle=getIntent().getIntExtra("isVehicle",0); //0 Entidad o persona, 1 Vehículo


        //TextView link
        txtZona=(TextView)findViewById(R.id.txtZona);
        txtDate=(TextView)findViewById(R.id.txtDate);
        txtArticle = (TextView)findViewById(R.id.txtArticle);
        txtDatos = (TextView)findViewById(R.id.txtDatos);
        lblEntidadVehiculo = (TextView)findViewById(R.id.lblEntidadVehiculo);

        txtZona.setText("Diligencia para hacer constar que en la zona del puerto \n"+Ubicacion);
        txtDate.setText("Siendo las "+hour+":"+minute+" horas del día "+day+" de "+mes+ " de " +year+ " del año en curso");
        txtArticle.setText("Ocurrió el siguiente hecho:\n"+mArticulo.getDescripcion()+". Conforme a lo estipulado en el "+mArticulo.getNumArticulo());

        if(isVehicle==0){
            lblEntidadVehiculo.setText("Datos del particular o Empresa");
            txtDatos.setText("Nombre y Apellidos o Nombre de Empresa: \n"+NombreMarca+" DNI O CIF: "+DniMatricula+"\n Domicilio o Razón Social: "+DomicilioReferencia);
        }
        else{
            lblEntidadVehiculo.setText("Datos del vehículo");
            txtDatos.setText("Tipo: "+myVehicle+" Matricula: "+DniMatricula+"\nMarca, Modelo, Color: "+NombreMarca+"\nNº Referencia A.Portuaria: "+DomicilioReferencia);
        }


        btnGetPhotos=(Button)findViewById(R.id.btnGetPhotos);


        btnGetPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ValidarActivity.this, Gallery_MainActivity.class);

                intent.putStringArrayListExtra("photoArray",imagePath);

                startActivity(intent);
            }
        });

        imagePath=getIntent().getStringArrayListExtra("photosArray");
    }


    @Override
    protected void onResume() {
        super.onResume();
        //imagePath=(ArrayList<String>)getIntent().getStringArrayListExtra("photosArray").clone();
        imagePath=getIntent().getStringArrayListExtra("photosArray");
        Toast toast=Toast.makeText(this,"Photos Array OK",Toast.LENGTH_SHORT);toast.show();

     }

}

