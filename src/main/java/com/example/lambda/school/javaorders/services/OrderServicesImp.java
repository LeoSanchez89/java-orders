package com.example.lambda.school.javaorders.services;


import com.example.lambda.school.javaorders.models.Order;
import com.example.lambda.school.javaorders.models.Payment;
import com.example.lambda.school.javaorders.repositories.OrderRepo;
import com.example.lambda.school.javaorders.repositories.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service(value = "orderServices")
public class OrderServicesImp implements OrderServices{

    @Autowired
    private OrderRepo orderrepo;

    @Autowired
    private PaymentRepo paymentrepo;

    @Autowired
    private CustomerServices customerServices;

    @Override
    public Order findOrderById(long id) {
        return orderrepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Order " + id + " Not Found!"));
    }

/**    CRUD METHODS    **/

//    DELETE
    @Transactional
    @Override
    public void delete(long id) {
        if (orderrepo.findById(id).isPresent()){
            orderrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Order " + id + " Not Found");
        }
    }

//    POST
    @Transactional
    @Override
    public Order save(Order order) {

        Order newOrder = new Order();
//        order check
        if (order.getOrdnum() != 0){
            orderrepo.findById(order.getOrdnum())
                    .orElseThrow(()->
                            new EntityNotFoundException("Order " +
                                    order.getOrdnum() + " Not Found"));
            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());

//        payment check *MANY TO MANY*
        newOrder.getPayments().clear();
        for (Payment p : order.getPayments()){
            Payment newPay = paymentrepo.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));

            newOrder.getPayments().add(newPay);
        }

//        customer check *MANY TO ONE*
        newOrder.setCustomer(customerServices.findCustomerById(order.getCustomer().getCustcode()));

        return orderrepo.save(newOrder);
    }

//    UPDATE
    @Transactional
    @Override
    public Order update(Order order, long id){
        Order currentOrder = orderrepo.findById(id)
                .orElseThrow(()->
                        new EntityNotFoundException("Order " + id + " Not Found"));

        if (order.hasordamount){
            currentOrder.setOrdamount(order.getOrdamount());
        }
        if (order.hasadvamount){
            currentOrder.setAdvanceamount(order.getAdvanceamount());
        }
        if (order.getOrderdescription() != null){
            currentOrder.setOrderdescription(order.getOrderdescription());
        }

//        payment check *MANY TO MANY*
        if (order.getPayments()
                .size() > 0)
        {
            currentOrder.getPayments()
                    .clear();
            for (Payment p : order.getPayments())
            {
                Payment newPay = paymentrepo.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));

                currentOrder.getPayments().add(newPay);
            }
        }
//        customer check *MANY TO ONE*
        if (order.getCustomer() != null){
            currentOrder.setCustomer(customerServices.findCustomerById(order.getCustomer().getCustcode()));
        }

        return orderrepo.save(currentOrder);
    }

}
