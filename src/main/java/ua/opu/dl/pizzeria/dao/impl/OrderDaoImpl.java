package ua.opu.dl.pizzeria.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import ua.opu.dl.pizzeria.dao.OrderDao;
import ua.opu.dl.pizzeria.model.*;

public class OrderDaoImpl implements OrderDao {

	private final String loadOrderById = "with tab1 as(  select att.value start_time,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='start_time' ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value end_time, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='end_time' ),tab4 as (  select att.value status, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='status'  ) select tab1.id_ id,tab4.status status,tab1.start_time start_time,tab3.end_time end_time,tab2.price price from tab1,tab2,tab3,tab4  where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab1.obtypeId=4 and tab1.id_=?";
	private final String loadOrderByStatus = "with tab1 as(  select att.value start_time,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='start_time' ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value end_time, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='end_time' ),tab4 as (  select att.value status, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='status'  ) select tab1.id_ id,tab4.status status,tab1.start_time start_time,tab3.end_time end_time,tab2.price price from tab1,tab2,tab3,tab4  where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab1.obtypeId=4 and tab4.status=?";
	private final String loadOrderByCustomer = "with tab1 as(  select att.value start_time,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='start_time'  ), tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price'  ), tab3 as (  select att.value end_time, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='end_time'  ), tab4 as (  select att.value status, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='status'  ),tab5 as(select tab1.id_ id,tab4.status status,tab1.start_time start_time,tab3.end_time end_time,tab2.price price from tab1,tab2,tab3,tab4  where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab1.obtypeId=4 ) select id,status,start_time,end_time,price from tab5,OBJREFERENCE where tab5.id=OBJREFERENCE.OBJECT_ID and ( status='PRE_ORDER' or status='DELIVERY' or status='IN_WORK') and  OBJREFERENCE.ATTR_ID=14 and OBJREFERENCE.reference=?";
	private final String addOrder = "{call pizza.addOrder(?,?,?,?,?,?,?,?)}";
	private final String updateOrder = "{call pizza.updateOrder(?,?,?,?,?,?,?,?,?)}";
	private final String loadOrdersForCook = "with tab1 as(  select att.value start_time,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='start_time' ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value end_time, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='end_time' ),tab4 as (  select att.value status, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='status'  ) select tab1.id_ id,tab4.status status,tab1.start_time start_time,tab3.end_time end_time,tab2.price price from tab1,tab2,tab3,tab4  where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab1.obtypeId=4 and (tab4.status='PRE_ORDER' or tab4.status='IN_WORK')";
	private List<Order> orders;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	private RowMapper<Order> rowMapper = new RowMapper<Order>() {
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setId(rs.getLong("id"));
			order.setStatus(Status.valueOf(rs.getString("status")));
			order.setStarttime(rs.getString("start_time"));
			order.setEndtime(rs.getString("end_time"));
			order.setPrice(rs.getDouble("price"));
			return order;
		}
	};

	private PreparedStatementSetter getPreparedStatementSetter(final Order order) {
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setLong(1, order.getId());
				ps.setString(2, order.getStatus().toString());
				ps.setString(3, order.getStarttime());
				ps.setString(4, order.getEndtime());
				ps.setDouble(5, order.getPrice());
				ps.setString(6, order.getCustomer().getPhone());
				ps.setString(7, order.getCustomer().getName());
				ps.setString(8, order.getCustomer().getAddress());
				ps.setString(9, order.getCustomer().getId().toString());
			}
		};
	}

	@Override
	public long addOrder(final Order order) {
		String resultValue = jdbcTemplate.execute(
				new CallableStatementCreator() {
					@Override
					public CallableStatement createCallableStatement(
							Connection connection) throws SQLException {
						CallableStatement sql = connection
								.prepareCall(addOrder);
						sql.setString(1, String.valueOf(order.getStarttime()));
						sql.setString(2, String.valueOf(order.getEndtime()));
						sql.setString(3, String.valueOf(order.getPrice()));
						sql.setString(4, String.valueOf(order.getStatus()));
						sql.setString(5, order.getCustomer().getPhone());
						sql.setString(6, order.getCustomer().getName());
						sql.setString(7, order.getCustomer().getAddress());
						sql.registerOutParameter(8, Types.BIGINT);
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
	public void updateOrder(Order order) {
		jdbcTemplate.update(updateOrder, getPreparedStatementSetter(order));
	}

	@Override
	public void deleteOrder(Order order) {

	}

	@Override
	public Order loadById(long id) {

		orders = jdbcTemplate.query(loadOrderById, rowMapper, id);
		if (orders.isEmpty()) {
			return null;
		}
		return orders.get(0);
	}

	@Override
	public List<Order> loadAllOrders() {

		return null;
	}

	@Override
	public List<Order> loadAllByStatus(Status status) {

		return jdbcTemplate.query(loadOrderByStatus, rowMapper,
				status.toString());
	}

	@Override
	public Order loadByCustomer(long Id) {

		orders = jdbcTemplate.query(loadOrderByCustomer, rowMapper, Id);
		if (orders.isEmpty()) {
			return null;
		}
		return orders.get(0);
	}

	@Override
	public List<Order> loadOrdersForCook() {
		return jdbcTemplate.query(loadOrdersForCook, rowMapper);
	}
}
