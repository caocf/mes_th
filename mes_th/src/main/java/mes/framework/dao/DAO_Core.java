package mes.framework.dao;

abstract class DAO_Core implements IDAO_Core {

	// ��ѯ���̶�����δʹ�õ������ռ�
	public String getSQL_QueryNameSpaceForProcessStatement(String nnamespaceid) {
		return "select NNAMESPACEID,CNAMESPACE from namespace_statement "
				+ "where NNAMESPACEID not in "
				+ "(select NNAMESPACEID from process_statement where nnamespaceid="
				+ nnamespaceid + ")";
	}

	public String getSQL_QueryNameSpaceForProcessStatement() {
		return "select NNAMESPACEID,CNAMESPACE,cdescription from namespace_statement ";
	}

	// ��֤�����Ƿ�ĳһ����ʹ��
	public String getSQL_QueryCountServiceIsUsed(int serviceid) {
		return "select count(*) from PROCESS_SERVERS where NSERVERID="
				+ serviceid + "";
	}

	// ��ӷ�������Ϣ
	public String getSQL_InsertService(int serviceid, String servicename,
			String classname, String servicedesc, String namespace) {

		return "insert into SERVER_STATEMENT values(" + serviceid + ",'"
				+ servicename + "','" + classname + "','" + servicedesc + "','"
				+ namespace + "')";
	}

	// ���·�������Ϣ
	public String getSQL_UpdateService(int serviceid, String servicename,
			String classname, String servicedesc, int namespace) {
		return "update SERVER_STATEMENT set CSERVERNAME='" + servicename
				+ "',CCLASSNAME='" + classname + "',CDESCRIPTION='"
				+ servicedesc + "' ,NNAMESPACEID=" + namespace
				+ " where NSERVERID=" + serviceid + "";
	}

	// ɾ����������Ϣ
	public String getSQL_DeleteService(int serviceid) {
		return "delete from SERVER_STATEMENT where NSERVERID=" + serviceid + "";
	}

	// ��֤ͬһ����ͬ���͵Ĳ����Ƿ�Ψһ
	public String getSQL_QueryCountServiceParaIsUnique(int serviceid,
			String paraname, String paratype) {
		return "select count(*) from parameter_statement where nserverid="
				+ serviceid + " and ctype='" + paratype + "'and cparameter='"
				+ paraname + "'";
	}

	// ���������Ӳ���
	public String getSQL_InsertServicePara(int serviceid, String paraname,
			String paratype, String paradesc) {
		return "insert into parameter_statement values(" + serviceid + ",'"
				+ paraname + "','" + paratype + "','" + paradesc + "')";
	}

	// ���·������
	public String getSQL_UpdateServicePara(int serviceid, String paratype,
			String setparatype, String paraname, String setparaname,
			String paradesc) {
		return "update PARAMETER_STATEMENT set CPARAMETER ='" + paraname
				+ "',CTYPE = '" + paratype + "',CDESCRIPTION ='" + paradesc
				+ "' where NSERVERID ='" + serviceid + "' and CTYPE='"
				+ setparatype + "' and CPARAMETER='" + setparaname + "'";
	}

	// ɾ���������
	public String getSQL_DeleteServicePara(int serviceid, String paratype,
			String paraname) {
		return "delete PARAMETER_STATEMENT where NSERVERID=" + serviceid
				+ " and CTYPE = '" + paratype + "' and CPARAMETER ='"
				+ paraname + "'";
	}

	// ��ѯ��������б�
	public String getSQL_QueryAllServiceParas(String info, String method) {
		String sql = "";
		if (info != null && (!info.equals(""))) {
			if (method.equals("ByParas")) {
				sql = "select a.nserverid,b.cSERVERNAME,a.CPARAMETER,a.CTYPE from "
						+ "PARAMETER_STATEMENT a inner join server_statement b on a.nserverid= b.nserverid where a.CPARAMETER like '%"
						+ info + "%' order by a.nserverid";
			}
			if (method.equals("ByService")) {
				sql = "select a.nserverid,b.cSERVERNAME,a.CPARAMETER,a.CTYPE from "
						+ "PARAMETER_STATEMENT a inner join server_statement b on a.nserverid= b.nserverid where b.CSERVERNAME like '%"
						+ info + "%' order by a.nserverid";
			}
		} else {
			sql = "select a.nserverid,b.cSERVERNAME,a.CPARAMETER,a.CTYPE from "
					+ "PARAMETER_STATEMENT a inner join server_statement b on a.nserverid= b.nserverid order by a.nserverid";
		}
		return sql;
	}

