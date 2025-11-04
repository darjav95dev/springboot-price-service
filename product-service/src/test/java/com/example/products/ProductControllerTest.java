package com.example.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ProductControllerTest {

	private MockMvc mockMvc;

	@BeforeEach
	void setup(WebApplicationContext context) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * Test para asegurar que la aplicación arranca sin excepciones.
	 */
	@Test
	void testMainMethod() {
		assertDoesNotThrow(() -> ProductsApplication.main(new String[]{}));
	}

	/**
	 * Tests parametrizados para precios esperados en diferentes fechas.
	 */
	@ParameterizedTest
	@CsvSource({
			"2020-06-14T10:00:00, 35.50, 1",
			"2020-06-14T16:00:00, 25.45, 2",
			"2020-06-14T21:00:00, 35.50, 1",
			"2020-06-15T10:00:00, 30.50, 3",
			"2020-06-16T21:00:00, 38.95, 4"
	})
	void testValidPrices(String date, double expectedPrice, int expectedPriceList) throws Exception {
		mockMvc.perform(get("/api/v1/products/35455/brands/1/price")
						.param("date", date))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(35455))
				.andExpect(jsonPath("$.brandId").value(1))
				.andExpect(jsonPath("$.priceList").value(expectedPriceList))
				.andExpect(jsonPath("$.startDate").exists())
				.andExpect(jsonPath("$.endDate").exists())
				.andExpect(jsonPath("$.price").value(expectedPrice))
				.andExpect(jsonPath("$.currency").value("EUR"));
	}

	/**
	 * Casos negativos y errores.
	 */
	@Test
	void testProductNotFound() throws Exception {
		mockMvc.perform(get("/api/v1/products/99999/brands/1/price")
						.param("date", "2020-06-14T10:00:00"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.error").value("Product Not Found"));
	}

	@Test
	void testBrandNotFound() throws Exception {
		mockMvc.perform(get("/api/v1/products/35455/brands/999/price")
						.param("date", "2020-06-14T10:00:00"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.error").value("Product Not Found"));
	}

	@Test
	void testMissingDateParam() throws Exception {
		mockMvc.perform(get("/api/v1/products/35455/brands/1/price"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testInvalidDateFormat() throws Exception {
		mockMvc.perform(get("/api/v1/products/35455/brands/1/price")
						.param("date", "invalid-date"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testNegativeProductId() throws Exception {
		mockMvc.perform(get("/api/v1/products/-1/brands/1/price")
						.param("date", "2020-06-14T10:00:00"))
				.andExpect(status().isNotFound());
	}
}
