package window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class ServerWorkPanel extends JPanel {
    private JTextArea textArea;
    private JButton playStop;

    public ServerWorkPanel(){
        textArea = new JTextArea();
        playStop = new JButton("PLAY");

        this.setLayout(new BorderLayout());
        this.add(textArea,BorderLayout.CENTER);
        //this.add(playStop,BorderLayout.SOUTH);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public JButton getPlayStop() {
        return playStop;
    }

    public void setPlayStop(JButton playStop) {
        this.playStop = playStop;
    }
}
