package game.shellda;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;


import javax.media.CannotRealizeException;
import javax.media.Controller;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.GainControl;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;

/*
 * Music player.
 * 
 * You can play .wav files.
 * If like me, you have mp3 files, you can transcode them:
 * 
 *    sudo apt-get install mpg123
 * 
 * Then to convert mp3 to wav (using -w option)
 *    mpg123 -w output.wav input.mp3
 *
 * Title:   Future RPG                      Artist: Eric Matyas
 * Comment: QMAYT1400787                    Album:  
 * Year:    6                               Genre:  Soundtrack, 
*/

public class Music {
  Player m_player;
  File m_file;
  GainControl m_ctr;

  public Music(File file) throws IOException, NoPlayerException, CannotRealizeException {
    m_file = file;
    load();
  }

  private void load() throws IOException, NoPlayerException, CannotRealizeException {
    URL url;
    url = m_file.toURL();

    m_player = Manager.createRealizedPlayer(url);
    m_ctr = m_player.getGainControl();
    m_player.addControllerListener(new ControllerListener() {

      @Override
      public void controllerUpdate(ControllerEvent arg0) {
        Controller ctr = arg0.getSourceController();
        if (arg0 instanceof EndOfMediaEvent) {
          m_player.setMediaTime(new Time(0));
          m_player.start();
        }
      }
    });
  }

  public Component getControls() {
    Component c;
    c = m_player.getControlPanelComponent();
    Dimension d = c.getPreferredSize();
    d.setSize(d.getWidth() + 100, d.getHeight());
    c.setPreferredSize(d);
    return c;
  }

  /**
   * Mute or unmute the signal associated with this GainControl. 
   * Calling setMute(true) on an object that is already muted is ignored, 
   * as is calling setMute(false) on an object that is not currently muted. 
   * Going from a muted to an unmuted state doesn't effect the gain. 
   * @param mute
   */
  public void mute(boolean mute) {
    m_ctr.setMute(mute);
  }

  /**
   * Set the gain using a floating point scale with values between 0.0 and 1.0.
   * @param level
   */
  public void setLevel(float level) {
    m_ctr.setLevel(level);
  }

  public void start() {
    m_player.start();
  }

  public void stop() {
    m_player.stop();
  }

}
