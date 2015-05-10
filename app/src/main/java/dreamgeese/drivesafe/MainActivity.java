/*DriveSafe
Making driving safer with the Nod Ring
Created by Serge Babayan and Steven Li during Wearhacks Toronto 2015
LICENCE: MIT
Github: https://github.com/DreamGeese/DriveSafe
 */

package dreamgeese.drivesafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.openspatial.OpenSpatialService;

public class MainActivity extends Activity {
    OpenSpatialController OpenSpatialController;
    CouchDBManager CouchDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CouchDBManager=new CouchDBManager(this);
        UserSettings UserSettings=new UserSettings(CouchDBManager.getDatabase());


        //creates a service that talks with the nod ring
        VolumeController VolumeController=new VolumeController(this);
        CallController CallController =new CallController(this,"5195800179");
        OpenSpatialController=new OpenSpatialController(this,VolumeController,CallController); //manages the connection to the nod ring

        bindService(new Intent(this, OpenSpatialService.class), OpenSpatialController.getServiceConnection(), BIND_AUTO_CREATE);

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
            Intent intent = new Intent(this, UserSettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
