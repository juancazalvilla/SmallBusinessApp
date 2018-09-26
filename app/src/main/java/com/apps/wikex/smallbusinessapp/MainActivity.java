package com.apps.wikex.smallbusinessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {


    Button continueB;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            loadCard(this);

            continueB = findViewById(R.id.button);
            continueB.setEnabled(false);
            radioGroup = findViewById(R.id.radioGroup);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    continueB.setEnabled(true);
                }
            });


    }

    private void loadCard(Context context){
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String cardname;
        int cardnumber;

        AppData.getInstance().cardCount = sh.getInt(AppData.CARDCOUNT, -1);
        Log.v("CWTS", "Cardcount load value :" + AppData.getInstance().cardCount);
        for (int i = 0; i < AppData.getInstance().cardCount; i++){
            cardname = sh.getString(AppData.CARDNAME + i, "");
            cardnumber = sh.getInt(AppData.CARDNUMBER + i, -1);
            AppData.getInstance().getList().add(new Cards(cardname, cardnumber) );
        }

    }

    public void nextScreen(View view){

        Intent intent = new Intent( this, PayActivity.class);
        radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String service = (String)radioButton.getHint();
        Log.v("CWTS", "Sending service :" + service);
        intent.putExtra(AppData.SERVICE, service);
        startActivity(intent);

    }
}
