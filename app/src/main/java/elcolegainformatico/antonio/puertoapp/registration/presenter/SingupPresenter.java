package elcolegainformatico.antonio.puertoapp.registration.presenter;

/**
 * Created by joseluissanchez-porrogodoy on 26/8/16.
 */
public interface SingupPresenter {
    void goToLogin();
    void goToResetPassword();
    void receiveUserSinUp(String email, String password);
    void onFailure();
    void onSuccess();
}
