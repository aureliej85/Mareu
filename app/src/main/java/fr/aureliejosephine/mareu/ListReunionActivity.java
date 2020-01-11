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
import fr.aureliejosephine.mareu.services.ReunionService;


public class ListReunionActivity extends AppCompatActivity {


    @BindView(R.id.my_recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.fab)
    public FloatingActionButton mButton;
    @BindView(R.id.searchView)
    public SearchView mSearchView;

    ReunionRecyclerViewAdapter adapter;
    private ReunionService mReunionService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reunion);

        mReunionService = DI.getReunionService();

        ButterKnife.bind(this);

        configSearchView();

        configRecyclerView();
    }


    public void configRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReunionRecyclerViewAdapter(this, mReunionService.getReunion());
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));
    }

    public void configSearchView(){
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
    }

    public void goToAddReunion(View view) {
        Intent intent = new Intent (this, AddReunionActivity.class);
        startActivity(intent);
    }


}
