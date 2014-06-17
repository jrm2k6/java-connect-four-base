import javax.swing.*;
import java.awt.*;

/**
 * Created by jrm2k6 on 6/15/14.
 */
public class MinimaxView extends JFrame {
    private Chip[][] chips;

    public MinimaxView(Chip[][] chips) {
        super();
        this.chips = chips;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        create();
        display();
    }

    private void display() {
        this.pack();
        this.setVisible(true);
    }

    private void create() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(chips.length, chips[0].length));

        for (int i=0; i< chips.length; i++) {
            for (int j=0; j< chips[i].length; j++) {
                JPanel panelTile = new JPanel();
                panelTile.setPreferredSize(new Dimension(50, 50));
                panelTile.setBorder(BorderFactory.createLineBorder(Color.black));
                if (chips[i][j] == null) {
                    panelTile.setBackground(Color.WHITE);
                } else {
                    panelTile.setBackground(chips[i][j].state.getColor());
                }
                panelTile.setName(i+"_"+j);
                panel.add(panelTile);
            }
        }

        this.setContentPane(panel);
    }
}
