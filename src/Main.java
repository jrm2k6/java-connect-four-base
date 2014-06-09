public class Main {
    public static void main(String[] args) {
        final int DIMENSION = 7;
        BoardView boardView = new BoardView(DIMENSION);
        BoardModel boardModel = new BoardModel(DIMENSION);
        RoundManager roundManager = new RoundManager();

        GameManager manager = new GameManager(boardModel, boardView, roundManager);
    }
}