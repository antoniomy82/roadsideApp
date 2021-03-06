package elcolegainformatico.antonio.roadsideApp.login.interactor;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import elcolegainformatico.antonio.roadsideApp.login.presenter.LoginPresenter;


/**
 * Created by antonio on 14/5/17.
 */
public class LoginInteractorImpl implements LoginInteractor {
    private final LoginPresenter presenter;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void attemptToLogIn(String email,final String password) {
        if (auth.getCurrentUser() != null) {
            presenter.onSuccess();
        }

        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                presenter.onFailure();
                            } else {
                                presenter.onFailure();

                            }
                        }else {

                            presenter.onSuccess();
                        }
                    }
                });

    }

}
