package com.govtech.pic.suggest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
    private Utils() { }

    public static Map<String, String> getNameValues(String in) {
        String delim = "&";
        Set<String> set = split(in, delim);
        return set.stream()
                .map(Utils::getNameValue)
                .filter(entry -> entry.getKey() != null && entry.getValue() != null)
                .collect(Collectors.toMap(
                        entry -> getString(entry.getKey()),
                        entry -> getString(entry.getValue())
                ));
    }

    private static String getString(final String entry) {
        try {
            return URLDecoder.decode(entry, String.valueOf(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<String> split(String in, String delim) {
        return new LinkedHashSet<>(Arrays.asList(in.split(delim)));
    }

    private static Map.Entry<String, String> getNameValue(String nameValuePair) {
        int equalsIndex = nameValuePair.indexOf('=');
        if (equalsIndex == -1) {
            return new AbstractMap.SimpleEntry<>(nameValuePair, null);
        } else {
            return new AbstractMap.SimpleEntry<>(
                    nameValuePair.substring(0, equalsIndex),
                    nameValuePair.substring(equalsIndex + 1)
            );
        }
    }
}