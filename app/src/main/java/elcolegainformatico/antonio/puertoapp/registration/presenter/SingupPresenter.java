package elcolegainformatico.antonio.puertoapp.registration.presenter;

/**
 * Created by antonio on 14/5/17.
 */
public interface SingupPresenter {
    void goToLogin();
    void goToResetPassword();
    void receiveUserSinUp(String email, String password);
    void onFailure();
    void onSuccess();
}
