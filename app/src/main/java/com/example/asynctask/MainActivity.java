package com.example.asynctask;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new CustomAdapter(this, R.layout.custom_item_layout, new ArrayList<>());
        listView.setAdapter(adapter);

        new FetchDataAsyncTask(this, listView).execute();
    }
}

