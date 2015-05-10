package dreamgeese.drivesafe;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import net.openspatial.GestureEvent;

public class ViewController {

    public static void setDisplayConnected(Activity currentActivity){
        TextView connection_status = (TextView)currentActivity.findViewById(R.id.connection_status);
        connection_status.setText("Connected");
        connection_status.setTextColor(Color.parseColor("#27a32d"));
    }
    public static void setDisplayDisconnected(Activity currentActivity){
        TextView connection_status = (TextView)currentActivity.findViewById(R.id.connection_status);
        connection_status.setText("Not Connected");
        connection_status.setTextColor(Color.parseColor("#e51212"));
    }

    public static void showDetectedGestures(Activity currentActivity,GestureEvent gEvent){
        TextView detected_gesture = (TextView)currentActivity.findViewById(R.id.detected_gesture);
        detected_gesture.setText(gEvent.gestureEventType.name());
    }

}
