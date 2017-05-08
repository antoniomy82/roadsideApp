package elcolegainformatico.antonio.puertoapp.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import elcolegainformatico.antonio.puertoapp.GalleryListPhotos.Gallery_MainActivity;
import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.Model.Sancion;
import elcolegainformatico.antonio.puertoapp.R;


/**
 * Created by antonio on 18/4/17.
 */

public class ValidarActivity extends AppCompatActivity implements Serializable{

    Articulo mArticulo;
    Sancion miSancion;

    private int hour,minute;
    private int day;
    //private int month;
    private String mes;
    private int year;
    private int isVehicle;
    private double sancion;
    boolean isSave; //Boolean Control to BtnSave (Set visible or invisible)
    private String toPrint;
    private Calendar calendar;

    private String DniMatricula,NombreMarca,DomicilioReferencia,Ubicacion,myVehicle;

    private TextView txtZona,txtDate,txtArticle,txtDatos,lblEntidadVehiculo;

    private Button btnGetPhotos;
    private Button btnSaveSancion;
    private Button btnPrint;
    private Button btnDelete;

    ArrayList<String> imagePath=null; //Array List of path
    ArrayList<Bitmap> imageBitmap=null;
    ArrayList<Sancion> sancionesSaved = new ArrayList<>(); //Store sanciones go from SancionesList



