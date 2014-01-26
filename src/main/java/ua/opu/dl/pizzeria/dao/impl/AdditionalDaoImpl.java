package ua.opu.dl.pizzeria.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ua.opu.dl.pizzeria.dao.AdditionalDao;
import ua.opu.dl.pizzeria.model.Additional;

public class AdditionalDaoImpl implements AdditionalDao {
	public final String loadAddbyId = "with tab1 as(  select att.value name_,ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='NAME'  ), tab2 as (  select att.value price, ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='price' ), tab3 as (  select att.value logo, ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='logo' ),tab4 as (  select att.value orderId, ob.OBJECT_ID id_ from Attributes att, Objects ob, ATTRTYPE attr where  ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='orderId' ) select tab1.id_ id,tab4.orderId orderId,tab1.name_ name,tab2.price price,tab3.logo logo from tab1,tab2,tab3,tab4 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab1.id_=?";
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
			additional.setId(rs.getInt("id"));
			additional.setOrderId(rs.getInt("orderId"));
			additional.setName(rs.getString("name"));
			additional.setPrice(rs.getDouble("price"));
			additional.setLogo(rs.getString("logo"));

			return additional;
		}
	};

	@Override
	public void addAdditional(Additional additional) {
		// TODO Auto-generated method stub

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
	public Additional loadById(Integer id) {

		return jdbcTemplate.queryForObject(loadAddbyId, rowMapper, id);
	}

	@Override
	public List<Additional> loadAdditionalsByOrder(Integer orderId) {

		return null;
	}

	@Override
	public Additional loadByName(String name) {

		return new Additional("pepsi", 10, "pepsi.png");
	}

	@Override
	public List<Additional> AllAdditionals() {
		List<Additional> list = new ArrayList();
		Additional pepsi = new Additional("pepsi", 10, "pepsi.png");
		Additional cola = new Additional("coca", 10, "cola.png");
		Additional gorchica = new Additional("gorchica", 10, "gorchica.png");

		list.add(pepsi);
		list.add(cola);
		list.add(gorchica);

		return list;
	}

}
