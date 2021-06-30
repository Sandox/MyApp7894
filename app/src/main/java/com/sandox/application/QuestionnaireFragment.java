package com.sandox.application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class QuestionnaireFragment extends Fragment {
    EditText text;
    public static View view;
    private RadioGroup groupDrugs, groupAlcohol, groupCriminal, groupArrested, groupCriminalGroup, groupDismissed, groupSteal, groupDishonest, groupDrugs2, groupPolGraph;
    Button button;
    public QuestionnaireFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_questionnaire, container, false);
        groupDrugs = view.findViewById(R.id.radio);
        button = view.findViewById(R.id.btn_next);
        groupAlcohol = view.findViewById(R.id.radioAlcholConsume);
        groupCriminal = view.findViewById(R.id.radioCriminalCharges);
        groupArrested = view.findViewById(R.id.radioArrested);
        groupCriminalGroup = view.findViewById(R.id.radioCriminalGroup);
        groupDismissed = view.findViewById(R.id.radioDismissed);
        groupSteal = view.findViewById(R.id.radioSteal);
        groupDishonest = view.findViewById(R.id.radioDishonesty);
        groupDrugs2 = view.findViewById(R.id.radioIllegalDrugs);
        groupPolGraph = view.findViewById(R.id.radioPolgraph);
        text = view.findViewById(R.id.input_email);
        text.setVisibility(View.GONE);

        groupCriminal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb=view.findViewById(checkedId);
                if(rb.getText().equals("Yes")){
                    text.setVisibility(View.VISIBLE);
                } else text.setVisibility(View.GONE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int drug = groupDrugs.getCheckedRadioButtonId();
                int alcohol = groupAlcohol.getCheckedRadioButtonId();
                int criminal = groupCriminal.getCheckedRadioButtonId();
                int arrested = groupArrested.getCheckedRadioButtonId();
                int criminalGroup = groupCriminalGroup.getCheckedRadioButtonId();
                int dismissed = groupDismissed.getCheckedRadioButtonId();
                int steal = groupSteal.getCheckedRadioButtonId();
                int dishonest = groupDishonest.getCheckedRadioButtonId();
                int drugs2 = groupDrugs2.getCheckedRadioButtonId();
                int polGraph = groupPolGraph.getCheckedRadioButtonId();
                if(drug>0){
                    if (alcohol>0){
                        if (criminal>0){
                            if (arrested>0){
                                if(criminalGroup>0){
                                    if(dismissed>0){
                                        if(steal>0){
                                            if(dishonest>0){
                                                if(drugs2>0){
                                                    if(polGraph>0){
                                                        Fragment detailFragment = new UploadFilesFragment();
                                                        Bundle bundle = new Bundle();
                                                        detailFragment.setArguments(bundle);
                                                        FragmentManager manager = getFragmentManager();
                                                        manager.beginTransaction().replace(R.id.content_main1, detailFragment)
                                                                .addToBackStack("HomeDetail").commit();
                                                    }else QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons (Pol graph)", null);
                                                }else QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons (drugs 2) ", null);
                                            }else QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons (dishonest)", null);
                                        }else QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons (steal)", null);
                                    }else QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons (dismissed)", null);
                                }else QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons (criminal group)", null);
                            }else QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons (arrested)", null);
                        }else QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons (criminal)", null);
                    }else QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons (alcohol)", null);
                } else {
                    System.out.println(drug);
                    QuestionnaireFragment.this.showMessageOKCancel("Please select all radio buttons(drugs)", null);
                }
            }

        });
        return view;
    }

    public void onClickedCharges(View view){
        text.setVisibility(view.VISIBLE);
    }

    public void onClickedChargesNo(View view){
        text.setVisibility(view.GONE);
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(QuestionnaireFragment.this.getActivity())
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}