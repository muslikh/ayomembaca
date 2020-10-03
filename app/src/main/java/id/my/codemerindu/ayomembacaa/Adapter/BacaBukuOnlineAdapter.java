package id.my.codemerindu.ayomembacaa.Adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.my.codemerindu.ayomembacaa.BacaBukuOnline;
import id.my.codemerindu.ayomembacaa.MainActivity;
import id.my.codemerindu.ayomembacaa.Model.Buku;
import id.my.codemerindu.ayomembacaa.R;

public class BacaBukuOnlineAdapter extends  RecyclerView.Adapter<BacaBukuOnlineAdapter.ViewHolder> {


    Context context;
    private List<Buku> bukuOnlineList; //inisialisasi List dengan object DataMahasiswa

    //construktor ListMahasiswaAdapter
    public BacaBukuOnlineAdapter(BacaBukuOnline bacaBukuAdapter, List<Buku> bukuOnlineList) {
        this.context = bacaBukuAdapter;
        this.bukuOnlineList = bukuOnlineList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view yang akan digunakan yaitu layout list_mahasiswa_row.xml
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.baca_buku_row, parent, false);
        ViewHolder holder = new ViewHolder(v); //inisialisasi ViewHolder
        return holder;
    } //fungsi yang dijalankan saat ViewHolder dibuat

    @Override
    public void onBindViewHolder(@NonNull final BacaBukuOnlineAdapter.ViewHolder holder, final int position) {
        Buku data = bukuOnlineList.get(position); //inisialisasi object
        holder.judul.setText(data.getJudul());
        holder.pengarang.setText("Nama : "+data.getPengarang()); //menset value view "mNim" sesuai data dari getNimMahasiswa();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(con)
////                pesan("AKu Tersentuh");
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return bukuOnlineList.size(); //mengambil item sesuai urutan
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView judul, pengarang; //inisialisasi variabel

        public ViewHolder(View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul); //find layout sesuai dengan yg di list_mahasiswa_row.xml
            pengarang = itemView.findViewById(R.id.pengarang); //find layout sesuai dengan yg di list_mahasiswa_row.xml
        }
    }

    public void pesan(final String isiPesan)
    {  AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert
                .setMessage(isiPesan)
                .setCancelable(false)
                .setNegativeButton("Okke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog kodesalah = alert.create();
        kodesalah.show();
    }
}