package elcolegainformatico.antonio.puertoapp.login.presenter;


import android.text.TextUtils;

import elcolegainformatico.antonio.puertoapp.login.interactor.LoginInteractor;
import elcolegainformatico.antonio.puertoapp.login.interactor.LoginInteractorImpl;
import elcolegainformatico.antonio.puertoapp.login.view.LoginView;


/**
 * Created by joseluissanchez-porrogodoy on 24/8/16.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private final LoginView loginView;
    private final LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void goToSingup() {
        loginView.goToSingup();
    }

    @Override
    public void goToResetPassword() {
        loginView.goToResetPassword();
    }

    @Override
    public void receiveUserLogin(String email, String password) {
        if (TextUtils.isEmpty(email)) {
             loginView.showAlertMessage("Enter email address!");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            loginView.showAlertMessage("Enter password!");
            return;
        }
        loginView.spinProgressBar();
        interactor.attemptToLogIn(email, password);
    }

    @Override
    public void onFailure() {
        loginView.stopProgressBar();
        loginView.onFailure();
    }

    @Override
    public void onSuccess() {
        loginView.stopProgressBar();
        loginView.logTheUserIn();

    }
}
