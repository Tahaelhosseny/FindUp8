package khaled.example.com.findup.UI.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Activities.CreateEventPresenter;
import khaled.example.com.findup.UI.ViewModel.Activites.CreateEventViewModel;
import khaled.example.com.findup.databinding.ActivityCreateEventBinding;

public class CreateEventActivity extends AppCompatActivity {

    private TextView start_result_txt , end_result_txt , day_start_txt , day_end_txt;
    static final int DIALOG_ID = 0;
    int hour_text , minute_text;
    int status = 0;
    int date_status = 0;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    ActivityCreateEventBinding activityCreateEventBinding;
    CreateEventViewModel createEventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createEventViewModel = new CreateEventViewModel(this);
        activityCreateEventBinding= DataBindingUtil.setContentView(this,R.layout.activity_create_event);
        start_result_txt = findViewById(R.id.start_at_txt_time);
        end_result_txt = findViewById(R.id.end_at_txt_time);
        day_start_txt = findViewById(R.id.txt_day_work_start);
        day_end_txt = findViewById(R.id.txt_day_work_end);
        Button btn_submit = findViewById(R.id.btn_submit);
        Button btn_createEventBack=findViewById(R.id.btn_createEventBack);
        activityCreateEventBinding.setCreateStoreEvents(createEventViewModel);
        activityCreateEventBinding.setPresenter(new CreateEventPresenter() {
            @Override
            public void addNewStoreEvent() {
                Toast.makeText(CreateEventActivity.this, "To View Model Method that add this event", Toast.LENGTH_SHORT).show();
//                createEventViewModel.addNewEvent();
            }
        });
        start_result_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = 1;
                showTimeDialog();
            }
        });
        end_result_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = 2;
                showTimeDialog();
            }
        });
        day_start_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date_status = 1;
                pickDate();

            }
        });
        day_end_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date_status = 2;
                pickDate();
            }
        });
        btn_createEventBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEventActivity.super.onBackPressed();
                finish();
            }
        });

    }
    private void pickDate(){
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(CreateEventActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                Date date = new Date(mYear, mMonth, mDay-1);
                String dayOfWeek = simpledateformat.format(date);
                if(date_status == 1){
                    day_start_txt.setText(dayOfWeek);
                }else{
                    day_end_txt.setText(dayOfWeek);
                }
            }
        },day , month , year
        );
        datePickerDialog.show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void  showTimeDialog(){
        showDialog(DIALOG_ID);
        }
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID){
            return new TimePickerDialog(CreateEventActivity.this ,timePickerListener , hour_text , minute_text , true);
        }
        return null;
    }
    protected TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            hour_text = hour; minute_text = minute;
            if(status == 1){
                start_result_txt.setText(hour_text+":"+minute_text);
            }else{
                end_result_txt.setText(hour_text+":"+minute_text);
            }
        }
    };

}
