package main;

/**
 * Created by Miruku on 11/24/2016.
 */
public class RacingTeam {
    private final int teamId;
    private final float maxSpeedMetersPerSecond;
    private final float accelerationMetersPerSecondSquare;

    private final float handlingFactor = 0.8f;
    private float currentPosition;
    private float currentSpeedMetersPerSecond;
    private boolean nitroUsed;

    /**
     * This constructor is used to create a team following standard rules - speed is 150 + 10i and acceleration is 2i
     * @param teamId
     */
    public RacingTeam(int teamId) {
        this(teamId, kphToMps(150f + 10f*teamId), 2f*teamId, -200f*(teamId-1f));
    }

    public RacingTeam(int teamId, float maxSpeedMetersPerSecond, float accelerationMetersPerSecondSquare, float startPosition) {
        this.teamId = teamId;
        this.maxSpeedMetersPerSecond = maxSpeedMetersPerSecond;
        this.accelerationMetersPerSecondSquare = accelerationMetersPerSecondSquare;
        currentPosition = startPosition;
        nitroUsed = false;
    }

    public int getTeamId() {
        return teamId;
    }

    public float getMaxSpeedMetersPerSecond() {
        return maxSpeedMetersPerSecond;
    }

    public float getAccelerationMetersPerSecondSquare() {
        return accelerationMetersPerSecondSquare;
    }

    public float getHandlingFactor() {
        return handlingFactor;
    }

    public float getCurrentPosition() {
        return currentPosition;
    }

    public float getCurrentSpeedMetersPerSecond() {
        return currentSpeedMetersPerSecond;
    }

    public RacingTeam setCurrentPosition(float currentPosition) {
        this.currentPosition = currentPosition;
        return this;
    }

    public RacingTeam setCurrentSpeedMetersPerSecond(float currentSpeedMetersPerSecond) {
        this.currentSpeedMetersPerSecond = currentSpeedMetersPerSecond;
        return this;
    }

    public void useHandle(){
        System.out.println("Team " + getTeamId() + " uses handle");
        this.currentSpeedMetersPerSecond = currentSpeedMetersPerSecond * handlingFactor;
    }

    public void useNitro(){
        if(!nitroUsed){
            nitroUsed = true;
            System.out.println("Using nitro for team " + getTeamId());
            currentSpeedMetersPerSecond = Math.min(maxSpeedMetersPerSecond, currentSpeedMetersPerSecond*2);
        }else{
            System.out.println("Nitro used already for team " + getTeamId());
        }
    }

    private static float kphToMps(float speedKmPerHour) {
        return speedKmPerHour*1000f/3600f;
    }

    @Override
    public String toString() {
        return "RacingTeam{" +
                "teamId=" + teamId +
                ", maxSpeedMetersPerSecond=" + maxSpeedMetersPerSecond +
                ", currentPosition=" + currentPosition +
                ", currentSpeedMetersPerSecond=" + currentSpeedMetersPerSecond +
                '}';
    }
}