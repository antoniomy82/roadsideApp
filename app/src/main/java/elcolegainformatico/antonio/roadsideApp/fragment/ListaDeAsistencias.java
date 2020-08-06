package elcolegainformatico.antonio.roadsideApp.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by antonio on 6/7/17.
 */

public class ListaDeAsistencias extends AsistenciasListFragment {

    public ListaDeAsistencias() {
    }

    @Override
    public Query getQuery(DatabaseReference mDatabase) {

        return mDatabase.child("asistencias").orderByChild("starCount");
    }
}
