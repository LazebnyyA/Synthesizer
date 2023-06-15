import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import java.util.Scanner;

public class Main {
    private static final int C = 60;
    private static final int D = 62;
    private static final int E = 64;
    private static final int F = 65;
    private static final int G = 67;
    private static final int A = 69;
    private static final int B = 70;

    public static void main(String[] args) throws Exception {

        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();

        Receiver receiver = synthesizer.getReceiver();

        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();


        while (!text.equals("exit")) {
            String[] music = text.split(" ");

            for (int i = 0; i < music.length; i++) {

                int noteId = convertNote(music[i]);
                playMusic(receiver, noteId);
            }
            text = scanner.nextLine();
        }
    }

    public static void playMusic(Receiver receiver, int noteId) throws Exception {
        ShortMessage message = new ShortMessage(ShortMessage.NOTE_ON, noteId, 100);
        receiver.send(message, -1);
        Thread.sleep(1000);
        ShortMessage offMsg = new ShortMessage(ShortMessage.NOTE_OFF, noteId, 100);
        receiver.send(offMsg, -1);
    }

    public static int convertNote(String note) {
        switch (note) {
            case "1":
                return A;
            case "2":
                return B;
            case "3":
                return C;
            case "4":
                return D;
            case "5":
                return E;
            case "6":
                return F;
            case "7":
                return G;
            default:
                return A;
        }
    }
}
