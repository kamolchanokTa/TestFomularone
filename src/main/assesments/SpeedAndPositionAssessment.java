package main.assesments;
import main.FinishingStats;
import main.RacingTeam;

import java.util.List;
/**
 * Created by u6037291 on 11/24/2016.
 */
public class SpeedAndPositionAssessment implements Assessment {

    private float lastAssessmentTime = 0;

    public void runAssessment(List<RacingTeam> racingTeams, List<FinishingStats> finishedTeams, float currentTime) {
        for (RacingTeam racingTeam : racingTeams) {
            adjustSpeedAndPosition(racingTeam, currentTime - lastAssessmentTime);
        }
        lastAssessmentTime = currentTime;
    }

    private void adjustSpeedAndPosition(RacingTeam racingTeam, float timePeriod) {
        float currentSpeed = racingTeam.getCurrentSpeedMetersPerSecond();
        float acceleration = racingTeam.getAccelerationMetersPerSecondSquare();
        float maxSpeed = racingTeam.getMaxSpeedMetersPerSecond();

        float timeToMaxSpeed = (maxSpeed - currentSpeed) / acceleration;
        if (timeToMaxSpeed > timePeriod) {
            //plain case - car was accelerating all time period
            float newPosition = racingTeam.getCurrentPosition() + currentSpeed * timePeriod + acceleration * timePeriod * timePeriod / 2f;
            racingTeam.setCurrentPosition(newPosition);
            float newSpeed = currentSpeed + acceleration * timePeriod;
            racingTeam.setCurrentSpeedMetersPerSecond(newSpeed);
        } else {
            //more tricky case. Car was accelerating for timeToMax and then moved with maxSpeed
            float newPosition = racingTeam.getCurrentPosition() + currentSpeed * timeToMaxSpeed + acceleration * timeToMaxSpeed * timeToMaxSpeed / 2f;
            newPosition = newPosition + maxSpeed * (timePeriod - timeToMaxSpeed);
            racingTeam.setCurrentPosition(newPosition);
            racingTeam.setCurrentSpeedMetersPerSecond(maxSpeed);
        }
    }

}
