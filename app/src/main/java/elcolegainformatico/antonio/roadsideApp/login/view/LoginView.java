package elcolegainformatico.antonio.roadsideApp.login.view;

/**
 * Created by antonio on 14/5/17.
 */
public interface LoginView {
    void showAlertMessage(String message);
    void goToSingup();
    void goToResetPassword();
    void logTheUserIn();
    void onFailure();
    void spinProgressBar();
    void stopProgressBar();
}
