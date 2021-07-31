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

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private Context ctx;
    private List<ScheduleModel.College> scheduleList;

    public ScheduleAdapter(Context ctx, List<ScheduleModel.College> scheduleList) {
        this.ctx = ctx;
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list_item, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder rowViewHolder = (ViewHolder) holder;

        int rowPos = rowViewHolder.getBindingAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.tvKelas.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvMatkul.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvDosen.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvJam.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvRuangan.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvSks.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.tvSemester.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.tvKelas.setText("Kelas");
            rowViewHolder.tvMatkul.setText("Mata Kuliah");
            rowViewHolder.tvDosen.setText("Dosen");
            rowViewHolder.tvJam.setText("Jam");
            rowViewHolder.tvRuangan.setText("Ruangan");
            rowViewHolder.tvSks.setText("SKS");
            rowViewHolder.tvSemester.setText("Semester");
        } else {
            ScheduleModel.College scList = scheduleList.get(position-1);

            rowViewHolder.tvKelas.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvMatkul.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvDosen.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvJam.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvRuangan.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvSks.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.tvSemester.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.tvKelas.setText(scList.getKelas() + "");
            rowViewHolder.tvMatkul.setText(scList.getMatkul() + "");
            rowViewHolder.tvDosen.setText(scList.getDosen() + "");
            rowViewHolder.tvJam.setText(scList.getJam() + "");
            rowViewHolder.tvRuangan.setText(scList.getRuangan() + "");
            rowViewHolder.tvSks.setText(scList.getSks() +"");
            rowViewHolder.tvSemester.setText(scList.getSemester() + "");
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKelas, tvMatkul, tvDosen, tvJam, tvRuangan, tvSks, tvSemester;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvKelas = itemView.findViewById(R.id.tvKelas);
            tvMatkul = itemView.findViewById(R.id.tvMatkul);
            tvDosen = itemView.findViewById(R.id.tvDosen);
            tvJam = itemView.findViewById(R.id.tvJam);
            tvRuangan = itemView.findViewById(R.id.tvRuangan);
            tvSks = itemView.findViewById(R.id.tvSks);
            tvSemester = itemView.findViewById(R.id.tvSemester);
        }
    }
}
