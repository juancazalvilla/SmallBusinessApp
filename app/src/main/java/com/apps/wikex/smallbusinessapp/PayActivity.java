package com.apps.wikex.smallbusinessapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {

    final int EXITINDEX = -99;
    Intent incomingIntent = null;
    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        payButton = findViewById(R.id.finishB);
        payButton.setEnabled(false);

        // If there no cards on the List jump to card input screem
        if (AppData.getInstance().cardCount < 1 ){
            Intent intent = new Intent(this,AddCard.class);
            intent.putExtra(AppData.SERVICE, incomingIntent.getStringExtra(AppData.SERVICE));
            Log.v("CWTS", "No cardcount , go to addcCard aticvity");
            startActivity(intent);
        }

        // Recive service type
        incomingIntent = getIntent();
        String service = incomingIntent.getStringExtra(AppData.SERVICE);


        // Add Card list
        final RadioGroup cardList = findViewById(R.id.cardListRG);
        cardList.removeAllViews();

        int index = 0;
        RadioButton rb;

        for(Cards card : AppData.getInstance().getList() ){
            rb = new RadioButton(cardList.getContext());
            rb.setId(index++);
            rb.setText(card.nameHolder + " : " + card.number);
            rb.setHint(Integer.toString(card.number));
            Log.v("CWTS", "load car value :" + card.nameHolder + " - " + card.number);
            cardList.addView(rb);
        }

        rb = new RadioButton(cardList.getContext());

        rb.setId(EXITINDEX);
        rb.setText("Pay with other card");
        cardList.addView(rb);


        // Add trigger "Pay whit other card" action
        final Context parent = this;

        cardList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(cardList.getCheckedRadioButtonId());
                if(radioButton.getId() == EXITINDEX){
                    Intent intent = new Intent(parent,AddCard.class);
                    intent.putExtra(AppData.SERVICE, incomingIntent.getStringExtra(AppData.SERVICE));
                    startActivity(intent);
                }

                payButton.setEnabled(true);
            }
        });
    }

    public void nextScreen(View view){

        RadioGroup cardList = findViewById(R.id.cardListRG);
        RadioButton selectedRB = findViewById(cardList.getCheckedRadioButtonId());

        Intent intent = new Intent(this,FinishActivity.class);
        intent.putExtra(AppData.SERVICE, incomingIntent.getStringExtra(AppData.SERVICE));
        intent.putExtra(AppData.CARDNUMBER, selectedRB.getHint());
        startActivity(intent);

    }
}
