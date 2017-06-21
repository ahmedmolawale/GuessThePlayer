package com.example.alien.guesstheplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText theName;
    Boolean footyKnowledge;
    String gender;
    CheckBox football;
    String message;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theName = (EditText) findViewById(R.id.nametf);
        football = (CheckBox) findViewById(R.id.footballKnowledge);
    }

    //Todo: Always specify an onClick for a button
    public void start(View view) {
        name = theName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            theName.setError("Please Enter Your name:");
        } else {
            RadioGroup types = (RadioGroup) findViewById(R.id.radiogroup);
            switch (types.getCheckedRadioButtonId()) {
                case R.id.male:
                    gender = "Male";
                    break;
                case R.id.female:
                    gender = "Female";
                    break;
            }
            footyKnowledge = football.isChecked();
            if (footyKnowledge)
                message = "with football knowledge";
            else
                message = "without football knowledge";

            //Todo Reconsider changing the name
            intentStart();
        }
        // Log.i("here","is me the button");
    }


    public void intentStart() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("gender", gender);
        intent.putExtra("message", message);
        startActivity(intent);
    }
        //Todo In summary, well done!

}