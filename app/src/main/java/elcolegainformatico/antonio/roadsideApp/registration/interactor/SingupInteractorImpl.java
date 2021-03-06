package elcolegainformatico.antonio.roadsideApp.registration.interactor;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import elcolegainformatico.antonio.roadsideApp.model.User;
import elcolegainformatico.antonio.roadsideApp.registration.presenter.SingupPresenter;


/**
 * Created by antonio on 14/5/17.
 */
public class SingupInteractorImpl implements SingupInteractor {
    private final SingupPresenter presenter;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    public SingupInteractorImpl(SingupPresenter presenter) {
        this.presenter = presenter;
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void attemptToSinup(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            presenter.onFailure();

                        } else {
                            onAuthSuccess(task.getResult().getUser());
                            presenter.onSuccess();
                        }
                    }
                });

    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }
}