	// �鿴�������������Ϣ
	public String getSQL_QueryServicePara(int serviceid, String paratype,
			String paraname) {
		return "select CPARAMETER,CTYPE,CDESCRIPTION from PARAMETER_STATEMENT where NSERVERID="
				+ serviceid
				+ " and CTYPE='"
				+ paratype
				+ "' and CPARAMETER='"
				+ paraname + "'";
	}

	// ��ѯ�����б�
	public String getSQL_QueryAllServices(String serviceinfo, String method) {
		String sql = "";
		if (serviceinfo != null && (!serviceinfo.equals(""))) {
			// ������Ų�ѯ
			if (method.equals("ById"))
				sql = "select NSERVERID,CSERVERNAME,CCLASSNAME,"
						+ "CDESCRIPTION,NNAMESPACEID from "
						+ "SERVER_STATEMENT  where  nserverid ='" + serviceinfo
						+ "'";
			// ���������Ʋ�ѯ
			if (method.equals("ByName"))
				sql = "select NSERVERID,CSERVERNAME,CCLASSNAME,"
						+ "CDESCRIPTION,NNAMESPACEID from "
						+ "SERVER_STATEMENT where cservername like '%"
						+ serviceinfo + "%' order by nserverid";

		} else
			sql = "select NSERVERID,CSERVERNAME,CCLASSNAME,"
					+ "CDESCRIPTION,NNAMESPACEID from "
					+ "SERVER_STATEMENT order by nserverid";

		return sql;
	}

	public String getSQL_InsertProcess(String processid, String sid,
			String serverid, String aliasname, String actid) {
		return "insert into PROCESS_SERVERS values(" + processid + ",'" + sid
				+ "','" + serverid + "','" + aliasname + "','" + actid + "')";
	}

	public String getSQL_DeleteProcess(String processid, String sid) {
		return "delete from PROCESS_SERVERS where NPROCESSID=" + processid
				+ " and NSID='" + sid + "'";
	}

	public String getSQL_UpdateProcess(String processid, String sid,
			String serverid, String aliasname, String actid) {
		return "update PROCESS_SERVERS set NSERVERID=" + serverid
				+ ",CALIASNAME='" + aliasname + "', NACTID='" + actid
				+ "' where NPROCESSID=" + processid + " and NSID='" + sid + "'";
	}

	public String getSQL_QueryCountProcessServerForProcessidAndSid(
			String processid, String sid) {
		return "select count(*) from process_servers where NPROCESSID="
				+ processid + " and NSID=" + sid;
	}

	public String getSQL_QueryCountProcessStatementForProcessid(String processid) {
		return "select count(*) from process_statement where NPROCESSID="
				+ processid;
	}

	public String getSQL_DeleteProcessStatement(String processid) {
		return "delete from PROCESS_STATEMENT where NPROCESSID=" + processid;
	}

	public String getSQL_InsertProcessStatement(String processid,
			String processname, String description, String namespace) {
		return "insert into PROCESS_STATEMENT values(" + processid + ",'"
				+ processname + "','" + description + "','" + namespace + "')";
	}

	public String getSQL_UpdateProcessStatement(String processid,
			String processname, String description, String namespace) {
		return "update PROCESS_STATEMENT set CPROCESSNAME='" + processname
				+ "',CDESCRIPTION='" + description + "', NNAMESPACEID='"
				+ namespace + "' where NPROCESSID=" + processid;
	}

	public String getSQL_DeleteAdapter(String processid, String aliasname) {
		return "delete adapter_statement where nprocessid=" + processid
				+ " and cialiasname='" + aliasname + "'";
	}

	// ��ѯ��������
	public String getSQL_QueryAllProcess() {
		return "select NPROCESSID,CPROCESSNAME,CDESCRIPTION,nnamespaceid"
				+ " from PROCESS_STATEMENT ";
	}

	public String getSQL_QueryProcess(String processid) {
		return "select NPROCESSID,CPROCESSNAME,CDESCRIPTION,NNAMESPACEID"
				+ " from PROCESS_STATEMENT" + " where nprocessid=" + processid;
	}

