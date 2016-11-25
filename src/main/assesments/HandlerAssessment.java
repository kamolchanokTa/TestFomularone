package main.assesments;
import main.FinishingStats;
import main.RacingTeam;

import java.util.List;
import java.util.TreeSet;
/**
 * Created by u6037291 on 11/24/2016.
 */
public class HandlerAssessment implements Assessment {

    private final float handlingDistance;

    public HandlerAssessment(float handlingDistance){
        this.handlingDistance = handlingDistance;
    }
    public void runAssessment(List<RacingTeam> racingTeams, List<FinishingStats> finishedTeams, float currentTime) {
        TreeSet<Float> locations = new TreeSet<>();
        for(RacingTeam racingTeam:racingTeams){
            locations.add(racingTeam.getCurrentPosition());
        }

        for(int i=0;i<racingTeams.size();i++){
            float currentTeamPosition = racingTeams.get(i).getCurrentPosition();

            if(locations.subSet(currentTeamPosition - handlingDistance, true, currentTeamPosition + handlingDistance, true).size()>1){
                racingTeams.get(i).useHandle();
            }
        }
    }
}