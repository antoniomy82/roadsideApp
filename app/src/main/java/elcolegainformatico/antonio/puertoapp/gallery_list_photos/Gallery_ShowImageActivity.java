package elcolegainformatico.antonio.puertoapp.gallery_list_photos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;

import java.io.IOException;

/**
 * Created by antonio on 22/4/17.
 */

public class Gallery_ShowImageActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Mostrar imagen");

        //Background color
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        String imagePath = getIntent().getStringExtra("ImagePath");
        String imageBitMap = getIntent().getStringExtra("ImageBitMap");

        Bitmap thumbnail=(BitmapFactory.decodeFile(imagePath));

        //Si obtengo la ruta, pero no tengo la imagen en el dispositivo.
        if(thumbnail==null){
            try {

                thumbnail=decodeFromFirebaseBase64(imageBitMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Gallery_TouchImageView img = new Gallery_TouchImageView(this);
        img.setImageBitmap(thumbnail); //Asigno el bitmap al touch event
        img.setMaxZoom(20f);
        setContentView(img);

    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

}

