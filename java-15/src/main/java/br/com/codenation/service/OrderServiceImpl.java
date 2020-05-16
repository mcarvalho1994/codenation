package br.com.codenation.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		AtomicReference<Double> sum = new AtomicReference<>(0.0);
		items.stream()
				.forEach(product -> {
					if(productRepository.findById(product.getProductId()).get().getIsSale() == true) {
						sum.set(sum.get() + (product.getQuantity() *
								(productRepository.findById(product.getProductId()).get().getValue() * 0.8)));
					} else {
						sum.set(sum.get() + (product.getQuantity() *
								productRepository.findById(product.getProductId()).get().getValue()));
					}
						}

				);
		return sum.get();
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream().filter(id -> productRepository.findById(id).isPresent())
				.map(id -> productRepository.findById(id).get()).collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream().filter(ordersList -> !ordersList.isEmpty())
				.mapToDouble(orderList -> calculateOrderValue(orderList)).sum();
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return productIds.stream()
				.filter(id -> id > 0 && id != null)
				.map(id -> productRepository.findById(id).get())
				.collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Product::getIsSale));
	}

}