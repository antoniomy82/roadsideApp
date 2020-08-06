package elcolegainformatico.antonio.roadsideApp.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


/**
 * Created by antonio on 4/5/17.
 */

public class Asistencia implements Parcelable {


    private int numUsuario;
    private Tipo mTipo;

    private int hourInfraccion;
    private int minuteInfraccion;
    private int dayInfraccion;



    private String mesInfraccion;
    private int yearInfraccion;
    private int isVehicle;
    private double importeSancion;

    //date to put sancion
    private int thisDay;
    private int thisMonth;
    private int thisYear;


    private String dniMatricula, nombreMarca, domicilioReferencia, ubicacion, myVehicle;

    //Image Gallery
    private ArrayList<String> imagePath;
    private ArrayList<String> imageBitmap;

    public Asistencia() {
        //needed for firebase
    }

    public Asistencia(Tipo mTipo, int hour, int minute, int day, String mes, int year, int isVehicle, double importeSancion, String DniMatricula, String NombreMarca, String DomicilioReferencia, String Ubicacion, String myVehicle, ArrayList<String> imagePath, ArrayList<String> imageBitmap, int thisDay, int thisMonth, int thisYear, int numUsuario) {
        this.mTipo = mTipo;
        this.hourInfraccion = hour;
        this.minuteInfraccion = minute;
        this.dayInfraccion = day;
        this.mesInfraccion = mes;
        this.yearInfraccion = year;
        this.isVehicle = isVehicle;

        this.importeSancion = importeSancion;
        this.dniMatricula = DniMatricula;
        this.nombreMarca = NombreMarca;
        this.domicilioReferencia = DomicilioReferencia;
        this.ubicacion = Ubicacion;
        this.myVehicle = myVehicle;
        this.imagePath = imagePath;
        this.imageBitmap = imageBitmap;

        this.thisDay = thisDay;
        this.thisMonth = thisMonth;
        this.thisYear = thisYear;
        this.numUsuario = numUsuario;

    }

    //Data Structure for Firebase . Map <Key,Value> because Firebase works with trees


    public Tipo getmTipo() {
        return mTipo;
    }

    public int getHourInfraccion() {
        return hourInfraccion;
    }

    public int getMinuteInfraccion() {
        return minuteInfraccion;
    }

    public int getDayInfraccion() {
        return dayInfraccion;
    }

    public String getMesInfraccion() {
        return mesInfraccion;
    }

    public int getYearInfraccion() {
        return yearInfraccion;
    }


    public String getDniMatricula() {
        return dniMatricula;
    }


    public String getNombreMarca() {
        return nombreMarca;
    }


    public String getDomicilioReferencia() {
        return domicilioReferencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }


    public String getMyVehicle() {
        return myVehicle;
    }


    public ArrayList<String> getImagePath() {
        return imagePath;
    }

    public ArrayList<String> getImageBitmap() {
        return imageBitmap;
    }

    public int getThisDay() {
        return thisDay;
    }

    public int getThisMonth() {
        return thisMonth;
    }

    public int getThisYear() {
        return thisYear;
    }


    public int getNumUsuario() {
        return numUsuario;
    }


    public void setNumUsuario(int numUsuario) {
        this.numUsuario = numUsuario;
    }

    public void setmTipo(Tipo mTipo) {
        this.mTipo = mTipo;
    }

    public void setHourInfraccion(int hourInfraccion) {
        this.hourInfraccion = hourInfraccion;
    }

    public void setMinuteInfraccion(int minuteInfraccion) {
        this.minuteInfraccion = minuteInfraccion;
    }

    public void setDayInfraccion(int dayInfraccion) {
        this.dayInfraccion = dayInfraccion;
    }

    public void setMesInfraccion(String mesInfraccion) {
        this.mesInfraccion = mesInfraccion;
    }

    public void setYearInfraccion(int yearInfraccion) {
        this.yearInfraccion = yearInfraccion;
    }

    public void setIsVehicle(int isVehicle) {
        this.isVehicle = isVehicle;
    }

    public void setImporteSancion(double importeSancion) {
        this.importeSancion = importeSancion;
    }

    public void setThisDay(int thisDay) {
        this.thisDay = thisDay;
    }

    public void setThisMonth(int thisMonth) {
        this.thisMonth = thisMonth;
    }

    public void setThisYear(int thisYear) {
        this.thisYear = thisYear;
    }

    public void setDniMatricula(String dniMatricula) {
        this.dniMatricula = dniMatricula;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public void setDomicilioReferencia(String domicilioReferencia) {
        this.domicilioReferencia = domicilioReferencia;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setMyVehicle(String myVehicle) {
        this.myVehicle = myVehicle;
    }

    public void setImagePath(ArrayList<String> imagePath) {
        this.imagePath = imagePath;
    }

    public void setImageBitmap(ArrayList<String> imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    protected Asistencia(Parcel in) {
        mTipo = (Tipo) in.readValue(Tipo.class.getClassLoader());
        hourInfraccion = in.readInt();
        minuteInfraccion = in.readInt();
        dayInfraccion = in.readInt();
        mesInfraccion = in.readString();
        yearInfraccion = in.readInt();
        isVehicle = in.readInt();
        importeSancion = in.readDouble();

        dniMatricula = in.readString();
        nombreMarca = in.readString();
        domicilioReferencia = in.readString();
        ubicacion = in.readString();
        myVehicle = in.readString();

        thisDay = in.readInt();
        thisMonth = in.readInt();
        thisYear = in.readInt();

        numUsuario = in.readInt();

        if (in.readByte() == 0x01) {
            imagePath = new ArrayList<>();
            in.readList(imagePath, String.class.getClassLoader());
        } else {
            imagePath = null;
        }
        if (in.readByte() == 0x01) {
            imageBitmap = new ArrayList<>();
            in.readList(imageBitmap, Bitmap.class.getClassLoader());
        } else {
            imageBitmap = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mTipo);
        dest.writeInt(hourInfraccion);
        dest.writeInt(minuteInfraccion);
        dest.writeInt(dayInfraccion);
        dest.writeString(mesInfraccion);
        dest.writeInt(yearInfraccion);
        dest.writeInt(isVehicle);
        dest.writeDouble(importeSancion);

        dest.writeString(dniMatricula);
        dest.writeString(nombreMarca);
        dest.writeString(domicilioReferencia);
        dest.writeString(ubicacion);
        dest.writeString(myVehicle);

        dest.writeInt(thisDay);
        dest.writeInt(thisMonth);
        dest.writeInt(thisYear);

        dest.writeInt(numUsuario);

        if (imagePath == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(imagePath);
        }
        if (imageBitmap == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(imageBitmap);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Asistencia> CREATOR = new Parcelable.Creator<Asistencia>() {
        @Override
        public Asistencia createFromParcel(Parcel in) {
            return new Asistencia(in);
        }

        @Override
        public Asistencia[] newArray(int size) {
            return new Asistencia[size];
        }
    };
}
