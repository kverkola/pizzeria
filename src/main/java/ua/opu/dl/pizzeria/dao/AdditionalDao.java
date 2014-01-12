package ua.opu.dl.pizzeria.dao;

import java.util.List;

import ua.opu.dl.pizzeria.model.Additional;

public interface AdditionalDao {
	void addAdditional(Additional additional);

	void updateAdditional(Additional additional);

	void deleteAdditional(Additional additional);

	Additional loadById(Integer id);

	List<Additional> loadAdditionalsByOrder(Integer orderId);
}
