package com.example.safiraaini.safira_1202154315_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {
    //mendeklarasikan variabel edittext dan database dari class database
    EditText ToDo, Description, Priority;
    database dtbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        //set title menjadi add to do
        setTitle("Add To Do");

        //menghubungkan dengan edit text pada layout
        ToDo = (EditText) findViewById(R.id.editTodo);
        Description = (EditText) findViewById(R.id.editDesc);
        Priority = (EditText) findViewById(R.id.editPriority);

        //menghubungkan dengan class database
        dtbase = new database(this);
    }

    //apabila tombol back di tekan
    @Override
    public void onBackPressed() {
        //intent yang menghubungkan dari add to do menuju list to do
        Intent intent = new Intent(AddToDo.this, ListToDo.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivitas setelah intent dijalankan
        this.finish();
    }

    //apabila tombol adding to do activity di klik
    public void tambah(View view) {
        //apabila data todoname, deskripsi dan prioritas telah selesau diisi maka akan mengambil text dan mengubahnya menjadi string
        if (dtbase.inputdata(new AddData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //menampilkan toast bahwa data telah berhasil ditambahkan ke dalam list item pada recycle view
            Toast.makeText(this, "To Do Activity added!", Toast.LENGTH_SHORT).show();
            //menghubungkan dari add to do kembali ke class list to do
            startActivity(new Intent(AddToDo.this, ListToDo.class));
            //menutup aktivitas agar tidak kembali ke activity yang dijalankan setelah intent
            this.finish();
        }else{
            //apabila edit text kosong maka akan muncul toast bahwa tidak bisa menambah ke dalam list
            Toast.makeText(AddToDo.this, "Data Adding Failed", Toast.LENGTH_SHORT).show();
            //melakukan set agar semua edit text menjadi kosong
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }

}