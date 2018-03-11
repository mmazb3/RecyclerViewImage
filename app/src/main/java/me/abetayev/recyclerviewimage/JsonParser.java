package me.abetayev.recyclerviewimage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mmazb3 on 10.03.2018.
 */

class JsonParser {
    private static final String TAG = "JsonParser";
    ArrayList<String> imageUrls = new ArrayList<>();

    ArrayList<String> fromJson(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            for (int i=0; i<itemsArray.length(); i++) {
                JSONObject object = itemsArray.getJSONObject(i);
                JSONObject media = object.getJSONObject("media");
                String imageUrl = media.getString("m");
                imageUrls.add(imageUrl);
            }
            return imageUrls;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
