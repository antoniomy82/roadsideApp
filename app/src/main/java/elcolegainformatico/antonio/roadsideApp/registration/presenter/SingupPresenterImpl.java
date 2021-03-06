package elcolegainformatico.antonio.roadsideApp.registration.presenter;

import android.text.TextUtils;

import elcolegainformatico.antonio.roadsideApp.registration.interactor.SingupInteractor;
import elcolegainformatico.antonio.roadsideApp.registration.interactor.SingupInteractorImpl;
import elcolegainformatico.antonio.roadsideApp.registration.view.SingupView;


/**
 * Created by antonio on 14/5/17.
 */
public class SingupPresenterImpl implements SingupPresenter {

    private final SingupView singupView;
    private final SingupInteractor interactor;

    public SingupPresenterImpl(SingupView singupView) {
        this.singupView = singupView;
        this.interactor = new SingupInteractorImpl(this);
    }



    @Override
    public void goToLogin() {
        singupView.goToLogin();
    }

    @Override
    public void goToResetPassword() {
        singupView.goToResetPassword();
    }

    @Override
    public void receiveUserSinUp(String email, String password) {

        if (TextUtils.isEmpty(email)) {
            singupView.showAlertMessage( "Enter email address!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            singupView.showAlertMessage("Enter password!");
            return;
        }
        if (password.length() < 6) {
            singupView.showAlertMessage("Password too short, enter minimum 6 characters!");
            return;
        }
        interactor.attemptToSinup(email, password);
    }

    @Override
    public void onFailure() {
        singupView.showAlertMessage("Authentication failed.");
    }

    @Override
    public void onSuccess() {
        singupView.showAlertMessage( "createUserWithEmail:onComplete:");
        singupView.goToLogin();
    }
}
