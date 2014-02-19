package controllersTest;
import config.AbstractControllerTest;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import ua.opu.dl.pizzeria.controllers.AdminController;
import ua.opu.dl.pizzeria.model.Customer;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Status;
import ua.opu.dl.pizzeria.model.UserRole;
import ua.opu.dl.pizzeria.model.Users;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.UserService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AdminControllerTest extends AbstractControllerTest {
	private OrderService orderService;
	private UserService userService;
	private Order order;
	private List<Order> orders = new ArrayList<Order>();
	private Users user;
	private List<Users> users = new ArrayList<Users>();
	private Customer customer;
	@Before
	public void initOrder() {
		order = new Order();
		customer = new Customer();
		customer.setAddress("odessa");
		customer.setId((long) 34);
		customer.setPhone("222444");
		order.setCustomer(customer);
		order.setEndtime("NotFinish");
		order.setStarttime("14.00");
		order.setId(1);
		order.setPrice(40);
		order.setStatus(Status.PRE_ORDER);
		orders.add(order);

	}
	@Before
	public void initUsers(){
		user=new Users();
		user.setFirstName("bob");
		user.setId(2);
		user.setLastName("bob");
		user.setLogin("bobic");
		user.setPassword("555");
		user.setRole(UserRole.ROLE_ADMIN);
		customer = new Customer();
		customer.setAddress("odessa");
		customer.setId((long) 34);
		customer.setPhone("222444");
		user.setCustomer(customer);
		users.add(user);	
	}
	
	
	
	
	
	
	@Test
	public void testOrderSearch() {

		orderService = mock(OrderService.class);
		when(orderService.loadByPhone("222444")).thenReturn(orders);
		AdminController adminController = new AdminController();
		ReflectionTestUtils.setField(adminController, "orderService",
				orderService);
		ExtendedModelMap uiModel = new ExtendedModelMap();
		HttpSession session = mock(HttpSession.class);
		String result = adminController.searchOrder("222444",
				"Search Orders By Phone", session, uiModel);
		assertNotNull(result);
		assertEquals(result, "/admin/updateOrder");
		List<Order> modelOrders = (List<Order>) uiModel.get("orders");
		assertEquals(1, modelOrders.size());

		when(orderService.loadAllByStatus(Status.PRE_ORDER)).thenReturn(orders);
		ReflectionTestUtils.setField(adminController, "orderService",
				orderService);
		result = adminController.searchOrder("PRE_ORDER",
				"Search Order By Status", session, uiModel);
		assertNotNull(result);
		assertEquals(result, "/admin/updateOrder");
		modelOrders = (List<Order>) uiModel.get("orders");
		assertEquals(1, modelOrders.size());

		when(orderService.loadById(1)).thenReturn(order);
		ReflectionTestUtils.setField(adminController, "orderService",
				orderService);
		result = adminController.searchOrder("1", "Search Order By Id",
				session, uiModel);
		assertNotNull(result);
		assertEquals(result, "/admin/updateOrder");
		modelOrders = (List<Order>) uiModel.get("orders");
		assertEquals(1, modelOrders.size());

	}
	@Test 
	public void testSearchUser(){
		
		userService = mock(UserService.class);
		HttpSession session = mock(HttpSession.class);
		when(userService.loadByRole(UserRole.ROLE_ADMIN)).thenReturn(users);
		AdminController adminController = new AdminController();
		ReflectionTestUtils.setField(adminController, "userService",
				userService);
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = adminController.searchUser("ROLE_ADMIN",
				"Search Users By Role", session, uiModel);
		assertNotNull(result);
		assertEquals(result, "/admin/updateUser");
		List<Users> modelUsers = (List<Users>) uiModel.get("users");
		assertEquals(1, modelUsers.size());

		when(userService.loadByLogin("bobic")).thenReturn(user);
		ReflectionTestUtils.setField(adminController, "userService",
				userService);
		result = adminController.searchUser("bobic",
				"Search User By login", session, uiModel);
		assertNotNull(result);
		assertEquals(result, "/admin/updateUser");
		modelUsers = (List<Users>) uiModel.get("users");
		assertEquals(1, modelUsers.size());

		when(userService.loadById(2)).thenReturn(user);
		ReflectionTestUtils.setField(adminController, "userService",
				userService);
		result = adminController.searchUser("2", "Search User By Id",
				session, uiModel);
		assertNotNull(result);
		assertEquals(result, "/admin/updateUser");
		modelUsers = (List<Users>) uiModel.get("users");
		assertEquals(1, modelUsers.size());
		
		
		
		
		
		
		
	}
	
	
	
}
