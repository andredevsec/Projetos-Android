package br.edu.ifsuldeminas.mch.fuel;

import static br.edu.ifsuldeminas.mch.fuel.R.id.editTextAlertDialogGasStationId;
import static br.edu.ifsuldeminas.mch.fuel.R.id.imageViewFuel;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
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


        //carregar componentes
        textInputEditTextEtanol = findViewById(R.id.textInputEditTextEtanol);
        textInputEditTextGas = findViewById(R.id.textInputEditTextGas);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        imageViewFuel= findViewById(R.id.imageViewFuel);
        imageViewShare= findViewById(R.id.imageViewShare);
        textViewMessage= findViewById(R.id.textViewMessage);


        escondeComponentes();

    buttonCalcular.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String stringEtanol = textInputEditTextEtanol.getText().toString();
            String stringGas = textInputEditTextGas.getText().toString();

            //validar valores

            if (stringEtanol.equals((""))) {
                Toast.makeText(getApplicationContext() , "Campo etanol não pode ser vazio" , Toast.LENGTH_SHORT).show();
                return;
            }

            if (stringGas.equals((""))) {
                Toast.makeText(getApplicationContext() , "Campo gasolina não pode ser vazio" , Toast.LENGTH_SHORT).show();
                return;
            }
            //const valores para numero
            Double valorEtanol = Double.parseDouble(stringEtanol);
            Double valorGas = Double.parseDouble(stringGas);

            //calcular o melhor Combustivel
            if ((valorEtanol/valorGas) <=0.7){
                imageViewFuel.setImageResource(R.drawable.ethanol);
                textViewMessage.setText("Melhor usar etanol");

            }else {
                //Melhor gasolina
                textViewMessage.setText("Melhor usar gasolina");
                imageViewFuel.setImageResource(R.drawable.gas);
            }

            textViewMessage.setVisibility(TextView.VISIBLE);
            imageViewFuel.setVisibility(imageViewFuel.VISIBLE);
            imageViewShare.setVisibility(imageViewShare.VISIBLE);
        }
    });
    }

    private void escondeComponentes() {
        imageViewFuel.setVisibility(imageViewFuel.INVISIBLE);
        imageViewShare.setVisibility(imageViewShare.INVISIBLE);
        textViewMessage.setVisibility(textViewMessage.INVISIBLE);
    }

    public void shareCLick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Preços de qual posto");

        LayoutInflater inflater = getLayoutInflater();
        View alert = inflater.inflate(R.layout.alert_dialog_gas_station_view, null);

        builder.setView(alert);

        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                EditText nomePosto = alert.findViewById(editTextAlertDialogGasStationId);

                String nomePostoStr = nomePosto.getText().toString();
                String stringEtanol = textInputEditTextEtanol.getText().toString();
                String stringGas = textInputEditTextGas.getText().toString();
                Double valorEtanol = Double.parseDouble(stringEtanol);
                Double valorGas = Double.parseDouble(stringGas);
                Double proporcao = valorEtanol/valorGas*100;

                String mensagem = String.format("Preços no posto '%s'. Etanol R$%.2f. Gasolina R$%.2f. " +
                        "Proporção %.1f%. Melhor usar '%s'.",
                        nomePostoStr, valorEtanol, valorGas, proporcao, proporcao>70 ? "Gasolina" : "Etanol");

                Intent intent =  new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, mensagem);
                intent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(intent, "Compartilhar preços combustivel");
                startActivity(shareIntent);

            }
        });

        builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();


    }
}