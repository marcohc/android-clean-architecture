package com.marcohc.architecture.common.util.helper;

import android.test.suitebuilder.annotation.LargeTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CipherHelperTest {

    @Test
    public void testGetMd5WithNull() {
        assertEquals(CipherHelper.getMd5Hash(null), null);
    }

    @Test
    public void testGetMd5WithEmpty() {
        assertEquals(CipherHelper.getMd5Hash(""), null);
    }

    @Test
    public void testGetMd5() {
        assertNotNull(CipherHelper.getMd5Hash("MY_SUPER_AWESOME_STRING"));
    }

    @Test
    public void testEncryptWithNull() {
        assertEquals(CipherHelper.encrypt(null), "");
    }

    @Test
    public void testEncryptWithEmpty() {
        assertEquals(CipherHelper.encrypt(""), "");
    }

    @Test
    public void testEncrypt() {
        String testToEncrypt = "Ey hello!";
        String textEncrypted = CipherHelper.encrypt(testToEncrypt);
        assertNotEquals(testToEncrypt, textEncrypted);
    }

    @Test
    public void testEncryptAndDecrypt() {
        String testToEncrypt = "Ey hello!";
        String textEncrypted = CipherHelper.encrypt(testToEncrypt);
        String textDecrypted = CipherHelper.decrypt(textEncrypted);
        assertEquals(testToEncrypt, textDecrypted);
    }
}