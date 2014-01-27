package ua.opu.dl.pizzeria.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ua.opu.dl.pizzeria.dao.IngredientDao;
import ua.opu.dl.pizzeria.model.Ingredient;

import javax.sql.DataSource;
public class IngredientDaoImpl implements IngredientDao {

	private final String loadIngredientsById = " with tab1 as(  select att.value name_,ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='name' ), tab2 as (  select att.value price, ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ), tab3 as (  select att.value weight, ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='weight' ), tab4 as (  select att.value pizzaId, ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='pizzaId' ) select tab1.id_ id,tab4.pizzaId pizzaId,tab1.name_ name ,tab2.price price,tab3.weight weight from tab1,tab2,tab3,tab4 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab1.id_= ?";
	
	private JdbcTemplate jdbcTemplate;
//private NamedParameterJdbcTemplate namedTemplate;
 
public void setDataSource(DataSource dataSource) {
this.jdbcTemplate = new JdbcTemplate(dataSource);
//this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
}


private RowMapper<Ingredient> rowMapper = new RowMapper<Ingredient>() {
@Override
public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
Ingredient ingredient = new Ingredient();
ingredient.setId(rs.getInt("id"));
ingredient.setPizzaId(rs.getInt("pizzaId"));
ingredient.setName(rs.getString("name"));
ingredient.setPrice(rs.getDouble("price"));
ingredient.setWeight(rs.getInt("weight"));
return ingredient;
}
};

	@Override
	public void addIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteIngredient(Ingredient ingredient) {

	}

	@Override
	public Ingredient loadById(Integer id) {
		Ingredient ingredient=jdbcTemplate.queryForObject(loadIngredientsById, rowMapper,id);
		//ingredient.setId(id);
		 return ingredient;
	}

	@Override
	public List<Ingredient> loadIngredientsByPizza(Integer pizzaId) {
		
		return null;
	}

}
