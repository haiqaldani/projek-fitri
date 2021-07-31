package id.ac.pcr.projekku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import id.ac.pcr.projekku.R;
import id.ac.pcr.projekku.model.ScheduleModel;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder> {

    private Context ctx;
    private List<ScheduleModel.College> scheduleList;

    public DosenAdapter(Context ctx, List<ScheduleModel.College> scheduleList) {
        this.ctx = ctx;
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list_dosen, parent, false);
        return new DosenAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DosenAdapter.ViewHolder rowViewHolder = (DosenAdapter.ViewHolder) holder;

        int rowPos = rowViewHolder.getBindingAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.tvKelas.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvMatkul.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvId.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvJam.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvRuangan.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvHari.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.tvKelas.setText("Kelas");
            rowViewHolder.tvMatkul.setText("Mata Kuliah");
            rowViewHolder.tvId.setText("ID");
            rowViewHolder.tvJam.setText("Jam");
            rowViewHolder.tvRuangan.setText("Ruangan");
            rowViewHolder.tvHari.setText("Hari");
        } else {
            ScheduleModel.College scList = scheduleList.get(position-1);

            rowViewHolder.tvKelas.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvMatkul.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvId.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvJam.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvRuangan.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvHari.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.tvKelas.setText(scList.getKelas() + "");
            rowViewHolder.tvMatkul.setText(scList.getMatkul() + "");
            rowViewHolder.tvId.setText(scList.getId() + "");
            rowViewHolder.tvJam.setText(scList.getJam() + "");
            rowViewHolder.tvRuangan.setText(scList.getRuangan() + "");
            rowViewHolder.tvHari.setText(scList.getHari() +"");
        }
    }

    @Override
    public int getItemCount() {
        return scheduleList.size() + 1;
    }

    public void setData(List<ScheduleModel.College> data) {
        scheduleList.clear();
        scheduleList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKelas, tvMatkul, tvId, tvJam, tvRuangan, tvHari;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvHari = itemView.findViewById(R.id.tvHari);
            tvJam = itemView.findViewById(R.id.tvJam);
            tvRuangan = itemView.findViewById(R.id.tvRuangan);
            tvKelas = itemView.findViewById(R.id.tvKelas);
            tvMatkul = itemView.findViewById(R.id.tvMatkul);
        }
    }
}
