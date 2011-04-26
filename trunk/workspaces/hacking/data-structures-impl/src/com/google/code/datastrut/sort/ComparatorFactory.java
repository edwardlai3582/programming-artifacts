package com.google.code.datastrut.sort;

public class ComparatorFactory {

    /**
     * @return A comparator for Integers based on the natural order of their values.
     */
    public static Comparator<Integer> makeIntegerComparator() {

        return new Comparator<Integer>() {
            @Override
            public int compare(Integer obj, Integer other) {
                return obj - other;
            }
        };
    }

    /**
     * @return A comparator for Strings based on the natural order of the characters.
     */
    public static Comparator<String> makeStringComparator() {

        return new Comparator<String>() {
            @Override
            public int compare(String obj, String other) {
                return (int)obj.charAt(0) - (int)other.charAt(0);
            }
        };
    }

    public static void main(String[] args) {
        int comparisonValue = ComparatorFactory.makeIntegerComparator().compare(23, 11);
        System.out.println("Compare 23 and 11: " + comparisonValue + " > 0 " + (comparisonValue > 0));

        comparisonValue = ComparatorFactory.makeIntegerComparator().compare(-1, -2);
        System.out.println("Compare -1 and -2: " + comparisonValue + " > 0 " + (comparisonValue > 0));

        comparisonValue = ComparatorFactory.makeIntegerComparator().compare(-3, 11);
        System.out.println("Compare -3 and 11: " + comparisonValue + " < 0 " + (comparisonValue < 0));

        comparisonValue = ComparatorFactory.makeIntegerComparator().compare(1112, 1112);
        System.out.println("Compare 1112 and 1112: " + comparisonValue + " < 0 " + (comparisonValue == 0));

        comparisonValue = ComparatorFactory.makeStringComparator().compare("C", "Java");
        System.out.println("Compare C and Java: " + comparisonValue + " < 0 " + (comparisonValue < 0));

        comparisonValue = ComparatorFactory.makeStringComparator().compare("Java", "Java");
        System.out.println("Compare Java and Java: " + comparisonValue + " == 0 " + (comparisonValue == 0));

        comparisonValue = ComparatorFactory.makeStringComparator().compare("Java", "C");
        System.out.println("Compare Java and C: " + comparisonValue + " > 0 " + (comparisonValue > 0));
    }
}
