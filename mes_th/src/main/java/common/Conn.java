package common;

import javax.naming.*;
import javax.sql.*;

public class Conn
{
  public Conn()
  {
  }
  //Using weblogic JNDI DataSource Pool connection to connect to Oracle
  public java.sql.Connection getConn()
  {
     try
     {
       DataSource ds;
       Context ctx;
       ctx = new InitialContext();
       //�����������Weblogic��
       //ds = (DataSource)ctx.lookup("mes_framework");
       //System.out.println("java:comp/env/mes_framework");
       //�����������Tomcat��
       ds = (DataSource)ctx.lookup("java:comp/env/mes_th");
       //TODO ����м����ʱ��Ҫ�ǵ��޸�������ӳ��ļ�
       return ds.getConnection();
     }
     catch (Exception e) 
     {
      e.printStackTrace();
       return null;
     }
  }//end of private static Connection getConn


}//end of class Conn