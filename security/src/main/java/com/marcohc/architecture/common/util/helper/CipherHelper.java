/*
 * Copyright (C) 2016 Marco Hernaiz Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marcohc.architecture.common.util.helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import timber.log.Timber;

public class CipherHelper {

    public static String getMd5Hash(String key) {
        if (!StringHelper.isBlank(key)) {
            try {
                MessageDigest m = MessageDigest.getInstance("MD5");
                m.update(key.getBytes(), 0, key.length());
                BigInteger i = new BigInteger(1, m.digest());
                return String.format("%1$032x", i);
            } catch (NoSuchAlgorithmException e) {
                Timber.e("getMd5Hash: {key:%s}", key);
            }
        }
        return null;
    }

    public static String encrypt(String text) {
        String base64EncryptedString = "";
        if (!StringHelper.isBlank(text)) {
            try {
                String secretKey = getSecretKey();
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
                byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

                SecretKey key = new SecretKeySpec(keyBytes, "DESede");
                Cipher cipher = Cipher.getInstance("DESede");
                cipher.init(Cipher.ENCRYPT_MODE, key);

                byte[] plainTextBytes = text.getBytes("utf-8");
                byte[] buf = cipher.doFinal(plainTextBytes);
                byte[] base64Bytes = Base64.encode(buf, Base64.DEFAULT);
                base64EncryptedString = new String(base64Bytes);
            } catch (Exception e) {
                Timber.e("encrypt: %s", e);
            }
        }
        return base64EncryptedString;
    }

    public static String decrypt(String text) {
        String base64EncryptedString = "";
        if (!StringHelper.isBlank(text)) {
            try {
                String secretKey = getSecretKey();
                byte[] message = Base64.decode(text.getBytes("utf-8"), Base64.DEFAULT);
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
                byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
                SecretKey key = new SecretKeySpec(keyBytes, "DESede");
                Cipher decipher = Cipher.getInstance("DESede");
                decipher.init(Cipher.DECRYPT_MODE, key);
                byte[] plainText = decipher.doFinal(message);
                base64EncryptedString = new String(plainText, "UTF-8");
            } catch (Exception e) {
                Timber.e("decrypt: %s", e);
            }
        }
        return base64EncryptedString;
    }

    private static String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < hex.length() - 1; i += 2) {
            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte aData : data) {
            int halfbyte = (aData >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = aData & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    private static String getSecretKey() {
        byte[] array = new byte[]{83, 51, 109, 48, 108, 52, 68, 51, 84, 114, 49, 103, 48, 68, 54, 114, 48, 80, 51, 114, 48, 68, 54, 114, 48, 68, 54, 114, 48};
        return convertHexToString(convertToHex(array));
    }

}
