package com.example.safiraaini.safira_1202154315_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class settings extends AppCompatActivity {
//deklarasi variabel yang akan digunakan
    TextView shapeclr;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Pengaturan"); //set judul

        //membuat alert dialog baru dengan variabel alert
        alert = new AlertDialog.Builder(this);

        //melakukan inisiasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        spe = sharedP.edit(); //mengambil int dari res color
        colorid = sharedP.getInt("Colourground", R.color.white);
        //menghubungkan dengan textview yang terdapat pada layout activity_settings
        shapeclr = findViewById(R.id.shapecolor);
        //set text  shape color sesuai dengan id color yang terpilih
        shapeclr.setText(getShapeColor(colorid));
    }

    //apabila tombol back di tekan pada smartphone
    @Override
    public void onBackPressed() {
        //intent baru menghubungkan dengan class ListToDo
        Intent intent = new Intent(settings.this, ListToDo.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivity setelah intent di jalanlan
        finish();
    }

    //method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

    //mendapatkan string warna pada res color
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //mengembalian id dari warna yang akan dipilih
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    //apabila linear layout pada layout activity settings dipilih
    public void choosecolor(View view) {
        //set title dari alert dialog menjadi Shape Color
        alert.setTitle("Shape Color");
        //membuat view baru dengan mendapatkan layout inflater
        View view1 = getLayoutInflater().inflate(R.layout.colorsettings, null);
        //menampilkan view yang telah dibuat
        alert.setView(view1);

        //menghubungkan dengan radio button
        final RadioGroup radG = view1.findViewById(R.id.radiocolor);
        radG.check(getColorid(colorid));

        //ketika tombol OK pada alert ditekan
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.green:
                        colorid = R.color.green;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                }
                //melakukan set shape color menjadi color id yang dipilih
                shapeclr.setText(getShapeColor(colorid));
                //meletakkan shared preference
                spe.putInt("Colourground", colorid);
                //melakukan commit shared preference
                spe.commit();
            }
        });

        //ketika tombol Cancel pada alert dialog ditekan
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //membuat dan menampilkan alert dialog
        alert.create().show();
    }
}
