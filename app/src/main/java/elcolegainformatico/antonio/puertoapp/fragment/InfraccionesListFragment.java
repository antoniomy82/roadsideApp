package elcolegainformatico.antonio.puertoapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import elcolegainformatico.antonio.puertoapp.R;
import elcolegainformatico.antonio.puertoapp.activities.ValidarActivity;
import elcolegainformatico.antonio.puertoapp.model.Articulo;
import elcolegainformatico.antonio.puertoapp.model.Infraccion;
import elcolegainformatico.antonio.puertoapp.viewholders.InfraccionesViewHolder;

/**
 * Created by antonio on 5/7/17.
 */

public abstract class InfraccionesListFragment extends Fragment {

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter <Infraccion,InfraccionesViewHolder> mRecycleAdapter;
    private RecyclerView mRecyclerV;
    private LinearLayoutManager mLlManager;

    public InfraccionesListFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.infraccion_list_recycleview,container,false);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mRecyclerV= (RecyclerView) rootView.findViewById(R.id.infracciones_list);
        mRecyclerV.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mLlManager = new LinearLayoutManager(getActivity());
        mLlManager.setReverseLayout(true);
        mLlManager.setStackFromEnd(true);
        mRecyclerV.setLayoutManager(mLlManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);

        mRecycleAdapter = new FirebaseRecyclerAdapter<Infraccion, InfraccionesViewHolder>(Infraccion.class, R.layout.item_infraccion_list,InfraccionesViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(InfraccionesViewHolder viewHolder, Infraccion model, final int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String uuid = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Infraccion miInfraccion=getItem(position);

                        Intent intent = new Intent(getActivity(), ValidarActivity.class);
                        Log.d("@@Mi Infraccion Hora:  ",String.valueOf(miInfraccion.getHour()));

                        intent.putExtra("hour", miInfraccion.getHour());
                        intent.putExtra("minute", miInfraccion.getMinute());
                        intent.putExtra("day", miInfraccion.getDay());
                        //intent.putExtra("month",month);
                        intent.putExtra("mes", miInfraccion.getMes());
                        intent.putExtra("year", miInfraccion.getYear());

                        intent.putExtra("DniMatricula", miInfraccion.getDniMatricula());

                        Log.d("@@Mi DniMatricula:  ",String.valueOf(miInfraccion.getDniMatricula()));
                        intent.putExtra("NombreMarca", miInfraccion.getNombreMarca());
                        intent.putExtra("DomicilioReferencia", miInfraccion.getDomicilioReferencia()) ;
                        intent.putExtra("Ubicacion", miInfraccion.getUbicacion());

                        intent.putExtra("myVehicle", miInfraccion.getMyVehicle());

                        //Extraigo el nodo, osea el número de multa
                        String miNodo= mDatabase.child("infracciones").child(uuid).getKey();

                        //Extraigo los datos de los articulos
                        DatabaseReference articuloDesRef= mDatabase.child("infracciones").child(uuid).child("articulo_Des");
                        DatabaseReference articuloNumRef= mDatabase.child("infracciones").child(uuid).child("articulo_Num");
                        DatabaseReference articuloTitRef= mDatabase.child("infracciones").child(uuid).child("articulo_Tit");

                       /*
                        String Articulo_Des;

                        articuloDesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                                   Articulo_Des= singleSnapshot.getValue().toString();
                                }
                            }

                        Articulo mArticulo = new Articulo(Articulo_Num, Articulo_Tit, Articulo_Des);


                        //FALTA PASAR BIEN EL ARTICULO
                        intent.putExtra("myArticulo", mArticulo);*/
                        intent.putExtra("isVehicle", miInfraccion.getIsVehicle());

                        intent.putExtra("ImagePath", miInfraccion.getImagePath());
                        //Bitmap is not necessary , is ineficient, we will use imagePath.


                        intent.putExtra("thisDay", miInfraccion.getThisDay());
                        intent.putExtra("thisMonth", miInfraccion.getThisMonth());
                        intent.putExtra("thisYear", miInfraccion.getThisYear());

                        intent.putExtra("numero", miInfraccion.getNumInfraccion());
                        intent.putExtra("agente", miInfraccion.getNumUsuario());

                        //Solo para versión sin Firebase.
                        //intent.putExtra("sancionesSaved",sancionesList);

                        intent.putExtra("isSave",false);
                        startActivity(intent);

                    }
                });
                viewHolder.bind(model);
            }
        };
        mRecyclerV.setAdapter(mRecycleAdapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mRecycleAdapter!=null){
            mRecycleAdapter.cleanup();
        }
    }

    protected abstract Query getQuery(DatabaseReference mDatabase);
}
