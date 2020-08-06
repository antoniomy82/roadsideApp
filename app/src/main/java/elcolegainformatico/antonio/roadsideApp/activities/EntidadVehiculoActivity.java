package elcolegainformatico.antonio.roadsideApp.activities;


import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import elcolegainformatico.antonio.roadsideApp.R;
import elcolegainformatico.antonio.roadsideApp.model.Tipo;


/**
 * Created by antonio on 14/4/17.
 */

public class EntidadVehiculoActivity extends AppCompatActivity {

    //First textviews (in blue)
    private TextView lbSelArticulo;
    private TextView lblTimeDate;

    //Spinners block
    private Spinner  spinner_entidad;
    private Spinner  spinner_vehiculo;
    private String  myVehicle; //To use String vble (Spinner only permited final)

    private EditText edDniMatricula;
    private EditText edNombreMarca;
    private EditText edDomicilioReferencia;
    private EditText edLocation;


    //Date & Time variables
    private Calendar calendar;
    private int hour,minute;
    private int day;
    private int month;
    private String mes;
    private int year;
    private int isVehicle;

    private Button btnNext; //to ValidarActitivy
    private Button btnLocation;

    private ImageButton home_custom_bar;
    private TextView text_custom_title;

    private Tipo mTipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entidad_vehiculo);

        //Custom title bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_title_bar);


        text_custom_title=(TextView)findViewById(R.id.text_custom_title);
        text_custom_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
        text_custom_title.setText(getResources().getString(R.string.IntroducirDatos));
        home_custom_bar=(ImageButton) findViewById(R.id.home_custom_bar);

        home_custom_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntidadVehiculoActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });



        //GetIntent Block
        mTipo = getIntent().getExtras().getParcelable("myAssistance");


        //TextView block
        lbSelArticulo = (TextView) findViewById(R.id.lbSelArticulo);
        lblTimeDate = (TextView) findViewById(R.id.lblTimeDate);
        lbSelArticulo.setText("Artículo: " + mTipo.getNumArticulo() + " : " + mTipo.getTitulo());

        //EditText block
        edDniMatricula = (EditText) findViewById(R.id.edDniMatricula);  //DNI o CIF / Matrícula
        edNombreMarca = (EditText) findViewById(R.id.edNombreMarca);    //Nombre y Apellidos o Nombre Empresa / Marca, Modelo, Color
        edDomicilioReferencia = (EditText) findViewById(R.id.edDomicilioReferencia);  //Domicilio o Razón Social / NºReferencia Portuaria
        edLocation = (EditText) findViewById(R.id.edLocation); // Otros

        //Link Next button
        btnNext =(Button) findViewById(R.id.btnNext2);
        btnLocation =(Button) findViewById(R.id.btnLocation);

        //Date (Get Variable)
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        mes = getMonth(month);
        year = calendar.get(Calendar.YEAR);

        //Time (Get Variable)
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        //Launch the date and time of the "hecho" to actual date/time
        showDateTime();


        //Vehículo
        spinner_vehiculo = (Spinner) findViewById(R.id.spiner_vehiculo);


        cleanScreen();
        edDniMatricula.setHint("Matricula");
        edNombreMarca.setHint("Marca, Modelo, Color");
        edDomicilioReferencia.setHint("Nombre y Apellidos - NIF");

        spinner_vehiculo.setVisibility(View.VISIBLE); //Show
        setIsVehicle(1);


        //Spinner Tipo de Vehículo
        spinner_vehiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String myVehicle = spinner_vehiculo.getSelectedItem().toString();
                setMyVehicle(myVehicle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /**
         * Buttons block
         */

        //Next Activity
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch the second activity
                Intent intent = new Intent(EntidadVehiculoActivity.this, ValidarActivity.class); // Activity Source , Activity Destination

                //Hacer un check de que estén los campos rellenos y llamar a dialogEmpty
                intent.putExtra("hourInfraccion",hour);
                intent.putExtra("minuteInfraccion",minute);
                intent.putExtra("dayInfraccion",day);
                //intent.putExtra("month",month);
                intent.putExtra("mesInfraccion",mes);
                intent.putExtra("yearInfraccion",year);

                intent.putExtra("DniMatricula",edDniMatricula.getText().toString());
                intent.putExtra("NombreMarca",edNombreMarca.getText().toString());
                intent.putExtra("DomicilioReferencia",edDomicilioReferencia.getText().toString()) ;
                intent.putExtra("Ubicacion",edLocation.getText().toString());

                intent.putExtra("myVehicle", myVehicle);
                intent.putExtra("myArticulo", mTipo);
                intent.putExtra("isVehicle", isVehicle);

                startActivity(intent); //Start intent
            }
        });

        //Get GPS Location
        btnLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String address; //Use to paint GeoLocation in edText or Toast
                GPSTracker gps = new GPSTracker(getApplicationContext(), EntidadVehiculoActivity.this);



                //Check GPS Permissions
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EntidadVehiculoActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                } else  //I have permissions
                {

                    if (gps.canGetLocation()) { //I have GPS enable

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();


                        // \n is for new line
                        Toast.makeText(getApplicationContext(), "Ubicación - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                        address = gps.getLocationAddress(latitude, longitude);
                        edLocation.setText(address);


                    } else { //I have GPS disable
                        dialogNoGPS();
                    }

                }
            }//onClick

        });

    }//onCreate

    /**
     *
     * Date & Time Picker Section
     */

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Calendario",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }
    //Date Picker
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    setYear(arg1);
                    setMonth(arg2);
                    setMes(arg2);
                    setDay(arg3);
                    showDateTime();
                }
            };

    //Set Time & Time Picker
    public void setTime(View view){

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minuteOfDay) {


                        setHour(hourOfDay);
                        setMinute(minuteOfDay);
                        showDateTime() ;
                    }
                }, hour, minute, DateFormat.is24HourFormat(this));

         timePickerDialog.show();
    }

    //Show Date and Time in lblTimeDate
    private void showDateTime() {

        String myMinute;

        if (getMinute() < 10) {
            myMinute = "0" + getMinute();
            lblTimeDate.setText("Recogida el " + getDay() + " de " + getMonth(getMonth()) + " de " + getYear() + " a las " + getHour() + ":" + myMinute);
        } else {
            myMinute = String.valueOf(getMinute());
            lblTimeDate.setText("Recogida el " + getDay() + " de " + getMonth(getMonth()) + " de " + getYear() + " a las " + getHour() + ":" + myMinute);
        }

    }


    /**
     * Utils Section
     */

    //Funtion to insert myVehicle (spinner_vehiculo) that is Final, into variable local myVehicle.
    private void setMyVehicle (String myVehicle){

        this.myVehicle = myVehicle;
    }

    //My own flag to know if Vehiculo o entidad.
    private void setIsVehicle(int position){
        this.isVehicle = position;
    }

    //Clean editx
    private void cleanScreen(){

        edDniMatricula.setText("");
        edNombreMarca.setText("");
        edDomicilioReferencia.setText("");
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

/**
 *  Getter and Setter DATE & TIME
 */
    private int getHour() {
        return hour;
    }

    private void setHour(int hour) {
        this.hour = hour;
    }

    private int getMinute() {
        return minute;
    }

    private void setMinute(int minute) {
        this.minute = minute;
    }

    private int getDay() {
        return day;
    }

    private void setDay(int day) {
        this.day = day;
    }

    private int getMonth() {
        return month;
    }

    private void setMonth(int month) {
        this.month = month;
    }

    private void setMes(int month){ this.mes=getMonth(month);}

    private int getYear() {
        return year;
    }

    private void setYear(int year) {
        this.year = year;
    }



    /**
     *  Dialog to Enable GPS
     */

    private void dialogNoGPS(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(EntidadVehiculoActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("GPS DESACTIVADO");
        dialog.setMessage("¿Desea activar GPS?");
        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                EntidadVehiculoActivity.this.startActivity(intent);
            }
        })
                .setNegativeButton("Cancelar ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    /**
     * Dialog Empty Edittext
     */
    public void dialogEmpty(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(EntidadVehiculoActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("DIRECCION VACIA");
        dialog.setMessage("Debe rellenar o obtener la dirección" );
        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }


}
