package Task_15;

public class Utils {
    public static String wrapMessage(String msg){
        return "#" + msg + "#";
    }

    public static String unwrapMessage(String msg){
        return msg.substring(1, msg.length()-1);
    }
}
