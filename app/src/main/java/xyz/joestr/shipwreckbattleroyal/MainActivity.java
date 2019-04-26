package xyz.joestr.shipwreckbattleroyal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import java.util.logging.Logger;

import xyz.joestr.shipwreckbattleroyal.activities.HomeActivity;
import xyz.joestr.shipwreckbattleroyal.data.User;
import xyz.joestr.shipwreckbattleroyal.data.task.SignInTask;
import xyz.joestr.shipwreckbattleroyal.data.task.SignUpTask;
import xyz.joestr.shipwreckbattleroyal.util.SuccessOrFailureListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SuccessOrFailureListener {

    // The logger
    private Logger logger;

    // Controls/Views from the acitivity_main.xml file
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonSignIn;
    private Button buttonSignUp;

    // Tasks used in this Activity
    private SignUpTask signUpTask;
    private SignInTask signInTask;

    // The user for easier access
    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Init shitty FirebaseApp to prevent god damn loading errors
            FirebaseApp.initializeApp(this);

            // Get the logger
            this.logger = Logger.getLogger("Shipwreck: Battle Royal");

            // Get the constrols/views
            this.editTextUsername = (EditText) this.findViewById(R.id.editText_Username);
            this.editTextPassword = (EditText) this.findViewById(R.id.editText_Password);
            this.buttonSignIn = (Button) this.findViewById(R.id.button_LogIn);
            this.buttonSignUp = (Button) this.findViewById(R.id.button_SignIn);

            // Setup listeners
            this.buttonSignIn.setOnClickListener(this);
            this.buttonSignUp.setOnClickListener(this);
        } catch (Exception ex) {
            logger.severe("Error: " + ex.getMessage());
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case(R.id.button_LogIn): {

                this.user = new User(
                        this.editTextUsername.getText().toString(),
                        this.editTextPassword.getText().toString()
                );

                this.signInTask = new SignInTask(this);
                this.signInTask.execute(this.user);

                this.buttonSignIn.setEnabled(false);
                this.buttonSignUp.setEnabled(false);

                break;
            }
            case(R.id.button_SignIn): {

                this.user = new User(
                        this.editTextUsername.getText().toString(),
                        this.editTextPassword.getText().toString()
                );

                this.signUpTask = new SignUpTask(this);
                this.signUpTask.execute(this.user);

                this.buttonSignIn.setEnabled(false);
                this.buttonSignUp.setEnabled(false);

                break;
            }
        }
    }

    @Override
    public void onSuccess(String message, String jsonData) {

        Toast.makeText(this, message + " / " + jsonData, Toast.LENGTH_LONG).show();
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("username", user.getName());
        this.startActivity(homeIntent);

        this.buttonSignIn.setEnabled(true);
        this.buttonSignUp.setEnabled(true);
    }

    @Override
    public void onFailure(String message, Exception exception) {

        logger.severe("Error: " + message + " / " + exception.getMessage());
        Toast.makeText(this, message + " / " + exception.getMessage(), Toast.LENGTH_LONG).show();

        this.buttonSignIn.setEnabled(true);
        this.buttonSignUp.setEnabled(true);
    }
}
