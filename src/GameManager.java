import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by jrm2k6 on 6/9/14.
 */
public class GameManager implements MoveEventListener {
    private ConnectFourEventDispatcher connectFourEventDispatcher;
    private BoardModel boardChips;
    private BoardView boardView;
    private RoundManager roundManager;
    private WinDetector winDetector;
    private Opponent opponent;

    public GameManager(BoardModel boardChips, BoardView boardView, RoundManager roundManager, ConnectFourEventDispatcher eventDispatcher) {
        this.boardChips = boardChips;
        this.boardView = boardView;
        this.roundManager = roundManager;
        this.winDetector = new WinDetector(boardChips);
        this.boardView.attachMouseListener(new GridMouseListener());
        this.connectFourEventDispatcher = eventDispatcher;
        this.connectFourEventDispatcher.addEventListener(this);
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
        this.connectFourEventDispatcher.addEventListener(this.opponent);
    }

    @Override
    public void moveEventReceived(MoveEvent event) {
        playMove(event.getColumnPlayed());
    }

    public void playMove(int columnPlayed) {
        int teamNumberPlaying = roundManager.getTeamNumberPlaying() - 1;
        Point positionToModify = boardChips.findSpot(columnPlayed);

        if (positionToModify.x > -1) {
            Chip newChip = new Chip(positionToModify.x, positionToModify.y);
            boardChips.addChip(newChip, teamNumberPlaying);
            boardChips.updateConnections(newChip);
            boardView.updateTile(positionToModify, State.values()[teamNumberPlaying].getColor());

            if (winDetector.checkIfWinner(newChip)) {
                boardView.showWinningOverlay(newChip.state);
            } else {
                roundManager.updateTeamPlayinNextRound();
                connectFourEventDispatcher.dispatchEvent(new RoundEvent(this, roundManager.getTeamNumberPlaying()));

            }
        } else {
            connectFourEventDispatcher.dispatchEvent(new ColumnFullEvent(this, columnPlayed));
        }
    }

    private class GridMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel clickedPanel = (JPanel) e.getSource();
            int columnClicked = Integer.parseInt(clickedPanel.getName().split("_")[1]);
            playMove(columnClicked);
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
