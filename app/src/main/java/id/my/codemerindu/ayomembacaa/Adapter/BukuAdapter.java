package id.my.codemerindu.ayomembacaa.Adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.my.codemerindu.ayomembacaa.BacaBukuOnline;
import id.my.codemerindu.ayomembacaa.DetailBuku;
import id.my.codemerindu.ayomembacaa.MainActivity;
import id.my.codemerindu.ayomembacaa.Model.Buku;
import id.my.codemerindu.ayomembacaa.R;

public class BukuAdapter extends  RecyclerView.Adapter<BukuAdapter.ViewHolder> {

    private final int limit = 5;

    Context context;
    private List<Buku> bukuList; //inisialisasi List dengan object DataMahasiswa

    //construktor ListMahasiswaAdapter
    public BukuAdapter(MainActivity bukuAdapter, List<Buku> bukuList) {
        this.context = bukuAdapter;
        this.bukuList = bukuList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TITLE) {

            //inflate view yang akan digunakan yaitu layout list_mahasiswa_row.xml
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buku_row, parent, false);
            ViewHolder holder = new ViewHolder(v); //inisialisasi ViewHolder
            return holder;
        } else if (viewType == LOAD_MORE) {

            //inflate view yang akan digunakan yaitu layout list_mahasiswa_row.xml
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_row, parent, false);
            ViewHolder holder = new ViewHolder(v); //inisialisasi ViewHolder
            return holder;
        } else {
            return null;
        }
    } //fungsi yang dijalankan saat ViewHolder dibuat

    @Override
    public void onBindViewHolder(@NonNull final BukuAdapter.ViewHolder holder, final int position) {

        if(position >= limit) {

        } else {
            Buku data = bukuList.get(position); //inisialisasi object
            final int id = bukuList.get(position).getId_buku();
            holder.judul.setText(data.getJudul());
            holder.pengarang.setText(data.getPengarang()); //menset value view "mNim" sesuai data dari getNimMahasiswa();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailBuku.class);
                    intent.putExtra("id_buku",Integer.toString(id));
                    context.startActivity(intent);
//                pesan("AKu Tersentuh");
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        if(bukuList.size() > limit){
            return limit + 1;
        }
        else
        {
            return bukuList.size();
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView judul, pengarang; //inisialisasi variabel

        public ViewHolder(View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul); //find layout sesuai dengan yg di list_mahasiswa_row.xml
            pengarang = itemView.findViewById(R.id.pengarang); //find layout sesuai dengan yg di list_mahasiswa_row.xml
        }
    }
    private final int TITLE = 0;
    private final int LOAD_MORE = 1;
    @Override
    public int getItemViewType(int position) {
        if (position < getItemCount()) {
            return TITLE;
//            return LOAD_MORE;
        } else {
//            return TITLE;
            return LOAD_MORE;
        }
    }
    private boolean hasLoadButton = true;

    public boolean isHasLoadButton() {
        return hasLoadButton;
    }

    public void setHasLoadButton(boolean hasLoadButton) {
        this.hasLoadButton = hasLoadButton;
        notifyDataSetChanged();
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