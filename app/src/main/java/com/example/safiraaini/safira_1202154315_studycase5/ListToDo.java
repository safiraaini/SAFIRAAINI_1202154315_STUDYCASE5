package com.example.safiraaini.safira_1202154315_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ListToDo extends AppCompatActivity {
    //Membuat atau melakukan deklarasi variable yang akan digunakan
    database dtbase;
    RecyclerView rv;
    adapter adapter;
    ArrayList<AddData> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_do); setTitle("To Do List");

        //menghubungkan dengan recycle view yang terdapat pada layout
        rv = findViewById(R.id.recview);
        //membuat araylist baru dengan variabel yang telah dideklarasikan
        datalist = new ArrayList<>();
        //membuat database baru yang tersambung dengan class database
        dtbase = new database(this);
        //memanggil method readdata yang terdapat pada class database
        dtbase.readdata(datalist);

        //melakukan inisiasi shared preferences
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //membuat adapter baru yang tersambung dengan class adapter
        adapter = new adapter(this,datalist, color);
        //melakukan setting ukuran yang tetap atau tidak berubah apabila aplikasi dijalankan
        rv.setHasFixedSize(true);
        //menampilkan layout dengan jenis linear
        rv.setLayoutManager(new LinearLayoutManager(this));
        //menetapkan atau set adapter untuk recycle view
        rv.setAdapter(adapter);

        //melakukan method agar recycle view dapat dihapus apabila di swipe
        hapusswipe();
    }

    //membuat method untuk menghapus item pada to do list dengan cara swipe
    public void hapusswipe(){
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);
                //apabila item pada recycle view di swipe ke arah kiri
                if(direction==ItemTouchHelper.LEFT){
                    //menghapus data utem yang telah dideklarasikan dalam getter pada class AddData
                    if(dtbase.removedata(current.getTodo())){
                        //melakukan penghapusan data
                        adapter.deleteData(position);
                        //membuat snack bar sebagai pemberitahuan bahwa item sudah terhapus dengan durasi 1 detik
                        Snackbar.make(findViewById(R.id.coor), "Data Deleted", 1000).show();
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rv);
    }
    //ketika menu pada class ListTodo dibuar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //memanggil layout menu pada res sebagai menubar
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    //method yang dijalankan apabila suatu item dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item yang
        int id = item.getItemId();
        //apabila item setting dipilih
        if (id==R.id.action_settings){
            //membuat intent baru dan menghubungkan ke class setting
            Intent intent = new Intent(ListToDo.this, settings.class);
            //memulai intent
            startActivity(intent);
            //menutup aktivitas setelah intent dijalankan
            finish();
        }
        return true;
    }

    //apabila floating bar dengan simbol tambah di klik
    public void add(View view) {
        //intent yang menghubungkan dari class list to do ke class add to do
        Intent intent = new Intent(ListToDo.this, AddToDo.class);
        //memulai intent
        startActivity(intent);
    }
}
