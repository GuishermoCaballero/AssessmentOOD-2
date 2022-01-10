/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.com504.oodd.bank.model.client.BankRestClient;

/**
 *
 * @author cgallen
 */
@Component
public class ShoppingServiceImpl1 implements ShoppingService {

    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    // note ConcurrentHashMap instead of HashMap if map can be altered while being read
/*    private Map<String, ShoppingItem> itemMap = new ConcurrentHashMap<String, ShoppingItem>();

    private List<ShoppingItem> itemlist = Arrays.asList(new ShoppingItem("house", 20000.00),
            new ShoppingItem("hen", 5.00),
            new ShoppingItem("car", 5000.00),
            new ShoppingItem("pet alligator", 65.00)
    );
     */
    public ShoppingServiceImpl1() {

        // initialised the hashmap
        // for (ShoppingItem item : itemlist) {
        //   itemMap.put(item.getName(), item);
        //}
    }

    @Override
    public List<ShoppingItem> getAvailableItems() {
        List<ShoppingItem> itemList = shoppingItemCatalogRepository.findAll();
        return itemList;
    }

    @Override
    public TransactionReplyMessage purchaseItems(List<ShoppingItem> shoppingCartItems, CreditCard cardFrom, Double shoppingcartTotal) {
        //String bankUrl = "http://com528bank.ukwest.cloudapp.azure.com:8080/rest/";
        String bankUrl = "https://com528bank.herokuapp.com/rest";
        BankRestClient client = new BankRestClientImpl(bankUrl);
        TransactionReplyMessage reply = null;
        String action = null;

        //Card To
        String name2 = "test user2";
        String endDate2 = "11/21";
        String cardnumber2 = "4285860000000021";
        String cvv2 = "123";
        String issueNumber2 = "01";
        CreditCard cardTo = new CreditCard();
        cardTo.setName(name2);
        cardTo.setEndDate(endDate2);
        cardTo.setCardnumber(cardnumber2);
        cardTo.setCvv(cvv2);
        cardTo.setIssueNumber(issueNumber2);

        for (ShoppingItem item : shoppingCartItems) {
            int requestedQuantity = item.getQuantity();

            Optional<ShoppingItem> itemComparando = shoppingItemCatalogRepository.findById(item.getId());
            int stock = itemComparando.get().getQuantity();

        }

        reply = client.transferMoney(cardFrom, cardTo, shoppingcartTotal);

        return reply;
    }

    @Override
    public ShoppingItem getNewItemByID(Long id) {
        List<ShoppingItem> list = shoppingItemCatalogRepository.findByID(id);
        ShoppingItem found = list.get(0);

        return found;
    }

    @Override
    public ShoppingItem getNewItemById(Long id) {

        Optional<ShoppingItem> found = shoppingItemCatalogRepository.findById(id);
        ShoppingItem item = found.get();
        return item;
    }

    @Override
    public void removeItemFromCatalog(Long id) {
        shoppingItemCatalogRepository.deleteById(id);
    }

    @Override
    public void addItemToCatalog(ShoppingItem shoppingItem) {
        shoppingItemCatalogRepository.save(shoppingItem);
    }

    @Override
    public void updateCatalogItem(Long id, String name, Integer quantity, Double price) {
        Optional<ShoppingItem> found = shoppingItemCatalogRepository.findById(id);
        ShoppingItem item = found.get();
        item.setName(name);
        item.setQuantity(quantity);
        item.setPrice(price);
        shoppingItemCatalogRepository.save(item);
    }
    

}
