package com.baraq.ecom;

import com.baraq.ecom.dto.OrderDto;
import com.baraq.ecom.models.*;
import com.baraq.ecom.repository.BuyerRepository;
import com.baraq.ecom.repository.OrderRepository;
import com.baraq.ecom.repository.PincodeServiceabilityRepository;
import com.baraq.ecom.repository.ProductRepository;
import com.baraq.ecom.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PincodeServiceabilityRepository pincodeServiceabilityRepository;

    @InjectMocks
    private OrderService orderService;


    @Test
     void testCreateOrder_successfulOrder() {

        Address buyerAddress = Address.builder().pincode("144022").build();
        Buyer buyer = Buyer.builder().name("tushar").address(buyerAddress).build();
        Address productAddress = Address.builder().pincode("111333").build();

        Product product = Product.builder().name("shampoo").inventory(10).price(100).address(productAddress).build();
        PincodeServiceability pincodeServiceability = PincodeServiceability.builder()
                .sourcePincode(productAddress).destinationPincode(buyerAddress).paymentMode(PaymentMode.COD_PREPAID).build();

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerId(buyer.getId());
        orderDto.setProductId(product.getId());
        orderDto.setQuantity(10);
        orderDto.setPaymentMode(PaymentMode.COD);

        Mockito.when(buyerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(buyer));
        Mockito.when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.of(product));
        Mockito.when(pincodeServiceabilityRepository.getServiceabilityDetails(Mockito.anyString(), Mockito.anyString())).thenReturn(pincodeServiceability);

        String orderId = orderService.createOrder(orderDto);

        Assertions.assertNotNull(orderId);
    }
}

