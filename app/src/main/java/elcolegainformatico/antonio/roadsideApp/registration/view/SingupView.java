package elcolegainformatico.antonio.roadsideApp.registration.view;

/**
 * Created by antonio on 14/5/17.
 */
public interface SingupView {
    void goToResetPassword();
    void goToLogin();
    void spinProgressBar();
    void stopProgressBar();
    void onSuccess();
    void onFailure();
    void showAlertMessage(String message);

}
