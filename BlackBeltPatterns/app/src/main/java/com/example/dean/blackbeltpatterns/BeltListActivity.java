package com.example.dean.blackbeltpatterns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dean.blackbeltpatterns.belt.BeltDegree;
import com.example.dean.blackbeltpatterns.belt.BeltItem;
import com.example.dean.blackbeltpatterns.belt.IBelt;


import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeltListActivity extends AppCompatActivity {

    RecyclerView mBeltsRecycleView;
    ArrayList<IBelt> beltDegrees = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.belt_list_activity);
        mBeltsRecycleView = findViewById(R.id.belts_list);

        // only attach an adaptor if the view has been created correctly,
        // have not create a local instance of the adaptor as no data is being manipulated,
        if (mBeltsRecycleView != null) {
            LinearLayoutManager llm = new LinearLayoutManager(this);
            mBeltsRecycleView.setLayoutManager(llm);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    // create a new adaptor and populdate it via the YAML file in the gitrepo
                    final BeltRecycleViewAdaptor beltAdaptor = new BeltRecycleViewAdaptor(populateDataset(downloadFile("https://raw.githubusercontent.com/cm-dev/android-tech-test/master/playlist.yaml")));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mBeltsRecycleView.setAdapter(beltAdaptor);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    /*
    if the input is not created after downloading, populate a InputStream using the offline version of the yaml file,
     */
    public ArrayList<IBelt> populateDataset(InputStream input) throws IOException {

        ArrayList<IBelt> belts = new ArrayList<>();
//
        if (input == null) {
            input = getAssets().open("playlist.yaml");
        }

        Yaml yaml = new Yaml();
//        yaml.load(input);
        List<Object> data = (List<Object>) yaml.load(input);
        // Loop over each record in the yaml and turn it into an object
        for (Object d : data) {
            String blackBeltTitle = "";
            ArrayList<BeltItem> beltItems = new ArrayList<>();

            // create a map based on the data object
            Map<String, BeltDegree> map = (Map<String, BeltDegree>) d;
            // check the map has a a title key and set the value of the title variable to the key value
            if (map.containsKey("title")) {
                blackBeltTitle = String.valueOf(map.get("title"));
            }

            // check if the map has a items key
            if (map.containsKey("items")) {
                // convert the maps items into a list of items
                List<String> itemMaps = (List<String>) map.get("items");
                if (itemMaps != null) {
                    for (Object itemObject : itemMaps) {
                        // loop over the items and turn each item into a map.
                        Map<String, BeltDegree> itemMap = (Map<String, BeltDegree>) itemObject;
                        if (itemMap.containsKey("name")) {
                            // check the map has a name key and if create a new belt item based on the value of the name.
                            String value = String.valueOf(itemMap.get("name"));
                            beltItems.add(new BeltItem(value));
                        }
                    }
                }
            }
            belts.add(new BeltDegree(blackBeltTitle, beltItems));
        }
        return belts;
    }

    /*
    downloadFile from a given url and return as a Inputstream,
    if the file fails return a null input stream
     */
    private InputStream downloadFile(String urlLocation) throws IOException {
        HttpURLConnection con = null;
        URL url;
        InputStream is = null;
        try {
            url = new URL(urlLocation);
            con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000 /* milliseconds */);
            con.setConnectTimeout(15000 /* milliseconds */);
            con.setRequestMethod("GET");
            con.setDoInput(true);
            // Start the query
            con.connect();
            is = con.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
}
