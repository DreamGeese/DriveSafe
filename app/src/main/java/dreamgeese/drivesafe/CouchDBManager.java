package dreamgeese.drivesafe;

import android.app.Activity;

import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;
import com.couchbase.lite.*;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;
import java.io.IOException;

public class CouchDBManager {
    private Manager manager;
    private String TAG="COUCHDB";


    public CouchDBManager(Activity currActivity){
        try {
            manager = new Manager(new AndroidContext(currActivity), Manager.DEFAULT_OPTIONS);
            Log.d(TAG, "CouchDB Manager Created");
        } catch (IOException e) {
            Log.e(TAG, "Could not create CouchDB Manager Object"+e);
        }
    }
}
