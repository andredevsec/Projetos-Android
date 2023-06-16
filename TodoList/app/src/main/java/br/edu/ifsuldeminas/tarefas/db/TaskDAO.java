package br.edu.ifsuldeminas.tarefas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.tarefas.domain.Task;

public class TaskDAO extends DAO {

    public TaskDAO(Context context) {
        super(context);
    }

    public boolean save(Task task) {
        SQLiteDatabase db = openToWrite();

        ContentValues values = new ContentValues();
        values.put("description", task.getDescription());
        values.put("active", task.getActive());

        db.insert("task", null, values);
        db.close();

        return true;
        //
    }

    public void update(Task task ){

    }
    public void delete(Task task ){

    }
    public List<Task> listAll () {
        SQLiteDatabase db = openToRead();
        List<Task> tasks = new ArrayList<>();

        String sql = "SELECT * FROM tasks"; // se tivesse outros campos como WHERE teria que passar na linha de baixo também

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getColumnIndexOrThrow("id");
            String desc = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            String activeSTR = cursor.getString(cursor.getColumnIndexOrThrow("active"));
            Boolean active = Boolean.getBoolean(activeSTR);

            Task task = new Task(id, desc, active);
            tasks.add(task);
        }

        cursor.moveToNext();
        db.close();

        return tasks;
    }

}
