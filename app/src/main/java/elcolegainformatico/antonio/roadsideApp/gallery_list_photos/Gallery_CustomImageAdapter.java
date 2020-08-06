package elcolegainformatico.antonio.roadsideApp.gallery_list_photos;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import elcolegainformatico.antonio.roadsideApp.R;

/**
 * Created by antonio on 22/4/17.
 */

public class Gallery_CustomImageAdapter extends BaseAdapter {

    private List<Gallery_GetSet> _data;
    private Context _c;
    private ViewHolder v;

    public Gallery_CustomImageAdapter(List<Gallery_GetSet> getData, Context context) {
        _data = getData;
        _c = context;
    }

    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int position) {
        return _data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater li = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.gallery_parcel_images, null);
        } else {
            view = convertView;
        }

        v = new ViewHolder();
        v.clickImage = (ImageButton) view.findViewById(R.id.capture);
        v.removeImage = (ImageButton) view.findViewById(R.id.cancel);
        v.parcelName = (TextView) view.findViewById(R.id.parcelName);
        v.label = (TextView) view.findViewById(R.id.imageFor);
        v.imageView = (ImageView) view.findViewById(R.id.imgPrv);

        // Set data in listView
        final Gallery_GetSet dataSet = _data.get(position);

        dataSet.setListItemPosition(position);

        if (!dataSet.isHaveImage()) {
            Bitmap icon;

            if(position!=7){
                icon = BitmapFactory.decodeResource(_c.getResources(), R.drawable.gallery_imgnodisponible56);
                v.imageView.setImageBitmap(icon);
            }
            else{
                icon = BitmapFactory.decodeResource(_c.getResources(), R.drawable.gallery_firma56);
                v.imageView.setImageBitmap(icon);
            }


        }

        else {
            v.imageView.setImageBitmap(dataSet.getImage());
        }

        v.parcelName.setText(dataSet.getLabel());
        v.label.setText(dataSet.getSubtext());

        if (dataSet.isStatus()&& !dataSet.getSaved()) {
            v.clickImage.setVisibility(View.VISIBLE);
            v.removeImage.setVisibility(View.GONE);
        }
        if (!dataSet.isStatus() && !dataSet.getSaved())
        {
            v.removeImage.setVisibility(View.VISIBLE);
            v.clickImage.setVisibility(View.GONE);
        }
        if(dataSet.getSaved()){
            v.removeImage.setVisibility(View.GONE);
            v.clickImage.setVisibility(View.GONE);
        }

        v.clickImage.setFocusable(false);
        v.removeImage.setFocusable(false);


        v.clickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call to dialog
                if(position!=7){
                    dialogGetImage(dataSet,"foto");
                }
                else {
                    dialogSign(dataSet);
                }
               }
        });

        v.removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeleteImage(dataSet);
            }
        });

        return view;
    }


    //Dialog Sign
    private void dialogSign(final Gallery_GetSet dataSet){

        final CharSequence[] options = { "SI","NO" };

        AlertDialog.Builder builder = new AlertDialog.Builder(_c);
        builder.setTitle("¿Seguro que desea guardar una firma?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("SI"))
                {
                    Toast.makeText(_c,"Guarde una firma como del mismo modo que una foto",Toast.LENGTH_SHORT).show();
                    dialogGetImage(dataSet,"firma");
                }
                if (options[item].equals("NO")) {
                    Toast.makeText(_c,"Elija otra fila para guardar fotos",Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.show();
    }

    //Delete Dialog
    private void dialogDeleteImage(final Gallery_GetSet dataSet){

        final CharSequence[] options = { "Borrar de fotos seleccionadas", "Borrar del móvil y fotos seleccionadas","Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(_c);
        builder.setTitle("¿Cómo desea borrarla?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Borrar de fotos seleccionadas"))
                {
                    //Delete from my adapter
                    dataSet.setStatus(true);
                    dataSet.setHaveImage(false);
                    dataSet.setImage(null);
                    ((Gallery_MainActivity)_c).deleteImageFromPicsSelected(dataSet.getListItemPosition());//Delete image in arrayList imagePath
                    notifyDataSetChanged();

                }
                if (options[item].equals("Borrar del móvil y fotos seleccionadas"))
                {

                    dataSet.setStatus(true);
                    dataSet.setHaveImage(false);
                    dataSet.setImage(null);
                    ((Gallery_MainActivity)_c).deleteImageFromMobile(dataSet.getListItemPosition()); //Delete image from sdcard
                    notifyDataSetChanged();

                }
                if (options[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    //Dialog to select the source to obtain a picture
    private void dialogGetImage(final Gallery_GetSet dataSet,String text) {
        final CharSequence[] options = { "Cámara", "Galeria de fotos","Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(_c);
        builder.setTitle("Seleccionar "+text);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Cámara"))
                {
                    // Call parent method of activity to click image
                    ((Gallery_MainActivity) _c).captureImage(dataSet.getListItemPosition(), dataSet.getLabel() + "" + dataSet.getSubtext());

                }
                if (options[item].equals("Galeria de fotos"))
                {

                    ((Gallery_MainActivity) _c).getImageGallery(dataSet.getListItemPosition());

                }
                if (options[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    /**
     * @param position Get position of of object
     * @param imageSrc set image in imageView
     */
    public void setImageInItem(int position, Bitmap imageSrc, String imagePath) {
        Gallery_GetSet dataSet = _data.get(position);
        dataSet.setImage(imageSrc);
        dataSet.setStatus(false);
        dataSet.setHaveImage(true);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView imageView;
        TextView label, parcelName;
        ImageButton clickImage, removeImage;
    }

}