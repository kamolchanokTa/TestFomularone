package main;

/**
 * Created by u6037291 on 11/24/2016.
 */
public class FinishingStats {
    private final int teamId;
    private final float speed;
    private final float time;

    public FinishingStats(int teamId, float speed, float time) {
        this.teamId = teamId;
        this.speed = speed;
        this.time = time;
    }

    public int getTeamId() {
        return teamId;
    }

    public float getSpeed() {
        return speed;
    }

    public float getTime() {
        return time;
    }

}
