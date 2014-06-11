public class Main {
    public static void main(String[] args) {
        final int DIMENSION = 7;
        BoardView boardView = new BoardView(DIMENSION);
        BoardModel boardModel = new BoardModel(DIMENSION);
        RoundManager roundManager = new RoundManager();
        ConnectFourEventDispatcher eventDispatcher = new ConnectFourEventDispatcher();
        Opponent opponent = new Opponent(new RandomStrategy(DIMENSION, 2, eventDispatcher));
        GameManager manager = new GameManager(boardModel, boardView, roundManager, eventDispatcher);
        manager.setOpponent(opponent);
    }
}