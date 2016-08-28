package com.marcohc.architecture.common.helper;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import junit.framework.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParseHelperTest {

    private class Model {
        private String name;
        private Date date;
        private Timestamp timestamp;
        private Date dateFromLong;
        private FooType fooType;
    }

    private class Entity {
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
        TWO;
    }

    @Test
    public void testToJsonString() throws ParseException {
        Model model = new Model();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        model.name = "MyName";
        model.date = dateFormat.parse("1997-07-16T19:20:30+0200");
        model.timestamp = new Timestamp(1472292095978L);
        model.dateFromLong = new Date(1472292095978L);
        model.fooType = FooType.ONE;
        Assert.assertNotNull(ParserHelper.getInstance().toJsonString(model));
    }

    @Test
    public void testParseIsNull1() {
        Assert.assertNull(ParserHelper.getInstance().parse(null, String.class));
    }

    @Test
    public void testParseIsNull2() {
        Model model = new Model();
        Assert.assertNull(ParserHelper.getInstance().parse(model, String.class));
    }

    @Test
    public void testParseFullObjectWithDates() {
        String jsonString = "{\"name\":\"MyName\",\"date\":\"1997-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}";
        Model model = ParserHelper.getInstance().parse(jsonString, Model.class);
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
        HashMap<String, Model> modelHashMap = ParserHelper.getInstance().parse(jsonString, new TypeToken<HashMap<String, Model>>() {
        });
        Assert.assertNotNull(modelHashMap);
    }

    @Test
    public void testParseList() {
        String jsonString = "[{\"name\":\"MyName\",\"date\":\"1997-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}]";
        List<Model> modelList = ParserHelper.getInstance().parse(jsonString, new TypeToken<List<Model>>() {
        });
        Assert.assertNotNull(modelList);
    }

    @Test
    public void testMapBetweenPojos() {
        String jsonString = "{\"name\":\"MyName\",\"date\":1472292095978,\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}";
        Entity entity = ParserHelper.getInstance().parse(jsonString, Entity.class);
        Assert.assertNotNull(entity);
        Model model = ParserHelper.getInstance().parse(entity, Model.class);
        Assert.assertNotNull(model);
        Assert.assertNotNull(model.name);
        Assert.assertNotNull(model.date);
        Assert.assertNotNull(model.timestamp);
        Assert.assertNotNull(model.dateFromLong);
        Assert.assertNotNull(model.fooType);
    }

    @Test
    public void testParseGenerics() {
        String jsonString = "[{\"name\":\"MyName\",\"date\":\"1997-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}]";
        List<Model> modelList = ParserHelper.getInstance().parseCollection(jsonString, new GenericCollection<List, Model>(List.class, Model.class) {
        });
        Assert.assertNotNull(modelList);
    }

    @Test
    public void testParseGenerics2() {
        String jsonString = "{\"key\": {\"name\":\"MyName\",\"date\":\"1997-07-16T19:20:30.45+01:00\",\"fooType\":\"one\",\"timestamp\":1472292095978,\"dateFromLong\":1472292095978}}";
        Map<String, Model> modelList = ParserHelper.getInstance().parseMap(jsonString, new GenericMap<HashMap, String, Model>(HashMap.class, String.class, Model.class) {
        });
        Assert.assertNotNull(modelList);
    }

}