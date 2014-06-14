import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by jrm2k6 on 6/8/14.
 */
public class BoardView extends JFrame{
    private final int numberColumns;
    private final int numberRows;

    // 0 base-indexed
    ArrayList<JPanel> panelTiles = new ArrayList<JPanel>();

    JPanel panel;

    public BoardView(int numberRows, int numberColumns) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.numberColumns = numberColumns;
        this.numberRows = numberRows;
        create();
        display();
    }

    private void display() {
        this.pack();
        this.setVisible(true);
    }

    private void create() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(numberRows, numberColumns));

        for (int i=0; i< numberRows; i++) {
            for (int j=0; j< numberColumns; j++) {
                JPanel panelTile = new JPanel();
                panelTile.setPreferredSize(new Dimension(200, 200));
                panelTile.setBorder(BorderFactory.createLineBorder(Color.black));
                panelTile.setBackground(Color.WHITE);
                panelTile.setName(i+"_"+j);
                panelTiles.add(panelTile);
                panel.add(panelTile);
            }
        }

        this.setContentPane(panel);
    }

    public void attachMouseListener(MouseListener mouseListener) {
        for (JPanel tile : panelTiles) {
            tile.addMouseListener(mouseListener);
        }
    }

    public void updateTile(Point positionToModify, Color color) {
        int position = positionToModify.x * this.numberColumns + positionToModify.y;
        panelTiles.get(position).setBackground(color);
    }

    public void showWinningOverlay(State state) {
        JOptionPane.showMessageDialog(this, "We have a winner!\n Team " + state.getColor() );
    }
}
