package elcolegainformatico.antonio.puertoapp.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 7/4/17.
 */

public class ArticulosAdapter extends BaseAdapter{

    private ArrayList<Articulo> articuloArrayList;
    private LayoutInflater inflater;
    private  Context contexto;

    //Obtengo un arrayList de articulos
    public ArticulosAdapter(ArrayList<Articulo> articuloArrayList, Context context){

        this.articuloArrayList = articuloArrayList;
        inflater= LayoutInflater.from(context); //Me va a inflar la información de cada articulo dentro de cada item
        contexto = context; // El contexto es una referencia a la aplicación (en qué activity hago uso de mi adaptador).
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

        myView= inflater.inflate(R.layout.item_articulo,null); //Inflamos nuestro item

        Articulo mArticulo = articuloArrayList.get(position); //Selecciono el articulo

        TextView txtNumArticulo = (TextView) myView.findViewById(R.id.txtNumArticulo);
        TextView txtDescriptionArticulo = (TextView) myView.findViewById(R.id.txtTituloArticulo);


        txtNumArticulo.setText(mArticulo.getNumArticulo()); //Obtengo el árticulo de mi modelo de datos articulo
        txtDescriptionArticulo.setText(mArticulo.getTitulo());

        return myView;
    }
}
