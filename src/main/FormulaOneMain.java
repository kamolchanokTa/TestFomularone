package main; /**
 * Created by u6037291 on 11/24/2016.
 */

import main.assesments.*;
import com.google.common.collect.ImmutableList;
import org.apache.commons.cli.*;

import java.util.List;
public class FormulaOneMain {

    private final static String NUMBER_OF_TEAM_OPTION = "n";
    private final static String TRACK_LENGTH_METERS = "lm";
    private final static float HANDLING_DISTANCE_METERS = 10;
    private final static float REARRANGE_TIME_WINDOW_SEC = 2;

    private final static Options options;



    static {
        Option numberOfTeamOption = new Option(NUMBER_OF_TEAM_OPTION, true, "Number of participating teams");
        numberOfTeamOption.setRequired(true);
        Option trackLengthOption = new Option(TRACK_LENGTH_METERS, true, "Track length in meters");
        trackLengthOption.setRequired(true);

        options = new Options();
        options.addOption(numberOfTeamOption);
        options.addOption(trackLengthOption);
    }


    public static void main(String[] args) throws ParseException {
       // CommandLineParser commandLineParser = new DefaultParser();
        //CommandLine commandLine = commandLineParser.parse(options, args);
        int numberOfTeams = 5;//Integer.parseInt(commandLine.getOptionValue(NUMBER_OF_TEAM_OPTION));
        float trackLengthMeters = 20000;//Float.parseFloat(commandLine.getOptionValue(TRACK_LENGTH_METERS));

        RaceResultCalculator raceResultCalculator = new RaceResultCalculatorImpl(numberOfTeams, trackLengthMeters, getListOfAllRequiredAssessments(trackLengthMeters), REARRANGE_TIME_WINDOW_SEC);
        List<FinishingStats> finishingStatsList = raceResultCalculator.calculateFinishingStatsForAllCars();

        printResults(finishingStatsList);
    }

    private static List<Assessment> getListOfAllRequiredAssessments(float trackLengthMeters){
        return ImmutableList.<Assessment>of(
                new SpeedAndPositionAssessment(),
                new FinishingAssessment(trackLengthMeters),
                new HandlerAssessment(HANDLING_DISTANCE_METERS),
                new LastPositionAssessment()
        );
    }

    private static void printResults(List<FinishingStats> finishingStatsList) {
        for(int i = 0; i<finishingStatsList.size(); i++){
            FinishingStats current = finishingStatsList.get(i);
            System.out.printf("Team #%d finished %f seconds after starts with speed %f \n", current.getTeamId(), current.getTime(), current.getSpeed());
        }
    }

}