	// ��ѯĳ�����̵Ķ�Ӧ�ķ�����Ϣ
	public String getSQL_QueryProcessItem(String id) {
		return "select NPROCESSID ,NSID, NSERVERID , CALIASNAME ,	cdescription from	"
				+ "process_servers , act_statement where "
				+ "process_servers.nactid= act_statement.nactid and nprocessid="
				+ id + " order by nsid";
	}

	// ����������Ϣ���в�ѯ������������Ϣ
	public String getSQL_QueryAllAdapterInfos() {
		return "select nprocessid,cialiasname,ciparameter,nsourceid,COALIASNAME,"
				+ "COPARAMETER from adapter_statement order by nprocessid,cialiasname";
	}

	/**
	 * ��ѯ������������Ϣ�������������ѯ��������Ϣ
	 * 
	 * @param nprocessname
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         nprocessid ���̺�<br>
	 *         cialiasname ����������<br>
	 *         ciparameter �������<br>
	 *         nsourceid ������Դ��<br>
	 *         COALIASNAME ��������<br>
	 *         COPARAMETER ���������<br>
	 * @author ������
	 */
	public String getSQL_QueryAllAdapterInfos(String nprocessname) {
		String sql = "";
		if (nprocessname != null && (!nprocessname.equals(""))) {
			sql = "select a.nprocessid,a.cialiasname,a.ciparameter,a.nsourceid,a.COALIASNAME,"
					+ "a.COPARAMETER from adapter_statement a,PROCESS_STATEMENT b where b.CPROCESSNAME='"
					+ nprocessname
					+ "' and a.nprocessid=b.nprocessid order by nprocessid,cialiasname";
		} else {
			sql = "select nprocessid,cialiasname,ciparameter,nsourceid,COALIASNAME,"
					+ "COPARAMETER from adapter_statement order by nprocessid,cialiasname";
		}

		return sql;
	}

	/**
	 * �������̺Ż�ȡ������
	 * 
	 * @param processid
	 *            ���̺�
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         cprocessname ������
	 */
	public String getSQL_QueryProcessNameForProcessID(String processid) {
		return "select cprocessname from process_statement where nprocessid="
				+ processid + "";
	}

	/**
	 * ���ݷ���Ż�ȡ������
	 * 
	 * @param serverid
	 *            �����
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         cservername ��������
	 */
	public String getSQL_QueryServerNameForServerID(String serverid) {
		return "select cservername from server_statement where nserverid="
				+ serverid + "";
	}

	/**
	 * �������̺ţ����������ȡ�����
	 * 
	 * @param processid
	 *            ���̺�
	 * @param serveralias
	 *            �������
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         nserverid �����
	 */

	public String getSQL_QueryProcessServerForProcessIDServerAlias(
			String processid, String serveralias) {
		return "select NPROCESSID,NSID,NSERVERID,CALIASNAME,NACTID from "
				+ "process_servers where nprocessid=" + processid
				+ " and caliasname='" + serveralias + "'";
	}

	/**
	 * ����������Դ�ţ���ȡ������Դ����
	 * 
	 * @param sourceid
	 *            ������Դ��
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         cdescription ������Դ����
	 */
	public String getSQL_QuerySourceDescriptionForSourceID(String sourceid) {
		return "select cdescription from source_statement where nsourceid="
				+ sourceid + "";
	}

	/**
	 * ���ز�ѯ����ҵ��������Ϣ��SQL���
	 * 
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         nprocessid ���̺�<br>
	 *         cprocessname ������<br>
	 *         CDESCRIPTION ����<br>
	 *         NNAMESPACEID �����ռ��
	 */
	public String getSQL_QueryAllProcessInfos() {
		return "select nprocessid,cprocessname,CDESCRIPTION,NNAMESPACEID from "
				+ "process_statement order by nprocessid";
	}

	// ���ز�ѯ����������Դ��Ϣ��SQL���
	public String getSQL_QueryAllSourceInfos() {
		return "select nsourceid,cdescription from source_statement";
	}

