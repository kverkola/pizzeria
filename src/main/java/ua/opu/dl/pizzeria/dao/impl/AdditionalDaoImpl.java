package ua.opu.dl.pizzeria.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import ua.opu.dl.pizzeria.dao.AdditionalDao;
import ua.opu.dl.pizzeria.model.Additional;

import javax.sql.DataSource;

public class AdditionalDaoImpl implements AdditionalDao {
	public final String loadAddbyId = "with tab1 as(  select att.value name_,ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='NAME'  ), tab2 as (  select att.value price, ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ), tab3 as (  select att.value logo, ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo' ),tab4 as (  select att.value orderId, ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='orderId' ) select tab1.id_ id,tab4.orderId orderId,tab1.name_ name,tab2.price price,tab3.logo logo from tab1,tab2,tab3,tab4 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab1.id_=?";
	private final String loadAllAdditionals = " with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='NAME' ), tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price'  ), tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo' ), tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='orderId' ) select tab1.id_ id,tab4.orderId orderId,tab1.name_ name,tab2.price price,tab3.logo logo from tab1,tab2,tab3,tab4 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab1.obtypeId=tab2.obtypeId and tab2.obtypeId=tab3.obtypeId and tab3.obtypeId=tab4.obtypeId and tab1.obtypeId=2";
	private final String loadAllAdditionalsByOrderId = " with tab1 as(  select att.value name_,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='NAME' ), tab2 as (  select att.value price, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price'  ), tab3 as (  select att.value logo, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo' ), tab4 as (  select att.value orderId, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='orderId' ), tab5 as ( select tab1.id_ id,tab4.orderId orderId,tab1.name_ name,tab2.price price,tab3.logo logo from tab1,tab2,tab3,tab4 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab1.obtypeId=tab2.obtypeId and tab2.obtypeId=tab3.obtypeId and tab3.obtypeId=tab4.obtypeId and tab1.obtypeId=2) select* from tab5 where orderId=?";
	private final String addAdditional = "{call addAdditional(?,?,?,?)}";
	private JdbcTemplate jdbcTemplate;

	// private NamedParameterJdbcTemplate namedTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		// this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private RowMapper<Additional> rowMapper = new RowMapper<Additional>() {
		@Override
		public Additional mapRow(ResultSet rs, int rowNum) throws SQLException {
			Additional additional = new Additional();
			additional.setId(rs.getLong("id"));
			additional.setOrderId(rs.getLong("orderId"));
			additional.setName(rs.getString("name"));
			additional.setPrice(rs.getDouble("price"));
			additional.setLogo(rs.getString("logo"));

			return additional;
		}
	};

	private PreparedStatementSetter getPreparedStatementSetter(
			final Additional additional) {
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setString(1, additional.getName());
				ps.setLong(2, additional.getOrderId());
				ps.setDouble(3, additional.getPrice());
				ps.setString(4, additional.getLogo());
			}
		};
	}

	@Override
	public void addAdditional(Additional additional) {
		jdbcTemplate.update(addAdditional,
				getPreparedStatementSetter(additional));

	}

	@Override
	public void updateAdditional(Additional additional) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAdditional(Additional additional) {
		// TODO Auto-generated method stub

	}

	@Override
	public Additional loadById(long id) {

		return jdbcTemplate.queryForObject(loadAddbyId, rowMapper, id);
	}

	@Override
	public List<Additional> loadAdditionalsByOrder(long orderId) {

		return jdbcTemplate.query(loadAllAdditionalsByOrderId, rowMapper,
				String.valueOf(orderId));
	}

	@Override
	public Additional loadByName(String name) {

		return new Additional("pepsi", 10, "pepsi.png");
	}

	@Override
	public List<Additional> AllAdditionals() {

		return jdbcTemplate.query(loadAllAdditionals, rowMapper);
	}

}
