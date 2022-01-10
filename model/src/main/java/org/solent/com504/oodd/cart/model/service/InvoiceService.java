/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import java.util.List;
import org.solent.com504.oodd.cart.model.dto.Invoice;

/**
 *
 * @author cgallen
 */
public interface InvoiceService {
    
        public List<Invoice> getInvoices();
        
        public List<Invoice> getInvoicesByUser(Long id);
         
        public void addInvoice(Invoice invoice);
                
}
