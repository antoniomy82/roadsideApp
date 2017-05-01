package elcolegainformatico.antonio.puertoapp.Activities;


import java.util.Calendar; //(API < 23 required)Time and Date with Calendar and this implementation.This supports until API23 (With API24 too work).

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
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.R;


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
    private DatePicker datePicker;
    private int hour,minute;
    private int day;
    private int month;
    private int year;

    private Button btnNext2; //to ValidarActitivy
    private Button btnLocation;

    Articulo mArticulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entidad_vehiculo);

        setTitle("Introduzca datos de entidad o vehículo");


        //GetIntent Block
        mArticulo = getIntent().getExtras().getParcelable("myArticulo");

        //TextView block
        lbSelArticulo = (TextView) findViewById(R.id.lbSelArticulo);
        lblTimeDate = (TextView) findViewById(R.id.lblTimeDate);
        lbSelArticulo.setText(mArticulo.getNumArticulo() + " : " + mArticulo.getTitulo());

        //EditText block
        edDniMatricula = (EditText) findViewById(R.id.edDniMatricula);  //DNI o CIF / Matrícula
        edNombreMarca = (EditText) findViewById(R.id.edNombreMarca);    //Nombre y Apellidos o Nombre Empresa / Marca, Modelo, Color
        edDomicilioReferencia = (EditText) findViewById(R.id.edDomicilioReferencia);  //Domicilio o Razón Social / NºReferencia Portuaria
        edLocation = (EditText) findViewById(R.id.edLocation); // Otros

        //Link Next button
        btnNext2=(Button) findViewById(R.id.btnNext2);
        btnLocation =(Button) findViewById(R.id.btnLocation);

        //Date (Get Variable)
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);

        //Time (Get Variable)
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        //Launch the date and time of the "hecho" to actual date/time
        showDateTime();


        //Spinner Entidad_Vehículo
        spinner_entidad = (Spinner) findViewById(R.id.spinner_entidad);
        spinner_vehiculo = (Spinner) findViewById(R.id.spiner_vehiculo);

        spinner_entidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) //Equal to Datos del particular o empresa
                {
                    cleanScreen();
                    edDniMatricula.setHint("DNI o CIF");
                    edNombreMarca.setHint("Nombre y Apellidos o Nombre de la Empresa");
                    edDomicilioReferencia.setHint("Domicilio o Razón Social");

                    spinner_vehiculo.setVisibility(View.GONE); //Hide spinner_vehículo
                }
                if (position == 1) //Vehículo
                {
                    cleanScreen();
                    edDniMatricula.setHint("Matricula");
                    edNombreMarca.setHint("Marca, Modelo, Color");
                    edDomicilioReferencia.setHint("Nº Referencia A. Portuaria");

                    spinner_vehiculo.setVisibility(View.VISIBLE); //Show
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch the second activity
                Intent intent = new Intent(EntidadVehiculoActivity.this, ValidarActivity.class); // Activity Source , Activity Destination


                //Hacer un check de que estén los campos rellenos y llamar a dialogEmpty
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                intent.putExtra("day",day);
                intent.putExtra("month",month);
                intent.putExtra("year",year);

                intent.putExtra("DniMatricula",edDniMatricula.getText().toString());
                intent.putExtra("NombreMarca",edNombreMarca.getText().toString());
                intent.putExtra("DomicilioReferencia",edDomicilioReferencia.getText().toString()) ;
                intent.putExtra("Ubicacion",edLocation.getText().toString());

                intent.putExtra("myVehicle", myVehicle);
                intent.putExtra("myArticulo",mArticulo);

                startActivity(intent); //Start intent
            }
        });

        //Get GPS Location
        btnLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String address = ""; //Use to paint GeoLocation in edText or Toast
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

                        address = gps.getLocationAddress();

                        if (gps.getAddress()) {
                            edLocation.setText(address); //I Paint my address with geodecoder function (GPSTracker)
                        } else //No geodecoder location
                        {
                            Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG).show(); //Show fail in a toast
                        }

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
        // TODO Auto-generated method stub
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
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    setYear(arg1);
                    setMonth(arg2);
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
            lblTimeDate.setText("Hecho ocurrido el " + getDay() + " de " + getMonth(getMonth())  + " de " + getYear() + " A las "+ getHour() + ":" + myMinute);
        } else {
            myMinute = String.valueOf(getMinute());
            lblTimeDate.setText("Hecho ocurrido el " + getDay() + " de " + getMonth(getMonth())  + " de " + getYear() + " A las "+ getHour() + ":" + myMinute);
        }

    }


    /**
     * Utils Section
     */

    //Funtion to insert myVehicle (spinner_vehiculo) that is Final, into variable local myVehicle.
    public void setMyVehicle (String myVehicle){

        this.myVehicle = myVehicle;
        Toast.makeText(this,myVehicle,Toast.LENGTH_SHORT).show();
    }

    //Clean editx
    public void cleanScreen(){

        edDniMatricula.setText("");
        edNombreMarca.setText("");
        edDomicilioReferencia.setText("");
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

/**
 *  Getter and Setter DATE & TIME
 */
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }



    /**
     *  Dialog to Enable GPS
     */

    public void dialogNoGPS(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(EntidadVehiculoActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("GPS DESACTIVADO");
        dialog.setMessage("¿Desea activar el GPS?" );
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
