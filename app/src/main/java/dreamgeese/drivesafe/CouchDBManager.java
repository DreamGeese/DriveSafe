package dreamgeese.drivesafe;

import android.app.Activity;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;
import java.io.IOException;

public class CouchDBManager {
    private Manager manager;
    private String TAG="COUCHDB";
    private String databaseName="com.drivesafe.couchdb";
    private Database database;

    public CouchDBManager(Activity currActivity){
        try {
            manager = new Manager(new AndroidContext(currActivity), Manager.DEFAULT_OPTIONS);
            Log.d(TAG, "CouchDB Manager Created");
        } catch (IOException e) {
            Log.e(TAG, "Could not create CouchDB Manager Object"+e);
        }
        createDatabase(databaseName);
    }

    private void createDatabase(String databaseName){
        // create a name for the database and make sure the name is legal
        if (!Manager.isValidDatabaseName(databaseName)) {
            Log.e(TAG, "Could not create CouchDB database because the name did not pass validation");
            return;
        }
        try {
            database = manager.getDatabase(databaseName);
            Log.d (TAG, "CouchDB Database Successfully Created");
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "Could not create CouchDB Database"+e);
            return;
        }
    }

    private Database getDatabase(){
        return database;
    }
}
