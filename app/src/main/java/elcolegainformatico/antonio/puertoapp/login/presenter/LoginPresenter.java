package elcolegainformatico.antonio.puertoapp.login.presenter;

/**
 * Created by antonio on 14/5/17.
 */
public interface LoginPresenter {
    void goToSingup();
    void goToResetPassword();
    void receiveUserLogin(String email, String password);
    void onFailure();
    void onSuccess();
}
