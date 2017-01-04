package com.marcohc.architecture.common.helper;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringHelperTest {

    @Test
    public void testIsEmptyWithNull() {
        assertThat(StringHelper.isBlank(null), is(true));
    }

    @Test
    public void testIsEmptyWithEmpty() {
        assertThat(StringHelper.isBlank(""), is(true));
    }

    @Test
    public void testIsEmptyWithSpaces() {
        assertThat(StringHelper.isBlank("   "), is(true));
    }

    @Test
    public void testIsEmptyWithText() {
        assertThat(StringHelper.isBlank("I'm not empty!"), is(false));
    }

    @Test
    public void testIsEmailValidWithNull() {
        assertThat(StringHelper.isEmailValid(null), is(false));
    }

    @Test
    public void testIsEmailValidWithEmpty() {
        assertThat(StringHelper.isEmailValid(""), is(false));
    }

    @Test
    public void testIsEmailValidWithWrongEmail1() {
        assertThat(StringHelper.isEmailValid("this_is_wrong@"), is(false));
    }

    @Test
    public void testIsEmailValidWithWrongEmail2() {
        assertThat(StringHelper.isEmailValid("this_is_wrong@aaaa"), is(false));
    }

    @Test
    public void testIsEmailValidWithWrongEmail3() {
        assertThat(StringHelper.isEmailValid("this_is_wrong@aaaa."), is(false));
    }

    @Test
    public void testIsEmailValidWithWrongEmail4() {
        assertThat(StringHelper.isEmailValid("this_is_wrong@aaaa.c"), is(false));
    }

    @Test
    public void testIsEmailValidWithWrongEmail5() {
        assertThat(StringHelper.isEmailValid("this_is_wrong@aaaa.co@"), is(false));
    }

    @Test
    public void testIsEmailValidWithCorrect() {
        assertThat(StringHelper.isEmailValid("this_is_correct@aaaa.com"), is(true));
    }

}