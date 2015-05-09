package dreamgeese.drivesafe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;

import java.io.IOException;

public class CallController {
    Activity currentActivity;
    private String emergencyDialNumber;
    private long timeSinceCall=0;
    private long timeSinceCallTimeout=2000;//in milliseconds

    public CallController(Activity currActivity){
        currentActivity=currActivity;
    }
    public CallController(Activity currActivity,String phonenumber){
        currentActivity=currActivity;
        emergencyDialNumber=phonenumber;
    }

    public void setNumber(String newNumber){
        emergencyDialNumber=newNumber;
    }
    public String getNumber(){
        return emergencyDialNumber;
    }
    public void acceptCall (final Activity currentActivity) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Runtime.getRuntime().exec("input keyevent " +
                            Integer.toString(KeyEvent.KEYCODE_HEADSETHOOK));
                } catch (IOException e) {
                    // Runtime.exec(String) had an I/O problem, try to fall back
                    String enforcedPerm = "android.permission.CALL_PRIVILEGED";
                    Intent btnDown = new Intent(Intent.ACTION_MEDIA_BUTTON).putExtra(
                            Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN,
                                    KeyEvent.KEYCODE_HEADSETHOOK));
                    Intent btnUp = new Intent(Intent.ACTION_MEDIA_BUTTON).putExtra(
                            Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP,
                                    KeyEvent.KEYCODE_HEADSETHOOK));

                    currentActivity.sendOrderedBroadcast(btnDown, enforcedPerm);
                    currentActivity.sendOrderedBroadcast(btnUp, enforcedPerm);
                }
            }

        }).start();
    }
    public void rejectCall (Activity CurrentActivity) {

    }

    public void callNumber (Activity currActivity) {
        if(emergencyDialNumber!=null){
            if(System.currentTimeMillis()-timeSinceCall>=timeSinceCallTimeout) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + emergencyDialNumber));
                currActivity.startActivity(callIntent);
                timeSinceCall=System.currentTimeMillis();
                Log.e("Afewewf", "calling callnumber mehtod");
            }

        }
    }
}
