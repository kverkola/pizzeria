package ua.opu.dl.pizzeria.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.opu.dl.pizzeria.dao.OrderDao;
import ua.opu.dl.pizzeria.model.*;

public class OrderDaoImpl implements OrderDao {

	private final String loadOrderById = "with tab1 as(  select att.value start_time,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='start_time' ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value end_time, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='end_time' ),tab4 as (  select att.value status, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='status'  ),  tab44 as (  select att.value phone, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='phone'  ), tab6 as (  select att.value nameCustomer, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='nameCustomer'  ), tab7 as (  select att.value address, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='address'  ) select tab1.id_ id,tab4.status status,tab1.start_time start_time,tab3.end_time end_time,tab2.price price,tab44.phone phone,tab6.nameCustomer nameCustomer,tab7.address address from tab1,tab2,tab3,tab4,tab44,tab6,tab7  where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab44.Id_ and tab44.Id_=tab6.Id_ and tab6.Id_=tab7.Id_ and tab1.obtypeId=4 and tab1.id_=?";
	private final String loadOrderByPhone = "with tab1 as(  select att.value start_time,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='start_time' ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value end_time, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='end_time' ),tab4 as (  select att.value status, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='status'  ),    tab44 as (  select att.value phone, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='phone'  ), tab6 as (  select att.value nameCustomer, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='nameCustomer'  ),tab7 as (  select att.value address, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='address'  ),tab5 as(select tab1.id_ id,tab4.status status,tab1.start_time start_time,tab3.end_time end_time,tab2.price price,tab44.phone phone,tab6.nameCustomer nameCustomer,tab7.address address from tab1,tab2,tab3,tab4,tab44,tab6,tab7 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab44.Id_ and  tab44.Id_=tab6.Id_ and  tab6.Id_=tab7.Id_ and tab1.obtypeId=4 )select* from tab5 where phone=?";
	private final String addOrder = "{call addOrder(?,?,?,?,?,?,?,?)}";

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

            Customer customer = new Customer();
            customer.setPhone(rs.getString("phone"));
			customer.setName(rs.getString("nameCustomer"));
			customer.setAddress(rs.getString("address"));

            order.setCustomer(customer);

			return order;
		}
	};

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

	}

	@Override
	public void deleteOrder(Order order) {

	}

	@Override
	public Order loadById(long id) {

		return jdbcTemplate.queryForObject(loadOrderById, rowMapper, id);
	}

	@Override
	public List<Order> loadAllOrders() {

		return null;
	}

	@Override
	public List<Order> loadByPhone(String phone) {

		return jdbcTemplate.query(loadOrderByPhone, rowMapper, phone);
	}

    @Override
    public List<Order> loadAllByStatus(Status status) {

        List<Order> orders = new ArrayList();

        List<Product> products = new ArrayList();
        Map<Ingredient, Integer> ingredients = new HashMap();
        ingredients.put(new Ingredient("Cheese", 20, 15), 1);
        ingredients.put(new Ingredient("Vegetables", 30, 5), 1);
        ingredients.put(new Ingredient("Mushrooms", 10, 30), 1);

        Pizza pizza = new Pizza("TestPizza", "", "", 10L, 29, "cook1");
        pizza.setMap(ingredients);
        products.add(pizza);
        products.add(pizza);

        Order order = new Order(products, new Date(), new Date(),
                                150, new Customer());
        orders.add(order);

        return orders;
    }
}
