package fr.aureliejosephine.mareu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Reunion;
import fr.aureliejosephine.mareu.services.ReunionService;


public class AddReunionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @BindView(R.id.sujetEdit)
    public EditText nSujet;
    @BindView(R.id.emailEdit1)
    public EditText nEmails;
    @BindView(R.id.emailEdit2)
    public EditText nEmails2;
    @BindView(R.id.emailEdit3)
    public EditText nEmails3;
    @BindView(R.id.emailEdit4)
    public EditText nEmails4;
    @BindView(R.id.spinner)
    public Spinner nSalle;
    @BindView(R.id.dateButton)
    public Button nCal;
    @BindView(R.id.heureButton)
    public Button nHeure;
    @BindView(R.id.addParticipant1)
    public ImageView mAdd1;
    @BindView(R.id.addParticipant2)
    public ImageView mAdd2;
    @BindView(R.id.addParticipant3)
    public ImageView mAdd3;
    @BindView(R.id.addParticipant4)
    public ImageView mAdd4;


    private ReunionService mReunionService;
    private List<String> mEmailList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reunion);

        mReunionService = DI.getReunionService();

        ButterKnife.bind(this);

        configSpinner();

    }

    public void Enregistrer(View view) {
        AddReunion();
    }

    public void AddReunion(){
        String sujet = nSujet.getText().toString();
        String emails = nEmails.getText().toString();
        String emails2 = nEmails2.getText().toString();
        String emails3 = nEmails3.getText().toString();
        String emails4 = nEmails4.getText().toString();
        String salle = nSalle.getSelectedItem().toString();
        String date = nCal.getText().toString();
        String heure = nHeure.getText().toString();


        int avatar = R.drawable.indigo_lens_24dp;
        switch(salle){
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

        Reunion reunion = new Reunion(salle, sujet, date, heure, avatar);
        reunion.addEmails(emails);
        reunion.addEmails(emails2);
        reunion.addEmails(emails3);
        reunion.addEmails(emails4);
        String mails = emails + " " + emails2 + " " + emails3 + " " + emails4;
        reunion.addEmails(mails);
        mReunionService.addReunion(reunion);

        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.toast_add), Toast.LENGTH_SHORT);
        toast.show();

        System.out.println("salle: " + salle + " Sujet: " + sujet + " Email: " + mails + " Date: " + date + " Heure: " + heure );

    }

    public void goCalendar(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddReunionActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        nCal.setText(day + "/" + (month + 1) +"/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    public void goClock(View view) {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(AddReunionActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                nHeure.setText(hours + "h" + minutes);
            }
        },hour,minute,true);
        timePickerDialog.show();


    }

    public void configSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.liste_salles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nSalle.setAdapter(adapter);
        nSalle.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected (AdapterView < ? > adapterView, View view,int i, long l){

    }

    @Override
    public void onNothingSelected (AdapterView < ? > adapterView){

    }


    public void addEmail(View view) {
        if (isEmailValid(nEmails.getText().toString())) {
            mAdd1.setImageResource(R.drawable.ic_done_black_24dp);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Adresse email incorrect", Toast.LENGTH_SHORT);
            toast.show();
            mAdd1.setImageResource(R.drawable.ic_error_outline_black_24dp);
        }
    }

    public void addEmail2(View view) {
        if (isEmailValid(nEmails2.getText().toString())) {
            mAdd2.setImageResource(R.drawable.ic_done_black_24dp);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Adresse email incorrect", Toast.LENGTH_SHORT);
            toast.show();
            mAdd2.setImageResource(R.drawable.ic_error_outline_black_24dp);
        }
    }

    public void addEmail3(View view) {
        if (isEmailValid(nEmails3.getText().toString())) {
            mAdd3.setImageResource(R.drawable.ic_done_black_24dp);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Adresse email incorrect", Toast.LENGTH_SHORT);
            toast.show();
            mAdd3.setImageResource(R.drawable.ic_error_outline_black_24dp);
        }
    }

    public void addEmail4(View view) {
        if (isEmailValid(nEmails4.getText().toString())) {
            mAdd4.setImageResource(R.drawable.ic_done_black_24dp);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Adresse email incorrect", Toast.LENGTH_SHORT);
            toast.show();
            mAdd4.setImageResource(R.drawable.ic_error_outline_black_24dp);
        }
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



}
