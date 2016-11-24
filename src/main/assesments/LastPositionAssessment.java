package main.assesments;
import main.FinishingStats;
import main.RacingTeam;

import java.util.List;
/**
 * Created by u6037291 on 11/24/2016.
 */
public class LastPositionAssessment implements Assessment {
    @Override
    public void runAssessment(List<RacingTeam> racingTeams, List<FinishingStats> finishedTeams, float currentTime) {
        if (racingTeams.size() == 0) {
            return;
        }
        racingTeams.sort((o1, o2) -> Float.compare(o1.getCurrentPosition(), o2.getCurrentPosition()));
        RacingTeam lastTeam = racingTeams.get(0);
        lastTeam.useNitro();
        float lastPosition = lastTeam.getCurrentPosition();
        //just in case we have several teams on last place
        for (int i = 1; i < racingTeams.size(); i++) {
            if (racingTeams.get(i).getCurrentPosition() == lastPosition) {
                racingTeams.get(i).useNitro();
            } else {
                break;
            }
        }
    }
}
