package com.arnaldo.moviekuy.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.arnaldo.moviekuy.BroadcastReceiver.AlarmReceiver;
import com.arnaldo.moviekuy.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;

public class home extends AppCompatActivity {

    private AdView mAdView;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        registerAlarm();

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.LARGE_BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button btn_follow = findViewById(R.id.flw);
        btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://kunthu.000webhostapp.com/profile/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(webIntent);

            }
        });

        Button btn_info = findViewById(R.id.info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.themoviedb.org");
                Intent webIntent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(webIntent);

            }
        });

        Button buttonhome =  findViewById(R.id.bthome);
        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnmaps = findViewById(R.id.maps);
        btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=movies");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
    }

    public void check(View view){
        SafetyNet.getClient(this).
                verifyWithRecaptcha("6LffUn8UAAAAAER1RDqWFFqdx7kotUrR1DOLspEM")
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.
                        RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse
                                                  recaptchaTokenResponse) {
                        Toast.makeText(home.this, "Success", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(home.this, mail.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(home.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit",
                Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void registerAlarm(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,7);
        calendar.set(Calendar.MINUTE,30);
        calendar.set(Calendar.SECOND,0);

        Intent intent = new Intent(home.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(home.this,0,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager
                .INTERVAL_DAY,pendingIntent);

    }
}