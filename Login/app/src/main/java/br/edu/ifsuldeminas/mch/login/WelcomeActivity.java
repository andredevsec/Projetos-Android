package br.edu.ifsuldeminas.mch.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "br.edu.ifsuldeminas.mch.login.activity_bem_vindo"; //tag para usar no metodo log
    private static final int FOTO_CODE = 1;
    private Button buttonTiraFoto;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intentChamadora = getIntent();

        String userName = intentChamadora.getStringExtra("USER");

        String message = String.format("Bem vindo '%s'", userName);

        View layoutBemVindo = findViewById(R.id.activity_bem_vindo_id);

        Snackbar snackbar = Snackbar.make(layoutBemVindo, message, Snackbar.LENGTH_SHORT);
        snackbar.show();

        Log.e (TAG, "onCreate da tela Bem Vindo Rodou");
        Log.w (TAG, "onCreate da tela Bem Vindo Rodou");
        Log.i (TAG, "onCreate da tela Bem Vindo Rodou");
        Log.d (TAG, "onCreate da tela Bem Vindo Rodou");
        Log.v (TAG, "onCreate da tela Bem Vindo Rodou");

        buttonTiraFoto = findViewById(R.id.buttonTiraFoto);
        imageView = findViewById(R.id.imageViewId);
        buttonTiraFoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                Intent intentTiraFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentTiraFoto, FOTO_CODE);

            }
        });
            }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == FOTO_CODE && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();

            Bitmap image = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(image);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e (TAG, "onStart da tela Bem Vindo Rodou");
        Log.w (TAG, "onStart da tela Bem Vindo Rodou");
        Log.i (TAG, "onStart da tela Bem Vindo Rodou");
        Log.d (TAG, "onStart da tela Bem Vindo Rodou");
        Log.v (TAG, "onStart da tela Bem Vindo Rodou");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e (TAG, "onResume da tela Bem Vindo Rodou");
        Log.w (TAG, "onResume da tela Bem Vindo Rodou");
        Log.i (TAG, "onResume da tela Bem Vindo Rodou");
        Log.d (TAG, "onResume da tela Bem Vindo Rodou");
        Log.v (TAG, "onResume da tela Bem Vindo Rodou");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e (TAG, "onPause da tela Bem Vindo Rodou");
        Log.w (TAG, "onPause da tela Bem Vindo Rodou");
        Log.i (TAG, "onPause da tela Bem Vindo Rodou");
        Log.d (TAG, "onPause da tela Bem Vindo Rodou");
        Log.v (TAG, "onPause da tela Bem Vindo Rodou");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e (TAG, "onStop da tela Bem Vindo Rodou");
        Log.w (TAG, "onStop da tela Bem Vindo Rodou");
        Log.i (TAG, "onStop da tela Bem Vindo Rodou");
        Log.d (TAG, "onStop da tela Bem Vindo Rodou");
        Log.v (TAG, "onStop da tela Bem Vindo Rodou");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e (TAG, "onRestart da tela Bem Vindo Rodou");
        Log.w (TAG, "onRestart da tela Bem Vindo Rodou");
        Log.i (TAG, "onRestart da tela Bem Vindo Rodou");
        Log.d (TAG, "onRestart da tela Bem Vindo Rodou");
        Log.v (TAG, "onRestart da tela Bem Vindo Rodou");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e (TAG, "onDestroy da tela Bem Vindo Rodou");
        Log.w (TAG, "onDestroy da tela Bem Vindo Rodou");
        Log.i (TAG, "onDestroy da tela Bem Vindo Rodou");
        Log.d (TAG, "onDestroy da tela Bem Vindo Rodou");
        Log.v (TAG, "onDestroy da tela Bem Vindo Rodou");
    }
}