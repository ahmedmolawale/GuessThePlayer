package com.example.alien.guesstheplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {


    TextView textView;
    ImageView imageView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ArrayList<String> playersNames = new ArrayList<>();
    ArrayList<String> playersImgREs = new ArrayList<>();
    int chosenPlayer = 0;
    int points = 0;
    String score;
    int noOfQuestions = 0;
    int locationOfCorrectAnswer = 0;
    int incorrectAnswerLocation;
    ArrayList<String> answers = new ArrayList<>();
    String name;
    String gender;
    String message;


    //Todo: change your variable naming to follow convention, i have refactored some though

    /*
    * method to generate questions
    * and also set answers to the buttons
    * */
    private void createNewQuestion() {
        Random random = new Random();
        String playerImage = playersImgREs.get(chosenPlayer);

        imageView.setImageResource(Integer.parseInt(playerImage));
        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(playersNames.get(chosenPlayer));
            } else {
                incorrectAnswerLocation = random.nextInt(playersImgREs.size());
                while (incorrectAnswerLocation == chosenPlayer) {
                    incorrectAnswerLocation = random.nextInt(playersImgREs.size());
                }
                answers.add(playersNames.get(incorrectAnswerLocation));
            }
        }
        button1.setText(answers.get(0));
        button2.setText(answers.get(1));
        button3.setText(answers.get(2));
        button4.setText(answers.get(3));

        chosenPlayer++;
    }


    /*
        * this is an onClick method for the buttons provided
        * each click of the right or wrong button generates a custom toast
        * showing the right answer if the user gets it wrong
        * */
    public void choosePlayer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Correct!!!");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
            points++;
            noOfQuestions++;

        } else {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast2,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            //Todo: the below solves the problem.
            int rightPlayer = chosenPlayer -1;

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Wrong! It was " + playersNames.get(rightPlayer));  //and here
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            noOfQuestions++;
        }
        score = points + "/" + noOfQuestions;

        /*
        * condition to test if questions are upto 10 in order to terminate and show
        * the score of the game player*/
        if (noOfQuestions >= 10) {  //can the player answer more than 10 questions?
            Intent intent = new Intent(this, LastActvitiy.class);
            intent.putExtra("score", score);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("message", message);
            startActivity(intent);
        } else {
            createNewQuestion();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Todo: To avoid using 'findViewById' everytime, check out a library called Butterknife.You can thank me later
        textView = (TextView) findViewById(R.id.textview);
        imageView = (ImageView) findViewById(R.id.image);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        /*
        * Stores resource id name for each player in an arraylist
        * */
        playersImgREs.add(String.valueOf(R.drawable.ibrahimovic));
        playersImgREs.add(String.valueOf(R.drawable.messi));
        playersImgREs.add(String.valueOf(R.drawable.ronaldo));
        playersImgREs.add(String.valueOf(R.drawable.beckham));
        playersImgREs.add(String.valueOf(R.drawable.drogba));
        playersImgREs.add(String.valueOf(R.drawable.henry));
        playersImgREs.add(String.valueOf(R.drawable.kaka));
        playersImgREs.add(String.valueOf(R.drawable.ozil));
        playersImgREs.add(String.valueOf(R.drawable.ronaldinho));
        playersImgREs.add(String.valueOf(R.drawable.sanchez));

        /*
        * Stores playerNames for each player in an arraylist
        * */
        //Todo: Avoid having string constants this way, you could set up an array of string constants ( static final class variable)
        playersNames.add("ibrahimovic");
        playersNames.add("messi");
        playersNames.add("ronaldo");
        playersNames.add("beckham");
        playersNames.add("drogba");
        playersNames.add("henry");
        playersNames.add("kaka");
        playersNames.add("ozil");
        playersNames.add("ronaldinho");
        playersNames.add("sanchez");

        /*
        * send intent message from mainActivity to lastActivity
        * also to recieve intent extras that weredisplayed in this activity
        * */
        //Todo: A bundle is set of key-value pairs, different from an intent. change the name to bundle or receivedBundle
        /**
         * See this
         * Intent intent = getIntent();
         * if(intent != null){
         *     Bundle bundle = intent.getExtras();
         *
         * }
         *
         */

        Bundle secondintent = getIntent().getExtras();
        if (secondintent == null) {
            return;
        } else {
            String name = secondintent.getString("name");
            String gender = secondintent.getString("gender");
            String message = secondintent.getString("message");
            textView.setText(name);
            this.gender = gender;
            this.message = message;
            this.name = name;
        }
        createNewQuestion();
    }
}
