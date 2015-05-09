package dreamgeese.drivesafe;


import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;

public class VolumeController {
    private AudioManager mAudioManager;
    private Activity currentActivity;

    public VolumeController(Activity currActivity){
        currentActivity=currActivity;
        mAudioManager = (AudioManager)currentActivity.getSystemService(Context.AUDIO_SERVICE);
        currentActivity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    public void raiseVolume () {
        mAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }

    public void lowerVolume () {
        mAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }

    public void toggleMusic () {
         Log.e("Media Control", "toggle pause/play");

        long eventtime = SystemClock.uptimeMillis() - 1;
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
        mAudioManager.dispatchMediaKeyEvent(downEvent);

        eventtime++;
        KeyEvent upEvent = new KeyEvent(eventtime,eventtime,KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
        mAudioManager.dispatchMediaKeyEvent(upEvent);

    }

    public void playMusic () {
        Log.e("Media Control", "play");

        long eventtime = SystemClock.uptimeMillis() - 1;
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY, 0);
        mAudioManager.dispatchMediaKeyEvent(downEvent);

        eventtime++;
        KeyEvent upEvent = new KeyEvent(eventtime,eventtime,KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_PLAY, 0);
        mAudioManager.dispatchMediaKeyEvent(upEvent);

    }
    public void pauseMusic () {
        Log.e("Media Control", "pause");

        long eventtime = SystemClock.uptimeMillis() - 1;
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PAUSE,0);
        mAudioManager.dispatchMediaKeyEvent(downEvent);

        eventtime++;
        KeyEvent upEvent = new KeyEvent(eventtime,eventtime,KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_PAUSE, 0);
        mAudioManager.dispatchMediaKeyEvent(upEvent);

    }
    public void nextSong () {
        Log.e("Media Control", "next");

        long eventtime = SystemClock.uptimeMillis() - 1;
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_NEXT, 0);
        mAudioManager.dispatchMediaKeyEvent(downEvent);

        eventtime++;
        KeyEvent upEvent = new KeyEvent(eventtime,eventtime,KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_NEXT, 0);
        mAudioManager.dispatchMediaKeyEvent(upEvent);

    }
    public void previousSong () {
        Log.e("Media Control", "previous");

        long eventtime = SystemClock.uptimeMillis() - 1;
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PREVIOUS,0);
        mAudioManager.dispatchMediaKeyEvent(downEvent);

        eventtime++;
        KeyEvent upEvent = new KeyEvent(eventtime,eventtime,KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_PREVIOUS, 0);
        mAudioManager.dispatchMediaKeyEvent(upEvent);
    }
}
