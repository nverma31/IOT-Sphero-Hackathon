import com.bezirk.hardwareevents.HexColor;
import com.bezirk.hardwareevents.robot.ChangeRobotColorEvent;
import com.bezirk.hardwareevents.robot.MoveRobotEvent;
import com.bezirk.hardwareevents.robot.Robot;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import java.util.concurrent.TimeUnit;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class RobotMove  {
    private Bezirk bezirk;
    private Robot robot;

    public RobotMove(){
        robot = new Robot("d02fc16c", "bezirk.simulator.robot");
        BezirkMiddleware.initialize("MHCI");
        bezirk = BezirkMiddleware.registerZirk("Robot Zirk");
        System.out.println("Got Bezirk instance");


    }

    public void MoveRobot(int i, int j, int k) {

        final MoveRobotEvent moveRobotEvent = new MoveRobotEvent(robot, i, j, k);
        bezirk.sendEvent(moveRobotEvent);

        System.out.println("Move event: " + moveRobotEvent.toString());


    }

    public void changeColorBlue() {

        final ChangeRobotColorEvent changeRobotColorEvent = new ChangeRobotColorEvent(robot, HexColor.BLUE);
        bezirk.sendEvent(changeRobotColorEvent);

        System.out.println("Move event: " + changeRobotColorEvent.toString());


    }
    public void changeColorRed() {

        final ChangeRobotColorEvent changeRobotColorEvent = new ChangeRobotColorEvent(robot, HexColor.RED);
        bezirk.sendEvent(changeRobotColorEvent);

        System.out.println("Move event: " + changeRobotColorEvent.toString());


    }



    public static void main(String args[]) throws InterruptedException, TwitterException{
    HexColor hexColor;
    //Create a new Robot
        RobotMove robotMove = new RobotMove();

// Search for the hash tag of the match in twitter
        String match = "#AlavesRealMadrid";
        //Get the twitter feed for the Scores
        String consumerKey = "UW8VTaIGNXNVfPghG4Ln0GMgI";

        //Your Twitter App's Consumer Secret
        String consumerSecret = "L7rBv7OSWDVgVA9cVptRehhOtKzTwI1o4p6U1wFqU4BopxZK9E";

        //Your Twitter Access Token
        String accessToken = "106980751-yJqMfncvPDjaFdHHSOcXV3k5VHR3etGuTsG3cwXk";

        //Your Twitter Access Token Secret
        String accessTokenSecret = "UApghZ6EcT0x2U0FY4ozeyEDIzMc465RDIBqeK8yR53ln";

        //Instantiate a re-usable and thread-safe factory
        TwitterFactory twitterFactory = new TwitterFactory();

        //Instantiate a new Twitter instance
        Twitter twitter = twitterFactory.getInstance();

        //setup OAuth Consumer Credentials
        twitter.setOAuthConsumer(consumerKey, consumerSecret);

        //setup OAuth Access Token
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        Query query = new Query(match);
        QueryResult result = twitter.search(query);
        String goal = "goal"+ match;
        for (Status status : result.getTweets()) {
            if(status.getText().contains(goal)) {
                //Blink the color of the team which scored for the number of goals that team have scored
                robotMove.changeColorBlue();
                TimeUnit.SECONDS.sleep(2);
                robotMove.changeColorRed();
                TimeUnit.SECONDS.sleep(2);
                robotMove.changeColorBlue();
                //and then move it in a circle Move it in a circle
                for (int i =0; i<365; i++) {
                    robotMove.MoveRobot(i, 25, 1000);
                }

            }
        }


    }

}