	// �������̺ţ����������������������ȡ��������Ϣ
	public String getSQL_QueryAdapterInfo(String processid, String aliasname,
			String parametername) {
		return "select nsourceid,COALIASNAME,COPARAMETER from ADAPTER_STATEMENT where NPROCESSID="
				+ processid
				+ " and CIALIASNAME='"
				+ aliasname
				+ "' and CIPARAMETER='" + parametername + "'";
	}

	// �������̺ţ���ȡ�����̵�������������Ϣ
	public String getSQL_QueryAdepterInfo(String processid) {
		return "select nprocessid,CIALIASNAME,CIPARAMETER,NSOURCEID, "
				+ "COALIASNAME,COPARAMETER from adapter_statement "
				+ "where nprocessid=" + processid + " order by CIALIASNAME";
	}

	/**
	 * �������̺�,����������,������� ����������Ϣ���л�ȡ��¼��
	 * 
	 * @param processid
	 *            ���̺�
	 * @param serveralias
	 *            �������
	 * @param parameter
	 *            ������
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         count(*) ��¼��
	 */
	public String getSQL_QueryCountAdeptInfo(String processid,
			String serveralias, String parameter) {
		return "select count(*) from ADAPTER_STATEMENT where NPROCESSID="
				+ processid + " and CIALIASNAME='" + serveralias
				+ "' and CIPARAMETER='" + parameter + "'";
	}

	/**
	 * �����������Ϣ
	 * 
	 * @param processid
	 *            ���̺�
	 * @param i_serveralias
	 *            ����������
	 * @param i_parameter
	 *            ���������
	 * @param source
	 *            ������Դ��
	 * @param o_serverlias
	 *            ����������
	 * @param o_parameter
	 *            ���������
	 * @return
	 */
	public String getSQL_InsertAdapterInfo(String processid,
			String i_serveralias, String i_parameter, String source,
			String o_serverlias, String o_parameter) {
		return "insert into ADAPTER_STATEMENT values(" + processid + ",'"
				+ i_serveralias + "','" + i_parameter + "'," + source + ",'"
				+ o_serverlias + "','" + o_parameter + "')";
	}

	/**
	 * �������̺�,����������,�������,ɾ����������Ϣ
	 * 
	 * @param processid
	 *            ���̺�
	 * @param i_serveralias
	 *            ����������
	 * @param i_parameter
	 *            �������
	 * @return
	 */
	public String getSQL_DeleteAdapterInfo(String processid,
			String i_serveralias, String i_parameter) {
		return "delete from ADAPTER_STATEMENT where NPROCESSID=" + processid
				+ " and CIALIASNAME='" + i_serveralias + "' and CIPARAMETER='"
				+ i_parameter + "'";
	}

	/**
	 * �������̺�,����������,���������,����������Դ��,����������,���������
	 * 
	 * @param processid
	 *            ���̺�
	 * @param i_serveralias
	 *            ����������
	 * @param i_parameter
	 *            ���������
	 * @param source
	 *            ������Դ��
	 * @param o_serverlias
	 *            ����������
	 * @param o_parameter
	 *            ���������
	 * @return
	 */
	public String getSQL_UpdateAdapterInfo(String processid,
			String i_serveralias, String i_parameter, String source,
			String o_serverlias, String o_parameter) {
		return "update ADAPTER_STATEMENT set NSOURCEID=" + source
				+ ",COALIASNAME='" + o_serverlias + "', COPARAMETER='"
				+ o_parameter + "' where NPROCESSID=" + processid
				+ " and CIALIASNAME='" + i_serveralias + "' and CIPARAMETER='"
				+ i_parameter + "'";
	}

	/**
	 * �������̺�,������������̷�����л�ȡ��¼��
	 * 
	 * @param processid
	 *            ���̺�
	 * @param serveralias
	 *            �������
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         count(*) ��¼��
	 */
	public String getSQL_QueryCountProcessServerInfo(String processid,
			String serveralias) {
		return "select count(*) from process_servers where nprocessid="
				+ processid + " and CALIASNAME='" + serveralias + "'";
	}

	/**
	 * ���ݷ����,�������������ʹӷ���������л�ȡ��¼��
	 * 
	 * @param serverid
	 *            �����
	 * @param parameter
	 *            ���������
	 * @param type
	 *            ��������(I/O)
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         count(*) ��¼��
	 */
	public String getSQL_QueryCountParameterInfo(String serverid,
			String parameter, String type) {
		return "select count(*) from parameter_statement where nserverid="
				+ serverid + " and cparameter='" + parameter + "' and ctype='"
				+ type + "'";
	}

