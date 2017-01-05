package com.marcohc.architecture.common.util.helper;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.marcohc.architecture.parser.GenericCollection;
import com.marcohc.architecture.parser.GenericMap;
import com.marcohc.architecture.parser.Parser;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class ParseTest {

    private static final int MAX_ITEMS = 2500;

    private static List<Model> manyModelList;
    private static Map<Integer, Model> manyModelMap;
    private static String manyJsonModelList;
    private static String manyJsonModelMap;

    private static class Model {
        private String name;
        private Date date;
        private Timestamp timestamp;
        private Date dateFromLong;
        private FooType fooType;
    }

    private static class Entity {
        private String name;
        private Long date;
        private Long timestamp;
        private Long dateFromLong;
        private FooType fooType;
    }

    private enum FooType {
        @SerializedName("one")
        ONE,
        @SerializedName("two")
        TWO
    }

    @SuppressLint("UseSparseArrays")
    @BeforeClass
    public static void setUp() throws ParseException {
        manyModelList = new ArrayList<>();
        manyModelMap = new HashMap<>();
        for (int i = 0; i < MAX_ITEMS; i++) {
            Model model = createRandomModel();
            manyModelList.add(model);
            manyModelMap.put(model.hashCode(), model);
        }
        manyJsonModelList = Parser.toJsonString(manyModelList);
        manyJsonModelMap = Parser.toJsonString(manyModelMap);
    }

    @NonNull
    private static Model createRandomModel() throws ParseException {
        Model model = new Model();
        Random random = new Random(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        model.name = "MyName" + random.nextInt(MAX_ITEMS);
        model.date = dateFormat.parse("1997-07-16T19:20:30+0200");
        model.timestamp = new Timestamp(random.nextLong());
        model.dateFromLong = new Date(random.nextLong());
        model.fooType = random.nextBoolean() ? FooType.ONE : FooType.TWO;
        return model;
    }

    // ************************************************************************************************************************************************************************
    // * Tests
    // ************************************************************************************************************************************************************************

    @Test
    public void testGsonBuilderNotNull() {
        Assert.assertNotNull(Parser.getGsonBuilder());
    }

    @Test
    public void testToJsonString() throws ParseException {
        Model model = createRandomModel();
        Assert.assertNotNull(Parser.toJsonString(model));
    }

    @Test
    public void testParseIsNull1() {
        Assert.assertNull(Parser.parse(null, String.class));
    }

    @Test
    public void testParseIsNull2() {
        Model model = new Model();
        Assert.assertNull(Parser.parse(model, String.class));
    }

    @Test
    public void testParseFullObjectWithDates() {
        String jsonString = "{\"name\":\"MyName\",\"date\":\"1997-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}";
        Model model = Parser.parse(jsonString, Model.class);
        Assert.assertNotNull(model);
        Assert.assertNotNull(model.name);
        Assert.assertNotNull(model.date);
        Assert.assertNotNull(model.timestamp);
        Assert.assertNotNull(model.dateFromLong);
        Assert.assertNotNull(model.fooType);
    }

    @Test
    public void testParseMap() {
        String jsonString = "{\"key\": {\"name\":\"MyName\",\"date\":\"1997-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}}";
        HashMap<String, Model> modelHashMap = Parser.parse(jsonString, new TypeToken<HashMap<String, Model>>() {
        });
        Assert.assertNotNull(modelHashMap);
    }

    @Test
    public void testParseList() {
        String jsonString = "[{\"name\":\"MyName\",\"date\":\"1997-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}]";
        List<Model> modelList = Parser.parse(jsonString, new TypeToken<List<Model>>() {
        });
        Assert.assertNotNull(modelList);
    }

    @Test
    public void testMapBetweenPojosFromEntityToModel() {
        String jsonString = "{\"name\":\"MyName\",\"date\":1472292095978,\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}";
        Entity entity = Parser.parse(jsonString, Entity.class);
        Assert.assertNotNull(entity);
        Model model = Parser.parse(entity, Model.class);
        Assert.assertNotNull(model);
        Assert.assertNotNull(model.name);
        Assert.assertNotNull(model.date);
        Assert.assertNotNull(model.timestamp);
        Assert.assertNotNull(model.dateFromLong);
        Assert.assertNotNull(model.fooType);
    }

    @Test
    public void testMapBetweenPojosFromModelToEntity() {
        String jsonString = "{\"name\":\"MyName\",\"date\":\"2016-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}";
        Model model = Parser.parse(jsonString, Model.class);
        Assert.assertNotNull(model);
        Entity entity = Parser.parse(model, Entity.class);
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.name);
        Assert.assertNotNull(entity.date);
        Assert.assertNotNull(entity.timestamp);
        Assert.assertNotNull(entity.dateFromLong);
        Assert.assertNotNull(entity.fooType);
    }

    @Test
    public void testParseGenerics() {
        String jsonString = "[{\"name\":\"MyName\",\"date\":\"1997-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}]";
        List<Model> modelList = Parser.parseCollection(jsonString, new GenericCollection<List, Model>(List.class, Model.class) {
        });
        Assert.assertNotNull(modelList);
    }

    @Test
    public void testParseGenerics2() {
        String jsonString = "{\"key\": {\"name\":\"MyName\",\"date\":\"1997-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}}";
        Map<String, Model> modelList = Parser.parseMap(jsonString, new GenericMap<HashMap, String, Model>(HashMap.class, String.class, Model.class) {
        });
        Assert.assertNotNull(modelList);
    }

    @Test
    public void testToJsonStringWithManyItems() throws ParseException {
        String jsonList = Parser.toJsonString(manyModelList);
        Assert.assertNotNull(jsonList);
    }

    @Test
    public void testParseListWithManyItems() throws ParseException {
        List<Model> modelList = Parser.parseCollection(manyJsonModelList, new GenericCollection<ArrayList, Model>(ArrayList.class, Model.class) {
        });
        Assert.assertTrue(modelList instanceof ArrayList);
        Assert.assertNotNull(modelList);
    }

    @Test
    public void testParseMapWithManyItems() throws ParseException {
        Map<String, Model> modelMap = Parser.parseMap(manyJsonModelMap, new GenericMap<HashMap, Integer, Model>(HashMap.class, Integer.class, Model.class) {
        });
        Assert.assertTrue(modelMap instanceof HashMap);
        Assert.assertNotNull(modelMap);
    }

}