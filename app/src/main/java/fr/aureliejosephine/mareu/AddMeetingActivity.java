package fr.aureliejosephine.mareu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Meeting;
import fr.aureliejosephine.mareu.services.MeetingService;
import android.content.Context;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.subjectEdit) public EditText subjectEditText;
    @BindView(R.id.emailEdit1) public EditText emailsParticipants;
    @BindView(R.id.spinner) public Spinner roomSpinner;
    @BindView(R.id.spinnerHeure) public Spinner hourSpinner;
    @BindView(R.id.addParticipant1) public ImageView addParticipantButton;
    @BindView(R.id.dateTextView) public TextView dateTextView;
    @BindView(R.id.listView) public ListView emailListView;

    private MeetingService mMeetingService;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Context context = AddMeetingActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_meeting);
        mMeetingService = DI.getReunionService();
        ButterKnife.bind(this);

        configSpinnerRoom();
        configSpinnerHour();
        configDateTextView();

        //ADAPTER
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        emailListView.setAdapter(adapter);

        //LISTENER
        emailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(AddMeetingActivity.this, context.getString(R.string.email_supprimé), Toast.LENGTH_LONG).show();
            }
        });

    }

    @OnClick(R.id.saveButton)
    public void saveTheMeeting() {
        String subjectMeeting = subjectEditText.getText().toString();
        String roomMeeting = roomSpinner.getSelectedItem().toString();
        String dateMeeting = dateTextView.getText().toString();
        String hourMeeting = hourSpinner.getSelectedItem().toString();
        int avatar = R.drawable.indigo_lens_24dp;

        switch(roomMeeting){
            case "Indigo":
                avatar = R.drawable.indigo_lens_24dp;
                break;
            case "Fuscia":
                avatar = R.drawable.fuschia_lens_24dp;
                break;
            case "Sépia":
                avatar = R.drawable.sepia_lens_24dp;
                break;
            case "Corail":
                avatar = R.drawable.corail_lens_4dp;
                break;
            case "Rubis":
                avatar = R.drawable.rubis_lens_24dp;
                break;
            case "Jade":
                avatar = R.drawable.jade_lens_24dp;
                break;
            case "Mauve":
                avatar = R.drawable.mauve_lens_24dp;
                break;
            case "Topaze":
                avatar = R.drawable.topaze_lens_24dp;
                break;
            case "Grège":
                avatar = R.drawable.grege_lens_24dp;
                break;
            case "Noire":
                avatar = R.drawable.ic_lens_black_24dp;
                break;
        }

        Meeting meeting = new Meeting(roomMeeting, subjectMeeting, dateMeeting, hourMeeting, avatar);
        ArrayList<String> listEmails = new ArrayList<String>(list);
        meeting.setAddEmail(listEmails);

        mMeetingService.addMeeting(meeting);

        Intent intent = new Intent(AddMeetingActivity.this, ListMeetingActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.dateTextView)
    public void goCalendar() {
        //Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(Locale.FRANCE);
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeetingActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateTextView.setText(day + "/" + (month + 1) +"/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void configDateTextView(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        dateTextView.setText(date);
    }

    public void configSpinnerRoom(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.liste_salles, R.layout.spinner_item_meeting);
        adapter.setDropDownViewResource(R.layout.spinner_item_meeting);
        roomSpinner.setAdapter(adapter);
        roomSpinner.setOnItemSelectedListener(this);
    }

    public void configSpinnerHour(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.horaires, R.layout.spinner_item_meeting);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(adapter);
        hourSpinner.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.addParticipant1)
    public void addEmail() {
        if (isEmailValid(emailsParticipants.getText().toString())) {
            list.add(emailsParticipants.getText().toString());
            adapter.notifyDataSetChanged();
            emailsParticipants.getText().clear();
            emailsParticipants.setBackground(null);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.emailErreur), Toast.LENGTH_SHORT);
            toast.show();
            borderEditText();
        }
    }

    @OnClick(R.id.closeIv)
    public void closeWindow(){
        Intent intent = new Intent(AddMeetingActivity.this, ListMeetingActivity.class);
        startActivity(intent);
    }

    public void borderEditText(){
        GradientDrawable gd = new GradientDrawable();
        //gd.setColor(Color.parseColor(getString(R.color.colorAccent2)));
        gd.setColor(ContextCompat.getColor(AddMeetingActivity.this, R.color.colorAccent2));
        gd.setStroke(2,Color.RED);
        emailsParticipants.setBackground(gd);
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onItemSelected (AdapterView < ? > adapterView, View view,int i, long l){

    }

    @Override
    public void onNothingSelected (AdapterView < ? > adapterView){

    }

}
