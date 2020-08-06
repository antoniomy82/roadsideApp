package elcolegainformatico.antonio.roadsideApp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import elcolegainformatico.antonio.roadsideApp.R;
import elcolegainformatico.antonio.roadsideApp.gallery_list_photos.Gallery_MainActivity;
import elcolegainformatico.antonio.roadsideApp.model.Asistencia;
import elcolegainformatico.antonio.roadsideApp.model.Tipo;


/**
 * Created by antonio on 18/4/17.
 */

public class ValidarActivity extends AppCompatActivity implements Serializable {

    private Tipo mTipo;
    private Asistencia miAsistencia;

    private int hourInfraccion;
    private int minuteInfraccion;
    private int dayInfraccion;
    //private int month;
    private String mesInfraccion;
    private int yearInfraccion;
    private int isVehicle;
    private double importeSancion;
    private boolean isSave; //Boolean Control to BtnSave (Set visible or invisible)
    private String toPrint;

    //today Date
    private Calendar calendar;
    private int thisDay, thisMonth,thisYear;

    String dniMatricula;
    private String nombreMarca;
    private String DomicilioReferencia;
    private String Ubicacion;
    private String myVehicle;

    private TextView txtZona,txtDate,txtArticle,txtDatos,lblEntidadVehiculo, txtToDay;

    private Button btnGetPhotos;
    private Button btnSaveSancion;
    private Button btnPrint;
    private Button btnDelete;

    private ArrayList<String> imagePath=null; //Array List of path
    public ArrayList<String> imageBitmap=null;

    private static final int REQUEST_CODE = 300; //Back pressed (On ActivityResult)
    private Toast mToastToShow; // Personalized Toast

    private ImageButton home_custom_bar;
    private TextView text_custom_title;

