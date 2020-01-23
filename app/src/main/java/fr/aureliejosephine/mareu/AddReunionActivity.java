package fr.aureliejosephine.mareu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Reunion;
import fr.aureliejosephine.mareu.services.ReunionService;

public class AddReunionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.subjectEdit) public EditText subject;
    @BindView(R.id.emailEdit1) public EditText emailsParticipants;
    @BindView(R.id.spinner) public Spinner room;
    @BindView(R.id.spinnerHeure) public Spinner hourSpinner;
    @BindView(R.id.addParticipant1) public ImageView addParticipantButton;
    @BindView(R.id.emailTv) public TextView emailsTextView;
    @BindView(R.id.dateTextView) public TextView dateTextView;

    private ReunionService mReunionService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reunion);

        mReunionService = DI.getReunionService();

        ButterKnife.bind(this);

        configSpinnerRoom();

        configSpinnerHour();

        configDateTextView();
    }

    @OnClick(R.id.saveButton)
    public void saveTheMeeting() {
        String subjectMeeting = subject.getText().toString();
        String roomMeeting = room.getSelectedItem().toString();
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

        Reunion reunion = new Reunion(roomMeeting, subjectMeeting, dateMeeting, hourMeeting, avatar);
        ArrayList<String> listEmails = new ArrayList<>(Arrays.asList(emailsTextView.getText().toString().split(" ")));
        reunion.setAddEmail(listEmails);

        mReunionService.addReunion(reunion);

        Intent intent = new Intent(AddReunionActivity.this, ListReunionActivity.class);
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddReunionActivity.this,
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.liste_salles, R.layout.spinner_item_reunion);
        adapter.setDropDownViewResource(R.layout.spinner_item_reunion);
        room.setAdapter(adapter);
        room.setOnItemSelectedListener(this);
    }

    public void configSpinnerHour(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.horaires, R.layout.spinner_item_reunion);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(adapter);
        hourSpinner.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.addParticipant1)
    public void addEmail() {
        if (isEmailValid(emailsParticipants.getText().toString())) {
            emailsTextView.append(emailsParticipants.getText().toString() + "\n");
            emailsParticipants.getText().clear();
            emailsParticipants.setBackground(null);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.emailErreur), Toast.LENGTH_SHORT);
            toast.show();
            borderEditText();
        }
    }

    public void borderEditText(){
        GradientDrawable gd = new GradientDrawable();
        //gd.setColor(Color.parseColor(getString(R.color.colorAccent2)));
        gd.setColor(ContextCompat.getColor(AddReunionActivity.this, R.color.colorAccent2));
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
