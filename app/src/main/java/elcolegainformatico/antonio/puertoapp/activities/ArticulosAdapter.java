package elcolegainformatico.antonio.puertoapp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import elcolegainformatico.antonio.puertoapp.model.Articulo;
import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 7/4/17.
 */

public class ArticulosAdapter extends BaseAdapter{

    private ArrayList<Articulo> articuloArrayList;
    private LayoutInflater inflater;
    private Context contexto;
    private boolean isReglamento;

    //Obtengo un arrayList de articulos
    public ArticulosAdapter(ArrayList<Articulo> articuloArrayList, Context context, boolean isReglamento){

        this.articuloArrayList = articuloArrayList;
        inflater= LayoutInflater.from(context); //Me va a inflar la información de cada articulo dentro de cada item
        contexto = context; // El contexto es una referencia a la aplicación (en qué activity hago uso de mi adaptador).
        this.isReglamento=isReglamento;
    }

    @Override
    //Devuelvo el tamaño del arrayList
    public int getCount() {
        return articuloArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return articuloArrayList.get(position); //Devuelvo la posición del item
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View myView, ViewGroup parent) {

        if(isReglamento) {
            myView = inflater.inflate(R.layout.item_reglamento_list, null); //Inflamos nuestro item
        }
        if(!isReglamento){
            myView = inflater.inflate(R.layout.item_ley_list, null); //Inflamos nuestro item
        }

        Articulo mArticulo = articuloArrayList.get(position); //Selecciono el articulo

        TextView txtNumArticulo = (TextView) myView.findViewById(R.id.txtNumArticulo);
        TextView txtDescriptionArticulo = (TextView) myView.findViewById(R.id.txtTituloArticulo);
        TextView txtDescriptionList= (TextView) myView.findViewById(R.id.txtDescriptionList);


        txtNumArticulo.setText(mArticulo.getNumArticulo()); //Obtengo el árticulo de mi modelo de datos articulo
        txtDescriptionArticulo.setText(mArticulo.getTitulo());
        txtDescriptionList.setText(mArticulo.getDescripcion());

        return myView;
    }
}
