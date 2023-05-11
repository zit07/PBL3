package datdocantin.Model;

public class CartModel {
	private int cart_id,user_id;
	
	public  CartModel() {
	}
	
	public  CartModel(Integer cart_id, Integer user_id) {
		this.cart_id = cart_id;
		this.user_id = user_id;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}