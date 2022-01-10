/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import java.util.List;
import org.solent.com504.oodd.bank.model.dto.CreditCard;

/**
 *
 * @author cgallen
 */
public interface ShoppingService {
    
        public List<ShoppingItem> getAvailableItems();
        
        public TransactionReplyMessage purchaseItems(List<ShoppingItem> shoppingCartItems, CreditCard cardFrom, Double shoppingcartTotal);
        
        public ShoppingItem getNewItemByID(Long id);
        
        public ShoppingItem getNewItemById(Long id);
        
        public void removeItemFromCatalog(Long id);
        
        public void addItemToCatalog(ShoppingItem shoppingItem);
        
        public void updateCatalogItem(Long id,String name, Integer quantity, Double price);
        
}
