/**
 * Created by jrm2k6 on 6/9/14.
 */
public class RoundManager {

    private int teamNumberPlaying;

    public RoundManager() {
        this.teamNumberPlaying = 1;
    }

    public void updateTeamPlayinNextRound() {
        teamNumberPlaying = (teamNumberPlaying == 1)? 2 : 1;
    }

    public int getTeamNumberPlaying() {
        return this.teamNumberPlaying;
    }
}
