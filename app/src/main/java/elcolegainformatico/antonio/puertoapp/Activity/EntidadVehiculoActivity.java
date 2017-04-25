package elcolegainformatico.antonio.puertoapp.Activity;


import java.util.Calendar; //(API < 23 required)Time and Date with Calendar and this implementation.This supports until API23 (With API24 too work).

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
    private TextView tvZona;
    private TextView lbSelAddress;
    private TextView lbSelArticulo;
    private TextView lblTimeDate;

    //Spinners block
    private Spinner  spinner_entidad;
    private Spinner  spinner_vehiculo;
    private String  myVehicle; //To use String vble (Spinner only permited final)

    private EditText edDniMatricula;
    private EditText edNombreMarca;
    private EditText edDomicilioReferencia;
    private EditText edOther;


    //Date & Time variables
    private  Calendar calendar;
    private DatePicker datePicker;
    private int hour,minute;
    private int day;
    private int month;
    private int year;

    private Button btnNext2; //to ValidarActitivy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entidad_vehiculo);

        setTitle("Introduzca datos de entidad o vehículo");


        //GetIntent Block
        Articulo mArticulo = getIntent().getExtras().getParcelable("selected_art");
        String address = getIntent().getStringExtra("myAddress");
        String zone = getIntent().getStringExtra("myZone");

        //TextView block
        tvZona = (TextView) findViewById(R.id.tvZona);
        lbSelAddress = (TextView) findViewById(R.id.lbSelAddress);
        lbSelArticulo = (TextView) findViewById(R.id.lbSelArticulo);
        lblTimeDate = (TextView) findViewById(R.id.lblTimeDate);

        //EditText block
        edDniMatricula = (EditText) findViewById(R.id.edDniMatricula);  //DNI o CIF / Matrícula
        edNombreMarca = (EditText) findViewById(R.id.edNombreMarca);    //Nombre y Apellidos o Nombre Empresa / Marca, Modelo, Color
        edDomicilioReferencia = (EditText) findViewById(R.id.edDomicilioReferencia);  //Domicilio o Razón Social / NºReferencia Portuaria
        edOther = (EditText) findViewById(R.id.edOther); // Otros

        //Set TextView Zone ,Address and Articulo.
        String myZone = "Zona: " + zone;
        String myAddress = "Lugar: " + address;

        tvZona.setText(myZone);
        lbSelAddress.setText(myAddress);
        lbSelArticulo.setText(mArticulo.getNumArticulo() + " : " + mArticulo.getTitulo());


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


        //Link Next button
        btnNext2=(Button) findViewById(R.id.btnNext2);


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

    public void setBtnNext2(View view){

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch the second activity
                Intent intent = new Intent(EntidadVehiculoActivity.this, ValidarActivity.class); // Activity Source , Activity Destination

                //QUEDA PASAR TODOS LOS EXTRASS!!!!!!

                 startActivity(intent); //Start intent
            }
        });

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

}