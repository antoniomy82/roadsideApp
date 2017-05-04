package elcolegainformatico.antonio.puertoapp.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.Model.Sancion;
import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 4/5/17.
 */

public class SancionesAdapter extends BaseAdapter {

    private ArrayList<Sancion> sancionesArrayList;
    private LayoutInflater inflater;
    private Context contexto;

    //Obtengo un arrayList de articulos
    public SancionesAdapter(ArrayList<Sancion> sancionesArrayList, Context context){

        this.sancionesArrayList = sancionesArrayList;
        inflater= LayoutInflater.from(context); //Me va a inflar la información de cada articulo dentro de cada item
        contexto = context; // El contexto es una referencia a la aplicación (en qué activity hago uso de mi adaptador).
    }

    @Override
    //Devuelvo el tamaño del arrayList
    public int getCount() {
        return sancionesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return sancionesArrayList.get(position); //Devuelvo la posición del item
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View myView, ViewGroup parent) {

        myView= inflater.inflate(R.layout.item_sancion_list,null); //Inflamos nuestro item

        Sancion miSancion = sancionesArrayList.get(position); //Selecciono el articulo


        TextView txtSDate = (TextView) myView.findViewById(R.id.txtSDate);
        TextView txtSDatos = (TextView) myView.findViewById(R.id.txtSDatos);
        TextView txtSSancion= (TextView) myView.findViewById(R.id.txtSSancion);


        txtSDate.setText(miSancion.getDay()+" de "+miSancion.getMes()+" "+miSancion.getYear()); //Obtengo el árticulo de mi modelo de datos articulo

        txtSDatos.setText(miSancion.getDniMatricula()+" - "+miSancion.getNombreMarca());
        txtSSancion.setText("Zona: "+miSancion.getUbicacion()+" Infracción: "+miSancion.getmArticulo().getDescripcion()+ " "+miSancion.getmArticulo().getNumArticulo()+  " Sanción: "+miSancion.getSancion()+ "€");

        return myView;
    }
}