    public static final int REQUEST_CODE = 300; //Back pressed (On ActivityResult)
    private Toast mToastToShow; // Personalized Toast


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_validar);

        setTitle("Validar Sanción");


        this.sancion=150; //Inicializo a 150€

        /**
         * Get intent Block
         */
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

        //Button link
        btnGetPhotos=(Button)findViewById(R.id.btnGetPhotos);
        btnSaveSancion=(Button)findViewById(R.id.btnSaveSancion);
        btnPrint=(Button) findViewById(R.id.btnPrint);
        btnDelete=(Button) findViewById(R.id.btnDelete);

        //SetText to Screen Block
        txtZona.setText("Diligencia para hacer constar que en la zona del puerto \n"+Ubicacion);
        txtDate.setText("Siendo las "+hour+":"+convertTwoDigits(minute)+" horas del día "+day+" de "+mes+ " de " +year+ " del año en curso");
        txtArticle.setText("Ocurrió el siguiente hecho:\n"+mArticulo.getDescripcion()+". Conforme a lo estipulado en el "+mArticulo.getNumArticulo()+"\n Siendo la multa de "+String.format("%.0f", sancion)+" EUROS" );

        //String to print
        toPrint = "AUTORIDAD PORTUARIA DE CEUTA\n POLICÍA PORTUARIA";
        toPrint = toPrint+ "\n---------------------------------------------\n\n";
        toPrint = toPrint+ "Diligencia para hacer constar que en la zona del puerto \n"+Ubicacion;
        toPrint = toPrint+ "\nSiendo las "+hour+":"+convertTwoDigits(minute)+" horas del día "+day+" de "+mes+ " de " +year+ " del año en curso";
        toPrint = toPrint+ "\nOcurrió el siguiente hecho:\n"+mArticulo.getDescripcion()+". Conforme a lo estipulado en el "+mArticulo.getNumArticulo()+"\n Siendo la multa de "+String.format("%.0f", sancion)+" EUROS";


        if(isVehicle==0){
            lblEntidadVehiculo.setText("Datos del particular o Empresa");
            txtDatos.setText("Nombre y Apellidos o Nombre de Empresa: \n"+NombreMarca+" DNI O CIF: "+DniMatricula+"\n Domicilio o Razón Social: "+DomicilioReferencia);

            //String to print
            toPrint = toPrint+ "\nDatos del particular o Empresa";
            toPrint = toPrint+ "\nNombre y Apellidos o Nombre de Empresa: \n"+NombreMarca+" DNI O CIF: "+DniMatricula+"\n Domicilio o Razón Social: "+DomicilioReferencia;
        }
        else{
            lblEntidadVehiculo.setText("Datos del vehículo");
            txtDatos.setText("Tipo: "+myVehicle+" Matricula: "+DniMatricula+"\nMarca, Modelo, Color: "+NombreMarca+"\nNº Referencia A.Portuaria: "+DomicilioReferencia);

            //String to print
            toPrint = toPrint+"\n\nDatos del vehículo";
            toPrint = toPrint+"\nTipo: "+myVehicle+" Matricula: "+DniMatricula+"\nMarca, Modelo, Color: "+NombreMarca+"\nNº Referencia A.Portuaria: "+DomicilioReferencia;

        }

        //Put to Invisible Delete button
        btnDelete.setVisibility(View.INVISIBLE);

        /**
         * OnClickListener Buttons
         */

        //Cámera-Gallery
        btnGetPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ValidarActivity.this, Gallery_MainActivity.class);

                intent.putExtra("validarToGallery", imagePath);
                intent.putExtra("isSave",isSave);


                startActivityForResult(intent,REQUEST_CODE);
            }
        });


        //Save
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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent SL = new Intent(ValidarActivity.this, SancionesListActivity.class);

                 //del image from my ArrayList.
                sancionesSaved.remove(this);
                
                //Solo para versión sin Firebase.
                SL.putExtra("sancionesSaved",sancionesSaved);

                startActivity(SL);


            }
        });



        //Gone from Sanciones List (Visible and Invisible Buttón check)
        if( getIntent().getBooleanExtra("isSave",true)==false)
        {
            btnSaveSancion.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            btnGetPhotos.setText("Fotos Guardadas");
            this.isSave=true;
        }

        //String to print
        toPrint = toPrint+ "\n---------------------------------------------\n\n";

        //Date (Get Variable)
        calendar = Calendar.getInstance();
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        int thisMonth = calendar.get(Calendar.MONTH)+1;
        String esteMes = getMonth(thisMonth);
        int thisYear = calendar.get(Calendar.YEAR);

        toPrint = toPrint + " Ceuta a "+ thisDay+ " de "+esteMes+" de "+thisYear;

        //Poner número agente denunciante.
        toPrint = toPrint + "\n El agente denunciante número: "+"1234";


        //Print Sanción
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast(v, toPrint, 10000); //Milliseconds

            }
        });

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

    /**
     * Utils block
     */

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

    //Return Spanish Month
    public String getMonth(int month){

        String thisMonth="";
        if(month==1)
        {
            thisMonth="Enero";
        }
        if(month==2)
        {
            thisMonth="Febrero";
        }
        if(month==3)
        {
            thisMonth="Marzo";
        }
        if(month==4)
        {
            thisMonth="Abril";
        }
        if(month==5)
        {
            thisMonth="Mayo";
        }
        if(month==6)
        {
            thisMonth="Junio";
        }
        if(month==7)
        {
            thisMonth="Julio";
        }
        if(month==8)
        {
            thisMonth="Agosto";
        }
        if(month==9)
        {
            thisMonth="Septiembre";
        }
        if(month==10)
        {
            thisMonth="Octubre";
        }
        if(month==11)
        {
            thisMonth="Noviembre";
        }
        if(month==12)
        {
            thisMonth="Diciembre";
        }
        return thisMonth;
    }


    //Show toast , set duration time and String to show in toast
    public void showToast(View view, String toShow, int duration) {
        // Set the toast and duration
        int toastDurationInMilliSeconds = duration;
        mToastToShow = Toast.makeText(this, toShow, Toast.LENGTH_LONG);

        // Set the countdown to display the toast
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 /*Tick duration*/) {
            public void onTick(long millisUntilFinished) {
                mToastToShow.show();
            }
            public void onFinish() {
                mToastToShow.cancel();
            }
        };
        // Show the toast and starts the countdown
        mToastToShow.show();
        toastCountDown.start();
    }

}//ValidarActivity

