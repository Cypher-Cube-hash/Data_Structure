package com.example.datastructures.cart;

import com.example.datastructures.product.ProductBst;
import com.example.models.Product;
import com.vaadin.flow.server.VaadinSession;

//Ai aided in this fraction with the session section
public class CartSession {

    private final CartLinkedList cartList;
    private final CartStack<CartAction> undoStack;
    private final CartStack<CartAction> redoStack;
    private String userId;
    private String userEmail;
    private final ProductBst cartBst;

    public CartSession(){
        this.userId = "";
        this.userEmail = "";
        cartList = new CartLinkedList();
        undoStack = new CartStack<>();
        redoStack = new CartStack<>();
        cartBst = new ProductBst();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public void addToCart(Product product) {
        CartItem item = new CartItem(product, 1);
        cartList.add(item);

        if (cartBst.searchById(product.getProductId()) == null) {
            cartBst.insert(product);
        }

        undoStack.push(new CartAction(CartAction.Type.ADD, item));
        redoStack.clear();
    }

    public boolean removeFromCart(int productId) {
        CartItem removed = cartList.remove(productId);
        if (removed == null) return false;

        cartBst.delete(productId);
        undoStack.push(new CartAction(CartAction.Type.REMOVE, removed));
        redoStack.clear();
        return true;
    }

    public boolean updateQuantity(int productId, int newQuantity) {
        if (newQuantity <= 0) return removeFromCart(productId);

        CartItem existing = cartList.findById(productId);
        if (existing == null) return false;

        int oldQty = cartList.updateQuantity(productId, newQuantity);
        CartItem snapshot = new CartItem(existing);
        snapshot.setQuantity(newQuantity);

        undoStack.push(new CartAction(CartAction.Type.QUANTITY, snapshot, oldQty));
        redoStack.clear();
        return true;
    }

    public String undo() {
        if (undoStack.isEmpty()) return null;

        CartAction action = undoStack.pop();

        switch (action.getType()) {

            case ADD:
                cartList.remove(action.getItem().getProduct().getProductId());
                cartBst.delete(action.getItem().getProduct().getProductId());
                redoStack.push(action);
                return "Removed \"" + action.getItem().getProduct().getProductName() + "\" from cart.";

            case REMOVE:
                cartList.add(action.getItem());
                cartBst.insert(action.getItem().getProduct());
                redoStack.push(action);
                return "Restored \"" + action.getItem().getProduct().getProductName() + "\" to cart.";

            case QUANTITY:
                cartList.updateQuantity(
                    action.getItem().getProduct().getProductId(),
                    action.getOldQuantity()
                );
                redoStack.push(action);
                return "Quantity of \"" + action.getItem().getProduct().getProductName()
                    + "\" reverted to " + action.getOldQuantity() + ".";
        }
        return null;
    }

    public String redo() {
        if (redoStack.isEmpty()) return null;

        CartAction action = redoStack.pop();

        switch (action.getType()) {

            case ADD:
                cartList.add(action.getItem());
                cartBst.insert(action.getItem().getProduct());
                undoStack.push(action);
                return "Re-added \"" + action.getItem().getProduct().getProductName() + "\".";
            case REMOVE:
                cartList.remove(action.getItem().getProduct().getProductId());
                cartBst.delete(action.getItem().getProduct().getProductId());
                undoStack.push(action);
                return "Removed \"" + action.getItem().getProduct().getProductName() + "\" again.";
            case QUANTITY:
                cartList.updateQuantity(
                    action.getItem().getProduct().getProductId(),
                    action.getItem().getQuantity()
                );
                undoStack.push(action);
                return "Quantity of \"" + action.getItem().getProduct().getProductName()
                    + "\" set back to " + action.getItem().getQuantity() + ".";
        }
        return null;
    }

    public Product findInCartById(int productId) {
        return cartBst.searchById(productId);
    }

    // ── Accessors ──────────────────────────────────────────────────────────

    public CartLinkedList getCartList() {
        return cartList;
    }
    public CartStack<CartAction> getUndoStack(){
        return undoStack;
    }
    public CartStack<CartAction> getRedoStack(){
        return redoStack;
    }

    public boolean canUndo(){
        return !undoStack.isEmpty();
    }
    public boolean canRedo(){
        return !redoStack.isEmpty();
    }
    public int cartSize(){
        return cartList.getSize();
    }
}