    private DatabaseReference dbFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_validar);


        //Custom title bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_title_bar);


        text_custom_title=(TextView)findViewById(R.id.text_custom_title);
        text_custom_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));

        text_custom_title.setText(getResources().getString(R.string.Validar));

        home_custom_bar=(ImageButton) findViewById(R.id.home_custom_bar);

        home_custom_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ValidarActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


        //Get instance Firebase DB
        dbFirebase = FirebaseDatabase.getInstance().getReference();


        /**
         * Get intent Block
         */
        mTipo = getIntent().getExtras().getParcelable("myArticulo");
        hourInfraccion = getIntent().getIntExtra("hourInfraccion", 0);
        minuteInfraccion = getIntent().getIntExtra("minuteInfraccion", 0);
        dayInfraccion = getIntent().getIntExtra("dayInfraccion", 0);
        // month=getIntent().getIntExtra("month",0);
        mesInfraccion = getIntent().getStringExtra("mesInfraccion");
        yearInfraccion = getIntent().getIntExtra("yearInfraccion", 0);

        dniMatricula = getIntent().getStringExtra("DniMatricula");
        nombreMarca = getIntent().getStringExtra("NombreMarca");
        DomicilioReferencia = getIntent().getStringExtra("DomicilioReferencia");
        Ubicacion = getIntent().getStringExtra("Ubicacion");

        isVehicle = getIntent().getIntExtra("isVehicle", 0); //0 Entidad o persona, 1 Vehículo


        myVehicle = getIntent().getStringExtra("myVehicle");


        if (getIntent().getStringArrayListExtra("ImagePath") != null) {
            this.imagePath = getIntent().getStringArrayListExtra("ImagePath");
            //Dont use bitmap
        }

        if (getIntent().getStringArrayListExtra("ImageBitmap") != null) {
            this.imageBitmap = getIntent().getStringArrayListExtra("ImageBitmap");
        }

        thisDay = getIntent().getIntExtra("thisDay", 0);

        if (thisDay != 0) {
            thisMonth = getIntent().getIntExtra("thisMonth", 0);
            thisYear = getIntent().getIntExtra("thisYear", 0);
        } else {
            //Date to put importeSancion
            calendar = Calendar.getInstance();
            thisDay = calendar.get(Calendar.DAY_OF_MONTH);
            thisMonth = calendar.get(Calendar.MONTH);
            thisYear = calendar.get(Calendar.YEAR);

        }

        //Calculo el importe de la infracción
        int tipoAsistencia = Integer.parseInt(mTipo.getNumArticulo());
        String tipo;

        if (tipoAsistencia < 10) {
            this.importeSancion = 47 * tipoAsistencia;
            tipo = "Aseguradora";
        } else {
            this.importeSancion = 58 * (tipoAsistencia / 10);
            tipo = "Particular";
        }

        String esteMes = getMonth(thisMonth);

        String _ToDay = " Madrid " + thisDay + " de " + esteMes + " de " + thisYear;
        String _Importe = "\nImporte Total: " + importeSancion + "€ - Tipo: " + tipo;


        //TextView link
        txtZona = (TextView) findViewById(R.id.txtZona);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtArticle = (TextView) findViewById(R.id.txtArticle);
        txtDatos = (TextView) findViewById(R.id.txtDatos);
        lblEntidadVehiculo = (TextView) findViewById(R.id.lblEntidadVehiculo);
        txtToDay = (TextView) findViewById(R.id.txtToDay);

        //Button link
        btnGetPhotos = (Button) findViewById(R.id.btnGetPhotos);
        btnSaveSancion = (Button) findViewById(R.id.btnSaveSancion);
        btnPrint = (Button) findViewById(R.id.btnPrint);
        btnDelete = (Button) findViewById(R.id.btnDelete);


        //SetText to Screen Block
        txtZona.setText("Diligencia para hacer constar que en la dirección" + "\n" + Ubicacion);
        txtDate.setText("Siendo las " + hourInfraccion + ":" + convertTwoDigits(minuteInfraccion) + " horas del " + dayInfraccion + " de " + mesInfraccion + " de " + yearInfraccion + " del año en curso");
        txtArticle.setText("Se realizó la siguiente asistencia: " + mTipo.getDescripcion() + " Conforme a lo estipulado en el " + mTipo.getNumArticulo() + " Siendo el importe de " + String.format("%.0f", importeSancion) + " EUROS");
        txtToDay.setText(_ToDay + _Importe);


        //String to print
        toPrint = "ROADSIDE ASSISTANCE APP\n ";
        toPrint = toPrint + "\n---------------------------------------------\n\n";
        toPrint = toPrint + "Diligencia para hacer constar que en la direccion \n" + Ubicacion;
        toPrint = toPrint + "\nSiendo las " + hourInfraccion + ":" + convertTwoDigits(minuteInfraccion) + " horas del " + dayInfraccion + " de " + mesInfraccion + " de " + yearInfraccion + " del año en curso";
        toPrint = toPrint + "\nSe realizó la siguiente asistencia:\n" + mTipo.getDescripcion() + ". Conforme a lo estipulado en el " + mTipo.getNumArticulo() + "\n Siendo el importe de " + String.format("%.0f", importeSancion) + " EUROS";


        lblEntidadVehiculo.setText(getResources().getString(R.string.DatosVehiculo));
        txtDatos.setText("Tipo: " + myVehicle + "- Matricula: " + dniMatricula + "\nMarca, Modelo, Color: " + nombreMarca + "\nNombre Completo y NIF " + DomicilioReferencia);

        //String to print
        toPrint = toPrint + "\n\nDatos del vehículo";
        toPrint = toPrint + "\nTipo: " + myVehicle + " Matricula: " + dniMatricula + "\nMarca, Modelo, Color: " + nombreMarca + "\nNombre completo - NIF: " + DomicilioReferencia;

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

                intent.putExtra("pathFromValidar", imagePath);
                intent.putExtra("imagesFromValidar", imageBitmap);
                intent.putExtra("isSave",isSave);

                startActivityForResult(intent,REQUEST_CODE);
            }
        });


        //Save
        btnSaveSancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Key = dbFirebase.child("asistencias").push().getKey();

                String Articulo_Num = ValidarActivity.this.mTipo.getNumArticulo();
                String Articulo_Tit = ValidarActivity.this.mTipo.getTitulo();
                String Articulo_Des = ValidarActivity.this.mTipo.getDescripcion();
                Tipo mTipo = new Tipo(Articulo_Num, Articulo_Tit, Articulo_Des);


                miAsistencia = new Asistencia(mTipo, hourInfraccion, minuteInfraccion, dayInfraccion, mesInfraccion, yearInfraccion, isVehicle, importeSancion, dniMatricula, nombreMarca, DomicilioReferencia, Ubicacion, myVehicle, imagePath, imageBitmap, thisDay, thisMonth, thisYear, 0);

                dbFirebase.child("asistencias").child(Key).setValue(miAsistencia);

                //Lanzar intent a AsistenciasListActivity
                Intent SL = new Intent(ValidarActivity.this, AsistenciasListActivity.class);

                startActivity(SL);

            }
        });

        //Del Asistencia
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogDelete();

            }
        });


        //Gone from Sanciones List (Visible and Invisible Buttón check)
        if (!getIntent().getBooleanExtra("isSave", true)) {
            btnSaveSancion.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            btnGetPhotos.setText(getResources().getString(R.string.fotoFirmaSaved));
            text_custom_title.setText("ASISTENCIA GUARDADA");
            this.isSave = true;
        }

        //String to print
        toPrint = toPrint+ "\n---------------------------------------------\n\n";
        toPrint = toPrint + _ToDay;
        toPrint = toPrint + _Importe;


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

            imageBitmap=new ArrayList<String>(data.getStringArrayListExtra("imagesToValidar"));

            if(data.getStringArrayListExtra("pathToValidar") !=null)
            {

                Bitmap auxBitmap;

                this.imagePath=data.getStringArrayListExtra("pathToValidar");
                String noImage=  "drawable://"+R.drawable.gallery_imgnodisponible56;
                int imageCount=0;

                for(int i=0; i<imagePath.size(); i++){
                    if(!imagePath.get(i).equals(noImage))
                    {
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
    private String convertTwoDigits(int minute){

        String myMinute;

        if(minute<10){
            myMinute="0"+minute; }

        else {
            myMinute = String.valueOf(minute);
        }

        return myMinute;
    }


    //Return Spanish Month
    private String getMonth(int month){

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
    private void showToast(View view, String toShow, int duration) {
        // Set the toast and duration

        mToastToShow = Toast.makeText(this, toShow, Toast.LENGTH_LONG);

        // Set the countdown to display the toast
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(duration, 1000 /*Tick duration*/) {
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



    private void dialogDelete() {

        final CharSequence[] options = {"SI", "NO"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿ESTÁ SEGURO QUE DESEA BORRAR ESTA ASISTENCIA?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {


                if (options[item].equals("SI")) {

                    String miNodo = getIntent().getStringExtra("nodo");

                    dbFirebase.child("asistencias").child(miNodo).removeValue();
                    Intent delIntent = new Intent(ValidarActivity.this, AsistenciasListActivity.class);
                    startActivity(delIntent);
                }

                if (options[item].equals("NO")) {
                    //Nothing

                }


            }
        });
        builder.show();
    }


}//ValidarActivity

