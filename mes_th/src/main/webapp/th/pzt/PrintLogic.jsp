<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.qm.th.mdorder.ConfigOrderHandler"%>
<%@ page import="com.qm.th.mdorder.bean.COrderEntity"%>

<%
	// ȡǰһҳ������
	String jspRq = (String)request.getAttribute("rq");

	// ��ӡ���ù�����
	ConfigOrderHandler coHandler = new ConfigOrderHandler(jspRq);

	// Ҫ��ӡ�������б�
	List<COrderEntity> list = coHandler.execute();

	for(COrderEntity entity : list) {
		out.write("<tr>");	
			out.write("<td>" + entity.getGroupId() + "</td>");
			out.write("<td>" + entity.getDescript() + "</td>");
			out.write("<td>");
				out.write("��ʷ�ܺ�<input name='oldjh" + entity.getGroupId() + "' id='oldjh" + entity.getGroupId() + "' size='3' value='0'/>");
				out.write("<input type='button' value='��ʷ��ӡ' onclick='printOld(" + entity.getGroupId() + ")'/>");
				out.write("<input type='button' value='���´�ӡ' onclick='reprint(" + entity.getGroupId() + ")'/>");
			out.write("</td>");
			out.write("<td>" + entity.getMaxCarno() + "</td>");
			out.write("<td >");
				out.write("<label ><strong><font color='#ff0000' size='3' face='����'>" + entity.getMinPartCount() + "</strong></label>");
			out.write("</td>");
			out.write("<td>");
				if(entity.getPrintRadio() == 1){
					out.write(entity.getTFassCount() + "����");
				}
				if(entity.getPrintRadio() == 2){
					out.write(entity.getTFassCount() * 2 + "����");	
				}
				if(entity.getPrintRadio() == 3){
					out.write(entity.getPerTimeCount() + "����");	
				}
			out.write("</td>");
			out.write("<td><input type='checkbox' name='checkBox" + entity.getGroupId() + "'   id='checkBox" + entity.getGroupId() + "'  disabled='true'");
			
			if(entity.getAuto().equals("1")){
				out.write("checked");
			}
			out.println("/> �Զ���ӡ</td>");
			out.write("<td><input type='button' name='button" + entity.getGroupId() + "' id='button" + entity.getGroupId() + "' value='�ύ' onclick='openApp(" + entity.getGroupId() + "," + entity.getPrintRadio() + "," + 1 + "," + entity.getPages() + "," + entity.getMinPartCount() + "," + entity.getPerTimeRow() + "," + (entity.isContinue() ? 1 : 0) + ")'/></label></td>");
		out.write("</tr>");
		
		// ����Զ���ӡ�ű�
		if(entity.getOpenApp() != null) { 
			String str = (String)request.getAttribute("openApp");
				   str = str == null ? entity.getOpenApp() : str + entity.getOpenApp();
			
			// �����Զ���ӡ�ű�
			request.setAttribute("openApp", str);
		}
	}
%>