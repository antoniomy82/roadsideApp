package elcolegainformatico.antonio.roadsideApp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import elcolegainformatico.antonio.roadsideApp.R;
import elcolegainformatico.antonio.roadsideApp.model.Asistencia;

/**
 * Created by antonio on 30/5/17.
 */

public class AsistenciasViewHolder extends RecyclerView.ViewHolder {

    public TextView txtSDate;
    public TextView txtSDatos;
    public TextView txtSSancion;


    public AsistenciasViewHolder(View itemView) {
        super(itemView);

        txtSDate = (TextView) itemView.findViewById(R.id.txtSDate);
        txtSDatos = (TextView) itemView.findViewById(R.id.txtSDatos);
        txtSSancion = (TextView) itemView.findViewById(R.id.txtSSancion);
    }

    public void bind(Asistencia asistencia) {

        txtSDate.setText(asistencia.getThisDay() + "/" + asistencia.getThisMonth() + "/" + asistencia.getThisYear());
        txtSDatos.setText(asistencia.getDniMatricula() + "/" + asistencia.getNombreMarca());
        txtSSancion.setText(asistencia.getmTipo().getNumArticulo() + "-" + asistencia.getmTipo().getDescripcion() + " Zona: " + asistencia.getUbicacion());

    }
}
