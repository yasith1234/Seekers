package com.example.booking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelpaer myDb;
    EditText editCustomerid, editHotelName, editNumbofGuest, editDate,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editCustomerid = (EditText) findViewById(R.id.editTextCustomerId);
        editHotelName = (EditText) findViewById(R.id.editTextHotelName);
        editNumbofGuest = (EditText) findViewById(R.id.editTextNombofGues);
        editDate = (EditText) findViewById(R.id.editTextDate);
        editTextId = (EditText) findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate =(Button)findViewById(R.id.button_update);
        btnDelete =(Button)findViewById(R.id.button_delete);

        AddData();
        viewAll();
        UpdateData();
        DeleteDate();

    }
    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){

                    public void onClick(View v){
                        Integer deletedRows= myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data  not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener(){
                    public void onclick(View v){
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),editCustomerid.getText().toString(),
                                editHotelName.getText().toString(),
                                editNumbofGuest.getText().toString(),editDate.getText().toString());

                        if(isUpdate == true) {
                            Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data  not Updated", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }
    public void AddData(){
        btnAddData.setOnClickListener(
             new View.OnClickListener(){
                 public void onClick(View v){
                  boolean isInserted=   myDb.insertData(editCustomerid.getText().toString(),
                                editHotelName.getText().toString(),
                                editNumbofGuest.getText().toString());
                  if(isInserted=true)
                      Toast.makeText(MainActivity.this,"Data inserted", Toast.LENGTH_LONG).show();
                  else
                      Toast.makeText(MainActivity.this,"Data  not inserted", Toast.LENGTH_LONG).show();

                 }
             }
        );
    }
  public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnContextClickListener(){
                    public void onClick(View v){
                       Cursor res= myDb.getAllData();
                       if(res.getCount() == 0){
                           // show message
                           showMessage("Error","Nothing found");
                           return;
                       }
                       StringBuffer buffer = new StringBuffer();
                       while (res.moveToNext()){
                           buffer.append("Id:"+ res.getString(0)+"\n");
                           buffer.append("CustomerId:"+ res.getString(1)+"\n");
                           buffer.append("HotelName:"+ res.getString(2)+"\n");
                           buffer.append("NombofGues:"+ res.getString(3)+"\n");
                           buffer.append("Date:"+ res.getString(4)+"\n\n");
                       }
                       // show all data
                         showMessage("Data",buffer.toString());
                    }
                }
        );
  }
  public void showMessage(String title,String Maessage){
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setCancelable(true);
      builder.setTitle(title);
      builder.setMessage(Message);
      builder.show();

  }
}