/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

/**
 *
 * @author Edwar Alonso Rojas Blanco - edwar.red@gmail.com
 */
public class JPAparser {

    Class EntityClass;

    public JPAparser(Class EntityClass) {
        this.EntityClass = EntityClass;
    }

    public Object parse(HashMap mapSQLresult) {
        Object val = null;
        String field = null;

        try {
            Object objInstance = EntityClass.newInstance();

            //Columns
            List<Column> lstCol = this.getColumns();
            List<Field> lstFie = this.getFieldColumns();
            for (int i = 0; i < lstCol.size(); i++) {
                val = mapSQLresult.get(lstCol.get(i).name());
                field = lstFie.get(i).getName().trim();
                Class classField = (Class) lstFie.get(i).getGenericType();

                //Recorriendo metodos
                Method[] arrMet = EntityClass.getDeclaredMethods();
                for (int j = 0; j < arrMet.length; j++) {
                    if (arrMet[j].getName().trim().toLowerCase().equals("set" + field.toLowerCase())) {
                        if (val != null) {
                            try {
                                if (!classField.getCanonicalName().equals(val.getClass().getCanonicalName())) {

                                    if (val.getClass().getCanonicalName().equals("java.math.BigDecimal") && classField.getCanonicalName().equals("java.math.BigInteger")) {
                                        val = new BigInteger(val.toString());
                                    }

                                    if (val.getClass().getCanonicalName().equals("java.math.BigDecimal") && classField.getCanonicalName().equals("java.lang.Long")) {
                                        val = new Long(val.toString());
                                    }

                                    if (val.getClass().getCanonicalName().equals("java.lang.String") && classField.getCanonicalName().equals("java.lang.Character")) {
                                        val = val.toString().charAt(0);
                                    }

                                    if (val.getClass().getCanonicalName().equals("java.math.BigDecimal") && classField.getCanonicalName().equals("int")) {
                                        val = Integer.parseInt(val.toString());
                                    }
                                    if (val.getClass().getCanonicalName().equals("java.math.BigDecimal") && classField.getCanonicalName().equals("long")) {
                                        val = Long.parseLong(val.toString());
                                    }
                                }

                                arrMet[j].invoke(objInstance, val);
                            } catch (Exception e) {
                                System.err.println("JPAparser -> FALLA: de casting (" + field + " = " + val + "), " + classField.getCanonicalName() + " != " + val.getClass().getCanonicalName() + " =>" + e);
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            //Join Columns
            List<JoinColumn> lstJoinCol = this.getJoinColumns();
            List<Field> lstFieJoin = this.getFieldJoinColumns();
            for (int i = 0; i < lstJoinCol.size(); i++) {
                //Recorriendo metodos
                Method[] arrMet = EntityClass.getDeclaredMethods();
                for (int j = 0; j < arrMet.length; j++) {
                    val = mapSQLresult.get(lstJoinCol.get(i).name().trim());
                    field = lstFieJoin.get(i).getName().trim();

                    if (arrMet[j].getName().trim().toLowerCase().equals("set" + field.toLowerCase())) {
                        Class auxClass = (Class) lstFieJoin.get(i).getGenericType();

                        Object objAux = null;
                        if (val != null) {
                            Class paramConstructorClass = val.getClass();
                            objAux = auxClass.getConstructor(paramConstructorClass).newInstance(val);
                        }

                        arrMet[j].invoke(objInstance, objAux);
                    }
                }
            }

            return objInstance;
        } catch (Exception e) {
            String c = "";
            if (val != null) {
                c = val.getClass().toString();
            }
            System.out.println("JPAparser dice: FALLA, instanciando objeto de clase \"" + EntityClass.toString() + "\" (" + field + " = " + val + "[" + c + "]):");
            e.printStackTrace();
        }

        return null;
    }

    private List<Column> getColumns() {
        List lst = new ArrayList();
        Field[] arrField = EntityClass.getDeclaredFields();
        for (int i = 0; i < arrField.length; i++) {
            Annotation a = arrField[i].getAnnotation(Column.class);
            if (a != null) {
                Column col = (Column) a;
                lst.add(col);
            }
        }
        return lst;
    }

    private List<Field> getFieldColumns() {
        List lst = new ArrayList();
        Field[] arrField = EntityClass.getDeclaredFields();
        for (int i = 0; i < arrField.length; i++) {
            Annotation a = arrField[i].getAnnotation(Column.class);
            if (a != null) {
                lst.add(arrField[i]);
            }
        }
        return lst;
    }

    private List<JoinColumn> getJoinColumns() {
        List lst = new ArrayList();
        Field[] arrField = EntityClass.getDeclaredFields();
        for (int i = 0; i < arrField.length; i++) {
            Annotation a = arrField[i].getAnnotation(JoinColumn.class);
            if (a != null) {
                JoinColumn col = (JoinColumn) a;
                lst.add(col);
            }
        }
        return lst;
    }

    private List<Field> getFieldJoinColumns() {
        List lst = new ArrayList();
        Field[] arrField = EntityClass.getDeclaredFields();
        for (int i = 0; i < arrField.length; i++) {
            Annotation a = arrField[i].getAnnotation(JoinColumn.class);
            if (a != null) {
                lst.add(arrField[i]);
            }
        }
        return lst;
    }

}
