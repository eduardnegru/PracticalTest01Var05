package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    CheckBox firstCheckbox;
    CheckBox secondCheckbox;
    CheckBox thirdCheckbox;

    EditText firstText;
    EditText secondText;
    EditText thirdText;
    public static final int REQ_CODE = 1;
    private int scor = 0;
    private boolean serviceStarted = false;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver();

    Button playButton;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE)
        {
            int score = data.getIntExtra("score", 0);
            this.scor += score;

            if(this.scor >= 0)
            {
                if(!serviceStarted)
                {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                    intent.putExtra("currentScore", this.scor);
                    startService(intent);
                    Toast.makeText(getApplicationContext(), "Started service", Toast.LENGTH_LONG).show();
                    serviceStarted = true;
                }
            }

            Toast.makeText(getApplicationContext(), "Scor " + this.scor, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("my.action");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(getApplicationContext(), PracticalTest01Var05Service.class));
        Toast.makeText(getApplicationContext(), "Stopped service", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scor", this.scor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);


        if(savedInstanceState != null)
        {
            int scorRecv = savedInstanceState.getInt("scor");
            this.scor = scorRecv;

            if(this.scor >= 0)
            {
                if(!serviceStarted)
                {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                    Toast.makeText(getApplicationContext(), "Started service", Toast.LENGTH_LONG).show();
                    startService(intent);
                }

            }

            Toast.makeText(getApplicationContext(), "Saved " + this.scor, Toast.LENGTH_SHORT).show();
        }


        firstCheckbox = findViewById(R.id.firstCheckbox);
        secondCheckbox = findViewById(R.id.secondCheckbox);
        thirdCheckbox = findViewById(R.id.thirdCheckbox);

        firstText = findViewById(R.id.firstText);
        secondText = findViewById(R.id.secondText);
        thirdText = findViewById(R.id.thirdText);

        playButton = findViewById(R.id.playButton);



        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String arr[] = new String[4];
                arr[0] = "0";
                arr[1] = "1";
                arr[2] = "2";
                arr[3] = "3";

                Random r = new Random();

                int a,b,c;

                if(!firstCheckbox.isChecked())
                {
                    a = r.nextInt(3);
                    firstText.setText(arr[a]);
                }
                else
                {
                    a = Integer.parseInt(String.valueOf(firstText.getText()));
                }


                if(!secondCheckbox.isChecked())
                {
                    b = r.nextInt(3);
                    secondText.setText(arr[b]);
                }
                {
                    b = Integer.parseInt(String.valueOf(secondText.getText()));
                }

                if(!thirdCheckbox.isChecked())
                {
                    c = r.nextInt(3);
                    thirdText.setText(arr[c]);
                }
                {
                    c = Integer.parseInt(String.valueOf(thirdText.getText()));
                }

                Toast.makeText(getApplicationContext(), "" + arr[a] + ", " + arr[b] + ", " + arr[c], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);

                intent.putExtra("firstNumber", arr[a]);
                intent.putExtra("secondNumber", arr[b]);
                intent.putExtra("thirdNumber", arr[c]);

                intent.putExtra("firstCheck", firstCheckbox.isChecked());
                intent.putExtra("secondCheck", secondCheckbox.isChecked());
                intent.putExtra("thirdCheck", thirdCheckbox.isChecked());

                startActivityForResult(intent, REQ_CODE);
            }
        });



    }
}
