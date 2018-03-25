package com.example.safiraaini.safira_1202154315_studycase5;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class adapter extends RecyclerView.Adapter<adapter.holder> {
    //melakukan deklarasi variable yang akan digunakan
    private Context cntx;
    private List<AddData> list;
    int color;

    //membuat konstruktor terhadap variabel yang telah dideklarasikan
    public adapter(Context cntx, List<AddData> list, int color){
        this.cntx=cntx;
        this.list=list;
        this.color=color;
    }

    //menentukan viewholder pada recycle view dengan parent cardview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baruberdasarkan layout cardview_list
        View view = LayoutInflater.from(cntx).inflate(R.layout.cardview_list, parent, false);
        holder hldr = new holder(view); //menentukan holde baru dan mengembalikannya
        return hldr;
    }

    //melakukan setting nilai dan posisi pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        AddData data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Description.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cardv.setCardBackgroundColor(cntx.getResources().getColor(this.color));
    }

    //mendapatkan item count atau jumlah dari lisu
    @Override
    public int getItemCount() {
        return list.size();
    }

    //mendapatkan list dari adapter dan menemoatkan sesuai dengan posisinya
    public AddData getData(int position){
        return list.get(position);
    }

    //menghapus data pada list
    public void deleteData(int i){
        //remove item yang dipilih untuk dihapus
        list.remove(i);
        //menampilkan notifikasi item yang di hapus
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }
//membuat class holder untuk recycle view
    class holder extends RecyclerView.ViewHolder{
        //membuat deklarasi variable yang digunakan
        public TextView ToDo, Description, Priority;
        public CardView cardv;
        public holder(View itemView){
            super(itemView);

            //menghubungkan dengan id yang terdapat pada layout cardview_list
            ToDo = itemView.findViewById(R.id.headline);
            Description = itemView.findViewById(R.id.explanation);
            Priority = itemView.findViewById(R.id.number);
            cardv = itemView.findViewById(R.id.cardlist);
        }
    }
}
