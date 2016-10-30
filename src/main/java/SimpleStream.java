import java.io.IOException;

import twitter4j.*;
import twitter4j.auth.AccessToken;

class SimpleStream {
    public static String cleanText(String text)
    {
        text = text.replace("\n", "\\n");
        text = text.replace("\t", "\\t");

        return text;
    }

    public static void main(String[] args) throws IOException, TwitterException {

        //Your Twitter App's Consumer Key
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

        Query query = new Query("#AlavesRealMadrid");
        QueryResult result = twitter.search(query);
        System.out.println(result);
        for (Status status : result.getTweets()) {

            if(status.getText().contains("1-2 #AlavesRealMadrid") && status.getText().contains("#AlavésRealMadrid")) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        }
    }

}
