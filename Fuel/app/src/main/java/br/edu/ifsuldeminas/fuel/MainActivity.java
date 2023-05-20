package br.edu.ifsuldeminas.fuel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.edu.ifsuldeminas.mch.fuel.R;

public class MainActivity extends AppCompatActivity {
    // Atributos
    private TextInputEditText textInputEditTextEtanol;
    private TextInputEditText textInputEditTextGas;
    private Button buttonCalcular;
    private ImageView imageViewFuel;
    private ImageView imageViewShare;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Carregar componentes
        textInputEditTextEtanol = findViewById(R.id.textInputEditTextEtanol);
        textInputEditTextGas = findViewById(R.id.textInputEditTextGas);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        imageViewFuel = findViewById(R.id.imageViewFuel);
        imageViewShare = findViewById(R.id.imageViewShare);
        textViewMessage = findViewById(R.id.textViewMessage);

        escondeComponentes();

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recuperar valores
                String stringEtanol = textInputEditTextEtanol.getText().toString();
                String stringGas = textInputEditTextGas.getText().toString();

                // Validar valores
                if (stringEtanol.equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Campo Etanol não pode ser vazio", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(stringGas.equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Campo Gasolina não pode ser vazio", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cast valores para número
                Double valorEtanol = Double.parseDouble(stringEtanol);
                Double valorGas = Double.parseDouble(stringGas);

                // Cálcula o melhor combustível
                if ((valorEtanol/valorGas) <= 0.7){
                    // Melhor etanol
                    imageViewFuel.setImageResource(R.drawable.ethanol);
                    textViewMessage.setText("Melhor usar etanol");
                } else {
                    // Melhor gasolina
                    imageViewFuel.setImageResource(R.drawable.gas);
                    textViewMessage.setText("Melhor usar gasolina");
                }

                textViewMessage.setVisibility(TextView.VISIBLE);
                imageViewFuel.setVisibility(ImageView.VISIBLE);
                imageViewShare.setVisibility(ImageView.VISIBLE);
             }
        });
    }

    private void escondeComponentes() {
        imageViewFuel.setVisibility(ImageView.INVISIBLE);
        imageViewShare.setVisibility(ImageView.INVISIBLE);
        textViewMessage.setVisibility(TextView.INVISIBLE);
    }
}