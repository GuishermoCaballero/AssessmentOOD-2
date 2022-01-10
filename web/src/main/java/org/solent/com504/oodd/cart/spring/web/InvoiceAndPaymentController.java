package org.solent.com504.oodd.cart.spring.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.model.dto.BankTransactionStatus;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.web.WebObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.service.InvoiceService;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;

@Controller
@RequestMapping("/")
public class InvoiceAndPaymentController {

    final static Logger LOG = LogManager.getLogger(InvoiceAndPaymentController.class);

    @Autowired
    ShoppingService shoppingService = null;

    @Autowired
    InvoiceService invoiceService = null;

    // note that scope is session in configuration
    // so the shopping cart is unique for each web session
    @Autowired
    ShoppingCart shoppingCart = null;

    private User getSessionUser(HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            sessionUser = new User();
            sessionUser.setUsername("anonymous");
            sessionUser.setUserRole(UserRole.ANONYMOUS);
            session.setAttribute("sessionUser", sessionUser);
        }
        return sessionUser;
    }

    //CHECKOUT 
    @RequestMapping(value = "/checkout", method = {RequestMethod.GET})
    public String checkout(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "home");

        String message = "";
        String errorMessage = "";

        if (!sessionUser.getUserRole().equals(UserRole.CUSTOMER)) {

            errorMessage = "You must log in before proceed to checkout";
            model.addAttribute("errorMessage", errorMessage);

            return "login";
        } else {

            List<ShoppingItem> availableItems = shoppingService.getAvailableItems();

            List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();

            Double shoppingcartTotal = shoppingCart.getTotal();

            // populate model with values
            model.addAttribute("availableItems", availableItems);
            model.addAttribute("shoppingCartItems", shoppingCartItems);
            model.addAttribute("shoppingcartTotal", shoppingcartTotal);
            model.addAttribute("message", message);
            model.addAttribute("errorMessage", errorMessage);

            return "checkout";

        }

    }

    @RequestMapping(value = "/checkout", method = {RequestMethod.POST})
    public String checkoutPayment(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            @RequestParam(name = "endDate1", required = false) String endDate1,
            @RequestParam(name = "cardnumber1", required = false) String cardnumber1,
            @RequestParam(name = "cvv1", required = false) String cvv1,
            @RequestParam(name = "issueNumber1", required = false) String issueNumber1,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "home");

        String message = "";
        String errorMessage = "";

        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();

        if (!sessionUser.getUserRole().equals(UserRole.CUSTOMER)) {

            errorMessage = "You must log in before proceed to pay";
            model.addAttribute("errorMessage", errorMessage);

            return "login";
        } else {

            List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
            Double shoppingcartTotal = shoppingCart.getTotal();

            //Costumer Credit Card
            CreditCard cardFrom = new CreditCard();
            cardFrom.setName("test user1");
            cardFrom.setEndDate(endDate1);
            cardFrom.setCardnumber(cardnumber1);
            cardFrom.setCvv(cvv1);
            cardFrom.setIssueNumber(issueNumber1);

            TransactionReplyMessage reply = shoppingService.purchaseItems(shoppingCartItems, cardFrom, shoppingcartTotal);

            if (reply.getStatus().equals(BankTransactionStatus.SUCCESS)) {

                //SAVE THE INVOICE
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                Invoice invoice = new Invoice();
                invoice.setDateOfPurchase(date);
                invoice.setAmountDue(shoppingcartTotal);
                invoice.setPurchasedItems(shoppingCart.getShoppingCartItems());
                invoice.setPurchaser(sessionUser);

                invoiceService.addInvoice(invoice);
                shoppingCart.clearCart();

                message = "£" + reply.getAmount() + " WERE SUCCESSFULLY PAID, go to the orders section to access the invoice ";
                // populate model with values

                model.addAttribute("availableItems", availableItems);

                model.addAttribute("invoice", invoice);
                model.addAttribute("message", message);
                model.addAttribute("errorMessage", errorMessage);

                return "home";
            } else {
                errorMessage = "No purhcase was made due to : " + reply.getMessage() + ". Please try Again";

                model.addAttribute("shoppingCartItems", shoppingCartItems);
                model.addAttribute("shoppingcartTotal", shoppingcartTotal);
                model.addAttribute("message", message);
                model.addAttribute("errorMessage", errorMessage);

                return "checkout";
            }
        }

    }

    //CHECKOUT END
    //SHOW ORDERS
    @RequestMapping(value = "/orders", method = {RequestMethod.GET})
    public String ordersList(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            Model model,
            HttpSession session) {

        String message = "";
        String errorMessage = "";

        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        if (sessionUser.getUserRole().equals(UserRole.CUSTOMER)) {

            List<Invoice> orderList = invoiceService.getInvoicesByUser(sessionUser.getId());

            model.addAttribute("invoiceListSize", orderList.size());
            model.addAttribute("invoiceList", orderList);
            model.addAttribute("selectedPage", "orders");
            return "orders";
        } else if (sessionUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            //List<Invoice> orderList = invoiceRepository.findAll();
            List<Invoice> orderList = invoiceService.getInvoices();

            model.addAttribute("invoiceListSize", orderList.size());
            model.addAttribute("invoiceList", orderList);
            model.addAttribute("selectedPage", "orders");
            return "orders";
        } else {
            errorMessage = "You must log in to see your orders";
            model.addAttribute("errorMessage", errorMessage);

            return "login";
        }

    }

    /*
     * Default exception handler, catches all exceptions, redirects to friendly
     * error page. Does not catch request mapping errors
     */
    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(final Exception e, Model model,
            HttpServletRequest request
    ) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String strStackTrace = sw.toString(); // stack trace as a string
        String urlStr = "not defined";
        if (request != null) {
            StringBuffer url = request.getRequestURL();
            urlStr = url.toString();
        }
        model.addAttribute("requestUrl", urlStr);
        model.addAttribute("strStackTrace", strStackTrace);
        model.addAttribute("exception", e);
        //logger.error(strStackTrace); // send to logger first
        return "error"; // default friendly exception message for sessionUser
    }

}
