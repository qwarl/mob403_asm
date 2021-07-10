package com.kietlpt.mob402_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kiennv.androidbaove.Dao.SachDao;
import com.kiennv.androidbaove.Model.Sach;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnThem;
    public EditText edtName, edtTacgia, edtTheloai,edtNXB,edtGia;

    Sach sv;
    SachDao svDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName=findViewById(R.id.edtName);
        edtTacgia=findViewById(R.id.edtTacgia);
        edtTheloai=findViewById(R.id.edtTheloai);
        edtNXB=findViewById(R.id.edtNXB);
        edtGia=findViewById(R.id.edtGia);


        btnThem=findViewById(R.id.btnThem);

        svDao = new SachDao(this);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sv = new Sach();

                sv.name=edtName.getText().toString();
                sv.tacgia=edtTacgia.getText().toString();
                sv.theloai=edtTheloai.getText().toString();
                sv.nxb=edtNXB.getText().toString();
                sv.gia=edtGia.getText().toString();

                if(!sv.name.isEmpty() && !sv.theloai.isEmpty()){
                    //them sach
                    svDao.insert(sv);

                    edtName.setText("");
                    edtTacgia.setText("");
                    edtTheloai.setText("");
                    edtNXB.setText("");
                    edtGia.setText("");

                }else{
                    Toast.makeText(MainActivity.this, "Vui long nhap du thong tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}