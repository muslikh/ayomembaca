package id.my.codemerindu.ayomembacaa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.my.codemerindu.ayomembacaa.Adapter.BacaBukuOnlineAdapter;
import id.my.codemerindu.ayomembacaa.Model.Buku;

public class BacaBukuOnline extends AppCompatActivity  implements SeekBar.OnSeekBarChangeListener{

//

    Button btn1;
    boolean gone = false;
    private BacaBukuOnlineAdapter bacaBukuOnlineAdapter;
    private ProgressBar progressBar;
    private List<Buku> bukuOnlineList;
    private RecyclerView recyclerView;
    String id;
    TextView JudulBaca,Pengarangbaca,halaman,isi;
    ActionBar actionBar;
    RelativeLayout relativeLayout;
    private SeekBar seekBar;
    private TextView Persentase;
    Toolbar toolbarcustom;
    Context context;
    int Brightness;
    RelativeLayout menuBacaFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baca_buku_online);

        id = getIntent().getStringExtra("id_buku");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.custom_toolbar);
         toolbarcustom =  findViewById(R.id.toolbarCustom);



        menuBacaFont = findViewById(R.id.menuBawahBacaOnline);
        menuBacaFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relativeLayout.setVisibility(View.GONE);
            }
        });
//        TextView textView = actionBar.getCustomView().findViewById(R.id.toolbar_title);
//        textView.setText("My Custom Title");

//        Pengarangbaca = findViewById(R.id.pengarangBaca);
//        JudulBaca = findViewById(R.id.judulBaca);
//        halaman = findViewById(R.id.halaman);

        isi = findViewById(R.id.isi);

//        recyclerView = findViewById(R.id.recycleViewbacabuku); //findId recyclerView yg ada pada activity_read_all.xml

//        recyclerView.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
//        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //menset layout manager sebagai LinearLayout(scroll kebawah)
//        bukuOnlineList = new ArrayList<>(); //arraylist untuk menyimpan data mahasiswa


        Persentase = findViewById(R.id.persentase);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);






        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi FAN
        getData(); // pemanggilan fungsi get data

        Toast.makeText(this,id,Toast.LENGTH_LONG).show();
//        progressBar = findViewById(R.id.progressBar);
//        showLoading(true);

//        btn1 = findViewById(R.id.btnhidden);
         relativeLayout = findViewById(R.id.menuBawahBacaOnline);
        LinearLayout ln = (LinearLayout)findViewById(R.id.linearBacaBuku);

        ln.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())



                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(!gone){
//                        btn1.setVisibility(View.GONE);
                        actionBar.hide();
                        relativeLayout.setVisibility(View.GONE);
                        menuBacaFont.setVisibility(View.GONE);
                        gone = true;
                    }else{
//                        btn1.setVisibility(View.VISIBLE);
                        actionBar.show();
                        relativeLayout.setVisibility(View.VISIBLE);
                        gone = false;

                    }


                }
                return true;
            }
        });
    }

    public void getData(){

        AndroidNetworking.get("https://muslikh.my.id/api/ayomembaca/bacabuku.php?id="+id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", "onResponse: " + response); //untuk log pada onresponse
                        // do anything with response
                        {
                            //mengambil data dari JSON array pada read_all.php
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
//
//                                    JudulBaca.setText(data.getString("judul"));
//                                    Pengarangbaca.setText(data.getString("nama"));
//                                    halaman.setText(data.getInt("halaman"));

                                    toolbarcustom.setTitle(data.getString("judul"));
                                    isi.setText(data.getString("isi"));

                                    //adding the product to product list
//                                    bukuOnlineList.add(new Buku(
//                                            data.getInt("id_buku"),
//                                            data.getString("judul"),
//                                            data.getString("nama"),
//                                            data.getString("isi")
//                                    ));
                                }
                                //men inisialisasi adapter RecyclerView yang sudah kita buat seberlumnya
//                                BacaBukuOnlineAdapter adapter = new BacaBukuOnlineAdapter(BacaBukuOnline.this,bukuOnlineList);
//                                recyclerView.setAdapter(adapter); //menset adapter yang akan digunakan pada recyclerView
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } @Override
                    public void onError(ANError error) {
                        Log.d("Pesan gan", "onError: " + error); //untuk log pada onerror
                        // handle error
                    }
                });
    }

//    private void showLoading(Boolean state) {
//        if (state) {
//            progressBar.setVisibility(View.VISIBLE);
//        } else {
//            progressBar.setVisibility(View.GONE);
//        }
//    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
        // Pemberitahuan Bahwa Nilai Pada Progress Telah Berubah

                Settings.System.putInt(this.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,value);
        Persentase.setText(String.valueOf(value));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Pemberitahuan Bahwa User Telah Menyentuh/Mengendalikan Progress Pada SeekBar
        Toast.makeText(this, "Progress Dimulai", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Pemberitahuan Bahwa User Telah Selesai Mengendalikan Progress Pada SeekBar
        Toast.makeText(this, "Progress Berhenti", Toast.LENGTH_SHORT).show();
    }

}

