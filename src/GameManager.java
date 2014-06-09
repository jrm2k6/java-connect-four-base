import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by jrm2k6 on 6/9/14.
 */
public class GameManager {
    private BoardModel boardChips;
    private BoardView boardView;
    private RoundManager roundManager;

    public GameManager(BoardModel boardChips, BoardView boardView, RoundManager roundManager) {
        this.boardChips = boardChips;
        this.boardView = boardView;
        this.roundManager = roundManager;

        this.boardView.attachMouseListener(new GridMouseListener());
    }

    private class GridMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel clickedPanel = (JPanel) e.getSource();
            int teamNumberPlaying = roundManager.getTeamNumberPlaying() - 1;
            int columnClicked = Integer.parseInt(clickedPanel.getName().split("_")[1]);
            Point positionToModify = boardChips.findSpot(columnClicked);

            if (positionToModify.x > -1) {
                Chip newChip = new Chip(positionToModify.x, positionToModify.y);
                boardChips.addChip(newChip, teamNumberPlaying);
                boardChips.updateConnections(newChip);
                boardView.updateTile(positionToModify, State.values()[teamNumberPlaying].getColor());
                roundManager.nextTurn();
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}