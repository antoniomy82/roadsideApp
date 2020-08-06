package elcolegainformatico.antonio.roadsideApp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import elcolegainformatico.antonio.roadsideApp.R;
import elcolegainformatico.antonio.roadsideApp.activities.ValidarActivity;
import elcolegainformatico.antonio.roadsideApp.model.Asistencia;
import elcolegainformatico.antonio.roadsideApp.viewholders.AsistenciasViewHolder;

/**
 * Created by antonio on 5/7/17.
 */

public abstract class AsistenciasListFragment extends Fragment {

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Asistencia, AsistenciasViewHolder> mRecycleAdapter;
    private RecyclerView mRecyclerV;
    private LinearLayoutManager mLlManager;

    public AsistenciasListFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.asistencia_list_recycleview, container, false);
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

        mRecycleAdapter = new FirebaseRecyclerAdapter<Asistencia, AsistenciasViewHolder>(Asistencia.class, R.layout.item_asistencia_list, AsistenciasViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(AsistenciasViewHolder viewHolder, Asistencia model, final int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String uuid = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Asistencia miAsistencia = getItem(position);

                        Intent intent = new Intent(getActivity(), ValidarActivity.class);
                        intent.putExtra("hourInfraccion", miAsistencia.getHourInfraccion());
                        intent.putExtra("minuteInfraccion", miAsistencia.getMinuteInfraccion());
                        intent.putExtra("dayInfraccion", miAsistencia.getDayInfraccion());
                        intent.putExtra("mesInfraccion", miAsistencia.getMesInfraccion());
                        intent.putExtra("yearInfraccion", miAsistencia.getYearInfraccion());
                        intent.putExtra("DniMatricula", miAsistencia.getDniMatricula());
                        intent.putExtra("NombreMarca", miAsistencia.getNombreMarca());
                        intent.putExtra("DomicilioReferencia", miAsistencia.getDomicilioReferencia());
                        intent.putExtra("Ubicacion", miAsistencia.getUbicacion());
                        intent.putExtra("myVehicle", miAsistencia.getMyVehicle());
                        intent.putExtra("myArticulo", miAsistencia.getmTipo());
                        intent.putExtra("ImagePath", miAsistencia.getImagePath());
                        intent.putExtra("ImageBitmap", miAsistencia.getImageBitmap());
                        intent.putExtra("thisDay", miAsistencia.getThisDay());
                        intent.putExtra("thisMonth", miAsistencia.getThisMonth());
                        intent.putExtra("thisYear", miAsistencia.getThisYear());
                        intent.putExtra("agente", miAsistencia.getNumUsuario());

                        //Extraigo el nodo, osea el número de multa
                        String miNodo = mDatabase.child("asistencias").child(uuid).getKey();
                        intent.putExtra("nodo", miNodo);
                        intent.putExtra("isSave", false);

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
