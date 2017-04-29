package elcolegainformatico.antonio.puertoapp.GalleryListPhotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        Bitmap thumbnail=(BitmapFactory.decodeFile(imagePath));

        Gallery_TouchImageView img = new Gallery_TouchImageView(this);
        img.setImageBitmap(thumbnail); //Asigno el bitmap al touch event
        img.setMaxZoom(20f);
        setContentView(img);

    }

}//

