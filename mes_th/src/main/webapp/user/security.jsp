<%@page import="java.util.Vector"  contentType="text/html;charset=gb2312" %>
<% 
	String userid=(String )session.getAttribute("userid");

	if(userid==null) 
	{
		out.write("���ʱ��ܾ���");
		return;
	}
	
	
	Vector v=(Vector)session.getAttribute("safemark");
	String safemark="user";
	if(v==null)
	{
		out.write("���ʱ��ܾ���");
		return;
	}
	else
	{
		boolean safesign=false;
		for(int i=0;i<v.size();i++)
		{
			String temp=(String)v.elementAt(i);
			if(temp.trim().equals(safemark))
			{
				safesign=true;
				break;
			}
		}
		if(!safesign){ out.write("���ʱ��ܾ���");return;}
	}
%>