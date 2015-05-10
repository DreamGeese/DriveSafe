package dreamgeese.drivesafe;

import android.app.Activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UserSettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_settings);

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
    }
}
