package com.apps.wikex.smallbusinessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCard extends AppCompatActivity {

    Intent incomingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        // Get Data from previus screen
        incomingIntent = getIntent();
        String service = incomingIntent.getStringExtra(AppData.SERVICE);

    }

    public void addCard(View view){
        checkValues(view);
        CheckBox remenberCB = findViewById(R.id.remmenberCB);
        Intent intent = new Intent(this,FinishActivity.class);
        EditText cardName = findViewById(R.id.cardName);
        EditText cardNumber = findViewById(R.id.cardNumber);

        if(remenberCB.isChecked()){
            saveCard(cardName.getText().toString(),
                    Integer.parseInt(cardNumber.getText().toString()),this);

            intent.putExtra("CARADDED", "cardAdded");
            Log.v("CWTS", "save car value :" + cardName.getText().toString()
                    + " - " + cardNumber.getText().toString());
        }

        intent.putExtra(AppData.SERVICE, incomingIntent.getStringExtra(AppData.SERVICE));
        intent.putExtra(AppData.CARDNUMBER, cardNumber.getText().toString());
        startActivity(intent);



    }

    private void saveCard(String nameCard, Integer numberCard, Context context){

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor shEditor = sh.edit();

        Cards savedCard = new Cards(nameCard,numberCard);

        shEditor.putString(AppData.CARDNAME + AppData.getInstance().cardCount, savedCard.getNameHolder());
        shEditor.putInt(AppData.CARDNUMBER + AppData.getInstance().cardCount, savedCard.getNumber());
        AppData.getInstance().getList().add(savedCard);
        AppData.getInstance().cardCount++;
        shEditor.putInt(AppData.CARDCOUNT, AppData.getInstance().cardCount);
        Log.v("DATA", "CardAdded : " + nameCard + " - " + numberCard + " ["+AppData.getInstance().cardCount+"]");
        shEditor.commit();

    }

    private void checkValues(View view){
        String text;
        EditText cardName = findViewById(R.id.cardName);
        EditText cardNumber = findViewById(R.id.cardNumber);

        if (cardName.getText().toString().isEmpty()){
            Toast.makeText(this,"The card name can't be empty", Toast.LENGTH_LONG).show();
        }
        if (cardNumber.getText().toString().isEmpty()){
            Toast.makeText(this,"The card number can't be empty", Toast.LENGTH_LONG).show();
        }

    }
}
