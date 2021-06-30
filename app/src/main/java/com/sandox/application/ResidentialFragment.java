package com.sandox.application;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class ResidentialFragment extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{
    public static View view;
    Spinner region, place, family, title;
    static String selectedRegion,selectedPlace, selectedFamily,selectedTittle ;
    Button button;
    final String LOG = "SendMessage.this";

    public ResidentialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_residential, container, false);
        region = view.findViewById(R.id.region);
        place = view.findViewById(R.id.place);
        family = view.findViewById(R.id.family);
        title = view.findViewById(R.id.title);
        button = view.findViewById(R.id.btn_next);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                R.array.region, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(adapter);
        region.setOnItemSelectedListener(ResidentialFragment.this);

        ArrayAdapter<CharSequence> familyAdapter = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                R.array.jobFamily, android.R.layout.simple_spinner_item);
        familyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        family.setAdapter(familyAdapter);
        family.setOnItemSelectedListener(ResidentialFragment.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String str_region = selectedRegion;
                String str_place = selectedPlace;
                String str_family = selectedFamily;
                String str_tittle = selectedTittle;

                HashMap postData = new HashMap();


                postData.put("str_region", str_region);
                postData.put("str_place", str_place);
                postData.put("str_family", str_family);
                postData.put("str_tittle",str_tittle );

                PostResponseAsyncTask cardTask = new PostResponseAsyncTask(ResidentialFragment.this.getActivity(), postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                        Log.d(LOG, s);
                        if (s.contains("added")) {

                            Fragment detailFragment = new QuestionnaireFragment();
                            Bundle bundle = new Bundle();
                            detailFragment.setArguments(bundle);
                            FragmentManager manager = getFragmentManager();
                            manager.beginTransaction().replace(R.id.content_main1, detailFragment)
                                    .addToBackStack("HomeDetail").commit();
                            //resetFields();

                        }
                    }
                });
                cardTask.execute("https://ngwenyaapp.000webhostapp.com/res.php");

            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()){
            case R.id.region:selectedRegion = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.place:selectedPlace = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.family:selectedFamily = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.title:selectedTittle = adapterView.getItemAtPosition(i).toString();
                break;
        }

        selectedRegion = adapterView.getItemAtPosition(i).toString();
        if(selectedRegion != null && selectedRegion.equals("Cape Metropole")){
            ArrayAdapter<CharSequence> placeAdapter = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                    R.array.CapeMetropoleTittle, android.R.layout.simple_spinner_item);
            placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            place.setAdapter(placeAdapter);
            place.setOnItemSelectedListener(ResidentialFragment.this);
        } else if(selectedRegion != null && selectedRegion.equals("West Coast")) {
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                    R.array.WestCoastTittle, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            place.setAdapter(adapter2);
            place.setOnItemSelectedListener(ResidentialFragment.this);
        } else if(selectedRegion != null && selectedRegion.equals("Cape Winelands")) {
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                    R.array.CapeWinelandsTittle, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            place.setAdapter(adapter2);
            place.setOnItemSelectedListener(ResidentialFragment.this);
        } else if(selectedRegion != null && selectedRegion.equals("Overberg")) {
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                    R.array.OverbergTittle, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            place.setAdapter(adapter2);
            place.setOnItemSelectedListener(ResidentialFragment.this);
        } else if(selectedRegion != null && selectedRegion.equals("Garden Route")) {
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                    R.array.GardenRouteTittle, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            place.setAdapter(adapter2);
            place.setOnItemSelectedListener(ResidentialFragment.this);
        }else if(selectedRegion != null && selectedRegion.equals("Klein Karoo")) {
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                    R.array.KleinKarooTittle, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            place.setAdapter(adapter2);
            place.setOnItemSelectedListener(ResidentialFragment.this);
        }

        selectedFamily = adapterView.getItemAtPosition(i).toString();

        if(selectedFamily != null && selectedFamily.equals("IT")){
            ArrayAdapter<CharSequence> placeAdapter = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                    R.array.itTitle, android.R.layout.simple_spinner_item);
            placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            title.setAdapter(placeAdapter);
            title.setOnItemSelectedListener(ResidentialFragment.this);
        } else if(selectedFamily != null && selectedFamily.equals("Finance")) {
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                    R.array.financeTittle, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            title.setAdapter(adapter2);
            title.setOnItemSelectedListener(ResidentialFragment.this);
        } else if(selectedFamily != null && selectedFamily.equals("Support")) {
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ResidentialFragment.this.getActivity(),
                    R.array.supportTittle, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            title.setAdapter(adapter2);
            title.setOnItemSelectedListener(ResidentialFragment.this);
        }

        switch (adapterView.getId()){
            case R.id.region:selectedRegion = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.place:selectedPlace = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.family:selectedFamily = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.title:selectedTittle = adapterView.getItemAtPosition(i).toString();
                break;
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public static void selectedPlace(){

    }
}