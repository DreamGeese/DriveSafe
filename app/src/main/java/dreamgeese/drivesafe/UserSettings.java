package dreamgeese.drivesafe;

import android.util.Log;

import com.couchbase.lite.Database;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;

import java.util.Iterator;
import java.util.Map;

public class UserSettings {
    public static String telephoneNumber=null;
    public static String playMusic="CLOCKWISE_ROTATION";
    public static String pauseMusic="COUNTERCLOCKWISE_ROTATION";
    public static String callNumber="TACTILE1_UP";
    public static String acceptCall="TOUCH2_UP";
    public static String rejectCall="TACTILE0_UP";
    public static String raiseVolume="SWIPE_UP";
    public static String lowerVolume="SWIPE_DOWN";
    public static String nextSong="SWIPE_RIGHT";
    public static String previousSong="SWIPE_LEFT";

    public UserSettings(Database database){
        // Let's find the documents that have conflicts so we can resolve them:
        Query query = database.createAllDocumentsQuery();

        try{
            QueryEnumerator result = query.run();
            for (Iterator<QueryRow> it = result; it.hasNext(); ) {
                QueryRow row = it.next();
                Map<String, Object> document=row.getDocumentProperties();
                Log.e("QUERY",document.toString());
            }
        }catch(Exception e){
            Log.e("QUERY","Could not query the database. Using the default values");
        }

    }
}
