/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.tncity.jpa.pojo.Usuario;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author edwar
 */
public class EntityUtil {

    public static String getAttrHqlToSql(String attr, Class entityClass) {
        for (Field f : entityClass.getDeclaredFields()) {
            if (f.getName().equals(attr)) {
                Column c = f.getAnnotation(Column.class);
                if (c != null) {
                    return c.name();
                }
            }
        }
        return null;
    }

    public static String getTableName(Class entityClass) {
        Annotation a = entityClass.getAnnotation(Table.class);
        Table t = (Table) a;
        if (t != null) {
            return t.name();
        }
        return null;
    }

    public static String getAttrId_SqlName(Class entityClass) {
        return getAttrHqlToSql(getAttrId(entityClass), entityClass);
    }

    public static String getAttrId(Class entityClass) {
        for (Field f : entityClass.getDeclaredFields()) {
            Id c = f.getAnnotation(Id.class);
            if (c != null) {
                return f.getName();
            }
        }
        return null;
    }

    public static Class getAttrIdClass(Class entityClass) {
        for (Field f : entityClass.getDeclaredFields()) {
            Id c = f.getAnnotation(Id.class);
            if (c != null) {
                return f.getType();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getAttrHqlToSql("isActivo", Usuario.class));
        System.out.println(getTableName(Usuario.class));
        System.out.println(getAttrId(Usuario.class));
        System.out.println(getAttrIdClass(Usuario.class));
        System.out.println(getAttrId_SqlName(Usuario.class));
    }
}
