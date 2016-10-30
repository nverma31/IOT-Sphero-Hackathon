import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public class HelloWorld {
    public static void main(String args[]) {
        //Initialize middleware and register this Zirk to get a Bezirk instance
        BezirkMiddleware.initialize();
        Bezirk bezirk = BezirkMiddleware.registerZirk("Hello World Zirk");

        //Create a new event and publish it



    }
}
