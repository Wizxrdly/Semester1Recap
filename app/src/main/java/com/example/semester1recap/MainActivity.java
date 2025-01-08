package com.example.semester1recap;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputNomorTelepon, shareText, Awal, Tujuan;
    private Button tombolTelepon, shareButton, Jalur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Deklarasi View
        inputNomorTelepon = findViewById(R.id.inputNomorTelepon);
        shareText = findViewById(R.id.shareText);
        tombolTelepon = findViewById(R.id.tombolTelepon);
        shareButton = findViewById(R.id.shareButton);
        Awal = findViewById(R.id.Awal);
        Tujuan = findViewById(R.id.Tujuan);
        Jalur = findViewById(R.id.Jalur);

        //Input keyboard berubah menjadi angka saja
        inputNomorTelepon.setInputType(InputType.TYPE_CLASS_PHONE);

        //Panggilan Telepon
        tombolTelepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomorTelepon = inputNomorTelepon.getText().toString().trim();
                if (!nomorTelepon.isEmpty()) {
                    buatPanggilan(nomorTelepon);
                } else {
                    Toast.makeText(MainActivity.this, "Dimohon untuk memasukkan nomor telepon!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Membagikan Teks
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = shareText.getText().toString().trim();
                if (!textToShare.isEmpty()) {
                    shareTextContent(textToShare);
                } else {
                    Toast.makeText(MainActivity.this, "Dimohon untuk memasukkan teks!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Mencari jalur
        Jalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String awal = Awal.getText().toString().trim();
                String tujuan = Tujuan.getText().toString().trim();
                if (!awal.isEmpty()) {
                    DisplayJalur(awal, tujuan);
                } else {
                    Toast.makeText(MainActivity.this, "Dimohon untuk memasukkan lokasi!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Metode untuk panggilan telepon
    private void buatPanggilan(String nomorTelepon) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + nomorTelepon));
        startActivity(callIntent);
    }

    //Metode untuk membagikan teks
    private void shareTextContent(String textToShare) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
        startActivity(Intent.createChooser(shareIntent, "Share to:"));
    }

    //Metode untuk mencari jalur
    private void DisplayJalur(String awal, String tujuan) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + awal + "/" + tujuan);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
