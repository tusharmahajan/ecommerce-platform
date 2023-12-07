package com.baraq.ecom.controller;

import com.baraq.ecom.dto.BuyerDto;
import com.baraq.ecom.dto.OrderDto;
import com.baraq.ecom.dto.PincodeServiceabilityDto;
import com.baraq.ecom.dto.ProductDto;
import com.baraq.ecom.service.BuyerService;
import com.baraq.ecom.service.OrderService;
import com.baraq.ecom.service.ProductService;
import com.baraq.ecom.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal")
public class ECommerceController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/registerBuyer")
    public ResponseEntity<String> registerBuyer(@RequestBody BuyerDto buyerDto){
        try {
            return ResponseEntity.ok(buyerService.registerBuyer(buyerDto));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/storeProduct")
    public ResponseEntity<String> storeProductDetails(@RequestBody ProductDto productDto){
        try {
            return ResponseEntity.ok(productService.storeProductDetails(productDto));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/storePincodeServiceability")
    public ResponseEntity<String> storePincodeServiceability(@RequestBody PincodeServiceabilityDto pincodeMappingDto){
        try {
            return ResponseEntity.ok(routeService.storePincodeServiceability(pincodeMappingDto));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto){
        try {
            return ResponseEntity.ok(orderService.createOrder(orderDto));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
