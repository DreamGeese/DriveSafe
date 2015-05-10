package dreamgeese.drivesafe;

import com.couchbase.lite.Database;

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


    }
}
