/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.solent.com504.oodd.cart.dao.impl.InvoiceRepository;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.service.InvoiceService;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        return invoiceList;
    }

    @Override
    public List<Invoice> getInvoicesByUser(Long id) {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<Invoice> userInvoiceList = new ArrayList();
        for(Invoice invoice: invoiceList){
            if(invoice.getPurchaser().getId()==id){
                userInvoiceList.add(invoice);
            }
        }
        return userInvoiceList;
    }

    @Override
    public void addInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

}
