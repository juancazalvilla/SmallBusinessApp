package com.apps.wikex.smallbusinessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Intent incomingInten = getIntent();

        // Checked save card
        if(incomingInten.hasExtra("CARADDED")){
            Toast.makeText(this, "Card Added to the list",Toast.LENGTH_LONG).show();
        }

        // Showing services and card info
        TextView transation = findViewById(R.id.transations);
        if(incomingInten.hasExtra(AppData.SERVICE) && incomingInten.hasExtra(AppData.CARDNUMBER)){
            transation.setText( "Requested service: " + incomingInten.getStringExtra(AppData.SERVICE)
            + "\n Card charged: " + incomingInten.getStringExtra(AppData.CARDNUMBER) );
        }

        TextView codeNumber = findViewById(R.id.codeNumber);
        codeNumber.setText(String.valueOf(AppData.ramdonNumber()));
    }
}
