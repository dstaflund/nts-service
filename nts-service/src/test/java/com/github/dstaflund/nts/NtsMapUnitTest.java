package com.github.dstaflund.nts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class NtsMapUnitTest {

    @RunWith(JMockit.class)
    public static class Clazz {

        @Test
        public void isHibernateEntity(){
            assertNotNull(NtsMap.class.getAnnotation(Entity.class));
        }

        @Test
        public void mapsToNTsMapsTable(){
            assertEquals("nts_maps", NtsMap.class.getAnnotation(Table.class).name());
        }

        @Test
        public void mapsToPublicScheme(){
            assertEquals("public", NtsMap.class.getAnnotation(Table.class).schema());
        }

        @Test
        public void hasFifteenNamedQueries(){
            assertEquals(15, NtsMap.class.getAnnotation(NamedQueries.class).value().length);
        }

        @Test
        public void hasTwoNamedNativeQueries(){
            assertEquals(2, NtsMap.class.getAnnotation(NamedNativeQueries.class).value().length);
        }
    }

    @RunWith(JMockit.class)
    public static class NameField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("name");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToIdColumn() {
            assertNotNull(mField.getAnnotation(Id.class));
        }

        @Test
        public void mapsToNameColumn() {
            assertEquals("name", mColumn.name());
        }

        @Test
        public void isUnique() {
            assertTrue(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }

        @Test
        public void hasMaximumLengthOfSixCharacters() {
            assertEquals(6, mColumn.length());
        }
    }

    @RunWith(JMockit.class)
    public static class SnippetField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("snippet");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void isCalledTitleWhenConvertedToJson() {
            assertEquals("title", mField.getAnnotation(JsonProperty.class).value());
        }

        @Test
        public void mapsToSnippetColumn() {
            assertEquals("snippet", mColumn.name());
        }

        @Test
        public void isNotUnique() {
            assertFalse(mColumn.unique());
        }

        @Test
        public void isNullable() {
            assertTrue(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }

        @Test
        public void hasMaximumLengthOfFortyCharacters() {
            assertEquals(40, mColumn.length());
        }
    }

    @RunWith(JMockit.class)
    public static class NorthField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("north");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToNorthColumn() {
            assertEquals("north", mColumn.name());
        }

        @Test
        public void isNotUnique() {
            assertFalse(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }

        @Test
        public void hasPrecisionOfFive() {
            assertEquals(5, mColumn.precision());
        }

        @Test
        public void hasScaleOfTwo() {
            assertEquals(2, mColumn.scale());
        }
    }

    @RunWith(JMockit.class)
    public static class SouthField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("south");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToSouthColumn() {
            assertEquals("south", mColumn.name());
        }

        @Test
        public void isNotUnique() {
            assertFalse(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }

        @Test
        public void hasPrecisionOfFive() {
            assertEquals(5, mColumn.precision());
        }

        @Test
        public void hasScaleOfTwo() {
            assertEquals(2, mColumn.scale());
        }
    }

    @RunWith(JMockit.class)
    public static class EastField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("east");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToEastColumn() {
            assertEquals("east", mColumn.name());
        }

        @Test
        public void isNotUnique() {
            assertFalse(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }

        @Test
        public void hasPrecisionOfFive() {
            assertEquals(5, mColumn.precision());
        }

        @Test
        public void hasScaleOfTwo() {
            assertEquals(2, mColumn.scale());
        }
    }

    @RunWith(JMockit.class)
    public static class WestField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("west");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToWestColumn() {
            assertEquals("west", mColumn.name());
        }

        @Test
        public void isNotUnique() {
            assertFalse(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }

        @Test
        public void hasPrecisionOfFive() {
            assertEquals(5, mColumn.precision());
        }

        @Test
        public void hasScaleOfTwo() {
            assertEquals(2, mColumn.scale());
        }
    }

    @RunWith(JMockit.class)
    public static class SearchNameField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("searchName");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToSearchNameColumn() {
            assertEquals("search_name", mColumn.name());
        }

        @Test
        public void isUnique() {
            assertTrue(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }

        @Test
        public void hasMaximumLengthOfSixCharacters() {
            assertEquals(6, mColumn.length());
        }

        @Test
        public void isIgnoredByJsonSerializer(){
            assertNotNull(mField.getAnnotation(JsonIgnore.class));
        }
    }

    @RunWith(JMockit.class)
    public static class SearchNorthField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("searchNorth");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToSearchNorthColumn() {
            assertEquals("search_north", mColumn.name());
        }

        @Test
        public void isNotUnique() {
            assertFalse(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }
        @Test
        public void hasMaximumLengthOfSevenCharacters() {
            assertEquals(7, mColumn.length());
        }

        @Test
        public void isIgnoredByJsonSerializer(){
            assertNotNull(mField.getAnnotation(JsonIgnore.class));
        }
    }

    @RunWith(JMockit.class)
    public static class SearchSouthField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("searchSouth");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToSearchSouthColumn() {
            assertEquals("search_south", mColumn.name());
        }

        @Test
        public void isNotUnique() {
            assertFalse(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }
        @Test
        public void hasMaximumLengthOfSevenCharacters() {
            assertEquals(7, mColumn.length());
        }

        @Test
        public void isIgnoredByJsonSerializer(){
            assertNotNull(mField.getAnnotation(JsonIgnore.class));
        }
    }

    @RunWith(JMockit.class)
    public static class SearchEastField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("searchEast");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToSearchEastColumn() {
            assertEquals("search_east", mColumn.name());
        }

        @Test
        public void isNotUnique() {
            assertFalse(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }
        @Test
        public void hasMaximumLengthOfSevenCharacters() {
            assertEquals(7, mColumn.length());
        }

        @Test
        public void isIgnoredByJsonSerializer(){
            assertNotNull(mField.getAnnotation(JsonIgnore.class));
        }
    }

    @RunWith(JMockit.class)
    public static class SearchWestField {
        private Field mField;
        private Column mColumn;

        @Before
        public void initialize() throws NoSuchFieldException {
            mField = NtsMap.class.getDeclaredField("searchWest");
            mField.setAccessible(true);
            mColumn = mField.getAnnotation(Column.class);
        }

        @Test
        public void mapsToSearchWestColumn() {
            assertEquals("search_west", mColumn.name());
        }

        @Test
        public void isNotUnique() {
            assertFalse(mColumn.unique());
        }

        @Test
        public void isNotNullable() {
            assertFalse(mColumn.nullable());
        }

        @Test
        public void isNotInsertable() {
            assertFalse(mColumn.insertable());
        }

        @Test
        public void isNotUpdatable() {
            assertFalse(mColumn.updatable());
        }
        @Test
        public void hasMaximumLengthOfSevenCharacters() {
            assertEquals(7, mColumn.length());
        }

        @Test
        public void isIgnoredByJsonSerializer(){
            assertNotNull(mField.getAnnotation(JsonIgnore.class));
        }
    }
}
