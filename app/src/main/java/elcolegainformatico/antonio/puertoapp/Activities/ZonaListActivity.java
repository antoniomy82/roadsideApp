package elcolegainformatico.antonio.puertoapp.Activities;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

import elcolegainformatico.antonio.puertoapp.Model.Zona;


/**
 * Created by antonio on 9/4/17.
 */

public class ZonaListActivity extends ListActivity {

    private ZonaAdapter mAdapter;
    ArrayList<Zona> zonasArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ZonaAdapter(this);


        loadZonas();


        //Recorremos el ArrayList de Zonas
        for (int i=0; i < zonasArrayList.size(); i++) {

            Zona mZona = zonasArrayList.get(i);
            String headerZona = mZona.getNombreZona();

            mAdapter.addSectionHeaderItem(headerZona);

            //Recorremos las zonas
            for(int j=1 ; j<=20 ; j++){
                String thisZone=mZona.getZona(j);

                if(thisZone!=null){
                    mAdapter.addItem(thisZone);
                }
                else{
                    j=20; //Corto la ejecución del for si igual a null
                }
             }

        }


        setListAdapter(mAdapter);


    }

    public  void loadZonas(){
        zonasArrayList = new ArrayList<Zona>();

        zonasArrayList.add(new Zona("Muelle España", "Garita Entrada", "Oficinas Centrales", "Museo Naval", "Torre de Control",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null));
        zonasArrayList.add(new Zona("Cañonero Dato", "Corte Ingles", "Lidel", "Casa Ros", "Supersol",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null));
        zonasArrayList.add(new Zona("Muelle de Levante", "Garita Entrada", "Zona Ambulancias", "Aridos Ceuta", "Rotonda Hercules",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null));




    }
}
