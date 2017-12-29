package com.example.dean.blackbeltpatterns.helper;

/**
 * Created by Dean on 26/12/2017.
 */

public class YamlHelper {
    private static final YamlHelper ourInstance = new YamlHelper();

    public static YamlHelper getInstance() {
        return ourInstance;
    }

    private YamlHelper() {
    }
}
