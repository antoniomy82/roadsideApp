package elcolegainformatico.antonio.roadsideApp.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by antonio on 11/7/17.
 */
@IgnoreExtraProperties
public class User {

    public String password;
    public String email;

    public User() {

    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
