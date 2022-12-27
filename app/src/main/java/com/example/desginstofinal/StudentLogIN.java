package com.example.desginstofinal;

import static com.example.desginstofinal.FBref.refAuth;
import static com.example.desginstofinal.FBref.refstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class StudentLogIN extends AppCompatActivity {
    TextView tVtitle, tVregister;
    Intent t;
    EditText eTemail, eTpass,eTphone,eTid,eTname;
    Button btn,btnFP;
    LinearLayout LL;
    Boolean stayConnect, registered, firstrun,inorder=false;
    CheckBox cBstayconnect;
    String name, phone, email, password, uid;
    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_log_in);
        tVtitle=(TextView)findViewById(R.id.tVtitle);
        eTemail=(EditText)findViewById(R.id.eTemail);
        eTpass=(EditText)findViewById(R.id.eTpass);
        eTphone=(EditText)findViewById(R.id.eTphone);
        eTname=(EditText)findViewById(R.id.eTname);
        btn = (Button) findViewById(R.id.btn);
        eTid=(EditText) findViewById(R.id.eTid);
        LL = findViewById(R.id.LL);
        cBstayconnect=(CheckBox)findViewById(R.id.cBstayconnect);
        tVregister=(TextView) findViewById(R.id.tVregister);
        btnFP=(Button) findViewById(R.id.btnFG);



        stayConnect=false;
        registered=true;

        regoption();
    }

    protected void onStart() {
        /**
         * Checks if the user already checked the "Stay Connected" button.
         * <p>
         *
         */
        super.onStart();
        SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
        Boolean isChecked=settings.getBoolean("stayConnect",false);
        Intent si = new Intent(StudentLogIN.this,StudentMain_Activity.class);
        if (refAuth.getCurrentUser()!=null && isChecked) {
            stayConnect=true;
            startActivity(si);
        }



    }

    protected void onPause() {
        super.onPause();
        if (stayConnect) finish();
    }

    private void regoption() {
        /**
         * Switches the screen from Login to Register.
         * <p>
         */
        SpannableString ss = new SpannableString("Don't have an account?  Register here!");
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                tVtitle.setText("Register");
                eTname.setVisibility(View.VISIBLE);
                eTphone.setVisibility(View.VISIBLE);
                eTid.setVisibility(View.VISIBLE);
                LL.setVisibility(View.VISIBLE);
                btnFP.setVisibility(View.INVISIBLE);
                btn.setText("Register");

                registered=false;
                logoption();
            }
        };
        ss.setSpan(span, 24, 38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tVregister.setText(ss);
        tVregister.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void logoption() {
        SpannableString ss = new SpannableString("Already have an account?  Login here!");
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                tVtitle.setText("Login");
                eTname.setVisibility(View.INVISIBLE);
                eTphone.setVisibility(View.INVISIBLE);
                eTphone.setVisibility(View.INVISIBLE);
                eTid.setVisibility(View.INVISIBLE);
                LL.setVisibility(View.INVISIBLE);
                btnFP.setVisibility(View.VISIBLE);


                btn.setText("Login");
                registered=true;
                regoption();
            }
        };
        ss.setSpan(span, 26, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tVregister.setText(ss);
        tVregister.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void logorreg(View view) {
        /**
         * Checks if the user is registered and logging in, else it will register.
         * <p>
         *
         * @param	view Button	on click operate the action.
         */
        if (registered) {
            email=eTemail.getText().toString();
            password=eTpass.getText().toString();

            final ProgressDialog pd=ProgressDialog.show(this,"Login","Connecting...",true);
            refAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pd.dismiss();
                            if (task.isSuccessful()) {
                                SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
                                SharedPreferences.Editor editor=settings.edit();
                                editor.putBoolean("stayConnect",cBstayconnect.isChecked());
                                editor.commit();
                                Log.d("MainActivity", "signinUserWithEmail:success");
                                Toast.makeText(StudentLogIN.this, "Login Success", Toast.LENGTH_LONG).show();
                                Intent si = new Intent(StudentLogIN.this,StudentMain_Activity.class);
                                startActivity(si);
                            } else {
                                Log.d("MainActivity", "signinUserWithEmail:fail");
                                Toast.makeText(StudentLogIN.this, "e-mail or password are wrong!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            name=eTname.getText().toString();
            phone=eTphone.getText().toString();
            email=eTemail.getText().toString();
            password=eTpass.getText().toString();
            uid=eTid.getText().toString();

            final ProgressDialog pd=ProgressDialog.show(this,"Register","Registering...",true);
            refAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pd.dismiss();
                            if (task.isSuccessful()) {
                                SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
                                SharedPreferences.Editor editor=settings.edit();
                                editor.putBoolean("stayConnect",cBstayconnect.isChecked());
                                editor.commit();
                                Log.d("MainActivity", "createUserWithEmail:success");
                                FirebaseUser user = refAuth.getCurrentUser();
                                uid = user.getUid();
                                student=new Student(name, phone,email, uid);
                                refstudent.child(name).setValue(student);
                                Toast.makeText(StudentLogIN.this, "Successful registration", Toast.LENGTH_LONG).show();
                                Intent si = new Intent(StudentLogIN.this,StudentMain_Activity.class);
                                startActivity(si);
                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                    Toast.makeText(StudentLogIN.this, "User with e-mail already exist!", Toast.LENGTH_LONG).show();
                                else {
                                    Log.w("MainActivity", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(StudentLogIN.this, "User create failed.",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        uid = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), uid, Toast.LENGTH_SHORT).show();
    }


    public void FGM(View view) {
        t=new Intent(StudentLogIN.this,ForGotMyPass.class);
        startActivity(t);
    }
}