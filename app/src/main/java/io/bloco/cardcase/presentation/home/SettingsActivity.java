package io.bloco.cardcase.presentation.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.bloco.cardcase.R;

public class SettingsActivity extends AppCompatActivity {

    public static class Factory {
        public static Intent getIntent(Context context) {
            return new Intent(context, SettingsActivity.class);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
