package com.up.mvvm.view;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.up.mvvm.R;
import com.up.mvvm.model.MainModel;
import com.up.mvvm.repository.MainResponse;
import com.up.mvvm.viewmodel.MainViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list;
    TextView text;
    ProgressBar dialog;

    MainViewModel mainViewModel;
    ArrayList<String> listItems  = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.list);
        text = (TextView)findViewById(R.id.text);
        dialog = (ProgressBar)findViewById(R.id.down);


        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        observeData(mainViewModel);




    }

    private void observeData(MainViewModel mainViewModel){
        mainViewModel.getMainData().observe(this, new Observer<MainResponse>() {
            @Override
            public void onChanged(MainResponse response) {

                String error = response.getError();
                List<MainModel> resp = response.getResponse();

                if(error == null) {

                    for (MainModel mainModel : resp) {
                        listItems.add(mainModel.getTitle() + " - " + mainModel.getCategory());
                    }

                    loadData();

                }else{
                    //Error in making request
                    text.setText(error);
                    dialog.setVisibility(View.GONE);
                }


            }
        });

    }

    private  void loadData(){
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listItems);
        list.setAdapter(adapter);
        dialog.setVisibility(View.GONE);
    }


}
