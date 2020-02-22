package fr.aureliejosephine.mareu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Meeting;
import fr.aureliejosephine.mareu.services.MeetingService;

public class ListMeetingActivity extends AppCompatActivity {

    @BindView(R.id.my_recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.fab)
    public FloatingActionButton fabAddMeeting;

    private MeetingRecyclerViewAdapter adapter;
    public MeetingService meetingService;

    public List<Meeting> filteredDate = new ArrayList<>();
    public List<Meeting> filteredRoom = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        meetingService = DI.getMeetingService();
        ButterKnife.bind(this);
        configRecyclerView();



    }

    public void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingRecyclerViewAdapter(meetingService.getMeeting());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));
    }

    /**
     * Click on FAB to go to {@link AddMeetingActivity}
     */
    public void goToAddMeeting(View view) {
        Intent intent = new Intent(this, AddMeetingActivity.class);
        startActivity(intent);
    }

    public void sortListByRoom() {
        Collections.sort(meetingService.getMeeting(), (o1, o2) -> {
            adapter.notifyDataSetChanged();
            return o1.getRoom().compareTo(o2.getRoom());
        });
    }

    public void sortListByTime() {
        Collections.sort(meetingService.getMeeting(), (o1, o2) -> {
            adapter.notifyDataSetChanged();
            return o1.getHour().compareTo(o2.getHour());
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_filter:
                init();
                return true;
            case R.id.filter_match_date:
                goCalendar();
                return true;
            case R.id.filter_match_room:
                sortListByRoom();
                return true;
            case R.id.reunion1:
                filterRoom("Réunion A");
                return true;
            case R.id.reunion2:
                filterRoom("Réunion B");
                return true;
            case R.id.reunion3:
                filterRoom("Réunion C");
                return true;
            case R.id.reunion4:
                filterRoom("Réunion D");
                return true;
            case R.id.reunion5:
                filterRoom("Réunion E");
                return true;
            case R.id.reunion6:
                filterRoom("Réunion F");
                return true;
            case R.id.reunion7:
                filterRoom("Réunion G");
                return true;
            case R.id.reunion8:
                filterRoom("Réunion H");
                return true;
            case R.id.reunion9:
                filterRoom("Réunion I");
                return true;
            case R.id.reunion10:
                filterRoom("Réunion J");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void goCalendar() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog mDatePickerDialog;

        mDatePickerDialog = new DatePickerDialog(ListMeetingActivity.this,
                (view, year, month, dayOfMonth) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(dayOfMonth, month, year);

                    String dateChoosen = dayOfMonth + "/" + (month + 1) +"/" + year;
                    Log.e("TAG: whichDate", "goCalendar: " + " " + dateChoosen);
                    filterDate(dateChoosen);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        mDatePickerDialog.show();
    }


    public void filterDate(String date) {

        for (Meeting meet : meetingService.getMeeting()) {
            if(meet.getDate() == date){
                filteredDate.add(meet);
                adapter = new MeetingRecyclerViewAdapter(filteredDate);
                recyclerView.setAdapter(adapter);
            }
        }

        Log.e("filterDate", "filterDate: " + date);
    }



    public void filterRoom(String room) {

        for (Meeting meet : meetingService.getMeeting()) {
            if(meet.getRoom() == room){
                filteredRoom.add(meet);
                adapter = new MeetingRecyclerViewAdapter(filteredRoom);
                recyclerView.setAdapter(adapter);
            }
        }

        Log.e("filterRoom", "filterRoom: " + room);
    }


    public void init(){
        adapter = new MeetingRecyclerViewAdapter(meetingService.getMeeting());
        recyclerView.setAdapter(adapter);
    }





}
