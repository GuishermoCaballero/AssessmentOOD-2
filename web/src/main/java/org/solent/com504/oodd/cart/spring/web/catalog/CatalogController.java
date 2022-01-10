package org.solent.com504.oodd.cart.spring.web.catalog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    // @RequestMapping("/add")
    // public ... addCatalogItem() {}

    // @RequestMapping("/list")
    // public ... getCatalogList() {}

    // @RequestMapping("/update") // It updates information about an item
    // public ... addCatalogItem(uuid, id, name, quantity, price) {}
    // 1.º voy al repo para traerme el shoppingitem con id = idpasado
    // 2.º modifico los campos name, ...
    // 3.º actualizo el repo con el .save(shoppingItem);
    // 4.º llamar a getCatalogList()

    // @RequestMapping("/delete") // It updates information about an item
    // public ... addCatalogItem() {}

    // cambio
}
