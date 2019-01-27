package com.github.dstaflund.nts;

import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class PagingParamsUnitTest {

    @RunWith(JMockit.class)
    public static class LimitProperty {
        @Tested PagingParams mParams;
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        private Validator mValidator;
        private Field mLimitField;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            // Set fields to values that pass validation
            mParams = new PagingParams();
            mParams.setLimit(10);
            mParams.setOffset(25);
            mParams.setSortField("name");
            mParams.setSortOrder(1);

            mLimitField = PagingParams.class.getDeclaredField("limit");
            mLimitField.setAccessible(true);
        }

        @Test
        public void errorsOutWhenLessThanOne() {
            mParams.setLimit(0);
            assertFalse(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void errorsOutWhenMoreThanTwoHundredFiftySix() {
            mParams.setLimit(257);
            assertFalse(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void acceptsValuesBetweenZeroAndTwoHundredFiftySix(){
            mParams.setLimit(148);
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void hasDefaultValueOfTwoHundredFiftySix() {
            assertEquals("256", mLimitField.getAnnotation(DefaultValue.class).value());
        }

        @Test
        public void retrievesValueFromQueryParameter(){
            assertNotNull(mLimitField.getAnnotation(QueryParam.class));
        }
    }

    @RunWith(JMockit.class)
    public static class OffsetProperty {
        @Tested PagingParams mParams;
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        private Validator mValidator;
        private Field mOffsetField;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            // Set fields to values that pass validation
            mParams = new PagingParams();
            mParams.setLimit(10);
            mParams.setOffset(25);
            mParams.setSortField("name");
            mParams.setSortOrder(1);

            mOffsetField = PagingParams.class.getDeclaredField("offset");
            mOffsetField.setAccessible(true);
        }

        @Test
        public void errorsOutWhenLessThanZero() {
            mParams.setOffset(-1);
            assertFalse(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void hasDefaultValueOfZero() {
            assertEquals("0", mOffsetField.getAnnotation(DefaultValue.class).value());
        }

        @Test
        public void retrievesValueFromQueryParameter(){
            assertNotNull(mOffsetField.getAnnotation(QueryParam.class));
        }
    }

    @RunWith(JMockit.class)
    public static class SortFieldProperty {
        @Tested PagingParams mParams;
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        private Validator mValidator;
        private Field mSortField;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            // Set fields to values that pass validation
            mParams = new PagingParams();
            mParams.setLimit(10);
            mParams.setOffset(25);
            mParams.setSortField("name");
            mParams.setSortOrder(1);

            mSortField = PagingParams.class.getDeclaredField("sortField");
            mSortField.setAccessible(true);
        }

        @Test
        public void sortsOnNameByDefault() {
            assertEquals("name", mSortField.getAnnotation(DefaultValue.class).value());
        }

        @Test
        public void retrievesValueFromQueryParameter(){
            assertNotNull(mSortField.getAnnotation(QueryParam.class));
        }

        @Test
        public void acceptsNameAsValue(){
            mParams.setSortField("name");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void acceptsTitleAsValue(){
            mParams.setSortField("title");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void acceptsNorthAsValue(){
            mParams.setSortField("north");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void acceptsSouthAsValue(){
            mParams.setSortField("south");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void acceptsEastAsValue(){
            mParams.setSortField("east");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void acceptsWestAsValue(){
            mParams.setSortField("west");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void doesCaseInsensitivePatternMatching(){
            mParams.setSortField("NAME");
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void acceptsNoOtherValue(){
            mParams.setSortField("northwest");
            assertFalse(mValidator.validate(mParams).isEmpty());
        }
    }

    @RunWith(JMockit.class)
    public static class SortOrderProperty {
        @Tested PagingParams mParams;
        @Mocked LogManager mLogManager;
        @Mocked Logger mLogger;

        private Validator mValidator;
        private Field mSortOrderField;

        @Before
        public void initialize() throws NoSuchFieldException {
            mValidator = Validation.buildDefaultValidatorFactory().getValidator();

            // Set fields to values that pass validation
            mParams = new PagingParams();
            mParams.setLimit(10);
            mParams.setOffset(25);
            mParams.setSortField("name");
            mParams.setSortOrder(1);

            mSortOrderField = PagingParams.class.getDeclaredField("sortOrder");
            mSortOrderField.setAccessible(true);
        }

        @Test
        public void errorsOutWhenLessThanMinusOne() {
            mParams.setSortOrder(-2);
            assertFalse(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void acceptsMinusOneWithoutError() {
            mParams.setSortOrder(-1);
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void acceptsOneWithoutError() {
            mParams.setSortOrder(1);
            assertTrue(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void errorsOutWhenMoreThanOne() {
            mParams.setSortOrder(2);
            assertFalse(mValidator.validate(mParams).isEmpty());
        }

        @Test
        public void hasDefaultValueOfOne() {
            assertEquals("1", mSortOrderField.getAnnotation(DefaultValue.class).value());
        }

        @Test
        public void retrievesValueFromQueryParameter(){
            assertNotNull(mSortOrderField.getAnnotation(QueryParam.class));
        }
    }
}
