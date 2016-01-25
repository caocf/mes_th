package mes.framework.dao;

public interface IDAO_Core {
	/**
	 * ɾ��������
	 * 
	 * @param processid
	 *            ���̺�
	 * @param aliasname
	 *            �������
	 * @return �������̺źͷ������ɾ����������
	 */
	String getSQL_DeleteAdapter(String processid, String aliasname);

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
			String i_serveralias, String i_parameter);

	/**
	 * ɾ�����̷���
	 * 
	 * @param processid
	 *            ���̺�
	 * @param sid
	 *            ����˳���
	 * @return �����̺ź�����˳���ɾ�����̷���
	 */
	String getSQL_DeleteProcess(String processid, String sid);

	/**
	 * ɾ�����̶���
	 * 
	 * @param processid
	 *            ���̺�
	 * @return �������̺�ɾ�����̶�����е�����
	 */
	String getSQL_DeleteProcessStatement(String processid);

	/**
	 * ɾ����������Ϣ
	 * 
	 * @param serviceid
	 *            �����
	 * @return ɾ��һ����������Ϣ
	 */
	String getSQL_DeleteService(int serviceid);

	/**
	 * ɾ���������
	 * 
	 * @param serviceid
	 *            �����
	 * @param paratype
	 *            ��������
	 * @param paraname
	 *            ������
	 * @return ɾ��һ���������
	 */
	String getSQL_DeleteServicePara(int serviceid, String paratype,
			String paraname);

	/**
	 * �����������Ϣ
	 * 
	 * @param 1��
	 *            processid ���̺�
	 * @param 2��
	 *            i_serveralias ����������
	 * @param 3��
	 *            i_parameter ���������
	 * @param 4��
	 *            source ������Դ��
	 * @param 5��
	 *            o_serverlias ����������
	 * @param 6��
	 *            o_parameter ���������
	 * @return
	 */
	public String getSQL_InsertAdapterInfo(String processid,
			String i_serveralias, String i_parameter, String source,
			String o_serverlias, String o_parameter);

	/**
	 * �������̷���
	 * 
	 * @param 1��
	 *            processid ���̺�
	 * @param 2��
	 *            sid ����˳���
	 * @param 3��
	 *            serverid �����
	 * @param 4��
	 *            aliasname �������
	 * @param 5��
	 *            actid �쳣����
	 * @return �������̷���
	 */
	String getSQL_InsertProcess(String processid, String sid, String serverid,
			String aliasname, String actid);

	/**
	 * �������̶����
	 * 
	 * @param 1��processid
	 *            ���̺�
	 * @param 2��processname
	 *            ������
	 * @param 3��description
	 *            ҵ������
	 * @param 4��namespace
	 *            �����ռ�
	 * @return
	 */
	String getSQL_InsertProcessStatement(String processid, String processname,
			String description, String namespace);

	/**
	 * ��ӷ�������Ϣ
	 * 
	 * @param 1��
	 *            serviceid �����
	 * @param 2��
	 *            servicename ������
	 * @param 3��
	 *            classname ����
	 * @param 4��
	 *            servicedesc ��������
	 * @param 5��
	 *            namespace �����ռ�
	 * @return ����һ��������Ϣ
	 */

	String getSQL_InsertService(int serviceid, String servicename,
			String classname, String servicedesc, String namespace);

	/**
	 * ��ӷ������
	 * 
	 * @param 1��
	 *            serviceid �����
	 * @param 2��
	 *            paraname ������
	 * @param 3��
	 *            paratype ��������
	 * @param 4��
	 *            paradesc ��������
	 * @return
	 */
	String getSQL_InsertServicePara(int serviceid, String paraname,
			String paratype, String paradesc);

	/**
	 * �������̺ţ����������������������ȡ��������Ϣ
	 * 
	 * @param processid
	 *            ���̺�
	 * @param aliasname
	 *            ����������
	 * @param parametername
	 *            ���������
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         1�� NSOURCEID ������Դ�� <br>
	 *         2�� COALIASNAME ����������<br>
	 *         3�� COALIASNAME �������
	 */
	public String getSQL_QueryAdapterInfo(String processid, String aliasname,
			String parametername);

	/**
	 * �������̺ţ���ȡ�����̵�������������Ϣ
	 * 
	 * @param processid
	 *            ���̺�
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         1�� nprocessid ���̺� <br>
	 *         2�� CIALIASNAME ����������<br>
	 *         3�� CIPARAMETER �������<br>
	 *         4�� NSOURCEID ������Դ�� <br>
	 *         5�� COALIASNAME ����������<br>
	 *         6�� COALIASNAME �������
	 */
	public String getSQL_QueryAdepterInfo(String processid);

	/**
	 * ��ѯ��Ϣ������
	 * 
	 * @param processid
	 *            ����id
	 * @param serviceName
	 *            �������
	 * @return ͨ��ָ��������id�ͷ��������ѯ �����صĽ�����ϰ��������ֶ�<br>
	 *         1��nprocessid ���̺�<br>
	 *         2��cialiasname ����������<br>
	 *         3��ciparameter �������<br>
	 *         4��nsourceid ������Դ��<br>
	 *         5��COALIASNAME ��������<br>
	 *         6��COPARAMETER ���������<br>
	 */
	String getSQL_QueryAdepterInfo(String processid, String serviceName);

	/**
	 * ����������Ϣ���в�ѯ������������Ϣ
	 * 
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         1�� nprocessid ���̺�<br>
	 *         2�� cialiasname ����������<br>
	 *         3�� ciparameter �������<br>
	 *         4�� nsourceid ������Դ��<br>
	 *         5�� COALIASNAME ��������<br>
	 *         6�� COPARAMETER ���������<br>
	 */
	public String getSQL_QueryAllAdapterInfos();

	/**
	 * ����������Ϣ���в�ѯ������������Ϣ
	 * 
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         1��nprocessid ���̺�<br>
	 *         2��cialiasname ����������<br>
	 *         3��ciparameter �������<br>
	 *         4��nsourceid ������Դ��<br>
	 *         5��COALIASNAME ��������<br>
	 *         6��COPARAMETER ���������<br>
	 */
	public String getSQL_QueryAllAdapterInfos(String nprocessname);

	/**
	 * ��ѯ��������
	 * 
	 * @return ���������һ���ֶ�<br>
	 *         1��NPROCESSID ���̺� <br>
	 *         2��CPROCESSNAME ������<br>
	 *         3��CDESCRIPTION ҵ������ <br>
	 *         4��CNAMESPACE �����ռ�
	 */
	String getSQL_QueryAllProcess();

	/**
	 * ���ز�ѯ����ҵ��������Ϣ��SQL���
	 * 
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         1��nprocessid ���̺�<br>
	 *         2��cprocessname ������<br>
	 *         3��CDESCRIPTION ����<br>
	 *         4��CNAMESPACE �����ռ�
	 */
	public String getSQL_QueryAllProcessInfos();

	/**
	 * ��ѯ��������б�
	 * 
	 * @return ��������������ֶ�<br>
	 *         1��nserverid �����<br>
	 *         2��cSERVERNAME ������<br>
	 *         3��CPARAMETER ������<br>
	 *         4��CTYPE ��������<br>
	 */
	String getSQL_QueryAllServiceParas(String info, String method);

	/**
	 * ��ѯ�����б�
	 * 
	 * @return ��������������ֶ� <br>
	 *         1��NSERVERID ����� <br>
	 *         2��CSERVERNAME ������ <br>
	 *         3��CCLASSNAME ����<br>
	 *         4��CDESCRIPTION �������� <br>
	 *         5��CNAMESPACE �����ռ�
	 */
	String getSQL_QueryAllServices(String serviceinfo, String method);

	/**
	 * ���ز�ѯ����������Դ��Ϣ��SQL���
	 * 
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         1��nsourceid ������Դ��<br>
	 *         2��cdescription ������Դ����
	 */
	public String getSQL_QueryAllSourceInfos();

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
			String serveralias, String parameter);

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
			String parameter, String type);

	/**
	 * ������̷�����Ƿ����ظ�����
	 * 
	 * @param processid
	 *            ���̺�
	 * @param sid
	 *            ����˳���
	 * @return �������̺ţ�����˳��ż����¼����<br>
	 *         count(*)
	 */
	String getSQL_QueryCountProcessServerForProcessidAndSid(String processid,
			String sid);

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
			String serveralias);

	/**
	 * ������̶�����Ƿ����ظ�����
	 * 
	 * @param processid
	 *            ���̺�
	 * @return �������̺ż�����̶�����м�¼����
	 */
	String getSQL_QueryCountProcessStatementForProcessid(String processid);

	/**
	 * ��֤�����Ƿ�ĳһ����ʹ��
	 * 
	 * @param serviceid
	 *            �����
	 * @return �÷���Ӧ�������̵ĸ���
	 */
	String getSQL_QueryCountServiceIsUsed(int serviceid);

	/**
	 * ��֤ͬһ����ͬ���͵Ĳ����Ƿ�Ψһ
	 * 
	 * @param serviceid
	 *            �����
	 * @param paraname
	 *            ������
	 * @param paratype
	 *            ��������
	 * @return ͬһ����ͬ����ͬ���Ĳ����ĸ���
	 */
	String getSQL_QueryCountServiceParaIsUnique(int serviceid, String paraname,
			String paratype);

	/**
	 * �������̶�������������̺�
	 * 
	 * @return �������̶�������������̺�
	 */
	String getSQL_QueryNextProcessid();

	/**
	 * ��������ţ�ֵΪ��ǰ��������ֵ��1
	 * 
	 * @return �����
	 */
	String getSQL_QueryNextServiceId();

	/**
	 * ��ѯĳһ��������Ϣ
	 * 
	 * @param processid
	 *            ����id
	 * @return ���������һ���ֶ�<br>
	 *         1��NPROCESSID ���̺� <br>
	 *         2��CPROCESSNAME ������<br>
	 *         3��CDESCRIPTION ҵ������ <br>
	 *         4��CNAMESPACE �����ռ�
	 */
	String getSQL_QueryProcess(String processid);

	/**
	 * ��ѯĳ�����̵Ķ�Ӧ�ķ�����Ϣ
	 * 
	 * @param id
	 *            ����id
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         1��NPROCESSID ���̺�<br>
	 *         2��NSID ����˳���<br>
	 *         3��NSERVERID �����<br>
	 *         4��CALIASNAME �������<br>
	 *         5��cdescription �쳣�����������Ϣ
	 */
	String getSQL_QueryProcessItem(String id);

	/**
	 * �������̺Ż�ȡ������
	 * 
	 * @param processid
	 *            ���̺�
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         cprocessname ������
	 * @deprecated �������������ϱ�����getSQL_QueryProcess(String)���صļ��ϰ�����������ʹ��
	 */
	public String getSQL_QueryProcessNameForProcessID(String processid);

	/**
	 * ���ݷ���Ż�ȡ������
	 * 
	 * @param serverid
	 *            �����
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         cservername ��������
	 * @deprecated �������������ϱ�����getSQL_QueryServiceForServiceid���صļ��ϰ�����������ʹ��
	 */
	public String getSQL_QueryServerNameForServerID(String serverid);

	/**
	 * �鿴����������Ϣ
	 * 
	 * @param serviceid
	 *            �����
	 * @return ��������������ֶ� <br>
	 *         1��NSERVERID ����� <br>
	 *         2��CSERVERNAME ������ <br>
	 *         3��CCLASSNAME ����<br>
	 *         4��CDESCRIPTION �������� <br>
	 *         5��CNAMESPACE �����ռ�
	 */
	String getSQL_QueryServiceForServiceid(int serviceid);

	/**
	 * �鿴�������������Ϣ
	 * 
	 * @param serviceid
	 *            �����<br>
	 * @param paratype
	 *            ��������<br>
	 * @param paraname
	 *            ������<br>
	 * @return CPARAMETER ������<br>
	 *         CTYPE ��������<br>
	 *         CDESCRIPTION ����
	 */
	String getSQL_QueryServicePara(int serviceid, String paratype,
			String paraname);

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
	public String getSQL_QuerySID(String processid, String aliasname);

	/**
	 * ����������Դ�ţ���ȡ������Դ����
	 * 
	 * @param sourceid
	 *            ������Դ��
	 * @return ���صĽ�����ϰ��������ֶ�<br>
	 *         cdescription ������Դ����
	 */
	public String getSQL_QuerySourceDescriptionForSourceID(String sourceid);

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
			String o_serverlias, String o_parameter);

	/**
	 * �������̷���
	 * 
	 * @param processid
	 *            ���̺�
	 * @param sid
	 *            ����˳���
	 * @param serverid
	 *            �����
	 * @param aliasname
	 *            �������
	 * @param actid
	 *            �쳣����
	 * @return �������̺ţ�����˳��Ÿ������̷���
	 */
	String getSQL_UpdateProcess(String processid, String sid, String serverid,
			String aliasname, String actid);

	/**
	 * �������̶����
	 * 
	 * @param processid
	 *            ���̺�
	 * @param processname
	 *            ������
	 * @param description
	 *            ҵ������
	 * @param namespace
	 *            �����ռ�
	 * @return �������̺Ÿ������̶����
	 */
	String getSQL_UpdateProcessStatement(String processid, String processname,
			String description, String namespace);

	/**
	 * ���·�������Ϣ
	 * 
	 * @param serviceid
	 *            �����
	 * @param servicename
	 *            ������
	 * @param classname
	 *            ����
	 * @param servicedesc
	 *            ��������
	 * @param namespace
	 *            �����ռ�
	 * @return ����һ����������Ϣ
	 */
	String getSQL_UpdateService(int serviceid, String servicename,
			String classname, String servicedesc, int namespace);

	/**
	 * ���·������
	 * 
	 * @param serviceid
	 *            �����
	 * @param paratype
	 *            ���º�Ĳ�������
	 * @param setparatype
	 *            ����ǰ�Ĳ�������
	 * @param paraname
	 *            ���º�Ĳ�����
	 * @param setparaname
	 *            ����ǰ�Ĳ�����
	 * @param paradesc
	 *            ���º�Ĳ�������
	 * @return ����һ���������
	 */
	String getSQL_UpdateServicePara(int serviceid, String paratype,
			String setparatype, String paraname, String setparaname,
			String paradesc);

	/**
	 * �������̺Ų�ѯ���̶�������ֶ�
	 * 
	 * @param processid
	 *            ����id <br>
	 * @param nnamespaceid
	 *            �����ռ�id<br>
	 * @return ���ؽ�������������ֶ�<br>
	 *         1��NPROCESSID ���̺�<br>
	 *         2��CPROCESSNAME ������<br>
	 *         3��CDESCRIPTION ��������<br>
	 *         4��CNAMESPACE �����ռ� <br>
	 * @author ������ <br>
	 */
	public String getSQL_QueryProcessStatementInfo(String processidInfo,
			String nnamespaceid);

	/**
	 * ��ѯ���̺ź�������
	 * 
	 * @return ���ؽ�������������ֶ�<br>
	 *         1��NPROCESSID ���̺�<br>
	 *         2��CPROCESSNAME ������<br>
	 *         3��CDESCRIPTION ����<br>
	 *         4��NNAMESPACEID �����ռ��
	 * @author ����
	 */
	public String getSQL_QueryAllProcessStatementIds(String processInfo,
			String method);

	/**
	 * �������̺�����˳��Ų�ѯ���̷�����Ϣ
	 * 
	 * @param Processid
	 *            ���̺�
	 * @param Sid
	 *            ����˳���
	 * @return ���������ֶ�<br>
	 *         1��NPROCESSID ���̺�<br>
	 *         2��NSID ����˳���<br>
	 *         3��NSERVERID �����<br>
	 *         4��CALIASNAME �������<br>
	 *         5��NACTID �쳣����
	 * @author ����
	 */
	public String getSQL_QueryProcessServerInfoForProcessidAndSid(
			String processid, String sid);

	/**
	 * �������̺Ų�ѯ���̷�����Ϣ
	 * 
	 * @param Processid
	 *            ���̺�
	 * @param Sid
	 *            ����˳���
	 * @return ���������ֶ�<br>
	 *         1��NPROCESSID ���̺�<br>
	 *         2��NSID ����˳���<br>
	 *         3��NSERVERID �����<br>
	 *         4��CALIASNAME �������<br>
	 *         5��NACTID �쳣����<br>
	 * @author ����
	 */
	public String getSQL_QueryProcessServerInfoForProcessid(String processid);

	/**
	 * �������̺Ż���������ѯ���̷�����Ϣ
	 * 
	 * @param processid
	 *            ���̺�
	 * @param processname
	 *            ������
	 * @return ���������ֶ�<br>
	 *         1��nprocessid ���̺�<br>
	 *         2��nsid ����˳���<br>
	 *         3��cprocessname ������<br>
	 *         4��cservername ������<br>
	 * @author ������
	 */
	public String getSQL_QueryAllProcessServerInfo(String processInfo,
			String method);

	/**
	 * �������̺źͷ��������ѯ���̷�����Ϣ
	 * 
	 * @param Processid
	 *            ���̺�
	 * @param CALIASNAME
	 *            �������
	 * @return ���������ֶ�<br>
	 *         1��NPROCESSID ���̺�<br>
	 *         2��NSID ����˳���<br>
	 *         3��NSERVERID �����<br>
	 *         4��CALIASNAME �������<br>
	 *         5��NACTID �쳣����<br>
	 * @author ����
	 */
	String getSQL_QueryProcessServerForProcessIDServerAlias(String processid,
			String serveralias);

	/**
	 * ��ѯ���е������ռ����Ƽ�������
	 * 
	 * @param namespaceinfo
	 *            �����ռ���Ϣ
	 * @param method
	 *            ��ѯ��ʽ
	 * @return ��������������ֶ� <br>
	 *         1��nnamespaceid �����ռ��<br>
	 *         2��cnamespace �����ռ�<br>
	 *         3��cdescription ����
	 */
	String getSQL_QueryAllNameSpaces(String namespaceinfo, String method);

	/**
	 * ��ѯ����ѡ������������ռ�š���
	 * 
	 * @param cnnamespace
	 * @return ��������������ֶ�<br>
	 *         1�� nnamespaceid �����ռ��<br>
	 *         2�� cnnamespace �����ռ���<br>
	 * @author ������
	 */
	String getSQL_QueryOtherNameSpaces(String cnnamespace);

	/**
	 * ���������ռ�Ų�ѯ�����ռ���Ϣ
	 * 
	 * @param NameSpace
	 *            �����ռ�
	 * @return ��������������ֶ� <br>
	 *         1��nnamespaceid �����ռ��<br>
	 *         2��cnamespace �����ռ�<br>
	 *         3��cdescription ����
	 */
	String getSQL_QueryNameSpaceForNameSpace(int id);

	/**
	 * ����µ������ռ�
	 * 
	 * @param namespace
	 *            �����ռ�
	 * @param description
	 *            ����
	 * @return
	 */
	String getSQL_InsertNameSpace(int id, String namespace, String description);

	/**
	 * ɾ�������ռ�
	 * 
	 * @param id
	 *            �����ռ��
	 * @return
	 */
	String getSQL_DeleteNameSpace(int id);

	/**
	 * ���������ռ�
	 * 
	 * @param id
	 *            ���µ������ռ��Ӧ�������ռ��
	 * @param namespace
	 *            �����ռ�
	 * @param descripition
	 *            ����
	 * @return
	 */
	String getSQL_UpdateNameSpace(int id, String namespace, String descripition);

	/**
	 * ��֤�����ռ�����
	 * 
	 * @param NameSpace
	 *            �����ռ�
	 * @return ��������������ֶ� <br>
	 *         count(*) ��¼��
	 */
	String getSQL_QueryCountNameSpaceForNameSpace(int id, String namespace);

	/**
	 * ���������ռ��
	 * 
	 * @param id
	 *            �����ռ��
	 * @return
	 */
	String getSQL_QueryNextNameSpaceId();

	/**
	 * ��ѯ���̶�����δʹ�õ������ռ� <br>
	 * 
	 * @param nnamespaceid
	 *            ʹ�õ������ռ�id <br>
	 * @return 1��nNameSpaceId �����ռ�id <br>
	 *         2��cNameSpace �����ռ����� <br>
	 * 
	 */
	String getSQL_QueryNameSpaceForProcessStatement(String nnamespaceid);

	/**
	 * ��ѯ���̶��������е������ռ� <br>
	 * 
	 * @return 1��nNameSpaceId �����ռ�id <br>
	 *         2��cNameSpace �����ռ�����
	 * @author ������
	 */
	String getSQL_QueryNameSpaceForProcessStatement();

	/**
	 * ��ѯ�����쳣��������
	 * 
	 * @return 1��nactid �쳣id <br>
	 *         2��cdescription �쳣����
	 * @author ������
	 */
	String getSQL_QueryActStatement();
	/**
	 * ��ѯҵ�����̷����
	 * 
	 * @return 1������id <br>
	 *         
	 * @author л����
	 */
	String getSQl_QueryProcessandserverbynprocessid(int id);
	/**
	 * ��ѯserver_statement����ؼ��ֲ�ѯ�ӣ�
	 * 
	 * @return sql <br>
	 *         
	 * @author ���
	 */
	 String getSQL_AllServices();
	 /**
	 * ��ѯserver_statement��process_servers�ȱ�
	 * @return sql <br>
	 *         
	 * @author ���
	 */
	 String getSQL_AllProcessServerInfo();
	 /**
	 * ��ѯ������
	 * 
	 * @return sql <br>
	 *         
	 * @author ���
	 */
	 String getSQL_AllServiceParas();
	 /**
	  * ��ѯ�����
	  * 
	  * @return sql <br>
	  *         
	  * @author ���
	  */
	 String getSQL_AllAdapterInfos();
	 
}
     
