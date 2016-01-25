package common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Conn_MES
{

  public Conn_MES()
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
       //�����������Tomcat��
       ds = (DataSource)ctx.lookup("java:comp/env/mes_th");
       //�����������Weblogic��
       //ds = (DataSource)ctx.lookup("soa_mes");
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