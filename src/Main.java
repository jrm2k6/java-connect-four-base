public class Main {
    public static void main(String[] args) {
        final int NUMBER_COLUMNS = 7;
        final int NUMBER_ROWS= 6;
        BoardView boardView = new BoardView(NUMBER_ROWS, NUMBER_COLUMNS);
        BoardModel boardModel = new BoardModel(NUMBER_ROWS, NUMBER_COLUMNS);
        RoundManager roundManager = new RoundManager();
        ConnectFourEventDispatcher eventDispatcher = new ConnectFourEventDispatcher();
        //Opponent opponent = new Opponent(new RandomStrategy(NUMBER_COLUMNS, 2, eventDispatcher));
        Opponent opponent = new Opponent(new DefensiveStrategy(boardModel, 2, eventDispatcher));
        //Opponent opponent = new Opponent(new MinimaxStrategy(boardModel, 2, eventDispatcher));
        GameManager manager = new GameManager(boardModel, boardView, roundManager, eventDispatcher);
        manager.setOpponent(opponent);
    }
}