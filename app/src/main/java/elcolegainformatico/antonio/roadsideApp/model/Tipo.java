package elcolegainformatico.antonio.roadsideApp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by antonio on 7/4/17.
 */


//Hardcode Data Structure , work in local because the articulos no change
public class Tipo implements Parcelable {


    private String numArticulo; //Cambiar por un int
    private String Titulo;
    private String Descripcion;

    public Tipo() {
        //needed for firebase
    }

    public Tipo(String numArticulo, String titulo, String descripcion) {
        this.numArticulo = numArticulo;
        Titulo = titulo;
        Descripcion = descripcion;
    }

    public void setNumArticulo(String numArticulo) {
        this.numArticulo = numArticulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNumArticulo() {
        return numArticulo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }


    //Change Public for read from Outside class
    public Tipo(Parcel in) {
        numArticulo = in.readString();
        Titulo = in.readString();
        Descripcion = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numArticulo);
        dest.writeString(Titulo);
        dest.writeString(Descripcion);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Tipo> CREATOR = new Parcelable.Creator<Tipo>() {
        @Override
        public Tipo createFromParcel(Parcel in) {
            return new Tipo(in);
        }

        @Override
        public Tipo[] newArray(int size) {
            return new Tipo[size];
        }
    };
}