package fr.aureliejosephine.mareu;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.services.MeetingService;


public class ListMeetingActivity extends AppCompatActivity {


    @BindView(R.id.my_recycler_view) public RecyclerView recyclerView;
    @BindView(R.id.fab) public FloatingActionButton fabAddMeeting;
    @BindView(R.id.searchView) public SearchView searchViewFilter;

    private MeetingRecyclerViewAdapter adapter;
    private MeetingService meetingService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        meetingService = DI.getMeetingService();
        ButterKnife.bind(this);
        configRecyclerView();
        configSearchView();

    }

    public void configRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingRecyclerViewAdapter(this, meetingService.getMeeting());
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));
    }

    public void configSearchView(){
        searchViewFilter.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchViewFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }


    /**
     * Click on FAB to go to {@link AddMeetingActivity}
     */
    public void goToAddMeeting(View view) {
        Intent intent = new Intent (this, AddMeetingActivity.class);
        startActivity(intent);
    }


}
