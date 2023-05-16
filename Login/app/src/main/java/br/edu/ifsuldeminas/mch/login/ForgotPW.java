package br.edu.ifsuldeminas.mch.login;

import android.view.View;
import android.widget.Toast;

public class ForgotPW implements View.OnClickListener {
    @Override
    public void onClick(View view){
        Toast toast = Toast.makeText(view.getContext(),
                R.string.button_forgor_clicado,
                Toast.LENGTH_SHORT);

        toast.show();
    }

}
