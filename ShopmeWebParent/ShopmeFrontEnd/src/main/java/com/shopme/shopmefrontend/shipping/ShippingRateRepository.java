package com.shopme.shopmefrontend.shipping;

import com.shopme.shopme.common.entity.Country;
import com.shopme.shopme.common.entity.ShippingRate;
import org.springframework.data.repository.CrudRepository;

public interface ShippingRateRepository extends CrudRepository<ShippingRate, Integer> {
	
	ShippingRate findByCountryAndState(Country country, String state);
}
