import com.agoda.formula.assesments.Assessment;
import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by u6037291 on 11/24/2016.
 */
public class RaceResultCalculatorImpl implements RaceResultCalculator {

    private final int numberOfTeams;
    private final float trackLength;
    private final float rearrageTimeFrame;

    private List<FinishingStats> results;
    private List<RacingTeam> racingTeams;

    private List<Assessment> allAssesments;


    public RaceResultCalculatorImpl(int numberOfTeams, float trackLength, List<Assessment> allAssesments, float rearrageTimeFrame) {
        this.numberOfTeams = numberOfTeams;
        this.trackLength = trackLength;
        this.rearrageTimeFrame = rearrageTimeFrame;
        racingTeams = new ArrayList<RacingTeam>(numberOfTeams);
        results = new ArrayList<FinishingStats>(numberOfTeams);
        this.allAssesments = allAssesments;
        initTeams();
    }

    private void initTeams() {
        for (int i = 1; i <= numberOfTeams; i++) {
            racingTeams.add(new RacingTeam(i));
        }
    }

    public List<FinishingStats> calculateFinishingStatsForAllCars() {
        List<FinishingStats> finishedTeams = new LinkedList<>();
        float currentTime = 0;
        while (finishedTeams.size() != numberOfTeams) {
            currentTime += rearrageTimeFrame;
            for (Assessment assessment : allAssesments) {
                assessment.runAssessment(racingTeams, finishedTeams, currentTime);
            }
            System.out.println("Status on " + currentTime + " second of race: ");
            for (RacingTeam racingTeam : racingTeams) {
                System.out.println("TeamId " + racingTeam.getTeamId() + " is on " + racingTeam.getCurrentPosition() + " meter and speed is " + racingTeam.getCurrentSpeedMetersPerSecond());
            }
        }
        return finishedTeams;
    }

    @VisibleForTesting
    public List<RacingTeam> getRacingTeams() {
        return racingTeams;
    }

    @VisibleForTesting
    public RaceResultCalculatorImpl setRacingTeams(List<RacingTeam> racingTeams) {
        this.racingTeams = racingTeams;
        return this;
    }
}
