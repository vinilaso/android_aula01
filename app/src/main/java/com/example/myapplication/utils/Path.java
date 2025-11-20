package com.example.myapplication.utils;

import java.io.File;

public class Path {
    public static String combine(String... paths) {
        StringBuilder sb = new StringBuilder();

        for (final String path : paths) {
            String formattedPath = path;

            if (path.endsWith(File.pathSeparator)) {
                formattedPath = path.substring(0, path.length() - File.pathSeparator.length());
            }

            sb.append(formattedPath).append(File.pathSeparator);
        }

        return sb.toString();
    }
}
