package dreamgeese.drivesafe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;

import java.io.IOException;
import java.lang.reflect.Method;

public class CallController {
    Activity currentActivity;
    private long timeSinceCall=0;
    private long timeSinceCallTimeout=2000;//in milliseconds

    public CallController(Activity currActivity){
        currentActivity=currActivity;
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
    public void rejectCall () {
            try {
                String serviceManagerName = "android.os.ServiceManager";
                String serviceManagerNativeName = "android.os.ServiceManagerNative";
                String telephonyName = "com.android.internal.telephony.ITelephony";
                Class<?> telephonyClass;
                Class<?> telephonyStubClass;
                Class<?> serviceManagerClass;
                Class<?> serviceManagerNativeClass;
                Method telephonyEndCall;
                Object telephonyObject;
                Object serviceManagerObject;
                telephonyClass = Class.forName(telephonyName);
                telephonyStubClass = telephonyClass.getClasses()[0];
                serviceManagerClass = Class.forName(serviceManagerName);
                serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
                Method getService = // getDefaults[29];
                        serviceManagerClass.getMethod("getService", String.class);
                Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);
                Binder tmpBinder = new Binder();
                tmpBinder.attachInterface(null, "fake");
                serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
                IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
                Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);
                telephonyObject = serviceMethod.invoke(null, retbinder);
                telephonyEndCall = telephonyClass.getMethod("endCall");
                telephonyEndCall.invoke(telephonyObject);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void callNumber (Activity currActivity) {
        if(UserSettings.telephoneNumber!=null){
            if(System.currentTimeMillis()-timeSinceCall>=timeSinceCallTimeout) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + UserSettings.telephoneNumber));
                currActivity.startActivity(callIntent);
                timeSinceCall=System.currentTimeMillis();
                Log.e("Afewewf", "calling callnumber mehtod");
            }

        }
    }
}
