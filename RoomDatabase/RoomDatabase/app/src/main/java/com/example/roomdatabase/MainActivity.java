package com.example.roomdatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabase.Database.UserDatabase;
import com.example.roomdatabase.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edt_username, edt_address, edt_timkiem;
    private Button btn_adduser, btntimkiem;
    private TextView txtdeleteall;

    // bo ba
    private List<User> mlistuser;
    private UserAdapter userAdapter;
    private RecyclerView rcv_user;
    private int My_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // addcontrools
        edt_username = findViewById(R.id.edt_username);
        edt_address = findViewById(R.id.edt_address);
        edt_timkiem = findViewById(R.id.edttimkiem);
        txtdeleteall = findViewById(R.id.txtdeleteall);

        btntimkiem = findViewById(R.id.btntimkiem);
        btn_adduser = findViewById(R.id.btn_adduser);

        txtdeleteall = findViewById(R.id.txtdeleteall);

        userAdapter = new UserAdapter(new UserAdapter.IClickitemUser() {
            @Override
            public void update(User user) {
                clickupdateuser(user);
            }

            @Override
            public void delete(User user) {
                clickdeleteuser(user);
            }
        });
        mlistuser = new ArrayList<>();
        userAdapter.SetData(mlistuser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_user = findViewById(R.id.rcv_user);
        rcv_user.setLayoutManager(linearLayoutManager);
        rcv_user.setAdapter(userAdapter);

        // thao tac voi database
        btn_adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 addUser();
            }
        });
        txtdeleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteAll();
            }
        });
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Searchkey();
            }
        });
        loadData();

    }

    private void Searchkey() {
        String txttimkiem = edt_timkiem.getText().toString().trim();
        List<User>mlist = new ArrayList<>();
        mlist = UserDatabase.getInstance(this).userDao().SearchUser(txttimkiem);
        userAdapter.SetData(mlist);
    }

    private void DeleteAll() {
        new AlertDialog.Builder(this)
                .setTitle("ban co muon xoa khong")
                .setMessage("are you sure ?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserDatabase.getInstance(MainActivity.this).userDao().DeleteAll();
                        Toast.makeText(MainActivity.this, "delete all  successful", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("no",null)
                .show();
    }

    private void clickdeleteuser(User user){
        new AlertDialog.Builder(this)
                .setTitle("ban co muon xoa khong")
                .setMessage("are you sure ?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserDatabase.getInstance(MainActivity.this).userDao().DeleteUser(user);
                        Toast.makeText(MainActivity.this, "delete successful", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("no",null)
                .show();
    }

    private void clickupdateuser(User user) {
        Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objuser",user);
        intent.putExtras(bundle);
        startActivityForResult(intent,My_REQUEST_CODE);
    }

    private void addUser() {
        String username = edt_username.getText().toString().trim();
        String address = edt_address.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(address)) {
            Toast.makeText(this, "khong dc de thieu ", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(username, address);
        if(checkusername(user)){
            Toast.makeText(this, "already exists in the system", Toast.LENGTH_SHORT).show();
            return;
        }
        UserDatabase.getInstance(this).userDao().Insertuser(user);
        Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();
        edt_address.setText("");
        edt_username.setText("");

        // loi co no ra
        loadData();

    }

    private void loadData() {
        mlistuser = UserDatabase.getInstance(this).userDao().getAll();
        userAdapter.SetData(mlistuser);
    }
    public  boolean checkusername(User user){
        List<User>list = UserDatabase.getInstance(this).userDao().Checkuser(user.getFirstname());
        return list!=null && !list.isEmpty();
    }
    // recevied data

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==My_REQUEST_CODE && resultCode== Activity.RESULT_OK){
            loadData();
        }
    }
}