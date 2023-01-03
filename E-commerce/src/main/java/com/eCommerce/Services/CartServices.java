package com.eCommerce.Services;

import java.util.List;

import com.eCommerce.exceptions.CartException;
import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.ProductException;
import com.eCommerce.model.Cart;
import com.eCommerce.model.Products;

public interface CartServices {

	public Cart AddProductToCart(Integer productId,String key,Integer customerId)
			throws LoginException,CustomerException,ProductException;
	
	public Cart descreaseQuantityOfProduct(Integer productId,String key,Integer customerId,Integer Quantity)
			throws LoginException,CustomerException,ProductException,CartException;
	
	public Cart removeProductFromCart(Integer productId,String Key,Integer customerId)
			throws LoginException,CustomerException,ProductException,CartException;
	
	public Cart emptycart(String key,Integer customerId)
			throws LoginException,CustomerException,CartException;
	
	public List<Products> getAllProductInCart(Integer customerId,String key)
			throws LoginException,CustomerException,CartException;
	
	public Integer getCartValue(Integer customerId,String key)
			throws LoginException,CustomerException;
	
	
}
