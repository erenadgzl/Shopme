package com.shopme.shopmefrontend.product;

import com.shopme.shopme.common.entity.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTests {

	@Autowired ProductRepository repo;
	
	@Test
	public void testFindByAlias() {
		String alias = "canon-eos-m50";
		Product product = repo.findByAlias(alias);
		
		assertThat(product).isNotNull();
	}
}