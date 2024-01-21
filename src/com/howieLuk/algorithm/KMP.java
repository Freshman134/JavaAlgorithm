package com.howieLuk.algorithm;

/**
 * @author HowieLuk
 * @date 2024/1/21 3:13
 */
public class KMP {
    public static int match(String str, String pat) {
        int[] kmpArr = getKMPArray(pat);

        int s = 0, p = 0;
        while (p < pat.length() && s < str.length()) {
            if (str.charAt(p) == str.charAt(s)) {
                p++; s++;
            } else {
                p = kmpArr[p > 0 ? 0 : p - 1];
            }
        }
        if (p == pat.length()) {
            return s - p;
        }
        return -1;
    }

    private static int[] getKMPArray(String pat) {
        int[] retArr = new int[pat.length()];
        int kmp;
        for (int i = 0; i < retArr.length; i++) {
            kmp = i + 1;
            if (pat.charAt(i) == pat.charAt(kmp)) {
                retArr[kmp] = retArr[i] + 1;
            } else {
                retArr[kmp] = 0;
            }
        }
        return retArr;
    }

}
