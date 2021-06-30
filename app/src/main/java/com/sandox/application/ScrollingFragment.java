package com.sandox.application;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class ScrollingFragment extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    public static View view;
    final String LOG = "SendMessage.this";

    static EditText name, surname, idNum, email,cellNum, dateOB;
    static String selectedText;
    Spinner title;
    Button button;
    static final Calendar myCalendar = Calendar.getInstance();
    public ScrollingFragment(){
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_scrolling, container, false);

        name = view.findViewById(R.id.edName);
        surname = view.findViewById(R.id.edSurname);
        title = view.findViewById(R.id.spinner);
        dateOB = view.findViewById(R.id.dateOB);
        idNum = view.findViewById(R.id.edIdNum);
        email = view.findViewById(R.id.input_email);
        cellNum = view.findViewById(R.id.input_number);
        button = view.findViewById(R.id.btn_next);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ScrollingFragment.this.getActivity(),
                R.array.title, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        title.setAdapter(adapter);
        title.setOnItemSelectedListener(ScrollingFragment.this);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateOB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ScrollingFragment.this.getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String str_name = name.getText().toString();
                String str_surname = surname.getText().toString();
                String str_title = selectedText;
                String str_dateOB = dateOB.getText().toString();
                String str_id = idNum.getText().toString();
                String str_email = email.getText().toString();
                String str_cellNum = cellNum.getText().toString();

                boolean validation;
                validation = basicInfoValidation();
                if(validation==true){

                    HashMap postData = new HashMap();
                    postData.put("str_name", str_name);
                    postData.put("str_surname", str_surname);
                    postData.put("str_title", str_title);
                    postData.put("str_dateOB",str_dateOB );
                    postData.put("str_id", str_id);
                    postData.put("str_email", str_email);
                    postData.put("str_cellNum", str_cellNum);

                    PostResponseAsyncTask cardTask = new PostResponseAsyncTask(ScrollingFragment.this.getActivity(), postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {

                            Log.d(LOG, s);
                            if (s.contains("added")) {

                                Fragment detailFragment = new ResidentialFragment();
                                Bundle bundle = new Bundle();
                                detailFragment.setArguments(bundle);
                                FragmentManager manager = getFragmentManager();
                                manager.beginTransaction().replace(R.id.content_main1, detailFragment)
                                        .addToBackStack("HomeDetail").commit();
                                //resetFields();

                            }else if (s.contains("Number Exists")){
                                AlertDialog.Builder dialogBox = new AlertDialog.Builder(ScrollingFragment.this.getActivity());
                                dialogBox.setMessage("ID Exist")
                                        .setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();//closes the dialog box
                                            }
                                        });
                                AlertDialog dialog = dialogBox.create();
                                dialog.setTitle("ERROR!");
                                dialog.show();
                                //dialog.getButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#808080"));
                            }
                        }
                    });
                    cardTask.execute("https://ngwenyaapp.000webhostapp.com/BasicQuestion.php");

                }


            }
        });
        return view;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateOB.setText(sdf.format(myCalendar.getTime()));
    }

    public boolean basicInfoValidation(){
         boolean validation=true;
        try {
            if(!surname.getText().toString().equals("")){
                if(!name.getText().toString().equals("")){
                    if(!selectedText.equals("")){
                        if(!idNum.getText().toString().equals("")){
                            if(!dateOB.getText().toString().equals("")){
                                if(!email.getText().toString().equals("")){
                                    if(!cellNum.getText().toString().equals("")){
                                        if(idNum.getText().toString().length() < 13){
                                            ScrollingFragment.this.showMessageOKCancel("Invalid ID", null);
                                            validation=false;
                                        }
                                    } else {
                                        ScrollingFragment.this.showMessageOKCancel("Cell number Should not be Empty", null);
                                        validation=false;
                                    }
                                } else {
                                    ScrollingFragment.this.showMessageOKCancel("Email Should not be Empty", null);
                                    validation=false;
                                }
                            } else {
                                ScrollingFragment.this.showMessageOKCancel("Date of birth Should not be Empty", null);
                                validation=false;
                            }
                        } else {
                            ScrollingFragment.this.showMessageOKCancel("ID Should not be Empty", null);
                            validation=false;
                        }
                    } else {
                        ScrollingFragment.this.showMessageOKCancel("Title Should not be Empty", null);
                        validation=false;
                    }
                } else {
                    ScrollingFragment.this.showMessageOKCancel("first name Should not be Empty", null);
                    validation=false;
                }
            } else {
                ScrollingFragment.this.showMessageOKCancel("last name Should not be Empty", null);
                validation=false;
            }
            return validation;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedText = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(ScrollingFragment.this.getActivity())
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}