package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.Database.UserDatabase;
import com.example.roomdatabase.entities.User;

public class UpdateActivity extends AppCompatActivity {
    private EditText edt_username;
    private  EditText edt_address;
    private Button btn_update_user;

    private User muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edt_username = findViewById(R.id.edt_username);
        edt_address = findViewById(R.id.edt_address);
        btn_update_user = findViewById(R.id.btn_update_user);

        // nhan du lieu
        muser = (User) getIntent().getExtras().get("objuser");
        if(muser==null){
            return;
        }
        edt_username.setText(muser.getFirstname());
        edt_address.setText(muser.getAddress());

        btn_update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateuser();
            }
        });

    }

    private void updateuser() {
        String username = edt_username.getText().toString().trim();
        String address = edt_address.getText().toString().trim();

        muser.setFirstname(username);
        muser.setAddress(address);

        UserDatabase.getInstance(this).userDao().updateuser(muser);
        Toast.makeText(this, "update successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();

    }
}