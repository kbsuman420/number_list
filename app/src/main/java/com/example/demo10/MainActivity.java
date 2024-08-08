package com.example.demo10;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnOpenDialog;
    RecyclerView recyclerView;
    ArrayList<ContactModel> arrContacts = new ArrayList<>();
    RecyclerContactAdapter adapter;
    MyDBHelper myDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        recyclerView = findViewById(R.id.recyclerView);
        myDBHelper = new MyDBHelper(MainActivity.this);
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update);
                EditText editName = dialog.findViewById(R.id.addName);
                EditText editNumber = dialog.findViewById(R.id.addNumber);
                Button button = dialog.findViewById(R.id.btnAction);

                button.setOnClickListener(new View.OnClickListener() {
                    String name="", number="";
                    @Override
                    public void onClick(View v) {
                        if (!editName.getText().toString().equals("")){
                            name = editName.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!editNumber.getText().toString().equals("")){
                            number = editNumber.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please enter your Number", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        arrContacts.add(new ContactModel(name, number));
                        ContactModel contactModel = new ContactModel(name, number);

                        myDBHelper.addContact(contactModel);


                        adapter.notifyItemInserted(arrContacts.size()-1);

                        recyclerView.scrollToPosition(arrContacts.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        arrContacts.add(new ContactModel("Suman Kundu", "908232487"));
        ArrayList<ContactModel> arrayList = myDBHelper.fetchContact();
        for (int i=0; i<arrayList.size(); i++){
            arrContacts.add(new ContactModel(arrayList.get(i).getName(), arrayList.get(i).getNumber()));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerContactAdapter(arrContacts, this);
        recyclerView.setAdapter(adapter);

    }
}