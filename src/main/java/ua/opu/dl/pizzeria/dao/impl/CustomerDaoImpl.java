package ua.opu.dl.pizzeria.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ua.opu.dl.pizzeria.dao.CustomerDao;
import ua.opu.dl.pizzeria.model.Customer;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Status;

public class CustomerDaoImpl implements CustomerDao {
	public final String LOADBYID = " with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='name'  ), tab2 as (  select att.value address, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='address'  ), tab3 as (  select att.value phone, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='phone'  ) select tab1.id_ id,tab1.name_ name,tab2.address address,tab3.phone phone from tab1,tab2,tab3 where  tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab1.obtypeId=6 and tab1.id_=?";
	public final String ADDCUSTOMER = "{call pizza.addCustomer(?,?,?,?)}";
	public final String LOADBYORDERID = "with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='name'  ), tab2 as (  select att.value address, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='address'  ), tab3 as (  select att.value phone, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='phone'  ) select tab1.id_ id,tab1.name_ name,tab2.address address,tab3.phone phone from tab1,tab2,tab3 where  tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab1.obtypeId=6 and tab1.id_ =(select reference from objreference where object_id=? and attr_id=14)";
	public final String LOADBYPHONE=" with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='name'  ), tab2 as (  select att.value address, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='address'  ), tab3 as (  select att.value phone, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='phone'  )select tab1.id_ id,tab1.name_ name,tab2.address address,tab3.phone phone from tab1,tab2,tab3 where  tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab1.obtypeId=6 and phone=?";
	public final String LOADBYUSERID="with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='name'  ), tab2 as (  select att.value address, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='address'  ), tab3 as (  select att.value phone, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='phone'  ) select tab1.id_ id,tab1.name_ name,tab2.address address,tab3.phone phone from tab1,tab2,tab3 where  tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab1.obtypeId=6 and tab1.id_ =(select reference from objreference where object_id=? and attr_id=36)";
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getLong("id"));
			customer.setName(rs.getString("name"));
			customer.setAddress(rs.getString("address"));
			customer.setPhone(rs.getString("phone"));

			return customer;
		}
	};

	@Override
	public Customer loadById(long id) {

		return jdbcTemplate.queryForObject(LOADBYID, rowMapper, id);
	}

	@Override
	public Customer loadByOrderId(long id) {

		return jdbcTemplate.queryForObject(LOADBYORDERID, rowMapper, id);
	}

	@Override
	public Customer loadByUserId(long id) {

		return jdbcTemplate.queryForObject(LOADBYUSERID, rowMapper, id);
	}

	@Override
	public long addCustomer(final Customer customer) {
		String resultValue = jdbcTemplate.execute(
				new CallableStatementCreator() {
					@Override
					public CallableStatement createCallableStatement(
							Connection connection) throws SQLException {
						CallableStatement sql = connection
								.prepareCall(ADDCUSTOMER);
						sql.setString(1, customer.getName());
						sql.setString(2, customer.getAddress());
						sql.setString(3, customer.getPhone());
						sql.registerOutParameter(4, Types.BIGINT);
						return sql;
					}
				}, new CallableStatementCallback<String>() {
					@Override
					public String doInCallableStatement(
							CallableStatement callablestatement)
							throws SQLException, DataAccessException {
						callablestatement.executeUpdate();
						return String.valueOf(callablestatement.getObject(8));
					}
				});
		return Long.parseLong(resultValue);
	
		
	}

	@Override
	public List<Customer> loadByPhone(String phone) {
		
		 return jdbcTemplate.query(LOADBYPHONE, rowMapper, phone);
	}

}
