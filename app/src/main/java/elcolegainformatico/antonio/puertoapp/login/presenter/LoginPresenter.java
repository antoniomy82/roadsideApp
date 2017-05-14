package elcolegainformatico.antonio.puertoapp.login.presenter;

/**
 * Created by joseluissanchez-porrogodoy on 24/8/16.
 */
public interface LoginPresenter {
    void goToSingup();
    void goToResetPassword();
    void receiveUserLogin(String email, String password);
    void onFailure();
    void onSuccess();
}