	/**
	 * �������̺�,���������ȡ����˳���
	 * 
	 * @param processid
	 *            ���̺�
	 * @param aliasname
	 *            �������
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         nsid ˳���
	 */
	public String getSQL_QuerySID(String processid, String aliasname) {
		return "select nsid from process_servers where nprocessid=" + processid
				+ " and CALIASNAME='" + aliasname + "'";
	}

	public String getSQL_QueryAdepterInfo(String processid, String serviceName) {
		return "select nprocessid,cialiasname,ciparameter,nsourceid,COALIASNAME,COPARAMETER"
				+ " from adapter_statement  where nprocessid="
				+ processid
				+ " and cialiasname='"
				+ serviceName
				+ "'  order by nprocessid,cialiasname";
	}

	// ����:������
	public String getSQL_QueryProcessStatementInfo(String processid,
			String nnamespaceid) {
		String sql = "";
		if (nnamespaceid.equals("null")) {
			sql = "select NPROCESSID,CPROCESSNAME,CDESCRIPTION "
					+ "from PROCESS_STATEMENT " + "where NPROCESSID='"
					+ processid + "' ";

		} else {
			sql = "select a.NPROCESSID,a.CPROCESSNAME,a.CDESCRIPTION,b.CNAMESPACE "
					+ "from PROCESS_STATEMENT a ,namespace_statement b "
					+ "where a.NPROCESSID='"
					+ processid
					+ "' and b.nnamespaceid='"
					+ new Integer(nnamespaceid)
					+ "'";
		}
		return sql;
	}

	// ������
	public String getSQL_QueryAllProcessStatementIds(String processInfo,
			String method) {
		String sql = "";
		if (processInfo != null && (!processInfo.equals(""))) {
			if (method.equals("ById"))
				sql = "select NPROCESSID,CPROCESSNAME,CDESCRIPTION,NNAMESPACEID from PROCESS_STATEMENT"
						+ " where NPROCESSID='"
						+ processInfo
						+ "' order by nprocessid";
			if (method.equals("ByName"))
				sql = "select NPROCESSID,CPROCESSNAME,CDESCRIPTION,NNAMESPACEID from PROCESS_STATEMENT"
						+ " where cprocessname = '"
						+ processInfo
						+ "'order by nprocessid";
		} else
			sql = "select NPROCESSID,CPROCESSNAME,CDESCRIPTION,NNAMESPACEID from PROCESS_STATEMENT "
					+ "order by nprocessid";
		return sql;
	}

	// ����
	public String getSQL_QueryProcessServerInfoForProcessidAndSid(
			String processid, String sid) {
		return "select NPROCESSID,NSID,NSERVERID,CALIASNAME,NACTID from "
				+ "PROCESS_SERVERS where NPROCESSID=" + processid
				+ " and nsid=" + sid;
	}

	// ����
	public String getSQL_QueryProcessServerInfoForProcessid(String processid) {
		return "select NPROCESSID,NSID,NSERVERID,CALIASNAME,NACTID from "
				+ "PROCESS_SERVERS where NPROCESSID=" + processid;
	}

	// ������
	public String getSQL_QueryAllProcessServerInfo(String processInfo,
			String method) {
		String sql = "";
		if (processInfo != null && (!processInfo.equals(""))) {
			if (method.equals("ById")) {
				sql = "select a.nprocessid,a.nsid,b.cprocessname,c.cservername from process_servers a,process_statement b,server_statement c where a.nprocessid=b.nprocessid and a.nserverid=c.nserverid "
						+ "and b.nprocessid='"
						+ processInfo
						+ "' order by a.nprocessid,a.nsid";
			}
			if (method.equals("ByName")) {
				sql = "select a.nprocessid,a.nsid,b.cprocessname,c.cservername from process_servers a,process_statement b,"
						+ "server_statement c where  b.cprocessname='"
						+ processInfo
						+ "' and a.nprocessid=b.nprocessid and a.nserverid=c.nserverid "
						+ "order by a.nprocessid,a.nsid";
			}
		} else
			sql = "select a.nprocessid,a.nsid,b.cprocessname,c.cservername from process_servers a,process_statement b,server_statement c where a.nprocessid=b.nprocessid and a.nserverid=c.nserverid"
					+ " order by a.nprocessid,a.nsid";
		return sql;
	}

