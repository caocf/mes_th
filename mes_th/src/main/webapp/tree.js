var mes;if(mes==undefined){mes = new Object();}if(mes.taglib==undefined){mes.taglib=new Object();}
if(mes.taglib.tree==undefined){
	mes.taglib.tree = function(treeid){
		this.iml = new mes.taglib.imagelist();
		this.iml.path = "images/";
		this.iml.add("plus_top","collapse_top");
		this.iml.add("plus_end","collapse_end");
		this.iml.add("plus_m","collapse");

		this.iml.add("minus_top","expand_top");
		this.iml.add("minus_m","expand");
		this.iml.add("minus_end","expand_end");
		
		this.iml.add("branch_end","branch_end");
		this.iml.add("branch","branch");
		
		
		this.iml.add("fold2k_open","open");
		this.iml.add("fold2k_close","close");

		//this.iml.add();
		
		
		this.root = document.getElementById(treeid);
		this.target = '_self';
		this.subNum = 0;
		this.count=0;
		this.addNode = function(info,url,openIcon,closeIcon,parentnode){
			var node = this.createNode(info,url,parentnode);
			node.addNode = function(info,url,openIcon,closeIcon){
				return this.root.addNode(info,url,openIcon,closeIcon,this);
			};
			closeIcon = (closeIcon==undefined&&openIcon!=undefined)?openIcon:closeIcon
			
			node.openIcon = (openIcon!=undefined)?openIcon:node.openIcon;
			node.closeIcon = (closeIcon!=undefined)?closeIcon:node.closeIcon;
			//��ӽڵ㵽���У������������ڵ��ͼ�ꡣ
			var preNode = null;
			if(parentnode==undefined){
				this.root.appendChild(node);
				preNode = (this.root.count>=2)?this.root.childNodes[this.root.count-2]:null;
			}else{
				parentnode.menu.appendChild(node);
				preNode = (parentnode.count>=2)?parentnode.menu.childNodes[parentnode.count-2]:null;
			}
			if(preNode!=null)
				preNode.modifyIcon();
			node.modifyIcon();
			return node;
		};
		
		this.createNode = function(info,url,parentnode){
			var node = document.createElement("div");
			node.id = 'mes_taglib_tree_node'+this.subNum++;//Ψһ��id
			node.root = this;//root�Ĺ���
			node.parent = (parentnode==undefined)?node.root:parentnode;//���ڵ�Ĺ���
			node.tier = (parentnode==undefined)?1:parentnode.tier+1;//�ڵ���
			node.index = (parentnode==undefined)?this.count++:parentnode.count++;
			node.isExpand = true;
			node.openIcon = "open";
			node.closeIcon = "close";
			
				//���ֵܽڵ��е�����ֵ
			if(parentnode!=undefined){parentnode.modifyIcon();}
				//������ӽڵ��ʱ���������ڵ���ʹ�õ�ͼ����Ϣ
			node.count = 0;
			node.exIcon = new Image();
			node.exIcon.className = "treeicon";
			node.exIcon.src = this.iml.item["expand_top"].src;
			node.exIcon.parent = node;
			node.exIcon.align="absbottom";
			node.exIcon.onclick = function(){
			//	Ҫ���ͼ�겿�ֵ�ʱ��Ҫ��չ��/�۵��ӽڵ㶯��
				var node = this.parent;
				node.isExpand = !node.isExpand;
				if(node.count>0){
					node.menu.style.display = node.isExpand?'block':'none';
				}
				node.modifyIcon();
			}  
			
			node.appendChild(node.exIcon);
			
			node.icon = new Image();
			node.icon.className = "treeicon";
			node.icon.src = this.iml.item["open"].src;
			node.icon.align="absbottom";
			node.appendChild(node.icon);
			
			node.info = document.createElement("span");//��ʾ�ڵ��������Ϣ
			node.info.innerHTML=info;
			//node.info.innerHTML+="(index:"+node.index+",parent:"+node.parent+",tier:"+node.tier+")";
			node.info.parent = node;
			node.info.onclick = function(){
				//������ڵ����ֲ��ֵ�ʱ��Ҫ��������
				if(url!=undefined){	window.open(url,this.parent.root.target);}
				else{this.parent.exIcon.onclick();}
			}
			
			
			node.info.onmouseover = function(){
				node.info.style.color="#993300";
				
			} 
			node.info.onmouseout = function(){
				node.info.style.color="black";
			} 
			
			
			node.info.className = "treeNode";
			node.modifyIcon = function(){
				//�ڽڵ�ṹ�����仯�������ڵ���ʹ�õ�ͼ�귽����
				//�򿪺͹رյ�ʱ���ͼ���п��ܻᱻ�û�����ָ������������Ҫ����Ч���жϡ�
				var item = this.root.iml.item[this.isExpand?this.openIcon:this.closeIcon];
				this.icon.src = (item!=null)?item.src:this.icon.src;
				/*zzz����ͼƬ���ã�������ʱ������
				if(this.index==0){
					this.exIcon.src = this.root.iml.item[this.isExpand?"expand_top":"collapse_top"].src;				
					return;
				} else */
				if(this.index==this.parent.count-1){
					
						this.exIcon.src = this.root.iml.item[
							this.count==0?"branch_end":
							this.isExpand?"expand_end":"collapse_end"
							].src;				
					return;
				}
				else{
					this.exIcon.src = this.root.iml.item[
						this.count==0?"branch":
						this.isExpand?"expand":"collapse"
						].src;
				}
			}
			node.appendChild(node.info);
			
			node.menu = document.createElement("div");
			node.menu.style.paddingLeft="20px";
			node.menu.className = "treeNodeMenu";
			node.menu.style.paddingTop="1px";
			
			
	
		
			
			node.appendChild(node.menu);
			return node;
		};
		
		return this;
	};
	
	
};
if(mes.taglib.imagelist==undefined){
	mes.taglib.imagelist = function(){
		var item=[],count=0;
		this.add=function(src,key){
			if(src==null || src=="")return;
			if(src.indexOf("/")==-1)src=this.path+src;
			if(!(/\.gif$|\.jpg$|\.png$|\.bmp$/i).test(src))src+="."+this.type;
			if(key==undefined||key==null || key=="")key=src.replace(/(.*\/){0,}([^\.]+).*/ig,"$2");
			
			var img=new Image();img.index=count;
			item[count]=img;item[key]=img;count++;
			img.src=src;
			return img;
		};
		
		this.path="";
		this.type="gif";
		this.item = item;
		this.count=function(){return count;}
	};

};