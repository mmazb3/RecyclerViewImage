package me.abetayev.recyclerviewimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;

    final String flickrUrl = "https://api.flickr.com/services/feeds/photos_public.gne?tags=brooklyn&tagmode=any&format=json&nojsoncallback=1";
//    ArrayList<String> imageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        downloadJson(flickrUrl);

        mRecyclerView = findViewById(R.id.recycler_view);
    }

    void downloadJson(String url) {
        Log.d(TAG, "downloadJson: started " + url);

        // AsyncHttpClient belongs to the loopj dependency.
        AsyncHttpClient client = new AsyncHttpClient();
        // Making an HTTP GET request by providing a URL.
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JsonParser parser = new JsonParser();
                ArrayList<String> imageUrls = parser.fromJson(response.toString());
                displayImages(imageUrls);
//                Log.d(TAG, "Success! JSON: " + response.toString());
//                Log.d(TAG, "onSuccess: " + imageUrls);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.e(TAG, "Fail " + e.toString());
                Log.d(TAG, "Status code " + statusCode);
                Log.d(TAG, "Here's what we got instead " + response.toString());
            }

        });
    }

    void displayImages(ArrayList<String> imageUrls) {
        mAdapter = new RVAdapter(imageUrls);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
