package elcolegainformatico.antonio.puertoapp.GalleryListPhotos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import elcolegainformatico.antonio.puertoapp.Activities.ValidarActivity;
import elcolegainformatico.antonio.puertoapp.R;

import static android.provider.MediaStore.EXTRA_OUTPUT;

/**
 * Created by antonio on 22/4/17.
 */

public class Gallery_MainActivity extends AppCompatActivity {

    Gallery_CustomImageAdapter customImageAdapter;  //ListView Adapter
    ArrayList<Gallery_GetSet> galleryGetSets;       //Object tipe GetSet
    ArrayList<String> imagePath=new ArrayList<>(8);       //Almaceno la ruta donde están guardadas las imagenes.
    ArrayList<String> auxImage = new ArrayList<>();

    ListView listView;


    int position;               //Temp variable where store listItem position
    String imageTempName;
    String[] imageFor;
    static final String myPictureDirectory = "PuertoAppFotos";  //Name of my Photo Directory
    static final File imageRoot = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), myPictureDirectory);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if( getIntent().getStringArrayListExtra("validar") !=null)
        {
            imagePath=new ArrayList<String>(getIntent().getStringArrayListExtra("validar"));
            //Toast toast = Toast.makeText(this, "ValidarImage Tiene Cosas", Toast.LENGTH_SHORT); toast.show();
        }



        //Background color
        getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);

        //Sino no se come el temp creado para el Extra_Output
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        setContentView(R.layout.gallery_activity_main);

        listView = (ListView) findViewById(R.id.captureList);

        galleryGetSets = new ArrayList<Gallery_GetSet>();

        imageFor = getResources().getStringArray(R.array.imageFor);

        //Number of rows
        for (int i = 0; i < 8; i++) {

            Gallery_GetSet inflate = new Gallery_GetSet();

            // Global Values
            inflate.setUid(String.valueOf(i));
            inflate.setLabel("Imagen");
            inflate.setHaveImage(false);
            inflate.setSubtext(imageFor[i]);
            inflate.setStatus(true);

            galleryGetSets.add(inflate);
        }

        customImageAdapter = new Gallery_CustomImageAdapter(galleryGetSets, Gallery_MainActivity.this);
        listView.setAdapter(customImageAdapter);


       //Push in a item, and view her fucking Pic!!!
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Gallery_GetSet mGet= galleryGetSets.get(position);

                Bitmap myBmp=mGet.getImage();

                if(myBmp !=null) {

                    Intent intent = new Intent(Gallery_MainActivity.this, Gallery_ShowImageActivity.class);
                    intent.putExtra("ImagePath", imagePath.get(position)); //i pass the path.... yeahh!!! I Speak Gibraltarian Inglish!! Lol!!
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"IMAGEN NO DISPONIBLE", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Pass Intent with back button
     */

    public void onBackPressed(){

        if(imagePath.size()!=0)
        {

            for(int i=0; i<imagePath.size(); i++){
                auxImage.add(imagePath.get(i));
            }
        }

        Intent myImage = new Intent();
        myImage.putStringArrayListExtra("galleryToValidar",auxImage);

        setResult(Activity.RESULT_OK,myImage);

        finish();
        super.onBackPressed();

    }

    /**
     * Capture Image and save into database
     */
    public void captureImage(int pos, String imageName) {
        position = pos;
        imageTempName = "temp.jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File f = new File(Environment.getExternalStorageDirectory(), imageTempName);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));  //EXTRA_OUTPUT PARA FULL RESOLUTION

        startActivityForResult(intent, 100);
    }

    /**
     * Get Image from Gallery and set into database and set to image preview
     */
    public void getImageGallery(int pos) {
        position=pos;
        Intent data = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(data, 200);
    }


    /**
     * In this Function is all "the tomatoes"
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED) {

            //Cámara
            if (requestCode == 100) {
                   File f = new File(Environment.getExternalStorageDirectory().toString());

                for (File temp : f.listFiles()) {
                  if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);
                    f.delete();

                    //Nombre de la foto (((AQUÍ HAY QUE TOCAR)
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(new Date());

                    imageRoot.mkdirs();  //Nos metemos en nuestro directorio

                    String path = imageRoot.getPath().toString(); //Lo asignamos a Path

                    Log.d("PATH",path);

                    OutputStream outFile = null;

                    File file = new File(path, timeStamp + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();

                        if(checkPermission()==true) {

                            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                            Uri tempUri = getImageUri(getApplicationContext(), bitmap, imageTempName);

                            String picturePath = getRealPathFromURI(tempUri);

                            imagePath.add(position, picturePath); //Almaceno la ruta donde está la foto

                            //Añadir imagen a la galeria
                            customImageAdapter.setImageInItem(position, bitmap, picturePath);

                            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file))); //Actualizo la galeria de fotos
                        }
                        else {
                           Toast toast=Toast.makeText(this,"Permite la escritura en memoria",Toast.LENGTH_SHORT);toast.show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

               //Galeria de imagenes
                if (requestCode == 200) {

                   if(checkPermission()==true)
                    {
                        Uri selectedImage = data.getData();
                        String[] filePath = { MediaStore.Images.Media.DATA };
                        Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                        c.moveToFirst();

                        int columnIndex = c.getColumnIndex(filePath[0]);
                        String picturePath = c.getString(columnIndex);
                        c.close();
                        Bitmap thumbnail=(BitmapFactory.decodeFile(picturePath));

                        imagePath.add(position,picturePath);

                        Log.d("Tamaño", String.valueOf(thumbnail.getByteCount()));

                        //thumbnail = getResizedBitmap(thumbnail, 400); //Por encima de 400K no lo coge bien , limitación de los intents
                        customImageAdapter.setImageInItem(position,thumbnail,picturePath);
                    }//Check
                   else {
                       Toast toast=Toast.makeText(this,"Permite la escritura en memoria",Toast.LENGTH_SHORT);toast.show();
                   }
                }
        }
    }

    /**
     * Set path, image , check Permissions and store my bich Pic in her right URII!!!
     * @param inContext
     * @param inImage
     * @param imageName
     * @return
     */
    public Uri getImageUri(Context inContext, Bitmap inImage, String imageName) {

        String path=null;
        path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, imageName, EXTRA_OUTPUT);
        return Uri.parse(path);
    }

    //API 23 Write in External Storage
    public boolean checkPermission(){

        //Check Write_External Permissions
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            return false;
        }
        else{
            return true;
        }

    }


    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public Bitmap convertSrcToBitmap(String imageSrc) {
        Bitmap myBitmap = null;
        File imgFile = new File(imageSrc);
        if (imgFile.exists()) {
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return myBitmap;
    }

    /**
     * reduces the size of the image
     * @param image
     * @param maxSize
     * @return
     */
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}