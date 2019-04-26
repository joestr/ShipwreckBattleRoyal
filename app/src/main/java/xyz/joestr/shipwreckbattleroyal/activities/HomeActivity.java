package xyz.joestr.shipwreckbattleroyal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import xyz.joestr.shipwreckbattleroyal.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    // The username
    private String currentUsername;

    // The controls/views
    private TextView textViewUsername;
    private Button buttonLobby;
    private Button buttonStatistics;
    private Button buttonSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            // Fill the username here
            this.currentUsername = this.getIntent().getStringExtra("username");

            // Fill the controls/views
            this.textViewUsername = this.findViewById(R.id.activity_home_textView_username);
            this.buttonLobby = this.findViewById(R.id.activity_home_button_lobby);
            this.buttonStatistics = this.findViewById(R.id.activity_home_button_statistics);
            this.buttonSignOut = this.findViewById(R.id.activity_home_button_signout);

            // Set the username in the textView
            this.textViewUsername.setText(this.currentUsername);

            // Register the listeners
            this.buttonLobby.setOnClickListener(this);
            this.buttonStatistics.setOnClickListener(this);
            this.buttonSignOut.setOnClickListener(this);
        } catch(Exception ex) {
            Log.e("d", "Error!", ex);
            Toast.makeText(this, "Error!: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case(R.id.activity_home_button_lobby): {

                break;
            }
            case(R.id.activity_home_button_statistics): {

                break;
            }
            case(R.id.activity_home_button_signout): {
                // Simply close this activity
                this.finish();
                break;
            }
        }
    }
}
