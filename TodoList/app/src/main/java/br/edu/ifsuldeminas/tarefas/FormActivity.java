package br.edu.ifsuldeminas.tarefas;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import br.edu.ifsuldeminas.tarefas.db.TaskDAO;
import br.edu.ifsuldeminas.tarefas.domain.Task;

public class FormActivity extends AppCompatActivity {

    private TextInputEditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        description = findViewById(R.id.task_description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String desc = description.getText().toString();

        if(desc.equals("")) {
            Toast.makeText(FormActivity.this, "Descrição nao pode ser vazia", Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(item);
        } else {
            Task task = new Task (null, desc, true);
            TaskDAO dao = new TaskDAO(FormActivity.this);
            dao.save(task);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}