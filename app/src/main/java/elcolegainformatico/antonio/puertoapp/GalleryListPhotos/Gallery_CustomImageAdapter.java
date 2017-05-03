package elcolegainformatico.antonio.puertoapp.GalleryListPhotos;

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

import java.util.List;

import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 22/4/17.
 */

public class Gallery_CustomImageAdapter extends BaseAdapter {

    List<Gallery_GetSet> _data;
    Context _c;
    ViewHolder v;

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
            Bitmap icon = BitmapFactory.decodeResource(_c.getResources(), R.drawable.gallery_imgnodisponible56);
            v.imageView.setImageBitmap(icon);
        }

        else {
            v.imageView.setImageBitmap(dataSet.getImage());
        }

        v.parcelName.setText(dataSet.getLabel());
        v.label.setText(dataSet.getSubtext());
        if (dataSet.isStatus()) {
            v.clickImage.setVisibility(View.VISIBLE);
            v.removeImage.setVisibility(View.GONE);
        } else {
            v.removeImage.setVisibility(View.VISIBLE);
            v.clickImage.setVisibility(View.GONE);
        }

        v.clickImage.setFocusable(false);
        v.removeImage.setFocusable(false);


        v.clickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call to dialog
                dialogImage(dataSet);
               }
        });

        v.removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet.setStatus(true);
                dataSet.setHaveImage(false);
                dataSet.setImage(null);
                ((Gallery_MainActivity)_c).deleteImage(dataSet.getListItemPosition()); //Delete image from sdcard
                notifyDataSetChanged();
            }
        });

        return view;
    }


    private void dialogImage(final Gallery_GetSet dataSet) {
        final CharSequence[] options = { "Cámara", "Galeria de fotos","Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(_c);
        builder.setTitle("Seleccionar Foto");
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