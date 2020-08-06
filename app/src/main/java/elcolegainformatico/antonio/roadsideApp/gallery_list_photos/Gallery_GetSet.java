package elcolegainformatico.antonio.roadsideApp.gallery_list_photos;
import android.graphics.Bitmap;

/**
 * Getter y Setter utils
 */

public class Gallery_GetSet {
    private String label;
    private Bitmap image;
    private boolean haveImage;
    private String subtext;
    private boolean status;
    private boolean isSaved;
    private int listItemPosition;
    private String uid;

    public Gallery_GetSet() {}

    public void setUid(String uid) {
        this.uid = uid;
    }


    public int getListItemPosition() {
        return listItemPosition;
    }

    public void setListItemPosition(int listItemPosition) {
        this.listItemPosition = listItemPosition;
    }



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