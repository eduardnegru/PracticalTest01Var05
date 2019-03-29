package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_var05_secondary);

        button = findViewById(R.id.okButton);
        textView = findViewById(R.id.resultText);

        final Intent intent = getIntent();

        String first = intent.getStringExtra("firstNumber");
        String second= intent.getStringExtra("secondNumber");
        String third = intent.getStringExtra("thirdNumber");

        boolean firstChecked = intent.getBooleanExtra("firstCheck", false);
        boolean secondChecked = intent.getBooleanExtra("secondCheck", false);
        boolean thirdChecked = intent.getBooleanExtra("thirdCheck", false);


        if((first.equals(third) || first.equals("0")  || third.equals("0")) && (first.equals(second) || first.equals("0")  || second.equals("0")) && (second.equals(third) || second.equals("0") || third.equals("0")))
        {
            if(firstChecked)
            {
                count++;
            }

            if(secondChecked)
            {
                count++;
            }

            if(thirdChecked)
            {
                count++;
            }

            int scor = 0;

            if(count == 0)
            {
                scor = 100;
            }
            else if(count == 1)
            {
                scor = 50;
            }
            else if(count == 2)
            {
                scor = 10;
            }
            String message = "Gained" + scor;

            textView.setText(message);

            Intent intent1 = new Intent();
            intent1.putExtra("score", scor);
            setResult(RESULT_OK, intent1);

        }
        else
        {
            textView.setText("Necastigator");
            int scor = 0;
            Intent intent1 = new Intent();
            intent1.putExtra("score", scor);
            setResult(RESULT_OK, intent1);
        }

    }
}
