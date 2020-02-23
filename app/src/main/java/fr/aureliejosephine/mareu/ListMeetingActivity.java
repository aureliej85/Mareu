package fr.aureliejosephine.mareu;

import android.app.DatePickerDialog;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Meeting;
import fr.aureliejosephine.mareu.services.MeetingService;

public class ListMeetingActivity extends AppCompatActivity {

    @BindView(R.id.my_recycler_view)  public RecyclerView recyclerView;
    @BindView(R.id.fab) public FloatingActionButton fabAddMeeting;

    private MeetingRecyclerViewAdapter adapter;
    public MeetingService meetingService;


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
        adapter.notifyDataSetChanged();
    }

    /**
     * Click on FAB to go to {@link AddMeetingActivity}
     */
    public void goToAddMeeting(View view) {
        Intent intent = new Intent(this, AddMeetingActivity.class);
        startActivity(intent);
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
            case R.id.sort_date:
                sortListByDate();
                return true;
            case R.id.sort_room:
                sortListByRoom();
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
                    Log.e("DatePicker", "goCalendar: " + " " + dateChoosen);
                    filterDate(dateChoosen);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        mDatePickerDialog.show();
    }


    public void filterDate(String date) {
        List<Meeting> filteredDate = new ArrayList<>();

        for (Meeting meet : meetingService.getMeeting()) {
            if(meet.getDate().trim().equals(date.trim())){
                filteredDate.add(meet);
                adapter = new MeetingRecyclerViewAdapter(filteredDate);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                Log.e("filterDate", "Loop");
            } else {
                adapter = new MeetingRecyclerViewAdapter(filteredDate);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

        }

        Log.e("filterDate", "filterDate: " + date);
    }


    public void filterRoom(String room) {
        List<Meeting> filteredRoom = new ArrayList<>();

        for (Meeting meet : meetingService.getMeeting()) {
            if(meet.getRoom().trim().equals(room.trim())){
                filteredRoom.add(meet);
                adapter = new MeetingRecyclerViewAdapter(filteredRoom);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                Log.e("filterRoom", "Loop ");
            } else {
                adapter = new MeetingRecyclerViewAdapter(filteredRoom);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        }

        Log.e("filterRoom", "filterRoom: ");
    }


    public void init(){
        adapter = new MeetingRecyclerViewAdapter(meetingService.getMeeting());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void sortListByRoom() {
        Collections.sort(meetingService.getMeeting(), (o1, o2) -> {
            adapter.notifyDataSetChanged();
            return o1.getRoom().compareTo(o2.getRoom());
        });
    }


    public void sortListByDate() {
        Collections.sort(meetingService.getMeeting(), (o1, o2) -> {
            adapter.notifyDataSetChanged();
            return o1.getHour().compareTo(o2.getHour());
        });
    }






}
