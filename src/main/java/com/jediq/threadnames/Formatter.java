package com.jediq.threadnames;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {

    private final String fieldStart = "\\$\\{";
    private final String fieldEnd = "\\}";

    private final String regex = fieldStart + "([^}]+)" + fieldEnd;
    private final Pattern pattern = Pattern.compile(regex);

    public String format(String format, List<Object> objects) {
        Matcher m = pattern.matcher(format);
        String result = format;
        while (m.find()) {
            String[] found = m.group(1).split("\\.");
            Object object = objects.get(Integer.parseInt(found[0]));
            result = result.replaceFirst(regex, object.toString());
        }
        return result;
    }
}