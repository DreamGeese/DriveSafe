/*DriveSafe
Making driving safer with the Nod Ring
Created by Serge Babayan and Steven Li during Wearhacks Toronto 2015
LICENCE: MIT
Github: https://github.com/DreamGeese/DriveSafe
 */

package dreamgeese.drivesafe;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.openspatial.OpenSpatialService;

public class MainActivity extends ActionBarActivity {
    OpenSpatialController OpenSpatialController=new OpenSpatialController(); //manages the connection to the nod ring
    private AudioManager myAudioManager;//for controlling the audio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creates a service that talks with the nod ring
        bindService(new Intent(this, OpenSpatialService.class), OpenSpatialController.getServiceConnection(), BIND_AUTO_CREATE);

        //for setting the volume
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        //for bluetooth connection stuff
        BluetoothController mBluetoothController=new BluetoothController();
        mBluetoothController.checkBluetoothStatus(this); //informs the user if their bluetooth is turned off
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(OpenSpatialController.getServiceConnection());
    }

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

    public void plus (View view) {
        myAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }

    public void minus (View view) {
        myAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }
}
