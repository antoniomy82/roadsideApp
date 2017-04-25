package elcolegainformatico.antonio.puertoapp.Model;

import java.util.ArrayList;

/**
 * Created by antonio on 8/4/17.
 */

//En el ArrayList nombreArrayList.add(0, "Nombre de la Zona");
// nombreArrayList.add( 1.. size(), " Nombre subzonas" ) EJ: Frente a la gasolinera DISA.

public class Zona {

    private String nombreZona;
    private String zona1;
    private String zona2 = null;
    private String zona3 = null;
    private String zona4 = null;
    private String zona5 = null;
    private String zona6 = null;
    private String zona7 = null;
    private String zona8 = null;
    private String zona9 = null;
    private String zona10 = null;
    private String zona11 = null;
    private String zona12 = null;
    private String zona13 = null;
    private String zona14 = null;
    private String zona15 = null;
    private String zona16 = null;
    private String zona17 = null;
    private String zona18 = null;
    private String zona19 = null;
    private String zona20 = null;

    public Zona(String nombreZona, String zona1, String zona2, String zona3, String zona4, String zona5, String zona6, String zona7, String zona8, String zona9, String zona10, String zona11, String zona12, String zona13, String zona14, String zona15, String zona16, String zona17, String zona18, String zona19, String zona20) {
        this.nombreZona = nombreZona;
        this.zona1 = zona1;
        this.zona2 = zona2;
        this.zona3 = zona3;
        this.zona4 = zona4;
        this.zona5 = zona5;
        this.zona6 = zona6;
        this.zona7 = zona7;
        this.zona8 = zona8;
        this.zona9 = zona9;
        this.zona10 = zona10;
        this.zona11 = zona11;
        this.zona12 = zona12;
        this.zona13 = zona13;
        this.zona14 = zona14;
        this.zona15 = zona15;
        this.zona16 = zona16;
        this.zona17 = zona17;
        this.zona18 = zona18;
        this.zona19 = zona19;
        this.zona20 = zona20;
    }


    public String getNombreZona() {
        return nombreZona;
    }

    public String getZona( int numZona) {

        if (numZona == 1) {
            return zona1;
        }
        if (numZona == 2) {
            return zona2;
        }

        if (numZona == 3) {
            return zona3;
        }
        if (numZona == 4) {
            return zona4;
        }
        if (numZona == 5) {
            return zona5;
        }
        if (numZona == 6) {
            return zona6;
        }
        if (numZona == 7) {
            return zona7;
        }
        if (numZona == 8) {
            return zona8;
        }
        if (numZona == 9) {
            return zona9;
        }
        if (numZona == 10) {
            return zona10;
        }
        if (numZona == 11) {
            return zona11;
        }
        if (numZona == 12) {
            return zona12;
        }
        if (numZona == 13) {
            return zona13;
        }
        if (numZona == 14) {
            return zona14;
        }
        if (numZona == 15) {
            return zona15;
        }
        if (numZona == 16) {
            return zona16;
        }
        if (numZona == 17) {
            return zona17;
        }
        if (numZona == 18) {
            return zona18;
        }
        if (numZona == 19) {
            return zona19;
        }
        if (numZona == 20) {
            return zona20;
        }
        else
        {
            return null;
        }
    }
}