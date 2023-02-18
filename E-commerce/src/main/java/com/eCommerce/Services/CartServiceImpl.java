package com.eCommerce.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.exceptions.CartException;
import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.ProductException;
import com.eCommerce.model.Cart;
import com.eCommerce.model.CurrentUserSession;
import com.eCommerce.model.Customer;
import com.eCommerce.model.Products;
import com.eCommerce.repo.CartRepo;
import com.eCommerce.repo.CurrentUserSessionRepo;
import com.eCommerce.repo.CustomerRepo;
import com.eCommerce.repo.Productrepo;

@Service
public class CartServiceImpl implements CartServices {

	@Autowired
	private CustomerRepo cRepo;

	@Autowired
	private CurrentUserSessionRepo cusRepo;

	@Autowired
	private Productrepo pRepo;

	@Autowired
	private CartRepo cartRepo;

	@Override
	public Cart AddProductToCart(Integer productId, String key, Integer customerId)
			throws LoginException, CustomerException, ProductException {
		Customer customer = checkLogin(key, customerId);

		Optional<Products> opt = pRepo.findById(productId);
		if (opt.isEmpty())
			throw new ProductException("Product not Found with Id:- " + productId);
		Products product = opt.get();

		Cart cart = customer.getCart();

		Map<Products, Integer> cartMap = cart.getProducts();

		if (cartMap.containsKey(product)) {
			cartMap.put(product, cartMap.get(product) + 1);
		} else {
			cartMap.put(product, 1);
		}

		cart.setCartValue(cart.getCartValue() + product.getPrice());

		Cart savedCart = cartRepo.save(cart);

		return savedCart;
	}

	@Override
	public Cart descreaseQuantityOfProduct(Integer productId, String key, Integer customerId, Integer Quantity)
			throws LoginException, CustomerException, ProductException, CartException {
		Customer customer = checkLogin(key, customerId);

		Optional<Products> opt = pRepo.findById(productId);
		if (opt.isEmpty())
			throw new ProductException("Product not Found with Id:- " + productId);
		Products product = opt.get();

		Cart cart = customer.getCart();

		Map<Products, Integer> cartMap = cart.getProducts();

		if (cartMap.containsKey(product)) {
			Integer quan = cartMap.get(product);
			if (quan > Quantity) {
				cartMap.put(product, quan - Quantity);
				cart.setCartValue(cart.getCartValue() - (product.getPrice() * Quantity));
			} else {
				cartMap.remove(product);
				cart.setCartValue(cart.getCartValue() - (product.getPrice() * quan));
			}
			Cart savedCart = cartRepo.save(cart);
			return savedCart;
		} else {
			throw new CartException("Product Not present In cart");
		}
	}

	@Override
	public Cart removeProductFromCart(Integer productId, String Key, Integer customerId)
			throws LoginException, CustomerException, ProductException, CartException {
		Customer customer = checkLogin(Key, customerId);

		Optional<Products> opt = pRepo.findById(productId);
		if (opt.isEmpty())
			throw new ProductException("Product not Found with Id:- " + productId);
		Products product = opt.get();

		Cart cart = customer.getCart();

		Map<Products, Integer> cartMap = cart.getProducts();
		
		if(cartMap.containsKey(product)) {
			Integer quantity = cartMap.get(product);
			
			cartMap.remove(product);
			cart.setCartValue(cart.getCartValue()-(product.getPrice()*quantity));
			
			Cart savedCart = cartRepo.save(cart);
			
			return savedCart;
			
		}else {
			throw new CartException("Product Not Found with Id:- "+productId);
		}

	}

	@Override
	public Cart emptycart(String key, Integer customerId) throws LoginException, CustomerException {
		Customer customer = checkLogin(key, customerId);
		
		Cart cart= customer.getCart();
		
		cart.setProducts(new HashMap<>());
		cart.setCartValue(0);
		Cart savedCart= cartRepo.save(cart);
		
		return savedCart;
	}

	@Override
	public Map<String, Integer> getAllProductInCart(Integer customerId, String key) throws LoginException, CustomerException, CartException {
		Customer customer = checkLogin(key, customerId);
		
		//List<Products> productList=new ArrayList<>();
		Map<String, Integer>productList = new HashMap<>();
		Cart cart= customer.getCart();
		Map<Products, Integer> cartMap= cart.getProducts();
		if(cartMap.size()==0) throw new CartException("No product Found In cart....");
		
		for(Map.Entry<Products, Integer> map:cartMap.entrySet()) {
			productList.put(map.getKey().getProductName(), map.getValue());
		}
		
		return productList;
	}

	@Override
	public Integer getCartValue(Integer customerId, String key) throws LoginException, CustomerException {
		Customer customer = checkLogin(key, customerId);
		
		return (int)customer.getCart().getCartValue();
	}

	
	
	
	
	public Customer checkLogin(String key, Integer customerId) throws LoginException, CustomerException {
		Optional<Customer> opt = cRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("No customer Found with id:- " + customerId);

		Customer customer = opt.get();
		CurrentUserSession cus = cusRepo.findByUuid(key);

		if (cus == null)
			throw new LoginException("Invalid Current Key");
		if (cus.getUserId() != customer.getCustomerId())
			throw new LoginException("Please Login first.....");

		return customer;

	}

}
