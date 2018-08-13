package com.example.user.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class CheckBoxScreen extends AppCompatActivity {
    CheckBox ch1,ch2;
    Button b1;
//        final Context context = getApplicationContext();
//    final CharSequence text = "Hello toast!";
//    final int duration = Toast.LENGTH_SHORT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_screen);

        ch1=(CheckBox)findViewById(R.id.checkBox);
        ch2=(CheckBox)findViewById(R.id.checkBox2);

        b1=(Button)findViewById(R.id.button3);

        b1.setOnClickListener(checkTest);
        ch2.setOnClickListener(checkTest);



    }
        private View.OnClickListener checkTest = new View.OnClickListener() {
        public void onClick(View v) {
                            Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

            StringBuffer result = new StringBuffer();
            result.append("Thanks : ").append(ch1.isChecked());
            result.append("\nThanks: ").append(ch2.isChecked());


                Toast toast = Toast.makeText(context, result, duration);
                toast.show();

        }
    };


}
