package elcolegainformatico.antonio.puertoapp.gallery_list_photos;

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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import elcolegainformatico.antonio.puertoapp.R;
import static android.provider.MediaStore.EXTRA_OUTPUT;

/**
 * Created by antonio on 22/4/17.
 */

public class Gallery_MainActivity extends AppCompatActivity {

    private Gallery_CustomImageAdapter customImageAdapter;  //ListView Adapter
    private ArrayList<Gallery_GetSet> galleryGetSets;       //Object tipe GetSet
    private ArrayList<String> imagePath=new ArrayList<>(8); //Almaceno la ruta donde están guardadas las imagenes.
    private ArrayList<String> imageBitmap=new ArrayList<>(8); //Almacen los bitmaps en string
    private ArrayList<String> auxImage = new ArrayList<>();

    private ListView listView;

    private int position;           //Temp variable where store listItem position
    private String imageTempName;
    private String[] imageFor;

    private static final String myPictureDirectory = "CeutaPort";  //Name of my Photo Directory
    private static final File imageRoot = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), myPictureDirectory); //My Path (the directory where I would like store my pictures)
    private String noImage=  "drawable://"+R.drawable.gallery_imgnodisponible56;

    private ImageButton home_custom_bar;
    private TextView text_custom_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gallery_activity_main);

        //Custom title bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_title_bar);


        text_custom_title=(TextView)findViewById(R.id.text_custom_title);
        text_custom_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
        text_custom_title.setText(getResources().getString(R.string.MenuPrincipal));

        home_custom_bar=(ImageButton) findViewById(R.id.home_custom_bar);

        home_custom_bar.setImageResource(R.drawable.logo_puerto36);

        //Background color
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        //Sino no se come el temp creado para el Extra_Output
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        listView = (ListView) findViewById(R.id.captureList);

        galleryGetSets = new ArrayList<>();

        imageFor = getResources().getStringArray(R.array.imageFor);

        //Number of rows to the ListView (Rows to my gallery)
        for (int i = 0; i < 8; i++) {

            Gallery_GetSet inflate = new Gallery_GetSet();

            // Global Values
            inflate.setUid(String.valueOf(i));
            if(i!=7) {
                inflate.setLabel("Imagen");
                inflate.setSubtext(imageFor[i]);
            }
            else{
                inflate.setLabel("FIRMA");
                inflate.setSubtext("Una de 8");
            }
            inflate.setHaveImage(false);
            inflate.setStatus(true);

            //get intent save photos.
            boolean mySavedIntent=getIntent().getBooleanExtra("isSave",true);

            if(mySavedIntent){
                inflate.setSaved(true);
            }
            if(!mySavedIntent){
                inflate.setSaved(false);
            }


            galleryGetSets.add(inflate);

        }

        customImageAdapter = new Gallery_CustomImageAdapter(galleryGetSets, Gallery_MainActivity.this);
        listView.setAdapter(customImageAdapter);


        //Relleno todo para que se pueda insertar en cualquier fila
        for(int i=0; i<8; i++){
            this.imagePath.add(noImage);
        }


       //Push in a item, and view her fucking Pic!!!
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Gallery_GetSet mGet= galleryGetSets.get(position);

                Bitmap myBmp=mGet.getImage();

                if(position!=7) {

                    if (myBmp != null) {

                        Intent intent = new Intent(Gallery_MainActivity.this, Gallery_ShowImageActivity.class);
                        intent.putExtra("ImagePath", imagePath.get(position)); //Pass the path.... yeahh!!! I Speak Gibraltarian Inglish!! Lol!!
                        intent.putExtra("ImageBitMap", imageBitmap.get(position));
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "IMAGEN NO DISPONIBLE", Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                    if (myBmp != null) {

                        Intent intent = new Intent(Gallery_MainActivity.this, Gallery_ShowImageActivity.class);
                        intent.putExtra("ImagePath", imagePath.get(position)); //Pass the path.... yeahh!!! I Speak Gibraltarian Inglish!! Lol!!
                        intent.putExtra("ImageBitMap", imageBitmap.get(position));
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "FIRMA NO DISPONIBLE", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        //Get intent !! Load my imagePath from validarActivity
        if( getIntent().getStringArrayListExtra("pathFromValidar") !=null && getIntent().getStringArrayListExtra("imagesFromValidar") !=null) {
            this.imagePath = new ArrayList<>(getIntent().getStringArrayListExtra("pathFromValidar"));
            this.imageBitmap = new ArrayList<>(getIntent().getStringArrayListExtra("imagesFromValidar"));

            if (checkPermission()) {

                for (int i = 0; i < imageBitmap.size(); i++) {

                    String picturePath = imagePath.get(i);

                    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

                    if (thumbnail != null) {
                        customImageAdapter.setImageInItem(i, thumbnail, picturePath);
                    }
                    else if( imageBitmap.get(i)!=null){
                        try {
                            thumbnail = decodeFromFirebaseBase64(imageBitmap.get(i));
                            customImageAdapter.setImageInItem(i, thumbnail, picturePath);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    }

                }
            }//Check

    }

    /**
     * Pass a intent to ValidarActivity when Push back button
     */

    public void onBackPressed(){
        Gallery_GetSet mGet= galleryGetSets.get(position);

        Bitmap myBmp=mGet.getImage();

        //Copy imagePath to auxImage
        if(imagePath.size()!=0) {

            for (int i = 0; i < 8; i++) {
                if (myBmp != null) {
                    auxImage.add(imagePath.get(i));
                }
                else {
                    if (auxImage.size()>0 && imagePath.size()>0)
                    {auxImage.remove(i);}
                }
            }
        }

        Intent myImage = new Intent();
        myImage.putStringArrayListExtra("pathToValidar",auxImage);
        myImage.putStringArrayListExtra("imagesToValidar",imageBitmap);

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
     * Delete a image
     * @param pos ArrayList ImagePath (Pathname and filename)
     */
    public void deleteImageFromMobile (int pos){

        File imageDel =new File(imagePath.get(pos));

        if(imageDel.exists() && checkPermission()){
            imageDel.delete(); //Del image from my sdcard.
            Toast toast=Toast.makeText(this,"Imagen Borrada",Toast.LENGTH_SHORT);toast.show();

        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imageDel))); //Actualizo la galeria de fotos
        this.imagePath.remove(pos); //del image from my ArrayList.

    }//Check

    public void deleteImageFromPicsSelected(int pos){

        this.imagePath.remove(pos); //del image from my ArrayList.
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

                    //Photo Name!!
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(new Date());

                    imageRoot.mkdirs();  //Nos metemos en nuestro directorio

                    String path = imageRoot.getPath(); //Lo asignamos a Path

                    Log.d("PATH",path);

                    OutputStream outFile;

                    File file = new File(path, timeStamp + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();

                        if(checkPermission()) {

                            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                            Uri tempUri = getImageUri(getApplicationContext(), bitmap, imageTempName);

                            String picturePath = getRealPathFromURI(tempUri);

                            imagePath.add(position, picturePath); //Almaceno la ruta donde está la foto
                            imageBitmap.add(position,encodeBitmapToString(bitmap)); //Almaceno el bitmap convertido a String

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

                   if(checkPermission())
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
                        thumbnail=getResizedBitmap(thumbnail,100);
                        imageBitmap.add(position,encodeBitmapToString(thumbnail)); //Almaceno el bitmap convertido a String


                        Log.d("Tamaño", String.valueOf(thumbnail.getByteCount()));

                        //thumbnail = getResizedBitmap(thumbnail, 400); //Por encima de 400K no lo coge bien , limitación de los intents
                        customImageAdapter.setImageInItem(position,thumbnail,picturePath);
                    }//Check
                   else {
                       Toast toast=Toast.makeText(this,"Error al escribir en memoria",Toast.LENGTH_SHORT);toast.show();
                   }
                }
             }
    }

    /**
     * Set path, image  and store my bich Pic in her right URII!!!
     * @param inContext
     * @param inImage
     * @param imageName
     * @return Your path (where is your new Pic!)
     */
    private Uri getImageUri(Context inContext, Bitmap inImage, String imageName) {

        String path;
        path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, imageName, EXTRA_OUTPUT);
        return Uri.parse(path);
    }

    //Check Writte external ---> From API 23 and Up!
    private boolean checkPermission(){

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


    private String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);

    }


    public String encodeBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);

    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    /**
     * reduces the size of the image
     * @param image
     * @param maxSize
     * @return my new resize Bitmap
     */
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        /*
        int width = image.getWidth();
        int height = image.getHeight();*/
        int width = 800;
        int height = 600;


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