package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author flyman
 */
public class RegexMain {

    public static void main(String[] args) {
        String patternString = "(['\"])[^\"']*\\1";
        String input = "\"abc\"";
        match(patternString, input);


    }

    private static void match(String patternString, String input) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        System.out.println("pattern = " + patternString);
        System.out.println("input = " + input);
        List<String> result = new ArrayList<>();
        while(matcher.find()) {
            result.add(String.format("From Index = %d, End Index = %d, MatchString = \"%s\" %n", matcher.start(), matcher.end() -1, matcher.group()));
        }
        if(result.isEmpty()) {
            System.out.println("Don't Match");
        }
        else {
            result.forEach(System.out::println);
        }
    }
}
