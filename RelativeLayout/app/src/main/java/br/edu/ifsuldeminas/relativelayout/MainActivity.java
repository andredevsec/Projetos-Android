package br.edu.ifsuldeminas.relativelayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Atributos

    private EditText editTextCelsius;
    private Button buttonConverter;
    private TextView textViewResultadoCelsiusF;
    private TextView textViewResultadoCelsiusK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Carrregando componentes
        editTextCelsius = findViewById(R.id.editTextCelsius);
        buttonConverter = findViewById(R.id.buttonConverter);
        textViewResultadoCelsiusF = findViewById(R.id.textViewResultadoCelsiusF);
        textViewResultadoCelsiusK = findViewById(R.id.textViewResultadoCelsiusK);

        escondeComponentes();

        buttonConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recuperando valor
                String tempCelsius = editTextCelsius.getText().toString();

                // Validar valor
                if(tempCelsius.equals("")){
                    Toast.makeText(getApplicationContext(), "Informe uma temperatura", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cast valor para número + calculo
                Double valorCelsius = Double.parseDouble(tempCelsius);
                Double tempFahrenheit = (valorCelsius * 1.8) + 32;
                Double tempKelvin = (valorCelsius + 273);

                // Mostrando resultado
                textViewResultadoCelsiusF.setText(getResources().getString(R.string.celsiusToF) + " " + tempFahrenheit + "°F");
                textViewResultadoCelsiusK.setText(getResources().getString(R.string.celsiusToK) + " " + tempKelvin  + "°K");
                textViewResultadoCelsiusF.setVisibility(TextView.VISIBLE);
                textViewResultadoCelsiusK.setVisibility(TextView.VISIBLE);
            }
        });
    }

    private void escondeComponentes() {
        textViewResultadoCelsiusF.setVisibility(TextView.INVISIBLE);
        textViewResultadoCelsiusK.setVisibility(TextView.INVISIBLE);
    }
}