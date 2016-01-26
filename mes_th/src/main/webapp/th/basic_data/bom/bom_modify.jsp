<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="com.qm.th.helpers.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>

<% 
	/** ����ID */
	String id = request.getParameter("intId");
	/** ҳ�� */
	String page_num = request.getParameter("strPage");
	/** �������� */
	String tfass = "";
	/** ����������� */
	String vwmainpart = "";
	/** ����������������� */
	String vwsubparttype = "";
	/** ����������� */
	String vwsubpart = "";
	/** ������������� */
	String vwsubpartquan = "";
	/** ������ */
	String qadno = "";
	/** �Ƿ���ȳ��� */
	String istempcar = "";
	/** ��ע */
	String remark = "";
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try{
		conn = new Conn_MES().getConn();
		stmt = conn.prepareStatement("SELECT * FROM PARTDATA WHERE ID = ?");
		stmt.setString(1, id);
		rs = stmt.executeQuery();
		
		if(rs.next()){
			tfass = rs.getString("cTFASS");
			vwmainpart = rs.getString("cVWMainPart");
			vwsubparttype = rs.getString("nVWSubPartType");
			vwsubpart = rs.getString("cVWSubPart");
			vwsubpartquan = rs.getString("nVWSubPartQuan");
			qadno = rs.getString("cQADNo");
			istempcar = rs.getString("cIsTempCar");
			remark = rs.getString("cRemark");
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		// resource release
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		if(stmt != null){
			try{
				stmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				stmt = null;
			}
		}
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	}
%>

<html>
    <head>
        <title>BOMά��</title>
        <meta http-equiv=content-type content="text/html;charset=GBK">
        <link rel="stylesheet" type="text/css" href="../../../cssfile/style.css">
        <link rel="stylesheet" type="text/css" href="../../../cssfile/th_style.css">
        <script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>
        <script type="text/javascript" src="js/core.js"></script>
    </head>
    <body>
        <div align="center">
            <div class="title"><strong><!-- InstanceBeginEditable name="����" -->BOMά��<!-- InstanceEndEditable --></strong></div>
	        <form action="bom_update.jsp" onsubmit="return checkForm(this)">
	        	<input type="hidden" name="page" value="<%=page_num%>">
	        	<input type="hidden" name="intId" value="<%=id%>">
	            <table border = 0 class="th_table" cellspacing = 0 cellpadding = 0>
	                <tr>
	                    <td class="tbl_title">
	                        ID:
	                    </td>
	                    <td>
	                       <mes:inputbox colorstyle="" id="partdata_id" name="partdata_id" reSourceURL="../../../JarResource" readonly="true" value="<%=id%>"/>
	                    </td>
	                    <td class = "tbl_title">
	                        ��������:
	                    </td>
	                    <td>
	                       <mes:inputbox colorstyle="box_red" id="partdata_ctfass" name="partdata_ctfass" reSourceURL="../../../JarResource" value="<%=tfass%>"/>
	                    </td>
	                </tr>
	                <tr>
	                    <td class = "tbl_title">
	                        �����������:
	                    </td>
	                    <td>
	                       <mes:inputbox colorstyle="" id="primary" name="primary" reSourceURL="../../../JarResource" readonly="true" value="<%=vwmainpart%>"/>
	                       <input type="button" value=".." onclick="showDialog(this)" alt="00001" id="fawprimary"/>
	                    </td>
	                    <td class = "tbl_title">
	                        ���������������:
	                    </td>
	                    <td>
	                       <mes:inputbox colorstyle="box_red" id="partdata_nvwsubparttype" name="partdata_nvwsubparttype" reSourceURL="../../../JarResource" maxlength="4" value="<%=vwsubparttype%>"/>
	                    </td>
	                </tr>
	                <tr>
	                   <td class="tbl_title">
                            �����������:
                       </td>
                       <td>
                           <mes:inputbox colorstyle="" id="sub" name="sub" reSourceURL="../../../JarResource" readonly="true" value="<%=vwsubpart%>"/>
                           <input type="button" value=".." onclick="showDialog(this)" alt="00002" id = "fawsub">
                       </td>
                       <td class="tbl_title">
                            �������������:
                       </td>
                       <td>
                           <mes:inputbox colorstyle="box_red" id="partdata_nvwsubpartquan" name="partdata_nvwsubpartquan" reSourceURL="../../../JarResource" maxlength="4" value="<%=vwsubpartquan%>"/>
                       </td>
	                </tr>
	                  <tr>
                        <td class="tbl_title">
                            ������:
                        </td>
                        <td>
                           <mes:inputbox colorstyle="box_red" id="plan_no" name="plan_no" reSourceURL="../../../JarResource" value="<%=qadno%>"/>
                        </td>
                        <td colspan = 2 align="center">
                            <input type="checkbox" id="tempcar" name="tempcar" style="" onfocus="this.blur()" tabindex="100" <%="T".equalsIgnoreCase(istempcar) ? "checked" : ""%>/> ���ȳ���
                        </td>
                    </tr>
                    <tr>
                        <td class="tbl_title">
                            ��ע:
                        </td>
                        <td colspan=3>
                            <mes:inputbox colorstyle="box_red" id="remark" name="remark" reSourceURL="../../../JarResource" size="40" maxlength="60" value="<%=remark%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan = 4 align="center">
                            <mes:button id="c_save" reSourceURL="../../../JarResource" value="�ύ" submit="true"/>
                            <mes:button id="c_clean" reSourceURL="../../../JarResource" value="���" submit="false"/>
                            <mes:button id="c_back" reSourceURL="../../../JarResource" value="����" onclick="backward()"/>
                        </td>
                    </tr>
	            </table>
	        </form>
        </div>
        <!-- ��ʾ���� -->
        <div class="fore" id="fore" align="center">
          <div>
               <div id = "subwin" style="border:solid 2px #336699;width:1px;height:238px;">
                       <!-- �������ڱ� -->
               </div>
               <div>
                   <input type="button" value="�ύ" onclick="apply()">
                  <input type="button" value="ȡ��" onclick="closeDialog()">
              </div>
          </div>
        </div>
    </body>
    <script>
        <!--
                /**
                 *  У��FormԪ��
                 *
                 *  @param fobj ������
                 */
                function checkForm(fobj){
                    var flg = checkEmpty(fobj);
                    if(flg){
                        flg = checkFormat(fobj);
                    }
                    return flg;  
                }
                
                /**
                 * ����ı�����Ϊ��
                 *
                 * @return false:�����쳣  true:���� 
                 */
                function checkEmpty(f){
                    var inputs = f.getElementsByTagName("input");
                    for(var i=0; i<inputs.length; i++){
                        var ipt = inputs[i];
                        if(ipt["type"] == "text"){
                            if(!ipt["value"]){
                                alert("�ı�����Ϊ��!");
                                ipt.focus();
                                return false;
                            }
                        }
                    }
                    return true;
                }
                
                /**
                 *  У���ı���ʽ
                 */
                function checkFormat(f){
                    // ��ʽ
                    var regexp = /^[0-9]+$/;
                    // ҪУ����ı����� ["���������������", "�������������"]
                    var elenames = ["partdata_nvwsubparttype", 
                                    "partdata_nvwsubpartquan"];
                   
                    for(var i =0; i<elenames.length; i++){
                        var name = elenames[i];
                        var inp = f[name];
                        
                        if(!inp.value.match(regexp)){
                            alert("ֻ����������");
                            inp.focus();
                            return false;
                        }
                    }
                    return true;
                }
                
                /**
                 * ����ҳ��
                 */
                function backward(){
                	var eid = "<%=id%>";
                	var page = "<%=page_num%>";
                	var url = "bom_manage.jsp?eid=" + eid + "&page=" + page;
                	window.location.href = url;
                }
        -->
    </script>
</html>