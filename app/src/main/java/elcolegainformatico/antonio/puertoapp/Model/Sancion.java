package elcolegainformatico.antonio.puertoapp.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


/**
 * Created by antonio on 4/5/17.
 */

public class Sancion implements Parcelable {

    Articulo mArticulo;

    private int hour,minute;
    private int day;
    private String mes;
    private int year;
    private int isVehicle;
    private double sancion;

    private String dniMatricula,nombreMarca,domicilioReferencia,ubicacion,myVehicle;

    //Image Gallery
    ArrayList<String> imagePath;
    ArrayList<Bitmap> imageBitmap;


    public Sancion(Articulo mArticulo, int hour, int minute, int day, String mes, int year, int isVehicle, double sancion, String DniMatricula, String NombreMarca, String DomicilioReferencia, String Ubicacion, String myVehicle, ArrayList<String> imagePath, ArrayList<Bitmap> imageBitmap) {
        this.mArticulo = mArticulo;
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.mes = mes;
        this.year = year;
        this.isVehicle = isVehicle;
        this.sancion = sancion;
        this.dniMatricula = DniMatricula;
        this.nombreMarca = NombreMarca;
        this.domicilioReferencia = DomicilioReferencia;
        this.ubicacion = Ubicacion;
        this.myVehicle = myVehicle;
        this.imagePath = imagePath;
        this.imageBitmap = imageBitmap;
    }


    public Articulo getmArticulo() {
        return mArticulo;
    }

    public void setmArticulo(Articulo mArticulo) {
        this.mArticulo = mArticulo;
    }

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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIsVehicle() {
        return isVehicle;
    }

    public void setIsVehicle(int isVehicle) {
        this.isVehicle = isVehicle;
    }

    public double getSancion() {
        return sancion;
    }

    public void setSancion(double sancion) {
        this.sancion = sancion;
    }

    public String getDniMatricula() {
        return dniMatricula;
    }

    public void setDniMatricula(String dniMatricula) {
        this.dniMatricula = dniMatricula;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getDomicilioReferencia() {
        return domicilioReferencia;
    }

    public void setDomicilioReferencia(String domicilioReferencia) {
        this.domicilioReferencia = domicilioReferencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getMyVehicle() {
        return myVehicle;
    }

    public void setMyVehicle(String myVehicle) {
        this.myVehicle = myVehicle;
    }

    public ArrayList<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(ArrayList<String> imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<Bitmap> getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(ArrayList<Bitmap> imageBitmap) {
        this.imageBitmap = imageBitmap;
    }


    protected Sancion(Parcel in) {
        mArticulo = (Articulo) in.readValue(Articulo.class.getClassLoader());
        hour = in.readInt();
        minute = in.readInt();
        day = in.readInt();
        mes = in.readString();
        year = in.readInt();
        isVehicle = in.readInt();
        sancion = in.readDouble();

        dniMatricula= in.readString();
        nombreMarca= in.readString();
        domicilioReferencia= in.readString();
        ubicacion= in.readString();
        myVehicle= in.readString();

        if (in.readByte() == 0x01) {
            imagePath = new ArrayList<String>();
            in.readList(imagePath, String.class.getClassLoader());
        } else {
            imagePath = null;
        }
        if (in.readByte() == 0x01) {
            imageBitmap = new ArrayList<Bitmap>();
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
        dest.writeValue(mArticulo);
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeInt(day);
        dest.writeString(mes);
        dest.writeInt(year);
        dest.writeInt(isVehicle);
        dest.writeDouble(sancion);

        dest.writeString(dniMatricula);
        dest.writeString(nombreMarca);
        dest.writeString(domicilioReferencia);
        dest.writeString(ubicacion);
        dest.writeString(myVehicle);


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
    public static final Parcelable.Creator<Sancion> CREATOR = new Parcelable.Creator<Sancion>() {
        @Override
        public Sancion createFromParcel(Parcel in) {
            return new Sancion(in);
        }

        @Override
        public Sancion[] newArray(int size) {
            return new Sancion[size];
        }
    };
}
