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
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.Arrays;
import java.util.List;

import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Reunion;
import fr.aureliejosephine.mareu.services.ReunionService;


public class ListReunionActivity extends AppCompatActivity {

    ReunionRecyclerViewAdapter adapter;

    private RecyclerView recyclerView;

    public FloatingActionButton mButton;

    private ReunionService mReunionService;

    private ImageView mAvatarReunion;

    private Reunion reunion;

    private SearchView mSearchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reunion);

        mReunionService = DI.getReunionService();

        mSearchView = findViewById(R.id.searchView);
        mButton = findViewById(R.id.fab);
        mAvatarReunion = findViewById(R.id.image_reunion);
        recyclerView = findViewById(R.id.my_recycler_view);

        mSearchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReunionRecyclerViewAdapter(this, mReunionService.getReunion());
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

    }


    public void goToAddReunion(View view) {
        Intent intent = new Intent (this, AddReunionActivity.class);
        startActivity(intent);
    }




}
