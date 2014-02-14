package ua.opu.dl.pizzeria.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import ua.opu.dl.pizzeria.dao.UserDao;
import ua.opu.dl.pizzeria.model.Users;
import ua.opu.dl.pizzeria.model.UserRole;

public class UserDaoImpl implements UserDao {
private final String loadUserById=" with tab1 as(  select att.value first_name,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='first_name'  ), tab2 as (  select att.value last_name, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='last_name'  ), tab3 as (  select att.value login, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='login' ),  tab4 as (  select att.value password, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='password'  ), tab44 as (  select att.value warker, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='warker' ) select tab1.id_ id,tab1.first_name first_name,tab4.password password,tab2.last_name last_name,tab3.login login,tab44.warker warker from tab1,tab2,tab3,tab4,tab44 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab44.Id_ and tab1.obtypeId=1 and tab1.id_=?";
private final String addUser="{call addUser(?,?,?,?,?,?,?,?,?,?)}";
private final String loadUserByLogin="with tab1 as(  select att.value first_name,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='first_name'  ), tab2 as (  select att.value last_name, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='last_name'  ), tab3 as (  select att.value login, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='login' ),  tab4 as (  select att.value password, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='password'  ), tab44 as (  select att.value warker, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='warker' ) select tab1.id_ id,tab1.first_name first_name,tab4.password password,tab2.last_name last_name,tab3.login login,tab44.warker warker from tab1,tab2,tab3,tab4,tab44 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab44.Id_ and tab1.obtypeId=1 and  tab3.login=?";
private final String updateUser="{call updateUser(?,?,?,?,?,?,?,?,?,?)}";
private final String loadUserByRole="with tab1 as(  select att.value first_name,ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where   obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='first_name'  ), tab2 as (  select att.value last_name, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='last_name'  ), tab3 as (  select att.value login, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='login' ),  tab4 as (  select att.value password, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='password'  ), tab44 as (  select att.value warker, ob.OBJECT_ID id_,obj.OBJECT_TYPE_ID obtypeId from Attributes att, Objects ob, ATTRTYPE attr,OBJTYPE obj where  obj.OBJECT_TYPE_ID=ob.OBJECT_TYPE_ID and ob.object_id = att.object_id and att.attr_id=attr.attr_id and attr.code='warker' ) select tab1.id_ id,tab1.first_name first_name,tab4.password password,tab2.last_name last_name,tab3.login login,tab44.warker warker from tab1,tab2,tab3,tab4,tab44 where tab1.ID_=tab2.ID_ and tab2.ID_=tab3.ID_ and tab3.Id_=tab4.Id_ and tab4.Id_=tab44.Id_ and tab1.obtypeId=1 and tab44.warker=?";
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper<Users> rowMapper = new RowMapper<Users>() {
		@Override
		public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
			Users user = new Users();
			user.setFirstName(rs.getString("first_Name"));
			user.setLastName(rs.getString("last_Name"));
			user.setLogin(rs.getString("login"));
			user.setPassword(rs.getString("password"));
			user.setRole(UserRole.valueOf(rs.getString("warker")));
			user.setId(rs.getLong("id"));

			return user;
		}
	};
	private PreparedStatementSetter getPreparedStatementSetter(
			final Users user) {
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getLogin());
				ps.setString(4, user.getPassword());
				ps.setString(5, user.getRole().toString());
				ps.setLong(6, user.getId());
				ps.setString(7, user.getCustomer().getName());
				ps.setString(8, user.getCustomer().getAddress());
				ps.setString(9, user.getCustomer().getPhone());
				ps.setLong(10, user.getCustomer().getId());
				
			}
		};
	}
	@Override
	public void addUser(Users user) {
		jdbcTemplate.update(addUser,
				getPreparedStatementSetter(user));
	}

	@Override
	public void updateUser(Users user) {
		jdbcTemplate.update(updateUser,
				getPreparedStatementSetter(user));	
	}

	@Override
	public void deleteUser(Users user) {

	}

	@Override
	public Users loadById(long id) {

		return jdbcTemplate.queryForObject(loadUserById, rowMapper, id);
	}

	@Override
	public Users loadByLogin(String name) {
		return jdbcTemplate.queryForObject(loadUserByLogin, rowMapper, name);
	}

	@Override
	public List<Users> loadAllusers() {

		return null;
	}
	@Override
	public List<Users> loadByRole(UserRole role) {
		
		return jdbcTemplate.query(loadUserByRole, rowMapper,role.toString());
	}

}
