package com.github.dstaflund.nts.search;

import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.QueryParam;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class SearchParamsUnitTest {

    @RunWith(JMockit.class)
    public static class NameField {
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        Field mName;
        SearchParams mParams;
        private Validator mValidator;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            mParams = new SearchParams();
            mParams.setName("72P14");
            mParams.setSnippet("LANIGAN");
            mParams.setNorth("52F");
            mParams.setSouth("51.75F");
            mParams.setEast("-105F");
            mParams.setWest("-105.5F");

            mName = SearchParams.class.getDeclaredField("name");
            mName.setAccessible(true);
        }

        @Test
        public void getsValueFromNameQueryParameter(){
            assertEquals("name", mName.getAnnotation(QueryParam.class).value());
        }
    }

    @RunWith(JMockit.class)
    public static class NorthField {
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        Field mNorth;
        SearchParams mParams;
        private Validator mValidator;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            mParams = new SearchParams();
            mParams.setName("72P14");
            mParams.setSnippet("LANIGAN");

            mNorth = SearchParams.class.getDeclaredField("north");
            mNorth.setAccessible(true);
        }

        @Test
        public void getsValueFromNameQueryParameter(){
            assertEquals("north", mNorth.getAnnotation(QueryParam.class).value());
        }
    }

    @RunWith(JMockit.class)
    public static class SouthField {
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        Field mSouth;
        SearchParams mParams;
        private Validator mValidator;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            mParams = new SearchParams();
            mParams.setName("72P14");
            mParams.setSnippet("LANIGAN");

            mSouth = SearchParams.class.getDeclaredField("south");
            mSouth.setAccessible(true);
        }

        @Test
        public void getsValueFromNameQueryParameter(){
            assertEquals("south", mSouth.getAnnotation(QueryParam.class).value());
        }
    }

    @RunWith(JMockit.class)
    public static class EastField {
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        Field mEast;
        SearchParams mParams;
        private Validator mValidator;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            mParams = new SearchParams();
            mParams.setName("72P14");
            mParams.setSnippet("LANIGAN");

            mEast = SearchParams.class.getDeclaredField("east");
            mEast.setAccessible(true);
        }

        @Test
        public void getsValueFromNameQueryParameter(){
            assertEquals("east", mEast.getAnnotation(QueryParam.class).value());
        }
    }

    @RunWith(JMockit.class)
    public static class WestField {
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        Field mWest;
        SearchParams mParams;
        private Validator mValidator;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            mParams = new SearchParams();
            mParams.setName("72P14");
            mParams.setSnippet("LANIGAN");

            mWest = SearchParams.class.getDeclaredField("west");
            mWest.setAccessible(true);
        }

        @Test
        public void getsValueFromNameQueryParameter(){
            assertEquals("west", mWest.getAnnotation(QueryParam.class).value());
        }
    }

    @RunWith(JMockit.class)
    public static class SnippetField {
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        Field mSnippet;
        SearchParams mParams;
        private Validator mValidator;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            mParams = new SearchParams();
            mParams.setName("72P14");
            mParams.setSnippet("LANIGAN");

            mSnippet = SearchParams.class.getDeclaredField("snippet");
            mSnippet.setAccessible(true);
        }

        @Test
        public void getsValueFromNameQueryParameter(){
            assertEquals("title", mSnippet.getAnnotation(QueryParam.class).value());
        }

        @Test
        public void returnsErrorWhenLongerThan40Characters(){
            mParams.setSnippet("12345678901234567890123456789012345678901");
            assertFalse(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void doesNotReturnErrorWhen40Characters(){
            mParams.setSnippet("1234567890123456789012345678901234567890");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void doesNotReturnErrorWhenLessThan40Characters(){
            mParams.setSnippet("123456789012345678901234567890123456789");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void doesNotReturnErrorWhen0Characters(){
            mParams.setSnippet("");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }
    }
}
