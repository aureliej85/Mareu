package fr.aureliejosephine.mareu;

import android.content.Intent;
import android.media.Image;
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
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Meeting;
import fr.aureliejosephine.mareu.services.MeetingService;


public class ListMeetingActivity extends AppCompatActivity {


    @BindView(R.id.my_recycler_view) public RecyclerView recyclerView;
    @BindView(R.id.fab) public FloatingActionButton fabAddMeeting;
    //@BindView(R.id.filterPic) public Image filterPic;

    private MeetingRecyclerViewAdapter adapter;
    public MeetingService meetingService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        meetingService = DI.getMeetingService();
        ButterKnife.bind(this);
        configRecyclerView();
        //configFilter();

    }

    public void configRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingRecyclerViewAdapter(this, meetingService.getMeeting());
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));
    }


    /*@OnClick
    public void configFilter(){

    }*/


    /**
     * Click on FAB to go to {@link AddMeetingActivity}
     */
    public void goToAddMeeting(View view) {
        Intent intent = new Intent (this, AddMeetingActivity.class);
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
            case R.id.filter:
                return true;
            case R.id.filter_raz: //init();
                return true;
            case R.id.filter_match_date: //performDateFilter(MATCH);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /*private void init() {
        Log.d("TAG", "init");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingRecyclerViewAdapter(this, meetingService);
        recyclerView.setAdapter(adapter);
    }

    private void init(Calendar date, MeetingService.DateFilter filterType) {
        Log.d("TAG", "init with date");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingRecyclerViewAdapter(this, meetingService, date, filterType);
        recyclerView.setAdapter(adapter);
    }*/
}
