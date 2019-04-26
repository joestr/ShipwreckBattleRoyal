package xyz.joestr.shipwreckbattleroyal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import xyz.joestr.shipwreckbattleroyal.R;

public class LobbyActivity extends AppCompatActivity {

    // The username
    private String currentUsername = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        // Fill the username here
        this.currentUsername = this.getIntent().getStringExtra("username");
    }
}
