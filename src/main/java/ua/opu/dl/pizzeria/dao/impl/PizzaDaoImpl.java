package ua.opu.dl.pizzeria.dao.impl;

import ua.opu.dl.pizzeria.dao.PizzaDao;
import ua.opu.dl.pizzeria.model.Pizza;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class PizzaDaoImpl implements PizzaDao {

	private JdbcTemplate jdbcTemplate;
	private final String loadPizzaByOrderId = "with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='NAME' ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo'  ), tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='orderId'  ),tab44 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='description'  ), tab5 as ( select tab1.id_ id,tab1.name_ name,tab4.orderId orderId,tab2.price price,tab3.logo logo,tab44.description description from tab1,tab2,tab3,tab4,tab44 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab44.Id_  and tab1.obtypeId=5 ) select* from tab5 where orderId=?";
	private final String loadPizzaById = "with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='NAME' ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo'  ), tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='orderId'  ),  tab44 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='description'  ) select tab1.id_ id,tab1.name_ name,tab4.orderId orderId,tab2.price price,tab3.logo logo,tab44.description description from tab1,tab2,tab3,tab4,tab44  where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab44.Id_  and tab1.obtypeId=5 and tab1.id_=?";
	private final String addPizza = "{call pizza.addPizza(?,?,?,?,?,?,?)}";
	private final String addCookFromPizza="INSERT INTO OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) VALUES (31,?,?);";
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	private RowMapper<Pizza> rowMapper = new RowMapper<Pizza>() {
		@Override
		public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {

			Pizza pizza = new Pizza();
			pizza.setName(rs.getString("name"));
			pizza.setLogo(rs.getString("logo"));
			pizza.setDescription(rs.getString("description"));
			pizza.setId(rs.getLong("id"));
			pizza.setPrice(rs.getDouble("price"));
			return pizza;
		}
	};

	@Override
	public long addPizza(final Pizza pizza) {

		String resultValue = jdbcTemplate.execute(
				new CallableStatementCreator() {
					@Override
					public CallableStatement createCallableStatement(
							Connection connection) throws SQLException {
						CallableStatement sql = connection
								.prepareCall(addPizza);
						sql.setString(1, pizza.getName());
						sql.setString(2, String.valueOf(pizza.getOrderId()));
						sql.setString(3, String.valueOf(pizza.getPrice()));
						sql.setString(4, pizza.getLogo());
						sql.setString(5, pizza.getDescription());
						sql.setLong(6, pizza.getCook().getId());
						sql.registerOutParameter(7, Types.BIGINT);
						return sql;
					}
				}, new CallableStatementCallback<String>() {
					@Override
					public String doInCallableStatement(
							CallableStatement callablestatement)
							throws SQLException, DataAccessException {
						callablestatement.executeUpdate();

						return String.valueOf(callablestatement.getObject(7));
					}
				});
		return Long.parseLong(resultValue);
	}

	@Override
	public void updatePizza(Pizza pizza) {

	}

	@Override
	public void deletePizza(Pizza pizza) {

	}

	@Override
	public Pizza loadById(long id) {

		return jdbcTemplate.queryForObject(loadPizzaById, rowMapper, id);
	}

	@Override
	public List<Pizza> loadByOrder(long orderId) {

		return jdbcTemplate.query(loadPizzaByOrderId, rowMapper,
				String.valueOf(orderId));
	}

	@Override
	public List<Pizza> loadAll() {

		return null;
	}

	@Override
	public void setCook(long id,long cookId) {
		jdbcTemplate.update(addCookFromPizza,id,cookId);
		
	}
}
