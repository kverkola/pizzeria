package ua.opu.dl.pizzeria.dao.impl;
import ua.opu.dl.pizzeria.dao.PizzaDao;
import ua.opu.dl.pizzeria.model.Pizza;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


public class PizzaDaoImpl implements PizzaDao {

    private JdbcTemplate jdbcTemplate;
	private final String loadPizzaByOrderId = "with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='NAME' ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo'  ),tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='orderId' ),tab44 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='description'  ),tab5 as( select tab1.id_ id,tab4.orderId orderId,tab1.name_ name,tab2.price price,tab3.logo logo,tab44.description description from tab1,tab2,tab3,tab4,tab44 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab44.Id_ and tab1.obtypeId=tab2.obtypeId and tab2.obtypeId=tab3.obtypeId and tab3.obtypeId=tab4.obtypeId and tab4.obtypeId=tab44.obtypeId and tab1.obtypeId=5 )select* from tab5 where orderId=?";
	private final String loadPizzaById = "with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='NAME' ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo'  ),tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='orderId' ),tab44 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='description'  ) select tab1.id_ id,tab4.orderId orderId,tab1.name_ name,tab2.price price,tab3.logo logo,tab44.description description from tab1,tab2,tab3,tab4,tab44 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab44.Id_ and tab1.obtypeId=tab2.obtypeId and tab2.obtypeId=tab3.obtypeId and tab3.obtypeId=tab4.obtypeId and tab4.obtypeId=tab44.obtypeId and tab1.obtypeId=5 and tab1.id_=?";
    private final String addPizza="{call addPizza(?,?,?,?,?,?)}";
	// private NamedParameterJdbcTemplate namedTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		// this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private RowMapper<Pizza> rowMapper = new RowMapper<Pizza>() {
		@Override
		public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {

            Pizza pizza = new Pizza(
                    rs.getString("name"),
                    rs.getString("logo"),
                    rs.getString("description"),
                    rs.getLong("id"),
                    rs.getDouble("price")
            );

			return pizza;
		}
	};
//	private PreparedStatementSetter getPreparedStatementSetter(
//			final Pizza pizza) {
//		return new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//
//				ps.setString(1, pizza.getName());
//				ps.setLong(2, pizza.getOrderId());
//				ps.setDouble(3, pizza.getPrice());
//				ps.setString(4, pizza.getLogo());
//				ps.setString(5, pizza.getDescription());
//			
//			}
//		};
//	}

	
	
	
	
	@Override
	public long addPizza(final Pizza pizza)  {
		
	        String resultValue = jdbcTemplate.execute(new CallableStatementCreator() {
	            @Override
	            public CallableStatement createCallableStatement(Connection connection) throws SQLException {
	                CallableStatement sql = connection.prepareCall(addPizza);
	                sql.setString(1, pizza.getName());
	                sql.setString(2, String.valueOf(pizza.getOrderId()));
	                sql.setString(3, String.valueOf(pizza.getPrice()));
	                sql.setString(4, pizza.getLogo());
	                sql.setString(5, pizza.getDescription());
	                sql.registerOutParameter(6, Types.BIGINT);
	                return sql;
	            }
	        }
	        , new CallableStatementCallback<String>() {
	            @Override
	            public String doInCallableStatement(CallableStatement callablestatement) throws SQLException,
	                    DataAccessException {
	                callablestatement.executeUpdate();
	           
	                return String.valueOf(callablestatement.getObject(6));
	            }
	        });
			return Long.parseLong(resultValue);}
					

//	@Override
//	public long addPizza(Pizza pizza) {
//	//	jdbcTemplate.	 jdbcTemplate.update(addPizza,
//		//		getPreparedStatementSetter(pizza));
//		return (Long) null;
//	}

	@Override
	public void updatePizza(Pizza pizza) {

	}

	@Override
	public void deletePizza(Pizza pizza) {

	}

	@Override
	public Pizza loadById(long id) {

        return	jdbcTemplate.queryForObject(loadPizzaById,rowMapper,id);
	}

	@Override
	public List<Pizza> loadByOrder(long orderId) {

		return jdbcTemplate.query(loadPizzaByOrderId, rowMapper,
				String.valueOf(orderId));
	}

	@Override
	public List<Pizza> loadAll() {

//		List<Pizza> pizzaList = new ArrayList();
//
//		Map<Ingredient, Integer> ingredientMap = new HashMap();
//		ingredientMap.put(new Ingredient("Ham", 100, 30), 1);
//		ingredientMap.put(new Ingredient("Vegetables", 80, 10), 1);
//		ingredientMap.put(new Ingredient("Cheese", 90, 20), 1);
//		ingredientMap.put(new Ingredient("Sauce", 60, 15), 1);
//		ingredientMap.put(new Ingredient("Crust", 300, 30), 1);
//
//		String[] imgs = { "chikenita_middle.png", "img_2.png",
//				"pizza_middle.png", "tanu_mini.png" };
//		String[] descrips = {
//				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",
//				"Aenean commodo ligula eget dolor. Aenean massa.",
//				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
//				"Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim." };
//
//		for (int i = 0; i < imgs.length; i++) {
//			pizzaList.add(new Pizza("TestPizza " + i, ingredientMap, imgs[i],
//					descrips[i], i, 45));
//		}
		return null;
	}
}
