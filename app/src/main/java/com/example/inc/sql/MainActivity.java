package com.example.inc.sql;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstname, lastname;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = (EditText)findViewById(R.id.firstname_input);
        lastname = (EditText)findViewById(R.id.lastname_input);
        textView = (TextView) findViewById(R.id.textView);
        controller = new DB_Controller(this,"",null,1);
    }

    public void btn_click(View view) {
        switch (view.getId()){
            case R.id.button_add:
                try {
                    controller.insert_student(firstname.getText().toString(),lastname.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(MainActivity.this, "ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_delete:
                controller.delete_student(firstname.getText().toString());
                break;
            case R.id.btn_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ENTER NEW FIRST NAME");

                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.update_student(firstname.getText().toString(),new_firstname.getText().toString());

                    }
                });
                dialog.show();


                break;
                case R.id.list_students:
                    controller.list_all_students(textView);
                break;
        }
    }
}
