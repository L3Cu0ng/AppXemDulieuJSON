package com.example.asynctask;

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchDataAsyncTask extends AsyncTask<Void, Void, List<CustomItem>> {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/users";
    private MainActivity mActivity;
    private ListView mListView;

    public FetchDataAsyncTask(MainActivity activity, ListView listView) {
        this.mActivity = activity;
        this.mListView = listView;
    }

    @Override
    protected List<CustomItem> doInBackground(Void... voids) {
        List<CustomItem> itemList = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();

            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = jsonArray.getJSONObject(i);
                String name = jsonItem.getString("name");
                String email = jsonItem.getString("email");

                CustomItem item = new CustomItem(name, email);
                itemList.add(item);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            mActivity.runOnUiThread(() -> Toast.makeText(mActivity, "Error fetching data", Toast.LENGTH_SHORT).show());
        }

        return itemList;
    }

    @Override
    protected void onPostExecute(List<CustomItem> itemList) {
        if (itemList != null) {
            CustomAdapter adapter = new CustomAdapter(mActivity, R.layout.custom_item_layout, itemList);
            mListView.setAdapter(adapter);
        }
    }
}



