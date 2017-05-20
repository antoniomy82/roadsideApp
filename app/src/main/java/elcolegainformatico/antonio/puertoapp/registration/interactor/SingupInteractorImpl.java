package elcolegainformatico.antonio.puertoapp.registration.interactor;

import android.support.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import elcolegainformatico.antonio.puertoapp.registration.presenter.SingupPresenter;


/**
 * Created by antonio on 14/5/17.
 */
public class SingupInteractorImpl implements SingupInteractor{
    private final SingupPresenter presenter;
    private FirebaseAuth auth;
    public SingupInteractorImpl(SingupPresenter presenter)
    {
        this.presenter = presenter;
        auth = FirebaseAuth.getInstance();
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
                    presenter.onSuccess();
                }
            }
        });

    }
}
