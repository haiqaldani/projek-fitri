package id.ac.pcr.projekku;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import id.ac.pcr.projekku.api.APIRequestData;
import id.ac.pcr.projekku.api.RetroServer;
import id.ac.pcr.projekku.databinding.ActivityTambahEventBinding;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahEvent extends AppCompatActivity {

    private ActivityTambahEventBinding binding;
    private ProgressDialog progressDialog;
    private Calendar date, date1;
    private String dateStart, dateEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Long calendarId = getCalendarId(this);
        Log.d("TambahEvent", "onCreate: " + calendarId);

        binding.button1.setOnClickListener(v -> {
            setProgressLoading();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, 200);
            } else {
                if (calendarId != null) {
                    syncEvent(calendarId, binding.etTitle.getText().toString(), date.getTime(), date1.getTime(), binding.etDesc.getText().toString(), binding.etLocation.getText().toString(), Integer.parseInt(binding.etNotif.getText().toString()));
                    addEvent(binding.etTitle.getText().toString(), binding.etDesc.getText().toString(), binding.etLocation.getText().toString(), dateStart, dateEnd);
                }
            }

        });

    }

    private void addEvent(String judul, String deskripsi, String lokasi, String waktu_mulai, String waktu_selesai) {
//        EventResponseModel model = new EventResponseModel(judul, deskripsi, lokasi, waktu_mulai, waktu_selesai);

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseBody> call = apiRequestData.createEvent(judul, deskripsi, lokasi, waktu_mulai, waktu_selesai);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    Toast.makeText(TambahEvent.this, "Event berhasil ditambahkan!", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                Toast.makeText(TambahEvent.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }

    private void setProgressLoading() {
        progressDialog = new ProgressDialog(TambahEvent.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setTitle("ProgressDialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    private void syncEvent(long id, String eventName,
                          Date sTime, Date eTime, String description, String location, int minuteNotif) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT-1"));
        Date dt = sTime;
        Date dt1 = eTime;

        Log.d("TambahEvent", "syncEvent: " + dt);
        Calendar beginTime = Calendar.getInstance();
        cal.setTime(dt);
        Log.d("TambahEvent", "syncEvent: " + cal.get(Calendar.HOUR_OF_DAY));

        beginTime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE));

        Calendar endTime = Calendar.getInstance();
        cal.setTime(dt1);

        endTime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE));

        ContentResolver cr = this.getContentResolver();
        ContentValues values = new ContentValues();

        values.put(CalendarContract.Events.DTSTART, beginTime.getTimeInMillis());
        values.put(CalendarContract.Events.DTEND, endTime.getTimeInMillis());
        values.put(CalendarContract.Events.TITLE, eventName);
        values.put(CalendarContract.Events.DESCRIPTION, description);
        values.put(CalendarContract.Events.EVENT_LOCATION, location);
        values.put(CalendarContract.Events.CALENDAR_ID, id);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
        long eventID = Long.parseLong(uri.getLastPathSegment());

        ContentValues reminder = new ContentValues();
        reminder.put(CalendarContract.Reminders.EVENT_ID, eventID);
        reminder.put(CalendarContract.Reminders.MINUTES, minuteNotif);
        reminder.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, reminder);
    }


    private Long getCalendarId(Context context) {
        String[] projection = new String[]{"_id", "calendar_displayName"};
        Cursor calCursor = context.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, projection, "visible = 1 AND isPrimary=1", (String[]) null, "_id ASC");
        if (calCursor != null && calCursor.getCount() <= 0) {
            calCursor = context.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, projection, "visible = 1", (String[]) null, "_id ASC");
        }

        if (calCursor != null && calCursor.moveToFirst()) {
            String calName = null;
            String calID = null;
            int nameCol = calCursor.getColumnIndex(projection[1]);
            int idCol = calCursor.getColumnIndex(projection[0]);

            String var10000 = calCursor.getString(nameCol);
            calName = var10000;
            var10000 = calCursor.getString(idCol);
            calID = var10000;

            Log.d("TambahEvent", "Calendar name = " + calName + " Calendar ID = " + calID);
            calCursor.close();

            return Long.parseLong(calID);
        } else {
            return null;
        }
    }

    private void showDateTimePickerStart() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            date.set(year, monthOfYear, dayOfMonth);
            new TimePickerDialog(TambahEvent.this, (view1, hourOfDay, minute) -> {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);

                binding.etStartDate.setText(date.getTime().toString());
                Log.d("TambahEvent", "The choosen one " + date.getTime());

                // Parse the date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateStart = sdf.format(date.getTime());

                Log.d("TambahEvent", "onCreate: "+dateStart);
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    private void showDateTimePickerEnd() {
        final Calendar currentDate1 = Calendar.getInstance();
        date1 = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            date1.set(year, monthOfYear, dayOfMonth);
            new TimePickerDialog(TambahEvent.this, (view1, hourOfDay, minute) -> {
                date1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date1.set(Calendar.MINUTE, minute);

                binding.etEndDate.setText(date1.getTime().toString());
                Log.d("TambahEvent", "The choosen one " + date1.getTime());

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateEnd = sdf1.format(date1.getTime());
            }, currentDate1.get(Calendar.HOUR_OF_DAY), currentDate1.get(Calendar.MINUTE), false).show();
        }, currentDate1.get(Calendar.YEAR), currentDate1.get(Calendar.MONTH), currentDate1.get(Calendar.DATE)).show();

    }

    public void showDateStart(View view) {
        showDateTimePickerStart();
    }

    public void showDateEnd(View view) {
        showDateTimePickerEnd();
    }
}