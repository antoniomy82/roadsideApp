package elcolegainformatico.antonio.roadsideApp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import elcolegainformatico.antonio.roadsideApp.R;
import elcolegainformatico.antonio.roadsideApp.model.Tipo;

/**
 * Created by antonio on 7/4/17.
 */

public class AsistenciasAdapter extends BaseAdapter {

    private ArrayList<Tipo> tipoArrayList;
    private LayoutInflater inflater;
    private Context contexto;
    private boolean isParticular;

    //Obtengo un arrayList de articulos
    public AsistenciasAdapter(ArrayList<Tipo> tipoArrayList, Context context, boolean isParticular) {

        this.tipoArrayList = tipoArrayList;
        inflater = LayoutInflater.from(context); //Me va a inflar la información de cada articulo dentro de cada item
        contexto = context; // El contexto es una referencia a la aplicación (en qué activity hago uso de mi adaptador).
        this.isParticular = isParticular;
    }

    @Override
    //Devuelvo el tamaño del arrayList
    public int getCount() {
        return tipoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return tipoArrayList.get(position); //Devuelvo la posición del item
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View myView, ViewGroup parent) {

        if (isParticular) {
            myView = inflater.inflate(R.layout.item_particular_list, null); //Inflamos nuestro item
        }
        if (!isParticular) {
            myView = inflater.inflate(R.layout.item_aseguradora_list, null); //Inflamos nuestro item
        }

        Tipo mTipo = tipoArrayList.get(position); //Selecciono el articulo

        TextView txtNumArticulo = (TextView) myView.findViewById(R.id.txtNumArticulo);
        TextView txtDescriptionArticulo = (TextView) myView.findViewById(R.id.txtTituloArticulo);
        TextView txtDescriptionList = (TextView) myView.findViewById(R.id.txtDescriptionList);


        txtNumArticulo.setText(mTipo.getNumArticulo()); //Obtengo el árticulo de mi modelo de datos articulo
        txtDescriptionArticulo.setText(mTipo.getTitulo());
        txtDescriptionList.setText(mTipo.getDescripcion());

        return myView;
    }
}
