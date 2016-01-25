package mes.system.factory;

import mes.system.elements.IMaterialType;

class DefMaterialType extends AElement implements IMaterialType {

	private int parentId;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tDefMaterialType����parentid:" + parentId;
	}

}
