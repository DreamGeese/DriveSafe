package dreamgeese.drivesafe;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

public class BluetoothController {
    private BluetoothAdapter mBluetoothAdapter;

    public BluetoothController(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }
    public void checkBluetoothStatus(Activity CurrentActivity){
        //Asks the user to turn on bluetooth if it's off
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }
        else if (!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            final int  REQUEST_ENABLE_BT=1;
            CurrentActivity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    };
}
