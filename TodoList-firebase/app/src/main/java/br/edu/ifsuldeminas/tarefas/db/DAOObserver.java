package br.edu.ifsuldeminas.tarefas.db;

import java.util.List;

import br.edu.ifsuldeminas.tarefas.domain.Task;

public interface DAOObserver {

    default void loadSuccess(List<Task> task) {};
    default void loadFailuere() {};
    default void saveSuccess() {};
    default void saveFailuere() {};
    default void updateSuccess() {};
    default void updateFailuere() {};
    default void deleteSuccess() {};
    default void deleteFailuere() {};
}
