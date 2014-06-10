public class Main {
    public static void main(String[] args) {
        final int DIMENSION = 7;
        BoardView boardView = new BoardView(DIMENSION);
        BoardModel boardModel = new BoardModel(DIMENSION);
        RoundManager roundManager = new RoundManager();
        Opponent opponent = new Opponent(new RandomStrategy(DIMENSION));

        GameManager manager = new GameManager(boardModel, boardView, roundManager);
        manager.setOpponent(opponent);
    }
}