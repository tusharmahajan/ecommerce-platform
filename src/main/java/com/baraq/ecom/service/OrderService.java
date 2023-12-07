package com.baraq.ecom.service;

import com.baraq.ecom.dto.OrderDto;
import com.baraq.ecom.exceptions.InvalidOrderException;
import com.baraq.ecom.models.*;
import com.baraq.ecom.repository.BuyerRepository;
import com.baraq.ecom.repository.OrderRepository;
import com.baraq.ecom.repository.PincodeServiceabilityRepository;
import com.baraq.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PincodeServiceabilityRepository pincodeServiceabilityRepository;

    public String createOrder(OrderDto orderDto) {

        try {
            validateOrderDetails(orderDto);
            OrderEntity order = createOrderEntity(orderDto);

            orderRepository.save(order);
            return order.getId();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private OrderEntity createOrderEntity(OrderDto orderDto) {


        Optional<Buyer> buyerOptional = buyerRepository.findById(orderDto.getBuyerId());
        Optional<Product> productOptional = productRepository.findById(orderDto.getProductId());

        return OrderEntity.builder()
                .buyer(buyerOptional.get())
                .product(productOptional.get())
                .quantity(orderDto.getQuantity())
                .paymentMode(orderDto.getPaymentMode()).build();

    }

    private void validateOrderDetails(OrderDto orderDto) {

        String buyerId = orderDto.getBuyerId();
        String productId = orderDto.getProductId();

        if(buyerId == null || buyerId.isEmpty()){
            throw new InvalidOrderException("Invalid Buyer id entered");
        }
        if(productId == null || productId.isEmpty()){
            throw new InvalidOrderException("Invalid Product id entered");
        }

        Optional<Buyer> buyerOptional = buyerRepository.findById(orderDto.getBuyerId());
        Optional<Product> productOptional = productRepository.findById(orderDto.getProductId());

        if (buyerOptional.isEmpty() || productOptional.isEmpty()) {
            throw new InvalidOrderException("Buyer/Product does not exist");
        }

        Buyer buyer = buyerOptional.get();
        Product product = productOptional.get();
        checkOrderServiceability(buyer, product, orderDto.getPaymentMode());
        checkAndUpdateOrderInventory(orderDto.getQuantity(), product);
    }

    private void checkOrderServiceability(Buyer buyer, Product product, PaymentMode orderPaymentMode) {

        String destinationPincode = buyer.getAddress().getPincode();
        String sourcePincode = product.getAddress().getPincode();

        PincodeServiceability pincodeServiceability = pincodeServiceabilityRepository.getServiceabilityDetails(sourcePincode, destinationPincode);
        if(pincodeServiceability == null){
            throw new InvalidOrderException("OrderEntity failed because pincode is unserviceable");
        }

        PaymentMode supportedPaymentMode = pincodeServiceability.getPaymentMode();

        if(!supportedPaymentMode.equals(PaymentMode.COD_PREPAID) && !supportedPaymentMode.equals(orderPaymentMode)){
            throw new InvalidOrderException(orderPaymentMode + " payment mode not supported");
        }
    }

    private synchronized void checkAndUpdateOrderInventory(int quantity, Product product) {
        int inventory = product.getInventory();
        if(quantity > inventory){
            throw new InvalidOrderException("OrderEntity failed because product stock is insufficient");
        }
        product.setInventory(inventory-quantity);
        productRepository.save(product);

    }
}
