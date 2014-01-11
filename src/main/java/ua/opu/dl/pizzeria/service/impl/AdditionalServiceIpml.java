package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import ua.opu.dl.pizzeria.dao.impl.AdditionalDaoImpl;
import ua.opu.dl.pizzeria.model.Additional;
import ua.opu.dl.pizzeria.service.AdditionalService;

public class AdditionalServiceIpml implements AdditionalService {
	@Autowired
	private AdditionalDaoImpl addDao;

	@Override
	public void addAdditional(Additional additional) {
		addDao.addAdditional(additional);

	}

	@Override
	public void updateAdditional(Additional additional) {
		addDao.updateAdditional(additional);

	}

	@Override
	public void deleteAdditional(Additional additional) {
		addDao.deleteAdditional(additional);

	}

	@Override
	public Additional loadById(Integer id) {

		return addDao.loadById(id);
	}

	@Override
	public List<Additional> loadByOrder(Integer orderId) {

		return addDao.loadByOrder(orderId);
	}

}
