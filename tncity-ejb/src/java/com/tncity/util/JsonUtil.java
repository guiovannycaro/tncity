/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author inmoticamaster
 */
public class JsonUtil {

    public static <T> T jsonToObject(String json, Class<T> c) {
        T data = new Gson().fromJson(json, c);
        return data;
    }

    public static String objectToJson(Object obj) {
        return new Gson().toJson(obj);
    }

    public static void main(String... args) throws Exception {
        TestData td = new TestData();
        td.setId(new Long(1));
        td.setName("First");
        td.getLstD().add("A");
        td.getLstD().add("B");
        td.getLstD().add("C");

        System.out.println("JSON:" + objectToJson(td));

        String json = "{\"id\":1,\"name\":\"First\",\"lstD\":[\"A\",\"B\",\"C\"]}";
        TestData tdRes = jsonToObject(json, TestData.class);
        System.out.println("ID:" + tdRes.getId());

        System.out.println("Example 2 -------------------------");
        json = "{\"messages\":[{\"to\":\"573157371236\",\"status\":{\"groupId\":1,\"groupName\":\"PENDING\",\"id\":7,\"name\":\"PENDING_ENROUTE\",\"description\":\"Message sent to next instance\"},\"messageId\":\"28016652717603536271\",\"smsCount\":1}]}";
        //List<messages> lstD = jsonToObject(json, messages.class).getData();
        // System.out.println("Size:" + lstD.size());

      

        String cadena[] = json.split(":");
        for (int i = 0; i < cadena.length; i++) {
           System.out.println( "posicion : " + i + "dato: " + cadena[i]);
        }

        String cadena2 = cadena[9];
        String cadena3[] = cadena2.split(",");
        System.out.println(cadena3[0].replace("\"", ""));

    }
}

class TestData {

    private Long id;
    private String name;
    private List<String> lstD = new ArrayList<>();
    private List<TestData> data = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLstD() {
        return lstD;
    }

    public void setLstD(List<String> lstD) {
        this.lstD = lstD;
    }

    public List<TestData> getData() {
        return data;
    }

    public void setData(List<TestData> data) {
        this.data = data;
    }

}

class messages {

    String to;

    String messageId;
    String smsCount;
    status sta = new status();
    private List<String> lstD = new ArrayList<>();
    private List<messages> data = new ArrayList<>();

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(String smsCount) {
        this.smsCount = smsCount;
    }

    public status getSta() {
        return sta;
    }

    public void setSta(status sta) {
        this.sta = sta;
    }

    public List<messages> getData() {
        return data;
    }

    public void setData(List<messages> data) {
        this.data = data;
    }

    public List<String> getLstD() {
        return lstD;
    }

    public void setLstD(List<String> lstD) {
        this.lstD = lstD;
    }

}

class status {

    String groupId;
    String groupName;
    String id;

    String name;
    String description;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
