package elcolegainformatico.antonio.puertoapp.Activities;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import elcolegainformatico.antonio.puertoapp.GalleryListPhotos.Gallery_MainActivity;
import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.Model.Sancion;
import elcolegainformatico.antonio.puertoapp.R;

import static android.support.design.R.styleable.CoordinatorLayout;


/**
 * Created by antonio on 18/4/17.
 */

public class ValidarActivity extends AppCompatActivity implements Serializable{

    Articulo mArticulo;


    private int hour,minute;
    private int day;
    //private int month;
    private String mes;
    private int year;
    private int isVehicle;
    private double sancion;
    boolean isSave =true;

    private String DniMatricula,NombreMarca,DomicilioReferencia,Ubicacion,myVehicle;

    private TextView txtZona,txtDate,txtArticle,txtDatos,lblEntidadVehiculo;
    private Button btnGetPhotos;
    private Button btnSaveSancion;


    ArrayList<String> imagePath=null; //Array List of path
    ArrayList<Bitmap> imageBitmap=null;
    ArrayList<Sancion> sancionesSaved = new ArrayList<>(); //Store sanciones go from SancionesList

    Sancion miSancion;


    public static final int REQUEST_CODE = 300; //Back pressed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_validar);

        setTitle("Validar Sanción");


        this.sancion=150; //Inicializo a 150€

        //GetIntent Block
        mArticulo = getIntent().getExtras().getParcelable("myArticulo");
        myVehicle = getIntent().getStringExtra("myVehicle");

        //Esto lo usamos en la versión sin FireBase, tenemos que pasarla a la siguiente activity
        sancionesSaved = (ArrayList<Sancion>) getIntent().getSerializableExtra("sancionesSaved");

        hour=getIntent().getIntExtra("hour",0);
        minute=getIntent().getIntExtra("minute",0);
        day=getIntent().getIntExtra("day",0);
       // month=getIntent().getIntExtra("month",0);
        mes=getIntent().getStringExtra("mes");
        year=getIntent().getIntExtra("year",0);

        DniMatricula=getIntent().getStringExtra("DniMatricula");
        NombreMarca = getIntent().getStringExtra("NombreMarca");
        DomicilioReferencia=getIntent().getStringExtra("DomicilioReferencia");
        Ubicacion=getIntent().getStringExtra("Ubicacion");

        isVehicle=getIntent().getIntExtra("isVehicle",0); //0 Entidad o persona, 1 Vehículo

        if(getIntent().getStringArrayListExtra("ImagePath")!=null){
            this.imagePath=getIntent().getStringArrayListExtra("ImagePath");
            //Dont use bitmap
        }



        //TextView link
        txtZona=(TextView)findViewById(R.id.txtZona);
        txtDate=(TextView)findViewById(R.id.txtDate);
        txtArticle = (TextView)findViewById(R.id.txtArticle);
        txtDatos = (TextView)findViewById(R.id.txtDatos);
        lblEntidadVehiculo = (TextView)findViewById(R.id.lblEntidadVehiculo);

        txtZona.setText("Diligencia para hacer constar que en la zona del puerto \n"+Ubicacion);
        txtDate.setText("Siendo las "+hour+":"+convertTwoDigits(minute)+" horas del día "+day+" de "+mes+ " de " +year+ " del año en curso");
        txtArticle.setText("Ocurrió el siguiente hecho:\n"+mArticulo.getDescripcion()+". Conforme a lo estipulado en el "+mArticulo.getNumArticulo()+"\n Siendo la multa de "+String.format("%.0f", sancion)+" EUROS" );

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

                intent.putExtra("validarToGallery", imagePath);

                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        btnSaveSancion=(Button)findViewById(R.id.btnSaveSancion);

        btnSaveSancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //Este chocotrolo lo tendré que subir a Firebase, de momento Objecto Sanción y va a un ArrayList<Sancion> en SancionesListActivity
             miSancion=new Sancion(mArticulo, hour, minute, day, mes, year, isVehicle, sancion, DniMatricula, NombreMarca, DomicilioReferencia, Ubicacion, myVehicle, imagePath, imageBitmap);
                //Lanzar intent a SancionesListActivity

                Intent SL = new Intent(ValidarActivity.this, SancionesListActivity.class);

                SL.putExtra("miSancion",miSancion);

                //Solo para versión sin Firebase.
                SL.putExtra("sancionesSaved",sancionesSaved);

                startActivity(SL);

            }
        });

        //Gone from Sanciones List
        if( getIntent().getBooleanExtra("isSave",true)==false)
        {
            btnSaveSancion.setVisibility(View.INVISIBLE);
        }

    }


    //To get ArrayList of ImagePath to Gallery_MainActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 300 && resultCode == RESULT_OK && data!=null){

              if(data.getStringArrayListExtra("galleryToValidar") !=null)
              {
                  this.imagePath=data.getStringArrayListExtra("galleryToValidar");
                  Toast toast = Toast.makeText(this, "Imagenes guardadas = "+imagePath.size(), Toast.LENGTH_SHORT); toast.show();
              }

        else
            {
               Toast toast = Toast.makeText(this, "No hay imagenes", Toast.LENGTH_SHORT); toast.show();
            }

        }
     }

    //Convert minutes to two digits.
    public String convertTwoDigits(int minute){

        String myMinute;

        if(minute<10){
            myMinute="0"+minute; }

        else {
            myMinute = String.valueOf(minute);
        }

        return myMinute;
    }

    public void setSancion(double sancion){
        this.sancion=sancion;
    }

}//ValidarActivity

