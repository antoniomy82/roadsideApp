package elcolegainformatico.antonio.puertoapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import elcolegainformatico.antonio.puertoapp.R;
import elcolegainformatico.antonio.puertoapp.model.Infraccion;

/**
 * Created by antonio on 30/5/17.
 */

public class InfraccionesViewHolder extends RecyclerView.ViewHolder{

    public TextView txtSDate;
    public TextView txtSDatos;
    public TextView txtSSancion;
    private Infraccion mInfraccion;



    public InfraccionesViewHolder(View itemView) {
        super(itemView);

        txtSDate = (TextView) itemView.findViewById(R.id.txtSDate);
        txtSDatos = (TextView) itemView.findViewById(R.id.txtSDatos);
        txtSSancion= (TextView) itemView.findViewById(R.id.txtSSancion);
    }

    public void bind(Infraccion infraccion) {
        mInfraccion=infraccion;
        txtSDate.setText(infraccion.getDay()+"/"+infraccion.getMes()+"/"+infraccion.getYear());
        txtSDatos.setText(infraccion.getDniMatricula()+"/"+infraccion.getNombreMarca());
        txtSSancion.setText(infraccion.getmArticulo()+"/"+infraccion.getSancion());

    }
}
