package br.edu.ifsuldeminas.tarefas.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Task implements Serializable {
    private String id;
    private String description;
    private Boolean active;
    private Date dataChanged;

    public Task (String id, String desc) {
        this.id = id;
        description = desc;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private String getActiveString() {
        return  active ? "Ativa" : "Realizada";

    }

    @Override
    public String toString() {
        String formatted = String.format("%s - %s", this.getDescription(), this.getActiveString());
        return formatted;
    }

    public Date getDataChanged() {
        return dataChanged;
    }

    public void setDataChanged(Date dataChanged) {
        this.dataChanged = Calendar.getInstance().getTime();
    }
}
