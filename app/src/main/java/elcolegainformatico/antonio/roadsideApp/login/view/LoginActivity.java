package elcolegainformatico.antonio.roadsideApp.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import elcolegainformatico.antonio.roadsideApp.R;
import elcolegainformatico.antonio.roadsideApp.activities.MenuActivity;
import elcolegainformatico.antonio.roadsideApp.activities.ResetPasswordActivity;
import elcolegainformatico.antonio.roadsideApp.login.presenter.LoginPresenter;
import elcolegainformatico.antonio.roadsideApp.login.presenter.LoginPresenterImpl;
import elcolegainformatico.antonio.roadsideApp.registration.view.SignupActivity;


public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view now
        setContentView(R.layout.activity_login);
        createUI();

    }

    private void createUI() {

        //setTitle("Roadside APP");

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToSingup();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToResetPassword();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.receiveUserLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
            }
        });
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    public void showAlertMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToSingup() {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }

    @Override
    public void goToResetPassword() {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }

    @Override
    public void logTheUserIn() {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure() {
        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
    }

    @Override
    public void spinProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        progressBar.setVisibility(View.GONE);
    }



}

