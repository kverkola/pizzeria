package ua.opu.dl.pizzeria.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ua.opu.dl.pizzeria.dao.AdditionalDao;
import ua.opu.dl.pizzeria.model.Additional;

public class AdditionalDaoImpl implements AdditionalDao {

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
		// TODO Auto-generated method stub
		return new Additional("pepsi",30,"pepsi") ;
	}

	@Override
	public List<Additional> loadAdditionalsByOrder(Integer orderId) {
		
		return null;
	}

	@Override
	public Additional loadByName(String name) {
		
		return new  Additional("pepsi",10,"pepsi.png");
	}

	@Override
	public List<Additional> AllAdditionals() {
		List<Additional> list=new ArrayList(); 
		Additional pepsi=new Additional("pepsi",10,"pepsi.png");
		Additional cola=new Additional("coca",10,"cola.png");
		Additional gorchica=new Additional("gorchica",10,"gorchica.png");
		
		list.add(pepsi);
		list.add(cola);
		list.add(gorchica);
		
		return list;
	}

}
