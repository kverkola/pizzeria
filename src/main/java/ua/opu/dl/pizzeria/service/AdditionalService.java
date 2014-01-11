package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.Additional;

public interface AdditionalService {
	void addAdditional(Additional additional);

	void updateAdditional(Additional additional);

	void deleteAdditional(Additional additional);

	Additional loadById(Integer id);

	List<Additional> loadByOrder(Integer orderId);
}
