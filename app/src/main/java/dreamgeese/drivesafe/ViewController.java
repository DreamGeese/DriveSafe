package dreamgeese.drivesafe;

import android.app.Activity;
import android.widget.TextView;

import net.openspatial.GestureEvent;

public class ViewController {

    public static void setDisplayConnected(Activity currentActivity){
        TextView connection_status = (TextView)currentActivity.findViewById(R.id.connection_status);
        connection_status.setText("Connected");
    }
    public static void setDisplayDisconnected(Activity currentActivity){
        TextView connection_status = (TextView)currentActivity.findViewById(R.id.connection_status);
        connection_status.setText("Not Connected");
    }

    public static void showDetectedGestures(Activity currentActivity,GestureEvent gEvent){
        TextView detected_gesture = (TextView)currentActivity.findViewById(R.id.detected_gesture);
        detected_gesture.setText(gEvent.gestureEventType.name());
    }

}
