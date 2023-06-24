package br.edu.ifsuldeminas.tarefas.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsuldeminas.tarefas.domain.Task;

public class TaskDAO {
    private DAOObserver observer;

    private static final String COLLECTION = "tasks";
    private static final String DESC = "description";
    private static final String ACT = "active";
    private static final String DT_CHANGE = "date_changed";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public TaskDAO(DAOObserver observer) {
        this.observer=observer;
    }

    public void loadTasks() {
        List<Task> tasks = new ArrayList<>();

        db.collection(COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(com.google.android.gms.tasks.Task<QuerySnapshot> taskFire) {

                if(taskFire.isSuccessful()){
                    for(QueryDocumentSnapshot doc : taskFire.getResult()){
                        String id = doc.getId();
                        String des = doc.get(DESC, String.class);
                        Boolean act = doc.get(ACT, Boolean.class);


                        Task task = new Task(id, des);
                        task.setActive(act);
                    }
                    observer.loadSuccess(tasks);
                }
            }
        });
    }

    public boolean save(Task task) {
        task.getDataChanged();

        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put(DESC, task.getDescription());
        taskMap.put(ACT, task.getDescription());
        taskMap.put(DT_CHANGE, task.getDescription());

        db.collection(COLLECTION).add(taskMap);
        return true;

        //
    }

    public void update(Task task ){
        task.getDataChanged();

        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put(DESC, task.getDescription());
        taskMap.put(ACT, task.getDescription());
        taskMap.put(DT_CHANGE, task.getDescription());

        db.collection(COLLECTION)// Recuperando coleção
                .document(task.getId()// recupera a task
                .toString()).update(taskMap); //  atualiza
    }
    public void delete(Task task ){
        db.collection(COLLECTION)
                .document(task.getId().toString())
                .delete();

    }
    public List<Task> listAll () {
        return null;
        }


}
