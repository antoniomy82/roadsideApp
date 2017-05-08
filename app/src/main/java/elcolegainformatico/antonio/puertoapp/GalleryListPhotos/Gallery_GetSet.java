package elcolegainformatico.antonio.puertoapp.GalleryListPhotos;
import android.graphics.Bitmap;

/**
 * Created by antonio on 22/4/17.
 */

/**
 * Getter y Setter utils
 */

public class Gallery_GetSet {
    String label;
    Bitmap image;
    boolean haveImage;
    String subtext;
    boolean status;
    boolean isSaved;

    public Gallery_GetSet() {

    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;

    public int getListItemPosition() {
        return listItemPosition;
    }

    public void setListItemPosition(int listItemPosition) {
        this.listItemPosition = listItemPosition;
    }

    int listItemPosition;

    public boolean isHaveImage() {
        return haveImage;
    }


    public void setHaveImage(boolean haveImage) {
        this.haveImage = haveImage;
    }

    public void setSaved(boolean isSaved){this.isSaved=isSaved; }

    public boolean getSaved(){ return isSaved;}

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}