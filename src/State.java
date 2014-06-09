import java.awt.*;

/**
 * Created by jrm2k6 on 6/9/14.
 */
public enum State {
    PLAYED_TEAM_1(Color.YELLOW),
    PLAYED_TEAM_2(Color.RED),
    NOT_PLAYED(Color.WHITE);

    private final Color color;

    State(Color color) {
        this.color = color;
    }

    Color getColor() {
        return this.color;
    }
}