package fr.aureliejosephine.mareu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

    public MeetingRecyclerViewAdapter meetingRecyclerViewAdapter;

    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private Context context = AddMeetingActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        mMeetingService = DI.getMeetingService();
        ButterKnife.bind(this);

        configSpinnerRoom();
        configSpinnerHour();
        configDateTextView();
        configListView();
        meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(mMeetingService.getMeeting());

    }

    @OnClick(R.id.saveButton)
    public void saveTheMeeting() {
        String subjectMeeting = subjectEditText.getText().toString();
        String roomMeeting = roomSpinner.getSelectedItem().toString();
        String dateMeeting = dateTextView.getText().toString();
        String hourMeeting = hourSpinner.getSelectedItem().toString();
        int avatar = R.drawable.indigo_lens_24dp;

        //An avatar have to match a room
        switch(roomMeeting){
            case "Réunion A": avatar = R.drawable.indigo_lens_24dp; break;
            case "Réunion B": avatar = R.drawable.fuschia_lens_24dp; break;
            case "Réunion C": avatar = R.drawable.sepia_lens_24dp; break;
            case "Réunion D": avatar = R.drawable.corail_lens_4dp; break;
            case "Réunion E": avatar = R.drawable.rubis_lens_24dp; break;
            case "Réunion F": avatar = R.drawable.jade_lens_24dp; break;
            case "Réunion G": avatar = R.drawable.mauve_lens_24dp; break;
            case "Réunion H": avatar = R.drawable.topaze_lens_24dp; break;
            case "Réunion I": avatar = R.drawable.grege_lens_24dp; break;
            case "Réunion J": avatar = R.drawable.ic_lens_black_24dp;break; }

        Meeting meeting = new Meeting(roomMeeting, subjectMeeting, dateMeeting, hourMeeting, avatar, null); //New meeting created
        ArrayList<String> listEmails = new ArrayList<String>(list);
        meeting.setEmailList(listEmails); //Add email list to to the new meeting created


        if (TextUtils.isEmpty(subjectEditText.getText())){
            subjectEditText.setError( getString(R.string.message_subject_field_require) ); //Subject field require
        } else {
            mMeetingService.addMeeting(meeting); //Add new meeting to the list
            Intent intent = new Intent(AddMeetingActivity.this, ListMeetingActivity.class);
            startActivity(intent);
        }

        meetingRecyclerViewAdapter.notifyDataSetChanged();
    }


    /**
     * Click to get a calendar to pick a date
     */
    @OnClick(R.id.dateTextView)
    public void goCalendar() {
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


    /**
     * Configure date format
     */
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


    public void configListView(){
        //ADAPTER
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        emailListView.setAdapter(adapter);

        //LISTENER
        emailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);  //click to delete email from the list
                Toast.makeText(AddMeetingActivity.this, context.getString(R.string.email_supprimé), Toast.LENGTH_LONG).show();
            }
        });
    }


    @OnClick(R.id.addParticipant1)
    public void addEmailToListView() {
        if (isEmailValid(emailsParticipants.getText().toString())) { //Check if the input is an email
            list.add(emailsParticipants.getText().toString());
            adapter.notifyDataSetChanged();
            emailsParticipants.getText().clear();
            emailsParticipants.setBackground(null);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.emailErreur), Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    /**
     * Back to the first activity without save anything
     */
    @OnClick(R.id.closeIv)
    public void closeWindow(){
        this.finish();
    }


    /**
     * Check if the input is an email
     */
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
