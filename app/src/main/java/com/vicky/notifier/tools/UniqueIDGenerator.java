package com.vicky.notifier.tools;

import java.util.UUID;

public class UniqueIDGenerator {
    private static int viewCounter;

    static {
        viewCounter = 0;
    }

    public static String getNewUUID() {
        return Long.toString(System.currentTimeMillis());
    }

    public static int getNewID() {
        int i = viewCounter + 1;
        viewCounter = i;
        return i;
    }
}