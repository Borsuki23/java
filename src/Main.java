public class Main {

    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static int countVowels(String s) {
        return (int) s.chars().filter(c -> "aeiouAEIOU".indexOf(c) != -1).count();
    }

    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static boolean isEmptyOrWhitespace(String s) {
        return s.trim().isEmpty();
    }

    public static int countLetters(String s) {
        return (int) s.chars().filter(Character::isLetter).count();
    }

    public static int countWords(String sentence) {
        return sentence.trim().isEmpty() ? 0 : sentence.split("\\s+").length;
    }

    public static String replaceDigits(String s) {
        return s.replaceAll("\\d", "*");
    }

    public static boolean areAnagrams(String s1, String s2) {
        char[] arr1 = s1.toLowerCase().toCharArray();
        char[] arr2 = s2.toLowerCase().toCharArray();
        java.util.Arrays.sort(arr1);
        java.util.Arrays.sort(arr2);
        return java.util.Arrays.equals(arr1, arr2);
    }

    public static String compressString(String s) {
        StringBuilder compressed = new StringBuilder();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                compressed.append(s.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        compressed.append(s.charAt(s.length() - 1)).append(count);
        return compressed.length() < s.length() ? compressed.toString() : s;
    }

    public static String longestCommonSubstring(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int maxLength = 0, endIndex = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        return s1.substring(endIndex - maxLength, endIndex);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("racecar"));
        System.out.println(countVowels("hello"));
        System.out.println(reverseString("hello"));
        System.out.println(isEmptyOrWhitespace("   "));
        System.out.println(countLetters("hello123"));
        System.out.println(countWords("This is a simple sentence"));
        System.out.println(replaceDigits("Hello123"));
        System.out.println(areAnagrams("listen", "silent"));
        System.out.println(compressString("aabcccccaaa"));
        System.out.println(longestCommonSubstring("abcdef", "zbcdf"));
    }
}