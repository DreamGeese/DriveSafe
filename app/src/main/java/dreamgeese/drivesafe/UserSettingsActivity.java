package dreamgeese.drivesafe;

import android.app.Activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class UserSettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_settings);

        //displays the current value of the phone number field
        if(UserSettings.telephoneNumber!=null){
            EditText phone_number_field = (EditText) findViewById(R.id.phone_number_field);
            phone_number_field.setText(UserSettings.telephoneNumber);
        }

        Spinner play_music_event = (Spinner) findViewById(R.id.play_music_event);
        Spinner pause_music_event = (Spinner) findViewById(R.id.pause_music_event);
        Spinner raise_volume_event = (Spinner) findViewById(R.id.raise_volume_event);
        Spinner lower_volume_event = (Spinner) findViewById(R.id.lower_volume_event);
        Spinner next_song_event = (Spinner) findViewById(R.id.next_song_event);
        Spinner previous_song_event = (Spinner) findViewById(R.id.previous_song_event);
        Spinner accept_call_event = (Spinner) findViewById(R.id.accept_call_event);
        Spinner reject_call_event = (Spinner) findViewById(R.id.reject_call_event);
        Spinner take_call_event = (Spinner) findViewById(R.id.take_call_event);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gesture_events, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        play_music_event.setAdapter(adapter);
        pause_music_event.setAdapter(adapter);
        raise_volume_event.setAdapter(adapter);
        lower_volume_event.setAdapter(adapter);
        next_song_event.setAdapter(adapter);
        previous_song_event.setAdapter(adapter);
        accept_call_event.setAdapter(adapter);
        reject_call_event.setAdapter(adapter);
        take_call_event.setAdapter(adapter);

        ArrayAdapter play_music_event_adapter = (ArrayAdapter) play_music_event.getAdapter();
        ArrayAdapter pause_music_event_adapter = (ArrayAdapter) pause_music_event.getAdapter();
        ArrayAdapter raise_volume_event_adapter = (ArrayAdapter) raise_volume_event.getAdapter();
        ArrayAdapter lower_volume_event_adapter = (ArrayAdapter) lower_volume_event.getAdapter();
        ArrayAdapter next_song_event_adapter = (ArrayAdapter) next_song_event.getAdapter();
        ArrayAdapter previous_song_event_adapter = (ArrayAdapter) previous_song_event.getAdapter();
        ArrayAdapter accept_call_event_adapter = (ArrayAdapter) accept_call_event.getAdapter();
        ArrayAdapter reject_call_event_adapter = (ArrayAdapter) reject_call_event.getAdapter();
        ArrayAdapter take_call_event_adapter = (ArrayAdapter) take_call_event.getAdapter();

        int spinnerPosition = play_music_event_adapter.getPosition(UserSettings.playMusic);
        play_music_event.setSelection(spinnerPosition);//set the default according to value

        spinnerPosition = pause_music_event_adapter.getPosition(UserSettings.pauseMusic);
        pause_music_event.setSelection(spinnerPosition);

        spinnerPosition = raise_volume_event_adapter.getPosition(UserSettings.raiseVolume);
        raise_volume_event.setSelection(spinnerPosition);

        spinnerPosition = lower_volume_event_adapter.getPosition(UserSettings.lowerVolume);
        lower_volume_event.setSelection(spinnerPosition);

        spinnerPosition = next_song_event_adapter.getPosition(UserSettings.nextSong);
        next_song_event.setSelection(spinnerPosition);

        spinnerPosition = previous_song_event_adapter.getPosition(UserSettings.previousSong);
        previous_song_event.setSelection(spinnerPosition);

        spinnerPosition = accept_call_event_adapter.getPosition(UserSettings.acceptCall);
        accept_call_event.setSelection(spinnerPosition);

        spinnerPosition = reject_call_event_adapter.getPosition(UserSettings.rejectCall);
        reject_call_event.setSelection(spinnerPosition);

        spinnerPosition = take_call_event_adapter.getPosition(UserSettings.callNumber);
        take_call_event.setSelection(spinnerPosition);



    }
}
