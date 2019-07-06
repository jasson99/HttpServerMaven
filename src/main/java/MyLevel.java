import org.apache.log4j.Level;

public class MyLevel extends Level {
 
    /*
     * Value of MyLevel level. This value is greater than DEBUG_INT .
    */
    public static final int MYLEVEL_INT = DEBUG_INT + 10;
 
    /*
     * Level representing my log level
     */
    public static final Level MYLEVEL = new MyLevel(MYLEVEL_INT, "MYLEVEL",10);
    
    
 
    /**
     * Constructor
     */
    protected MyLevel(int arg0, String arg1, int arg2) {
        super(arg0, arg1, arg2);
 
    }
 
    /*
     * Checks whether logArgument is "MYLEVEL" level. If yes then returns
     * MYLEVEL}, else calls MyLevel#toLevel(String, Level) passing
     * it Level#DEBUG as the defaultLevel.
     */
    
    public static Level toLevel(String logArgument) {
        if (logArgument != null && logArgument.toUpperCase().equals("MYLEVEL")) {
            return MYLEVEL;
        }
        return (Level) toLevel(logArgument, Level.DEBUG);
    }
    
 
    /*
     * Checks whether val is MyLevel#MYLEVEL_INT. If yes then
     * returns MyLevel#MYLEVEL, else calls
     * MyLevel#toLevel(int, Level) passing it Level#DEBUG as the
     * defaultLevel
     * 
     */
    
    public static Level toLevel(int val) {
        if (val == MYLEVEL_INT) {
            return MYLEVEL;
        }
        return (Level) toLevel(val, Level.DEBUG);
    }
 
    /*
     * Checks whether val is MyLevel#MYLEVEL_INT. If yes
     * then returns MyLevel#MYLEVEL, else calls Level#toLevel(int, org.apache.log4j.Level)
     * 
     */
    public static Level toLevel(int val, Level defaultLevel) {
        if (val == MYLEVEL_INT) {
        //    return MYLEVEL;
        }
        
        return Level.toLevel(val, defaultLevel);
    }
     
    /*
     * Checks whether logArgument is "MYLEVEL" level. If yes then returns
     * MyLevel#MYLEVEL, else calls
     */

    public static Level toLevel(String logArgument, Level defaultLevel) {
        if (logArgument != null && logArgument.toUpperCase().equals("MYLEVEL")) {
            return MYLEVEL;
        }
        return Level.toLevel(logArgument, defaultLevel);
    }

}