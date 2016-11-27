package com.example.lbofinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.ibm.icu.text.BreakIterator;

public class TextBreaker {
    public List<String> segments;

    public TextBreaker(String text, Locale locale) {
        BreakIterator breakIterator = BreakIterator.getLineInstance(locale);
        breakIterator.setText(text);

        segments = new ArrayList<String>();

        for (int start = breakIterator.first(), end = breakIterator.next(); end != BreakIterator.DONE; start = end, end = breakIterator.next()) {
            segments.add(text.substring(start, end));
        }
    }
}
