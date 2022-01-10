/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;

/**
 *
 * @author cgallen
 */
public class ShoppingCartImpl implements ShoppingCart {

    private HashMap<Long, ShoppingItem> itemMap;
    
    public ShoppingCartImpl() {
        itemMap = new HashMap<Long, ShoppingItem>();
    }

    @Override
    public List<ShoppingItem> getShoppingCartItems() {
        List<ShoppingItem> itemlist = new ArrayList();
        for (Long id : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(id);
            itemlist.add(shoppingCartItem);
        }
        return itemlist;
    }

    @Override
    public void addItemToCart(ShoppingItem shoppingItem) {
        
        if (itemMap.containsKey(shoppingItem.getId())) {
            ShoppingItem itemShoppingCart = itemMap.get(shoppingItem.getId());
            itemShoppingCart.setQuantity(itemShoppingCart.getQuantity() + 1);            
            itemMap.put(itemShoppingCart.getId(), itemShoppingCart);
            return;
        }
        
        itemMap.put(shoppingItem.getId(), shoppingItem);
        System.out.println(itemMap);
    }

    @Override
    public void removeItemFromCart(Long itemID) {
        itemMap.remove(itemID);
    }

    @Override
    public double getTotal() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // ANSWER
        double total = 0;

         for (Long itemID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemID);
            total = total + shoppingCartItem.getPrice() * shoppingCartItem.getQuantity();
        }
         
        return total;

    }
    
    @Override
    public void clearCart() {
        itemMap.clear();
    }

}
