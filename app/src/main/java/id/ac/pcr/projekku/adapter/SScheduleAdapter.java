package id.ac.pcr.projekku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.pcr.projekku.R;
import id.ac.pcr.projekku.model.ScheduleModel;

public class SScheduleAdapter extends RecyclerView.Adapter<SScheduleAdapter.ViewHolder> {

    private Context ctx;
    private List<ScheduleModel.College> ssList;

    public SScheduleAdapter(Context ctx, List<ScheduleModel.College> ssList) {
        this.ctx = ctx;
        this.ssList = ssList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_jadwal_ganti, parent, false);
        return new SScheduleAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SScheduleAdapter.ViewHolder rowViewHolder = (SScheduleAdapter.ViewHolder) holder;

        int rowPos = rowViewHolder.getBindingAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.tvKelas.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvMatkul.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvId.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvJam.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvRuangan.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.tvKelas.setText("Kelas");
            rowViewHolder.tvMatkul.setText("Mata Kuliah");
            rowViewHolder.tvId.setText("ID");
            rowViewHolder.tvJam.setText("Jam Ganti");
            rowViewHolder.tvRuangan.setText("Ruangan");
            rowViewHolder.tvTanggal.setText("Tanggal");
        } else {
            ScheduleModel.College scList = ssList.get(position - 1);

            rowViewHolder.tvKelas.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvMatkul.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvId.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvJam.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvRuangan.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.tvKelas.setText(scList.getKelas() + "");
            rowViewHolder.tvMatkul.setText(scList.getMatkul() + "");
            rowViewHolder.tvId.setText(scList.getId() + "");
            rowViewHolder.tvJam.setText(scList.getJam() + "");
            rowViewHolder.tvRuangan.setText(scList.getRuangan() + "");
            rowViewHolder.tvTanggal.setText(scList.getTanggal() + "");
        }
    }

    @Override
    public int getItemCount() {
        return ssList.size() + 1;
    }

    public void setData(List<ScheduleModel.College> data) {
        ssList.clear();
        ssList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKelas, tvMatkul, tvId, tvJam, tvRuangan, tvTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvJam = itemView.findViewById(R.id.tvJam);
            tvRuangan = itemView.findViewById(R.id.tvRuangan);
            tvKelas = itemView.findViewById(R.id.tvKelas);
            tvMatkul = itemView.findViewById(R.id.tvMatkul);
        }
    }
}
