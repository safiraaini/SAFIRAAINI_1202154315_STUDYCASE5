package com.example.safiraaini.safira_1202154315_studycase5;
public class AddData {
    //melakukan deklarasi variable yang akan digunakan
    String todo, desc, prior;

    //membuat konstruktor dengan variabel yang telah dideklarasikan
    public AddData(String todo, String desc, String prior){
        this.todo=todo;
        this.desc=desc;
        this.prior=prior;
    }

    //menentukan method setter dan getter untuk konstruktor dari variabel yang telah dideklarasikan
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
