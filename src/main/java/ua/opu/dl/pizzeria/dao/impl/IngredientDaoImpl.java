package ua.opu.dl.pizzeria.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import ua.opu.dl.pizzeria.dao.IngredientDao;
import ua.opu.dl.pizzeria.model.Ingredient;

import javax.sql.DataSource;

public class IngredientDaoImpl implements IngredientDao {

	private final String loadIngredientsById = " with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='name'  ),tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value weight, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='weight' ),tab4 as (  select att.value pizzaId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='pizzaId' ),tab6 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo' ),tab7 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='description' )select tab1.id_ id,tab4.pizzaId pizzaId,tab1.name_ name,tab2.price price,tab3.weight weight,tab6.logo logo,tab7.description description from tab1,tab2,tab3,tab4,tab6,tab7 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and  tab3.Id_=tab4.Id_ and tab4.Id_=tab6.Id_ and tab6.Id_=tab7.Id_ and tab1.obtypeId=3 and tab1.id_=?";
	private final String loadIngredientsByPizzaId = " with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='name' ), tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ),tab3 as (  select att.value weight, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='weight' ), tab4 as (  select att.value pizzaId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='pizzaId' ),  tab6 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo'),tab7 as (  select att.value description, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='description' ),tab5 as ( select tab1.id_ id,tab4.pizzaId pizzaId,tab1.name_ name,tab2.price price,tab3.weight weight,tab6.logo logo,tab7.description description from tab1,tab2,tab3,tab4,tab6,tab7 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab6.Id_ and tab6.Id_=tab7.Id_ and tab1.obtypeId=tab2.obtypeId and tab2.obtypeId=tab3.obtypeId and tab3.obtypeId=tab4.obtypeId and tab4.obtypeId=tab6.obtypeId and tab6.obtypeId=tab7.obtypeId and tab1.obtypeId=3 ) select* from tab5 where pizzaId=?";;
	private final String addIngredient = "{call pizza.addIngredients(?,?,?,?,?,?)}";

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	private RowMapper<Ingredient> rowMapper = new RowMapper<Ingredient>() {
		@Override
		public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
			Ingredient ingredient = new Ingredient();
			ingredient.setId(rs.getLong("id"));
			ingredient.setPizzaId(rs.getLong("pizzaId"));
			ingredient.setName(rs.getString("name"));
			ingredient.setPrice(rs.getDouble("price"));
			ingredient.setWeight(rs.getDouble("weight"));
			ingredient.setLogo(rs.getString("logo"));
			ingredient.setDescription(rs.getString("description"));
			return ingredient;
		}
	};

	private PreparedStatementSetter getPreparedStatementSetter(
			final Ingredient ingredient) {
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setLong(1, ingredient.getPizzaId());
				ps.setString(2, ingredient.getName());
				ps.setDouble(3, ingredient.getPrice());
				ps.setDouble(4, ingredient.getWeight());
				ps.setString(5, ingredient.getLogo());
				ps.setString(6, ingredient.getDescription());
			}
		};
	}

	@Override
	public void addIngredient(Ingredient ingredient) {
		jdbcTemplate.update(addIngredient,
				getPreparedStatementSetter(ingredient));
	}

	@Override
	public void updateIngredient(Ingredient ingredient) {

	}

	@Override
	public void deleteIngredient(Ingredient ingredient) {

	}

	@Override
	public Ingredient loadById(long id) {

		return jdbcTemplate.queryForObject(loadIngredientsById, rowMapper, id);
	}

	@Override
	public List<Ingredient> loadIngredientsByPizza(long pizzaId) {

		return jdbcTemplate.query(loadIngredientsByPizzaId, rowMapper,
				String.valueOf(pizzaId));
	}

}
