package elcolegainformatico.antonio.puertoapp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import elcolegainformatico.antonio.puertoapp.model.Infraccion;
import elcolegainformatico.antonio.puertoapp.R;
import elcolegainformatico.antonio.puertoapp.viewholders.InfraccionesViewHolder;

/**
 * Created by antonio on 4/5/17.
 */

public class InfraccionesAdapter extends BaseAdapter {

    private ArrayList<Infraccion> sancionesArrayList;
    private LayoutInflater inflater;
    private Context contexto;

    //Obtengo un arrayList de articulos
    public InfraccionesAdapter(ArrayList<Infraccion> sancionesArrayList, Context context){

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


    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        InfraccionesViewHolder holder;

        if(item == null){
            LayoutInflater inflater=LayoutInflater.from(contexto); //Use this outside activities - all you need is to provide a Context
            item=inflater.inflate(R.layout.item_infraccion_list,null); //Inflamos nuestro item
            holder= new InfraccionesViewHolder(item);
            item.setTag(holder);
        }

        else {
            holder=(InfraccionesViewHolder)item.getTag();
        }

        Infraccion miInfraccion = sancionesArrayList.get(position); //Selecciono el articulo

       /*
        TextView txtSDate = (TextView) item.findViewById(R.id.txtSDate);
        TextView txtSDatos = (TextView) item.findViewById(R.id.txtSDatos);
        TextView txtSSancion= (TextView) item.findViewById(R.id.txtSSancion);*/


        holder.txtSDate.setText(miInfraccion.getDay()+" de "+ miInfraccion.getMes()+" "+ miInfraccion.getYear()); //Obtengo el árticulo de mi modelo de datos articulo

        holder.txtSDatos.setText(miInfraccion.getDniMatricula()+" - "+ miInfraccion.getNombreMarca());

      if(miInfraccion.getmArticulo()!=null) {
          holder.txtSSancion.setText("Zona: " + miInfraccion.getUbicacion() + " Infracción: " + miInfraccion.getmArticulo().getDescripcion() + " " + miInfraccion.getmArticulo().getNumArticulo() + " Sanción: " + miInfraccion.getSancion() + "€");
      }


        return item;
    }
}
