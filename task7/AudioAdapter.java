interface MediaPlayer {
    void play(String format, String file);
}

class AdvancedAudioPlayer {
    public void playMp4(String file) {
        System.out.println("Playing MP4 file: " + file);
    }
    
    public void playVlc(String file) {
        System.out.println("Playing VLC file: " + file);
    }
}

public class AudioAdapter implements MediaPlayer {
    private final AdvancedAudioPlayer advancedPlayer;

    public AudioAdapter() {
        this.advancedPlayer = new AdvancedAudioPlayer();
    }

    @Override
    public void play(String format, String file) {
        if ("mp4".equalsIgnoreCase(format)) {
            advancedPlayer.playMp4(file);
        } else if ("vlc".equalsIgnoreCase(format)) {
            advancedPlayer.playVlc(file);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    public static void main(String[] args) {
        String studentId = "25103061";
        System.out.println("Student ID: " + studentId);

        MediaPlayer player = new AudioAdapter();
        
        player.play("mp4", "test_" + studentId + ".mp4");
        player.play("vlc", "test_" + studentId + ".vlc");
    }
}
