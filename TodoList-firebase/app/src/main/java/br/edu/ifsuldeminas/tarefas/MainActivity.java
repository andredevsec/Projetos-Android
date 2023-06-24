package br.edu.ifsuldeminas.tarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.ifsuldeminas.tarefas.db.DAOObserver;
import br.edu.ifsuldeminas.tarefas.db.TaskDAO;
import br.edu.ifsuldeminas.tarefas.domain.Task;

public class MainActivity extends AppCompatActivity implements DAOObserver {

    private ListView todoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class); // abrir a partir da mainActivity,  a formActivity
                startActivity(intent); // passar a intent que queremos criar
            }
        });

        todoList = findViewById(R.id.todo_list);
        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Task task = (Task) todoList.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, FormActivity.class);

                intent.putExtra("task-to-edit", task);
                startActivity(intent);

            }
        });

        registerForContextMenu(todoList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem menuItemRemover = menu.add("Remover");
        MenuItem menuItemAtivar = menu.add("Ativar/Desativar");

        menuItemRemover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo adapterView = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Task task = (Task) todoList.getItemAtPosition(adapterView.position);
                TaskDAO dao = new TaskDAO(MainActivity.this);
                dao.delete(task);

                loadTasks();

                Toast.makeText(MainActivity.this, "Tarefa excluída com sucesso", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        menuItemAtivar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo adapterView = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Task task = (Task) todoList.getItemAtPosition(adapterView.position);
                task.setActive(false);
                TaskDAO dao = new TaskDAO(MainActivity.this);
                dao.update(task);

                loadTasks();

                Toast.makeText(MainActivity.this, "Tarefa desativada com sucesso", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //loadTasks();
    }

    private void updateTaskList() {
        TaskDAO taskDAO = new TaskDAO(this);
        taskDAO.loadTasks();
    }

    @Override
    public void loadSuccess(List<Task> task) {

        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, task);

        todoList.setAdapter(adapter);
    }

    private void loadTasks () {
        TaskDAO dao = new TaskDAO(MainActivity.this);

        List<Task> tasks = dao.listAll();


    }
}