package ua.opu.dl.pizzeria.dao.impl;

import ua.opu.dl.pizzeria.dao.CustomerDao;
import ua.opu.dl.pizzeria.model.Customer;

public class CustomerDaoImpl implements CustomerDao {
public final String LOADBYID=" with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='name'  ), tab2 as (  select att.value address, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='address'  ), tab3 as (  select att.value phone, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='phone'  ) select tab1.id_ id,tab1.name_ name,tab2.address address,tab3.phone phone from tab1,tab2,tab3 where  tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab1.obtypeId=6 and tab1.id_=?";
	@Override
	public Customer loadById(long id) {
		
		return null;
	}

	@Override
	public long addCustomer() {
		// TODO Auto-generated method stub
		return 0;
	}

}
