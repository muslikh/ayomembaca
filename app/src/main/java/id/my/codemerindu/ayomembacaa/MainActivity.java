package id.my.codemerindu.ayomembacaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.my.codemerindu.ayomembacaa.Adapter.BukuAdapter;
import id.my.codemerindu.ayomembacaa.Model.Buku;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    private BukuAdapter bukuAdapter;
    private ProgressBar progressBar;
    private List<Buku> bukuList,terpopulerList,CerpenNovelList,SejarahList,DakwahList,UmumList;
    private RecyclerView recyclerView,recycleViewPopuler,recycleViewCerpeenNovel,recycleViewSejarah,
            recycleViewDakwah,recycleViewUmum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView); //findId recyclerView yg ada pada .
        recyclerView.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        bukuList = new ArrayList<>(); //arraylist untuk menyimpan data

        recycleViewPopuler = findViewById(R.id.recycleViewPopuler); //findId recyclerView yg ada pada .
        recycleViewPopuler.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        recycleViewPopuler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        terpopulerList = new ArrayList<>(); //arraylist untuk menyimpan data

        recycleViewCerpeenNovel = findViewById(R.id.recycleViewCerpeenNovel); //findId recyclerView yg ada pada .
        recycleViewCerpeenNovel.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        recycleViewCerpeenNovel.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        CerpenNovelList = new ArrayList<>(); //arraylist untuk menyimpan data

        recycleViewSejarah = findViewById(R.id.recycleViewSejarah); //findId recyclerView yg ada pada .
        recycleViewSejarah.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        recycleViewSejarah.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        SejarahList = new ArrayList<>(); //arraylist untuk menyimpan data

        recycleViewUmum = findViewById(R.id.recycleViewUmum); //findId recyclerView yg ada pada .
        recycleViewUmum.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        recycleViewUmum.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        UmumList = new ArrayList<>(); //arraylist untuk menyimpan data

        recycleViewDakwah = findViewById(R.id.recycleViewDakwah); //findId recyclerView yg ada pada .
        recycleViewDakwah.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        recycleViewDakwah.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        DakwahList = new ArrayList<>(); //arraylist untuk menyimpan data

        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi FAN
        getData(); // pemanggilan fungsi get data
        getDataTerpopuler();
        getDataCerpenNovel();
        getDataDakwah();
        getDataSejarah();
        getDataUmum();
//        progressBar = findViewById(R.id.progressBar);
//        showLoading(true);
    }

    public void getData(){

        AndroidNetworking.get("https://muslikh.my.id/api/ayomembaca/listbuku.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", "onResponse: " + response); //untuk log pada onresponse
                        // do anything with response
                        {

                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
                                    //adding the product to product list
                                    bukuList.add(new Buku(
                                            data.getInt("id_buku"),
                                            data.getString("judul"),
                                            data.getString("nama"),
                                            data.getString("isi")
                                    ));
                                }
                                //men inisialisasi adapter RecyclerView yang sudah kita buat seberlumnya
                                BukuAdapter adapter = new BukuAdapter(MainActivity.this,bukuList);
                                recyclerView.setAdapter(adapter); //menset adapter yang akan digunakan pada recyclerView
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

    public void getDataTerpopuler(){

        AndroidNetworking.get("https://muslikh.my.id/api/ayomembaca/listbuku.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", "onResponse: " + response); //untuk log pada onresponse
                        // do anything with response
                        {

                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
                                    //adding the product to product list
                                    terpopulerList.add(new Buku(
                                            data.getInt("id_buku"),
                                            data.getString("judul"),
                                            data.getString("nama"),
                                            data.getString("isi")
                                    ));
                                }
                                //men inisialisasi adapter RecyclerView yang sudah kita buat seberlumnya
                                BukuAdapter adapter = new BukuAdapter(MainActivity.this,terpopulerList);
                                recycleViewPopuler.setAdapter(adapter); //menset adapter yang akan digunakan pada recyclerView
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

    public void getDataCerpenNovel(){

        AndroidNetworking.get("https://muslikh.my.id/api/ayomembaca/listbuku.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", "onResponse: " + response); //untuk log pada onresponse
                        // do anything with response
                        {

                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
                                    //adding the product to product list
                                    CerpenNovelList.add(new Buku(
                                            data.getInt("id_buku"),
                                            data.getString("judul"),
                                            data.getString("nama"),
                                            data.getString("isi")
                                    ));
                                }
                                //men inisialisasi adapter RecyclerView yang sudah kita buat seberlumnya
                                BukuAdapter adapter = new BukuAdapter(MainActivity.this,CerpenNovelList);
                                recycleViewCerpeenNovel.setAdapter(adapter); //menset adapter yang akan digunakan pada recyclerView
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

    public void getDataSejarah(){

        AndroidNetworking.get("https://muslikh.my.id/api/ayomembaca/listbuku.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", "onResponse: " + response); //untuk log pada onresponse
                        // do anything with response
                        {

                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
                                    //adding the product to product list
                                    SejarahList.add(new Buku(
                                            data.getInt("id_buku"),
                                            data.getString("judul"),
                                            data.getString("nama"),
                                            data.getString("isi")
                                    ));
                                }
                                //men inisialisasi adapter RecyclerView yang sudah kita buat seberlumnya
                                BukuAdapter adapter = new BukuAdapter(MainActivity.this,SejarahList);
                                recycleViewSejarah.setAdapter(adapter); //menset adapter yang akan digunakan pada recyclerView
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

    public void getDataDakwah(){

        AndroidNetworking.get("https://muslikh.my.id/api/ayomembaca/listbuku.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", "onResponse: " + response); //untuk log pada onresponse
                        // do anything with response
                        {

                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
                                    //adding the product to product list
                                    DakwahList.add(new Buku(
                                            data.getInt("id_buku"),
                                            data.getString("judul"),
                                            data.getString("nama"),
                                            data.getString("isi")
                                    ));
                                }
                                //men inisialisasi adapter RecyclerView yang sudah kita buat seberlumnya
                                BukuAdapter adapter = new BukuAdapter(MainActivity.this,DakwahList);
                                recycleViewDakwah.setAdapter(adapter); //menset adapter yang akan digunakan pada recyclerView
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


    public void getDataUmum(){

        AndroidNetworking.get("https://muslikh.my.id/api/ayomembaca/listbuku.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", "onResponse: " + response); //untuk log pada onresponse
                        // do anything with response
                        {

                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
                                    //adding the product to product list
                                    UmumList.add(new Buku(
                                            data.getInt("id_buku"),
                                            data.getString("judul"),
                                            data.getString("nama"),
                                            data.getString("isi")
                                    ));
                                }
                                //men inisialisasi adapter RecyclerView yang sudah kita buat seberlumnya
                                BukuAdapter adapter = new BukuAdapter(MainActivity.this,UmumList);
                                recycleViewUmum.setAdapter(adapter); //menset adapter yang akan digunakan pada recyclerView
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
    public void onBackPressed()
    {

        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            //super.onBackPressed();
            moveTaskToBack(true);
        }else {
            Toast.makeText(getBaseContext(), "Tekan Back Sekali lagi untuk Keluar",
                    Toast.LENGTH_SHORT).show(); }
        mBackPressed = System.currentTimeMillis();
    }

}
