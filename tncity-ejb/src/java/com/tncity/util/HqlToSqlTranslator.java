package com.tncity.util;


import java.util.Collections;
import org.hibernate.SessionFactory;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.QueryTranslator;
import org.hibernate.hql.QueryTranslatorFactory;
import org.hibernate.hql.ast.ASTQueryTranslatorFactory;
 
public class HqlToSqlTranslator {
  private SessionFactory sessionFactory;
 
  public HqlToSqlTranslator(SessionFactory sessionFactory){
    this.sessionFactory = sessionFactory;
  }
 
  public String toSql(String hqlQueryText){
    if (hqlQueryText!=null && hqlQueryText.trim().length()>0){
      final QueryTranslatorFactory translatorFactory = new ASTQueryTranslatorFactory();
      final SessionFactoryImplementor factory = 
        (SessionFactoryImplementor) sessionFactory;
      final QueryTranslator translator = translatorFactory.
        createQueryTranslator(
          hqlQueryText, 
          hqlQueryText, 
          Collections.EMPTY_MAP, factory
        );
      translator.compile(Collections.EMPTY_MAP, false);
      return postProcesor(translator.getSQLString()); 
    }
    return null;
  }
  
  private static String postProcesor(String sql){
      String pos="";
      sql=sql.trim();
      
      int b=sql.toLowerCase().indexOf("select");
      int e=sql.toLowerCase().indexOf("from");
      pos=sql.substring(b+6, e);
      
      sql=sql.replaceAll(pos," _XX_ ");
      
      String content="";
      String[] arrPos=pos.split(",");
      for (int i = 0; i < arrPos.length; i++) {
          String string = arrPos[i];
          String[] a=string.toLowerCase().split(" as ");
          content+=a[0]+",";
      }
      if(content.length()>0){
          content=content.substring(0,content.length()-1);
      }
      
      sql=sql.replaceAll("_XX_", content);
      return sql;
  }  
}