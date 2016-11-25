package main.assesments;
import main.RacingTeam;
import main.FinishingStats;
import java.util.List;
/**
 * Created by u6037291 on 11/24/2016.
 */
public interface Assessment {

    public void runAssessment(List<RacingTeam> racingTeams, List<FinishingStats> finishedTeams, float currentTime);
}