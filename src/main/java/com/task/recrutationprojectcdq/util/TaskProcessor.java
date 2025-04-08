package com.task.recrutationprojectcdq.util;

import com.task.recrutationprojectcdq.model.Classification;
import com.task.recrutationprojectcdq.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskProcessor {

    public static Map<String, Classification> comparePeople(final Person previous, final Person current) {
        Map<String, Classification> result = new HashMap<>();
        if (previous != null && current != null) {
            compareField("name", previous.getName(), current.getName(), result);
            compareField("surname", previous.getSurname(), current.getSurname(), result);
            compareField("birthDate", previous.getBirthDate().toString(), current.getBirthDate().toString(), result);
            compareField("company", previous.getCompany(), current.getCompany(), result);
        } else {
            classifyCases(previous, current, result);
        }
        return result;
    }

    private static void compareField(final String field, final String previous, final String current,
                                     final Map<String, Classification> result) {
        double similarity = calculateSimilarity(previous, current);
        Classification classification;
        if (similarity > 0.9) classification = Classification.HIGH;
        else if (similarity >= 0.4) classification = Classification.MEDIUM;
        else classification = Classification.LOW;
        result.put(field, classification);
    }

    private static void classifyCases(final Person previous, final Person current, final Map<String, Classification> result) {
        if (previous == null && current != null) {
            createResult(Classification.ADDED, result);
        } else if (previous != null && current == null) {
            createResult(Classification.DELETED, result);
        }
    }

    private static void createResult(final Classification classification, final Map<String, Classification> result) {
        var fields = List.of("name", "surname", "birthDate", "company");
        for (String field : fields) {
            result.put(field, classification);
        }
    }

    private static double calculateSimilarity(final String s1, final String s2) {
        int difference = calculateDifference(s1, s2);
        int maxLength = Math.max(s1.length(), s2.length());
        return 1.0 - ((double) difference / maxLength);
    }

    private static int calculateDifference(final String s1, final String s2) {
        int len = Math.max(s1.length(), s2.length());
        int difference = 0;
        for (int i = 0; i < len; i++) {
            char c1 = i < s1.length() ? s1.charAt(i) : 0;
            char c2 = i < s2.length() ? s2.charAt(i) : 0;
            if (c1 != c2) difference++;
        }
        return difference;
    }
}
