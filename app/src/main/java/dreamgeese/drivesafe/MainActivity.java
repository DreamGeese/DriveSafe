package dreamgeese.drivesafe;

import android.bluetooth.BluetoothAdapter;
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

        //Asks the user to turn on bluetooth if it's off
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }
        else if (!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            final int  REQUEST_ENABLE_BT=1;
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(mOpenSpatialServiceConnection);
    }

    public void UpdateAngle(String ring, EulerAngle angle) {
        String logline = ring + " " + Double.toString(angle.roll)
                + " " + Double.toString(angle.pitch)
                + " " + Double.toString(angle.yaw);
        Log.d(TAG, logline);
    }

    private ServiceConnection mOpenSpatialServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mOpenSpatialService = ((OpenSpatialService.OpenSpatialServiceBinder)service).getService();
            // CODE FROM STEP FIVE GOES HERE

            mOpenSpatialService.getConnectedDevices();
            mOpenSpatialService.initialize(TAG, new OpenSpatialService.OpenSpatialServiceCallback() {
                @Override
                public void deviceConnected(final BluetoothDevice bluetoothDevice) {
                    try {
                        mOpenSpatialService.registerForPose6DEvents(bluetoothDevice, new OpenSpatialEvent.EventListener() {
                            @Override
                            public void onEventReceived(OpenSpatialEvent openSpatialEvent) {
                                String ringname = bluetoothDevice.getName();
                                Pose6DEvent event = (Pose6DEvent)openSpatialEvent;
                                EulerAngle angle = event.getEulerAngle();
                                UpdateAngle(ringname, angle);
                            }
                        });
                    } catch (OpenSpatialException e) {
                        Log.e(TAG, "Could not register for Pose6D event " + e);
                    }
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
                public void gestureEventRegistrationResult(BluetoothDevice bluetoothDevice, int i) {
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
