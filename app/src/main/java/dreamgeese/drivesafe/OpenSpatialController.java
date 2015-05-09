package dreamgeese.drivesafe;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import net.openspatial.GestureEvent;
import net.openspatial.OpenSpatialEvent;
import net.openspatial.OpenSpatialException;
import net.openspatial.OpenSpatialService;

public class OpenSpatialController {
    private static final String NAME = "DriveSafe";
    private ServiceConnection mOpenSpatialServiceConnection;
    private OpenSpatialService mOpenSpatialService;

    public OpenSpatialController(){
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
                                    Log.d(NAME, gEvent.gestureEventType + ""); //DO STUFF WITH THE EVENT HERE
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

    public ServiceConnection getServiceConnection(){
        return mOpenSpatialServiceConnection;
    }
}
