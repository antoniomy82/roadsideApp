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
    private double importeSancion;
    boolean isSave; //Boolean Control to BtnSave (Set visible or invisible)
    private String toPrint;

    //today Date
    private Calendar calendar;
    private int thisDay, thisMonth,thisYear;

    private int numero,agente;

    private String DniMatricula,NombreMarca,DomicilioReferencia,Ubicacion,myVehicle;

    private TextView txtZona,txtDate,txtArticle,txtDatos,lblEntidadVehiculo, txtToDay;

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


        this.importeSancion =150; //Inicializo a 150€
        this.agente= 1234;

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

        thisDay=getIntent().getIntExtra("thisDay",0);

        if(thisDay!=0){
          thisMonth=getIntent().getIntExtra("thisMonth",0);
          thisYear=getIntent().getIntExtra("thisYear",0);
          numero=getIntent().getIntExtra("numero",0);
          agente=getIntent().getIntExtra("agente",0);
        }

        else {
        //Date to put importeSancion
        calendar = Calendar.getInstance();
        thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        thisMonth = calendar.get(Calendar.MONTH);
        thisYear = calendar.get(Calendar.YEAR);

         //Genero el número de multa
         int pos=sancionesSaved.size();

         if(pos == 0) //Initial case
             {
              numero=100;
              }
         if (pos>0)
             {
              numero= this.sancionesSaved.get(pos-1).getNumero()+1;
             }

           //agente=getAgente(); ( o como coño lo llame)
        }

        String esteMes = getMonth(thisMonth);

        String _Agente= "\nEl agente denunciante número: "+agente;
        String _ToDay= " Ceuta "+ thisDay+ " de "+esteMes+" de "+thisYear;


        //TextView link
        txtZona=(TextView)findViewById(R.id.txtZona);
        txtDate=(TextView)findViewById(R.id.txtDate);
        txtArticle = (TextView)findViewById(R.id.txtArticle);
        txtDatos = (TextView)findViewById(R.id.txtDatos);
        lblEntidadVehiculo = (TextView)findViewById(R.id.lblEntidadVehiculo);
        txtToDay=(TextView)findViewById(R.id.txtToDay);

        //Button link
        btnGetPhotos=(Button)findViewById(R.id.btnGetPhotos);
        btnSaveSancion=(Button)findViewById(R.id.btnSaveSancion);
        btnPrint=(Button) findViewById(R.id.btnPrint);
        btnDelete=(Button) findViewById(R.id.btnDelete);



        //SetText to Screen Block
        txtZona.setText("Diligencia para hacer constar que en la zona del puerto \n"+Ubicacion);
        txtDate.setText("Siendo las "+hour+":"+convertTwoDigits(minute)+" horas del "+day+" de "+mes+ " de " +year+ " del año en curso");
        txtArticle.setText("Ocurrió el siguiente hecho:\n"+mArticulo.getDescripcion()+". Conforme a lo estipulado en el "+mArticulo.getNumArticulo()+"\n Siendo la multa de "+String.format("%.0f", importeSancion)+" EUROS" );
        txtToDay.setText(_ToDay+" Nº "+numero+_Agente);
        //String to print
        toPrint = "AUTORIDAD PORTUARIA DE CEUTA\n POLICÍA PORTUARIA";
        toPrint = toPrint+ "\n---------------------------------------------\n\n";
        toPrint = toPrint+ "Diligencia para hacer constar que en la zona del puerto \n"+Ubicacion;
        toPrint = toPrint+ "\nSiendo las "+hour+":"+convertTwoDigits(minute)+" horas del "+day+" de "+mes+ " de " +year+ " del año en curso";
        toPrint = toPrint+ "\nOcurrió el siguiente hecho:\n"+mArticulo.getDescripcion()+". Conforme a lo estipulado en el "+mArticulo.getNumArticulo()+"\n Siendo la multa de "+String.format("%.0f", importeSancion)+" EUROS";


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
             miSancion=new Sancion(mArticulo, hour, minute, day, mes, year, isVehicle, importeSancion, DniMatricula, NombreMarca, DomicilioReferencia, Ubicacion, myVehicle, imagePath, imageBitmap,thisDay,thisMonth,thisYear,numero,agente);
                //Lanzar intent a SancionesListActivity

                Intent SL = new Intent(ValidarActivity.this, SancionesListActivity.class);

                SL.putExtra("miSancion",miSancion);

                //Solo para versión sin Firebase.
                SL.putExtra("sancionesSaved",sancionesSaved);

                startActivity(SL);

            }
        });

        //Del Sancion
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 //del image from my ArrayList.
                sancionesSaved.remove(getArrayListPosition (numero));

                Intent delIntent = new Intent(ValidarActivity.this, SancionesListActivity.class);

                //Solo para versión sin Firebase.
                delIntent.putExtra("sancionesSaved",sancionesSaved);

                startActivity(delIntent);


            }
        });


        //Gone from Sanciones List (Visible and Invisible Buttón check)
        if( getIntent().getBooleanExtra("isSave",true)==false)
        {
            btnSaveSancion.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            btnGetPhotos.setText("Fotos y Firma Guardadas");
            this.isSave=true;
        }

        //String to print
        toPrint = toPrint+ "\n---------------------------------------------\n\n";
        toPrint = toPrint + _ToDay;
        toPrint = toPrint +_Agente;


        //Print Sanción (Emule a printer)
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast(v, toPrint, 10000); //Milliseconds
                //Send toPrint
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
                  String noImage=  "drawable://"+R.drawable.gallery_imgnodisponible56;
                  int imageCount=0;

                  for(int i=0; i<imagePath.size(); i++){
                     if(imagePath.get(i).equals(noImage))
                      {

                      }
                      else{
                          imageCount++;
                      }
                  }
                  Toast toast = Toast.makeText(this, "Imagenes guardadas = "+imageCount, Toast.LENGTH_SHORT); toast.show();
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

    //Modify importe Sanción
    public void setImporteSancion(double importeSancion){
        this.importeSancion = importeSancion;
    }

    //Return Spanish Month
    public String getMonth(int month){

        String thisMonth="";
        if(month==0)
        {
            thisMonth="Enero";
        }
        if(month==1)
        {
            thisMonth="Febrero";
        }
        if(month==2)
        {
            thisMonth="Marzo";
        }
        if(month==3)
        {
            thisMonth="Abril";
        }
        if(month==4)
        {
            thisMonth="Mayo";
        }
        if(month==5)
        {
            thisMonth="Junio";
        }
        if(month==6)
        {
            thisMonth="Julio";
        }
        if(month==7)
        {
            thisMonth="Agosto";
        }
        if(month==8)
        {
            thisMonth="Septiembre";
        }
        if(month==9)
        {
            thisMonth="Octubre";
        }
        if(month==10)
        {
            thisMonth="Noviembre";
        }
        if(month==11)
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

   //Return position into arrayList
    public int getArrayListPosition (int numero){

        int position=0;

        for (int i=0; i<sancionesSaved.size(); i++){

            if(sancionesSaved.get(i).getNumero()== numero){
                position=i;
            }
        }
      return position;
    }

}//ValidarActivity

