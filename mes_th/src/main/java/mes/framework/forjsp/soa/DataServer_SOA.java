package mes.framework.forjsp.soa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * �������ݲ������
 * @author ���� 2007-7-2
 */


public class DataServer_SOA 
{		
	private Connection conn=null;
	
	public DataServer_SOA(Connection conn)
	{
		this.conn=conn;
	}
	
	//�������̺Ż�ȡ������
	public String getProcessName_ProcessID(String processid) throws Exception
	{
		String processname="";
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try
		{
			if(processid==null||processid.trim().equals("")) throw new Exception("ʵ��Ϊ�գ�");
			sql="select cprocessname from process_statement where nprocessid="+processid+"";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				processname=rs.getString(1);	
			}
			return processname;
		}
		catch(Exception e)
		{
			System.out.println("DataServer_SOA��getProcessName_ProcessID(String processid)�����׳��쳣"+e);
			throw e;
		}finally{
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
		}
	}
	
	//���ݷ���Ż�ȡ������
	public String getServerName_ServerID(String serverid) throws Exception
	{
		String servername="";
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try
		{
			if(serverid==null||serverid.trim().equals("")) throw new Exception("ʵ��Ϊ�գ�");
			sql="select cservername from server_statement where nserverid="+serverid+"";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				servername=rs.getString(1);	
			}
			return servername;
		}
		catch(Exception e)
		{
			System.out.println("DataServer_SOA��getServerName_ServerID(String serverid)�����׳��쳣"+e);
			throw e;
		}finally{
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
		}
	}
	
	//�������̺ţ����������ȡ�����
	public String getServerID_Processid_ServerAlias(String processid,String serveralias) throws Exception
	{
		
		String serverid="";
		
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try
		{
			if(processid==null||processid.trim().equals("")) throw new Exception("ʵ�����̺�Ϊ�գ�");
			if(serveralias==null||serveralias.trim().equals("")) throw new Exception("ʵ�η������Ϊ�գ�");
			sql="select nserverid from process_servers where nprocessid="+processid+" and caliasname='"+serveralias+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				serverid=rs.getString(1);	
			}
			return serverid;
		}
		catch(Exception e)
		{
			System.out.println("DataServer_SOA��getServerID_Processid_ServerAlias(String processid,String serveralias)�����׳��쳣"+e);
			throw e;
		}finally{
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
		}
	}
	
	//����������Դ�ţ���ȡ������Դ����
	public String getSourceDescription_SourceID(String sourceid) throws Exception
	{
		
		String sourcedescription="";
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try
		{
			if(sourceid==null||sourceid.trim().equals("")) throw new Exception("ʵ��Ϊ��");
			sql="select cdescription from source_statement where nsourceid="+sourceid+"";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				sourcedescription=rs.getString(1);	
			}
			return sourcedescription;
		}
		catch(Exception e)
		{
			System.out.println("DataServer_SOA��getSourceDescription_SourceID(String sourceid)�����׳��쳣"+e);
			throw e;
		}finally{
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
		}
	}	
}