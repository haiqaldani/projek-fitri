package id.ac.pcr.projekku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ac.pcr.projekku.R;
import id.ac.pcr.projekku.model.BeritaModel;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.HolderBerita>{
    private Context ctx;
    private List<BeritaModel> listData; //membuat list dan memasukkan ke dalam array

    public AdapterBerita(Context ctx, List<BeritaModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderBerita onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.berita_adapter, parent, false);
        return new HolderBerita(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBerita holder, int position) {
        BeritaModel bm = listData.get(position);

        holder.tvId.setText(String.valueOf(bm.getId_berita()));
        holder.tvEmail.setText(bm.getPengirim());
        holder.tvSubjek.setText(bm.getHeadline());
        holder.tvIsi.setText(bm.getIsi());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class HolderBerita extends RecyclerView.ViewHolder{
        TextView tvId, tvEmail, tvSubjek, tvIsi;
        CircleImageView tvFoto;

        public HolderBerita(@NonNull View itemView){
            super(itemView);

            this.tvId = itemView.findViewById(R.id.tv_id);
            this.tvFoto = itemView.findViewById(R.id.imgfotoEmail);
            this.tvEmail = itemView.findViewById(R.id.tv_email);
            this.tvSubjek = itemView.findViewById(R.id.tv_headline);
            this.tvIsi = itemView.findViewById(R.id.tv_isi);
        }
    }

//    @NonNull
//    @Override
//    public HolderBerita onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
//        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.berita_adapter,null);
//        return new HolderBerita(view);
//    }

//    @Override
//    public void onBindViewHolder(@NonNull HolderBerita holder, int i) {
//
//        holder.mNama.setText(models.get(i).getNama());
//        holder.mSubjek.setText(models.get(i).getSubjek());
//        holder.mIsi.setText(models.get(i).getIsi());
//        holder.mfoto.setImageResource(models.get(i).getFoto());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return models.size();
//    }
}
