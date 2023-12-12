package com.base.moviebooking;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class VietnamComparator implements Comparator<String> {
    private Collator collator;

    public VietnamComparator() {
        collator = Collator.getInstance(new Locale("vi", "VN"));
    }

    @Override
    public int compare(String o1, String o2) {
        return collator.compare(o1, o2);
    }


}
