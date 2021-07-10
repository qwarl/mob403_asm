package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UpdateContactActivity extends AppCompatActivity {
    EditText etAddContact, etAddName, etAddEmail, etAddPhone;
    Button btnUpdateContact, btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        etAddContact = findViewById(R.id.etAddContact);
        etAddName = findViewById(R.id.etAddName);
        etAddEmail = findViewById(R.id.etAddEmail);
        etAddPhone = findViewById(R.id.etAddPhone);
        btnDel = findViewById(R.id.btnDel);
        btnUpdateContact = findViewById(R.id.btnUpdateContact);
        getConctactDetail();
        btnUpdateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDate();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del();
            }
        });
    }

    private void del() {
        String key=etAddContact.getText().toString();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("contacts");
        myRef.child(key).removeValue();
        finish();

    }

    private void upDate() {
        String key=etAddContact.getText().toString();
        String phone=etAddPhone.getText().toString();
        String name=etAddName.getText().toString();
        String email=etAddEmail.getText().toString();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("contacts");
        myRef.child(key).child("phone").setValue(phone);
        myRef.child(key).child("name").setValue(name);
        myRef.child(key).child("email").setValue(email);
    }


    private void getConctactDetail() {
        Intent intent = getIntent();
        final String key = intent.getStringExtra("KEY");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");
        myRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    HashMap<String, Object> hashMap = (HashMap<String, Object>) snapshot.getValue();
                    etAddContact.setText(key);
                    etAddName.setText(hashMap.get("name").toString());
                    etAddEmail.setText(hashMap.get("email").toString());
                    etAddPhone.setText(hashMap.get("phone").toString());
                } catch (Exception ex) {
                    Log.e("LOI_JSON", ex.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}