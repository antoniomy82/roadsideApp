package elcolegainformatico.antonio.puertoapp.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


/**
 * Created by antonio on 4/5/17.
 */

public class Infraccion implements Parcelable {

    private Articulo mArticulo;

    private int hour,minute;
    private int day;
    private String mes;
    private int year;
    private int isVehicle;
    private double sancion;

    //date to put sancion
    private int thisDay;
    private int thisMonth;
    private int thisYear;

    private int numero;
    private int agente;

    private String dniMatricula,nombreMarca,domicilioReferencia,ubicacion,myVehicle;

    //Image Gallery
    private ArrayList<String> imagePath;
    private ArrayList<Bitmap> imageBitmap;


    public Infraccion(Articulo mArticulo, int hour, int minute, int day, String mes, int year, int isVehicle, double sancion, String DniMatricula, String NombreMarca, String DomicilioReferencia, String Ubicacion, String myVehicle, ArrayList<String> imagePath, ArrayList<Bitmap> imageBitmap, int thisDay, int thisMonth, int thisYear, int numero, int agente) {
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

        this.thisDay=thisDay;
        this.thisMonth=thisMonth;
        this.thisYear=thisYear;
        this.numero=numero;
        this.agente=agente;


    }


    public Articulo getmArticulo() {
        return mArticulo;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDay() {
        return day;
    }

    public String getMes() {
        return mes;
    }

    public int getYear() {
        return year;
    }


    public int getIsVehicle() {
        return isVehicle;
    }


    public double getSancion() {
        return sancion;
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

    public ArrayList<Bitmap> getImageBitmap() {
        return imageBitmap;
    }

    public int getThisDay() {return thisDay;}

    public int getThisMonth() {return thisMonth;}

    public int getThisYear() {return thisYear;}


    public int getNumero() {return numero;}

    public int getAgente() {return agente;}

    protected Infraccion(Parcel in) {
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

        thisDay=in.readInt();
        thisMonth=in.readInt();
        thisYear=in.readInt();

        numero=in.readInt();
        agente=in.readInt();

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

        dest.writeInt(thisDay);
        dest.writeInt(thisMonth);
        dest.writeInt(thisYear);

        dest.writeInt(numero);
        dest.writeInt(agente);

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
    public static final Parcelable.Creator<Infraccion> CREATOR = new Parcelable.Creator<Infraccion>() {
        @Override
        public Infraccion createFromParcel(Parcel in) {
            return new Infraccion(in);
        }

        @Override
        public Infraccion[] newArray(int size) {
            return new Infraccion[size];
        }
    };
}
