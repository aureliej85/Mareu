package fr.aureliejosephine.mareu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Reunion;
import fr.aureliejosephine.mareu.services.ReunionService;


public class AddReunionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    public EditText nSujet;
    public EditText nEmails;
    public Spinner nSalle;
    public EditText nDate;
    public Button nCal;
    public Button nHeure;
    public Reunion reunion;

    private ReunionService mReunionService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reunion);

        mReunionService = DI.getReunionService();

        nSujet = findViewById(R.id.sujetEdit);

        nEmails = findViewById(R.id.emailEdit);

        nDate = findViewById(R.id.dateEditText);

        nSalle = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.liste_salles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nSalle.setAdapter(adapter);
        nSalle.setOnItemSelectedListener(this);

        nCal = findViewById(R.id.dateButton);

        nHeure = findViewById(R.id.heureButton);

    }

    public void Enregistrer(View view) {
        AddReunion();
    }


    public void AddReunion(){
        String sujet = nSujet.getText().toString();
        String emails = nEmails.getText().toString();
        String salle = nSalle.getSelectedItem().toString();
        String date = nCal.getText().toString();
        String heure = nHeure.getText().toString();

        mReunionService.addReunion(new Reunion(salle, sujet, emails, date, heure));

        Toast toast = Toast.makeText(getApplicationContext(), "Réunion enregistrée !", Toast.LENGTH_SHORT);
        toast.show();

        System.out.println("salle: " + salle + " Sujet: " + sujet + " Email: " + emails + " Date: " + date + " Heure: " + heure);

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







    @Override
    public void onItemSelected (AdapterView < ? > adapterView, View view,int i, long l){

    }

    @Override
    public void onNothingSelected (AdapterView < ? > adapterView){

    }

}
