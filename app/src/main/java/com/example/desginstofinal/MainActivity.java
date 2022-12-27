package com.example.desginstofinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button manager,student;
    Intent t;
    @Override
    /**
     * @author		Ahmad mashal
     * @version	    V1.0
     * @since		6/12/2022
     * user will   Choose who he is student/manegmant person.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        student=(Button) findViewById(R.id.student);
        manager=(Button)findViewById(R.id.manager);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * moveing to the next student activity.
                 * <p>
                 *next:LOGIN
                 * @param	view Button	on click operate the action.
                 */
                t=new Intent(MainActivity.this,StudentLogIN.class);
                startActivity(t);
            }
        });
    }



}