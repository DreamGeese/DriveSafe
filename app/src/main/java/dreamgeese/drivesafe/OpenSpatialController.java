package dreamgeese.drivesafe;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import net.openspatial.ButtonEvent;
import net.openspatial.GestureEvent;
import net.openspatial.OpenSpatialEvent;
import net.openspatial.OpenSpatialException;
import net.openspatial.OpenSpatialPointerEvent;
import net.openspatial.OpenSpatialService;
import net.openspatial.PointerEvent;

public class OpenSpatialController {
    private static final String NAME = "DriveSafe";
    private ServiceConnection mOpenSpatialServiceConnection;
    private OpenSpatialService mOpenSpatialService;
    private VolumeController volumeController;

    public OpenSpatialController(VolumeController volumeCtrl){
        volumeController=volumeCtrl;
        //Creates a background service that listens for events in the nod ring
          mOpenSpatialServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mOpenSpatialService = ((OpenSpatialService.OpenSpatialServiceBinder)service).getService();
                mOpenSpatialService.getConnectedDevices();

                mOpenSpatialService.initialize(NAME, new OpenSpatialService.OpenSpatialServiceCallback() {
                    //when a connection has been made to the nod ring
                    @Override
                    public void deviceConnected(final BluetoothDevice bluetoothDevice) {
                        try {
                            //capture all gesture events
                            mOpenSpatialService.registerForGestureEvents(bluetoothDevice, new OpenSpatialEvent.EventListener() {
                                @Override
                                public void onEventReceived(OpenSpatialEvent event) { //when a new gesture event has been received
                                    GestureEvent gEvent = (GestureEvent) event;
                                    handleGestureEvent(gEvent); //handles the gesture events
                                    Log.d(NAME, gEvent.gestureEventType + "");
                                }
                            });

                            //capture all gesture events
                            mOpenSpatialService.registerForButtonEvents(bluetoothDevice, new OpenSpatialEvent.EventListener() {
                                @Override
                                public void onEventReceived(OpenSpatialEvent event) { //when a new button event has been received
                                    ButtonEvent bEvent = (ButtonEvent)event;
                                    handleTouchEvent(bEvent); //handles the button events
                                    Log.e(NAME,bEvent.buttonEventType+"");
                                }
                            });
                        } catch (OpenSpatialException e) {
                            Log.e(NAME, "Could Not Register for Gesture Events" + e);
                        }
                    }

                    @Override
                    public void gestureEventRegistrationResult(BluetoothDevice bluetoothDevice, int i) {
                        Log.d(NAME, "Gesture Event Registration Result: "+i);
                    }

                    @Override
                    public void deviceDisconnected(BluetoothDevice bluetoothDevice) {
                    }

                    @Override
                    public void buttonEventRegistrationResult(BluetoothDevice bluetoothDevice, int i) {
                    }

                    @Override
                    public void pointerEventRegistrationResult(BluetoothDevice bluetoothDevice, int i) {
                    }

                    @Override
                    public void pose6DEventRegistrationResult(BluetoothDevice bluetoothDevice, int i) {
                    }

                    @Override
                    public void motion6DEventRegistrationResult(BluetoothDevice bluetoothDevice, int i) {
                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mOpenSpatialService = null;
            }
        };
    }//end of constructor

    private void handleGestureEvent(GestureEvent gEvent){
        if(gEvent.gestureEventType==GestureEvent.GestureEventType.SWIPE_UP){ //for some reason a swipe down is recognized as a swipe app
            volumeController.raiseVolume();
        }
        else if(gEvent.gestureEventType==GestureEvent.GestureEventType.SWIPE_DOWN){
            volumeController.lowerVolume();
        }
        else if(gEvent.gestureEventType==GestureEvent.GestureEventType.CLOCKWISE_ROTATION){
                volumeController.playMusic();
        }
        else if(gEvent.gestureEventType==GestureEvent.GestureEventType.COUNTERCLOCKWISE_ROTATION){
                volumeController.pauseMusic();
        }
        else if(gEvent.gestureEventType==GestureEvent.GestureEventType.SWIPE_RIGHT){
            volumeController.nextSong();
        }
        else if(gEvent.gestureEventType==GestureEvent.GestureEventType.SWIPE_LEFT){
            volumeController.previousSong();
        }
    }
    public void handleTouchEvent(ButtonEvent bEvent){
        if(bEvent.buttonEventType.equals("TOUCH2_DOWN")){

        }
    }

    public ServiceConnection getServiceConnection(){
        return mOpenSpatialServiceConnection;
    }
}
