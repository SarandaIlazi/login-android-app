package com.example.sarandailazi.loginapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


/**
 * Created by Saranda Ilazi on 13/09/2017.
 */
public class SignUp extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    TextView tvDate;
    Calendar onCurrentDate;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        tvDate = (TextView) findViewById(R.id.textview_date);

        onCurrentDate = Calendar.getInstance();
        day = onCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = onCurrentDate.get(Calendar.MONTH);
        year = onCurrentDate.get(Calendar.YEAR);
        month = month + 1;

        tvDate.setText(day + "/" + month + "/" + year);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        tvDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);


                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
    }

    public void backToLogin(View v) {
        if (v.getId() == R.id.TFbacktologin) {
            Intent i = new Intent(SignUp.this, MainActivity.class);
            startActivity(i);
        }
    }

    public void onSignUpClick(View v) {
        EditText name = (EditText) findViewById(R.id.TFname);
        EditText surname = (EditText) findViewById(R.id.TFsurname);
        EditText email = (EditText) findViewById(R.id.TFemail);
        EditText username = (EditText) findViewById(R.id.TFuname);
        EditText pass1 = (EditText) findViewById(R.id.TFpass1);
        EditText pass2 = (EditText) findViewById(R.id.TFpass2);
        EditText address = (EditText) findViewById(R.id.TFaddress);

        String namestr = name.getText().toString();
        String surnamestr = surname.getText().toString();
        String emailstr = email.getText().toString();
        String usernamestr = username.getText().toString();
        String pass1str = pass1.getText().toString();
        String pass2str = pass2.getText().toString();
        String birthdaystr = tvDate.getText().toString();
        String addressstr = address.getText().toString();
        String genderstr = "";
        if (findViewById(R.id.radioButton_f).isSelected()) {
            genderstr = "f";
        } else {
            genderstr = "m";
        }

        Contact contact = new Contact();
        contact.setName(namestr);
        contact.setSurname(surnamestr);
        contact.setEmail(emailstr);
        contact.setUname(usernamestr);
        contact.setPass(pass1str);
        contact.setBirthday(birthdaystr);
        contact.setGender(genderstr);
        contact.setAddress(addressstr);

        if (!pass1str.equals(pass2str)) {
            Toast.makeText(SignUp.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
        } else if (!contact.validate().equals("")) {
            Toast.makeText(SignUp.this, contact.validate(), Toast.LENGTH_SHORT).show();
        } else if (helper.userExistsByEmail(contact.getEmail())) {
            Toast.makeText(SignUp.this, "This email is already registered", Toast.LENGTH_SHORT).show();
        } else {
            helper.insertContact(contact);
            Toast.makeText(SignUp.this, "User is registered succesfully. Go back to login", Toast.LENGTH_LONG).show();
        }
    }

}
