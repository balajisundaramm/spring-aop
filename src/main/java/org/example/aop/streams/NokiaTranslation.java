package org.example.aop.streams;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author = mbalaji on 26-08-2023
 * @project = spring-aop
 * <p>
 * abc 2
 * def 3
 * ghi 4
 * jkl 5
 * mno 6
 * pqrs 7
 * tuv 8
 * wxyz 9
 */
public class NokiaTranslation {
    public static Map<String, String> map = Map.ofEntries(
            Map.entry("2", "A"), Map.entry("22", "B"), Map.entry("222", "C"),
            Map.entry("3", "D"), Map.entry("33", "E"), Map.entry("333", "F"),
            Map.entry("4", "G"), Map.entry("44", "H"), Map.entry("444", "I"),
            Map.entry("5", "J"), Map.entry("55", "K"), Map.entry("555", "L"),
            Map.entry("6", "M"), Map.entry("66", "N"), Map.entry("666", "O"),
            Map.entry("7", "P"), Map.entry("77", "Q"), Map.entry("777", "R"), Map.entry("7777", "S"),
            Map.entry("8", "T"), Map.entry("88", "U"), Map.entry("888", "V"),
            Map.entry("9", "W"), Map.entry("99", "X"), Map.entry("999", "Y"), Map.entry("9999", "Z")
    );
    public static Map<String, String> swappedMap = map.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    public static void main(String[] args) {
        String sentence = "444 26 2.2662663.3266";
        String result = "";
        if (StringUtils.hasText(sentence)) {
            String[] split = sentence.split(" ");
            for (String s : split) {
                result = result + getAlphaChar(s) + " ";
            }
        }
        System.out.println(result);
        String numbers = "I AM AANANDDAN";
        String words = "";
        if (StringUtils.hasText(numbers)) {
            String[] split = numbers.split(" ");
            for (String s : split) {
                words = words + getNumeric(s) + " ";
            }
        }
        System.out.println(words);
    }

    public static String getAlphaChar(String word) {
        String result = "";
        if (StringUtils.hasText(word)) {
            String key = String.valueOf(word.charAt(0));
            boolean singleChar = false;
            for (int i = 1; i < word.length(); i++) {
                if (String.valueOf(word.charAt(i)).equalsIgnoreCase(".")) {
                    result = result + map.get(key);
                    i++;
                    key = String.valueOf(word.charAt(i));
                } else {
                    if (String.valueOf(key.charAt(key.length() - 1)).equals(String.valueOf(word.charAt(i)))) {
                        key = key + word.charAt(i);
                        singleChar = true;
                    } else {
                        result = result + map.get(key);
                        singleChar = false;
                        if (i != word.length() - 1) {
                            key = String.valueOf(word.charAt(i));
                        } else {
                            result = result + map.get(String.valueOf(word.charAt(i)));
                            return result;
                        }
                    }
                }
            }
            if (singleChar) {
                result = result + map.get(key);
            }
        }
        return result;
    }

    public static String getNumeric(String word) {
        if (StringUtils.hasText(word)) {
            String result = swappedMap.get(String.valueOf(word.charAt(0)));
            for (int i = 1; i < word.length(); i++) {
                if(word.charAt(i-1) == word.charAt(i)) {
                    result = result + ".";
                }
                result = result + swappedMap.get(String.valueOf(word.charAt(i)));
            }
            return result;
        }
        return null;
    }
}
