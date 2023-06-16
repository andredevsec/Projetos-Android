package br.edu.ifsuldeminas.tarefas.domain;

public class Task {
    private Integer id;
    private String description;
    private Boolean active;

    public Task (Integer id, String desc, Boolean active) {
        this.id = id;
        description = desc;
        this.active = active;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
