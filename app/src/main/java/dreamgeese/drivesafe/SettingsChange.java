package dreamgeese.drivesafe;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class SettingsChange extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner setting;
    String eventType;

    public SettingsChange(Spinner Setting, String type){
        setting=Setting;
        eventType=type;
        setting.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        if (eventType.equals("play_music")) {
            UserSettings.playMusic=setting.getSelectedItem().toString();
            UserSettings.changeSettingDB("playMusic",setting.getSelectedItem().toString());
        }
        else if (eventType.equals("pause_music")) {
            UserSettings.pauseMusic=setting.getSelectedItem().toString();
            UserSettings.changeSettingDB("pauseMusic",setting.getSelectedItem().toString());
        }
        else if (eventType.equals("raise_volume")) {
            UserSettings.raiseVolume=setting.getSelectedItem().toString();
            UserSettings.changeSettingDB("raiseVolume",setting.getSelectedItem().toString());
        }
        else if (eventType.equals("lower_volume")) {
            UserSettings.lowerVolume=setting.getSelectedItem().toString();
            UserSettings.changeSettingDB("lowerVolume",setting.getSelectedItem().toString());
        }
        else if (eventType.equals("next_song")) {
            UserSettings.nextSong=setting.getSelectedItem().toString();
            UserSettings.changeSettingDB("nextSong",setting.getSelectedItem().toString());
        }
        else if (eventType.equals("previous_song")) {
            UserSettings.previousSong=setting.getSelectedItem().toString();
            UserSettings.changeSettingDB("previousSong",setting.getSelectedItem().toString());
        }
        else if (eventType.equals("accept_call")) {
            UserSettings.acceptCall=setting.getSelectedItem().toString();
            UserSettings.changeSettingDB("acceptCall",setting.getSelectedItem().toString());
        }
        else if (eventType.equals("reject_call")) {
            UserSettings.rejectCall=setting.getSelectedItem().toString();
            UserSettings.changeSettingDB("rejectCall",setting.getSelectedItem().toString());
        }
        else if (eventType.equals("take_call")) {
            UserSettings.callNumber=setting.getSelectedItem().toString();
            UserSettings.changeSettingDB("callNumber",setting.getSelectedItem().toString());
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}