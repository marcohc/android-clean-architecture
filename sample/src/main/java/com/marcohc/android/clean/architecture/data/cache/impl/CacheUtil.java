package com.marcohc.android.clean.architecture.data.cache.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import timber.log.Timber;

public class CacheUtil {

    public static String getKey(String key) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(key.getBytes(), 0, key.length());
            BigInteger i = new BigInteger(1, m.digest());
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            Timber.e("CacheUtil.getKey: {key:%s}", key);
        }
        return null;
    }
}