	// �ڵ�

	public String getSQL_QueryAllNameSpaces(String namespaceinfo, String method) {
		String sql = "";
		if (namespaceinfo != null && (!namespaceinfo.equals(""))) {
			// �����ܺŲ�ѯ
			if (method.equals("ById"))
				sql = "select nnamespaceid, cnamespace,cdescription from namespace_statement  where  nnamespaceid ='"
						+ namespaceinfo + "' order by nnamespaceid";
			// ����������ѯ
			if (method.equals("ByName"))
				sql = "select nnamespaceid, cnamespace,cdescription from namespace_statement where cnamespace like '%"
						+ namespaceinfo + "%' order by nnamespaceid";
		} else
			sql = "select nnamespaceid, cnamespace,cdescription from namespace_statement order by nnamespaceid";
		return sql;
	}

	// ������
	public String getSQL_QueryOtherNameSpaces(String cnnamespace) {
		return "select nnamespaceid, cnamespace,cdescription from namespace_statement where cnamespace<>'"
				+ cnnamespace + "' order by nnamespaceid";
	}

	// �ڵ�
	public String getSQL_InsertNameSpace(int id, String namespace,
			String description) {
		return "insert into namespace_statement values(" + id + ",'"
				+ namespace + "','" + description + "')";
	}

	// �ڵ�
	public String getSQL_DeleteNameSpace(int id) {
		return "delete from namespace_statement where nnamespaceid =" + id + "";
	}

	// �ڵ�
	public String getSQL_UpdateNameSpace(int id, String namespace,
			String descripition) {
		return "update namespace_statement set cnamespace ='" + namespace
				+ "', cdescription='" + descripition + "' where nnamespaceid="
				+ id + "";
	}

	// �ڵ�
	public String getSQL_QueryCountNameSpaceForNameSpace(int id,
			String namespace) {
		return "select count(*) from namespace_statement where cnamespace='"
				+ namespace + "' and nnamespaceid !=" + id + "";
	}

	// �ڵ�
	public String getSQL_QueryNameSpaceForNameSpace(int id) {
		return "select cnamespace,cdescription from namespace_statement where nnamespaceid='"
				+ id + "'";
	}

	// ������
	public String getSQL_QueryActStatement() {
		return "select nactid,cdescription from act_statement order by nactid";
	}
	//л����
	public String getSQl_QueryProcessandserverbynprocessid(int id){
		String sql="select  ps.nprocessid,ps.nsid from  PROCESS_SERVERS ps,process_statement p  " +
				" where p.nprocessid=ps.nprocessid and p.nprocessid="+id;
		return sql;
	}
	//���
	public String getSQL_AllServices(){
	    String sql = "select NSERVERID,CSERVERNAME,CCLASSNAME,"
			+ "CDESCRIPTION,NNAMESPACEID from "
			+ "SERVER_STATEMENT ";
	    return sql;	
	}
	//���
	public String getSQL_AllProcessServerInfo(){
		String sql = "select a.nprocessid,a.nsid,b.cprocessname,c.cservername from process_servers a,process_statement b,server_statement c where a.nprocessid=b.nprocessid and a.nserverid=c.nserverid"
					+ " ";
		return sql;
	}
	//���
	public String getSQL_AllServiceParas(){
		String sql = "select a.nserverid,b.cSERVERNAME,a.CPARAMETER,a.CTYPE from "
			+ "PARAMETER_STATEMENT a inner join server_statement b on a.nserverid= b.nserverid";
    return sql;
	}
    //���
	 public String getSQL_AllAdapterInfos(){
		String sql="select a.nprocessid,a.cialiasname,a.ciparameter,a.nsourceid,a.COALIASNAME,"
          +" a.COPARAMETER  ,p.cprocessname ,s.cservername"
          +" from adapter_statement a"
          +" inner join PROCESS_STATEMENT p on p.nprocessid=a.nprocessid"
          +" inner join process_servers b on a.nprocessid=b.nprocessid and a.cialiasname = b.caliasname"
          +" inner join server_statement s on s.nserverid=b.nserverid" 
          +"";
		return sql;
	 }
}
