package controllersTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import ua.opu.dl.pizzeria.controllers.BaseController;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.PizzaService;
import config.AbstractControllerTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class BaseControllerTest extends AbstractControllerTest {
	private PizzaService pizzaService;
	private final List<Pizza> pizzas = new ArrayList<Pizza>();

	@Before
	public void initPizzas() {
		Pizza pizza = new Pizza();
		pizza.setCook("22");
		pizza.setDescription("best pizza");
		pizza.setId(1);
		pizza.setLogo("logo");
		pizza.setName("pizza1");
		pizza.setOrderId(0);
		pizza.setPrice(24);
        pizzas.add(pizza);
	}

	@Test
	public void testMenu() {

		pizzaService = mock(PizzaService.class);
		when(pizzaService.loadByOrder(0)).thenReturn(pizzas);
		BaseController baseController = new BaseController();
		ReflectionTestUtils.setField(baseController, "pizzaService",
		pizzaService);
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = baseController.menu(uiModel);
		assertNotNull(result);
		assertEquals(result, "menu");
		List<Pizza> modelPizzas = (List<Pizza>) uiModel.get("menu");
		assertEquals(1, modelPizzas.size());

	}

}