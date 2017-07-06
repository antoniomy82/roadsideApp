package elcolegainformatico.antonio.puertoapp.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by antonio on 6/7/17.
 */

public class ListaDeInfracciones extends InfraccionesListFragment {

    public ListaDeInfracciones() {
    }

    @Override
    public Query getQuery(DatabaseReference mDatabase) {
        Query myTopPostsQuery = mDatabase.child("infracciones").orderByChild("starCount");

        return myTopPostsQuery;
    }
}
