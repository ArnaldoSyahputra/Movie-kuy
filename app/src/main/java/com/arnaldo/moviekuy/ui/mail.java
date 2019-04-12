package com.arnaldo.moviekuy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arnaldo.moviekuy.R;

public class mail extends AppCompatActivity {

    private EditText editTextTo;
    private EditText editTextSubject;
    private EditText editTextIdeas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        editTextTo = findViewById(R.id.emailaddress);
        editTextSubject = findViewById(R.id.subject);
        editTextIdeas = findViewById(R.id.body);

        Button buttonSend = findViewById(R.id.btnsend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    private void sendMail(){
        String recipientList = editTextTo.getText().toString();
        String[] recipients = recipientList.split(",");


        String subject = editTextSubject.getText().toString();
        String ideas = editTextIdeas.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, ideas);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose your email app!"));

    }
}
