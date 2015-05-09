package dreamgeese.drivesafe;


import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.view.View;

public class VolumeController {
    private AudioManager myAudioManager;
    private Activity currentActivity;

    public VolumeController(Activity currActivity){
        currentActivity=currActivity;
        myAudioManager = (AudioManager)currentActivity.getSystemService(Context.AUDIO_SERVICE);
        currentActivity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    public void raiseVolume () {
        myAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }

    public void lowerVolume () {
        myAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }
}
