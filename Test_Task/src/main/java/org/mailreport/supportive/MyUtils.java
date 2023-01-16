package org.mailreport.supportive;

import java.util.List;
import java.util.regex.Pattern;

public class MyUtils {
    public static String listToString(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(s -> stringBuilder.append(s).append(" "));
        return stringBuilder.substring(0, stringBuilder.length()-1);
    }

    public static boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);

        return email != null && pattern.matcher(email).matches();
    }
}
