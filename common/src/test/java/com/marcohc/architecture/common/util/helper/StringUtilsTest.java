package com.marcohc.architecture.common.util.helper;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringUtilsTest {

    @Test
    public void testIsEmailValidWithCorrect() {
        assertThat(StringUtils.isEmailValid("this_is_correct@aaaa.com"), is(true));
    }

    @Test
    public void testIsEmailValidWithEmpty() {
        assertThat(StringUtils.isEmailValid(""), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void testIsEmailValidWithNull() {
        StringUtils.isEmailValid(null);
    }

    @Test
    public void testIsEmailValidWithWrongEmail1() {
        assertThat(StringUtils.isEmailValid("this_is_wrong@"), is(false));
    }

    @Test
    public void testIsEmailValidWithWrongEmail2() {
        assertThat(StringUtils.isEmailValid("this_is_wrong@aaaa"), is(false));
    }

    @Test
    public void testIsEmailValidWithWrongEmail3() {
        assertThat(StringUtils.isEmailValid("this_is_wrong@aaaa."), is(false));
    }

    @Test
    public void testIsEmailValidWithWrongEmail4() {
        assertThat(StringUtils.isEmailValid("this_is_wrong@aaaa.c"), is(false));
    }

    @Test
    public void testIsEmailValidWithWrongEmail5() {
        assertThat(StringUtils.isEmailValid("this_is_wrong@aaaa.co@"), is(false));
    }

    @Test
    public void testIsEmptyWithEmpty() {
        assertThat(StringUtils.isBlank(""), is(true));
    }

    @Test
    public void testIsEmptyWithNull() {
        assertThat(StringUtils.isBlank(null), is(true));
    }

    @Test
    public void testIsEmptyWithSpaces() {
        assertThat(StringUtils.isBlank("   "), is(true));
    }

    @Test
    public void testIsEmptyWithText() {
        assertThat(StringUtils.isBlank("I'm not empty!"), is(false));
    }

    @Test
    public void testGetFirstCapitalize() {
        assertThat(StringUtils.getFirstCapitalize("lol"), is("Lol"));
    }

    @Test
    public void testGetAllCapitalize() {
        assertThat(StringUtils.getAllCapitalize("lol"), is("LOL"));
    }

}