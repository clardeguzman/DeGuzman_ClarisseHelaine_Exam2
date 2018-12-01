package com.deguzman.exam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;
    EditText eFname, eLname, eExam1, eExam2;
    TextView tAverage;
    ArrayList<String> keyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("grade");
        eFname = findViewById(R.id.eFN);
        eLname = findViewById(R.id.eLN);
        eExam1 = findViewById(R.id.eExamFirst);
        eExam2 = findViewById(R.id.eExamSecond);
        tAverage = findViewById(R.id.average);
        keyList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ss: dataSnapshot.getChildren()) {
                    keyList.add(ss.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getAverage(View v) {
        String fname = eFname.getText().toString().trim();
        String lname = eLname.getText().toString().trim();
        Long exam1 = Long.parseLong(eExam1.getText().toString().trim());
        Long exam2 = Long.parseLong(eExam1.getText().toString().trim());
        Long average = (exam1 + exam2) / 2;
        Student sgrade = new Student(fname,lname,average);
        String key = root.push().getKey();
        root.child(key).setValue(sgrade);
        keyList.add(key);
        tAverage.setText(average.toString());
        Toast.makeText(this,"Student's information is added to firebase",Toast.LENGTH_LONG).show();
    }
}