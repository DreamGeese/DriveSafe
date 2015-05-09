/*DriveSafe
Making driving safer with the Nod Ring
Created by Serge Babayan and Steven Li during Wearhacks Toronto 2015
LICENCE: MIT
Github: https://github.com/DreamGeese/DriveSafe
 */

package dreamgeese.drivesafe;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import net.openspatial.*;

public class MainActivity extends ActionBarActivity {
    public static final String TAG = "DriveSafe";
    OpenSpatialService mOpenSpatialService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(new Intent(this, OpenSpatialService.class), mOpenSpatialServiceConnection, BIND_AUTO_CREATE);

        BluetoothController mBluetoothController=new BluetoothController();
        mBluetoothController.checkBluetoothStatus(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(mOpenSpatialServiceConnection);
    }

    //Creates a background service that listens for events in the nod ring
    private ServiceConnection mOpenSpatialServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mOpenSpatialService = ((OpenSpatialService.OpenSpatialServiceBinder)service).getService();
            mOpenSpatialService.getConnectedDevices();

            mOpenSpatialService.initialize(TAG, new OpenSpatialService.OpenSpatialServiceCallback() {
                //when a connection has been made to the nod ring
                @Override
                public void deviceConnected(final BluetoothDevice bluetoothDevice) {
                    try {
                        //capture all gesture events
                        mOpenSpatialService.registerForGestureEvents(bluetoothDevice, new OpenSpatialEvent.EventListener() {
                            @Override
                            public void onEventReceived(OpenSpatialEvent event) { //when a new gesture event has been received
                                GestureEvent gEvent = (GestureEvent) event;
                                Log.d(TAG, gEvent.gestureEventType+""); //DO STUFF WITH THE EVENT HERE
                            }
                        });
                    } catch (OpenSpatialException e) {
                        Log.e(TAG, "Could Not Register for Gesture Events" + e);
                    }
                }

                @Override
                public void gestureEventRegistrationResult(BluetoothDevice bluetoothDevice, int i) {
                    Log.d(TAG, "Gesture Event Registration Result: "+i);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
