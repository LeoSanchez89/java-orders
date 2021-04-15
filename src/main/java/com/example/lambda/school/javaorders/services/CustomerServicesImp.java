package com.example.lambda.school.javaorders.services;


import com.example.lambda.school.javaorders.models.Customer;
import com.example.lambda.school.javaorders.models.Order;
import com.example.lambda.school.javaorders.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImp implements CustomerServices{

    @Autowired
    private CustomerRepo customerrepo;

    @Autowired
    private AgentServices agentServices;

    @Override
    public Customer findCustomerById(long id) {
        return customerrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));
    }

    @Override
    public List<Customer> findByNameLike(String subname) {

        List<Customer> rtnList = customerrepo.findByCustnameContainingIgnoringCase(subname);
        return rtnList;
    }

    @Override
    public List<Customer> findAllCustomerOrders() {
        List<Customer> list = new ArrayList<>();
        customerrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    /********************************
    ********  CRUD METHODS  *********
    ********************************/

/**    DELETE    **/
    @Transactional
    @Override
    public void delete(long id) {
        if(customerrepo.findById(id).isPresent()){
            customerrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer " + id + " Not Found");
        }
    }

/**    POST    **/
    @Transactional
    @Override
    public Customer save(Customer customer) {

        Customer newCustomer = new Customer();
//    customer check
        if (customer.getCustcode() != 0){
            customerrepo.findById(customer.getCustcode())
                    .orElseThrow(()->
                            new EntityNotFoundException("Customer " +
                                    customer.getCustcode() + " Not Found"));
            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());

//        agent check *MANY TO ONE*
        newCustomer.setAgent(agentServices.findAgentById(customer.getAgent().getAgentcode()));

//        orders check *ONE TO MANY*
        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()){
            Order newOrder = new Order(o.getOrdamount(),
                                    o.getAdvanceamount(),
                                    o.getOrderdescription(),
                                        newCustomer);
            newCustomer.getOrders().add(newOrder);
        }
        return customerrepo.save(newCustomer);
    }

/**    UPDATE   **/
    @Transactional
    @Override
    public Customer update(Customer customer, long id) {

        Customer currentCustomer = customerrepo.findById(id)
                .orElseThrow(()->
                        new EntityNotFoundException("Customer " + id + " Not Found"));

        if (customer.getCustname() != null){
            currentCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null){
            currentCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getWorkingarea() != null){
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getCustcountry() != null){
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null){
            currentCustomer.setGrade(customer.getGrade());
        }
        if (customer.hasopeningvalue){
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.hasreceivevalue){
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }
        if (customer.haspaymentvalue){
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.hasoutstandingvalue){
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if (customer.getPhone() != null){
            currentCustomer.setPhone(customer.getPhone());
        }

//        check agent *MANY TO ONE*
        if (customer.getAgent() != null){
            currentCustomer.setAgent(agentServices.findAgentById(customer.getAgent().getAgentcode()));
        }

//        check orders *ONE TO MANY*
        if (customer.getOrders().size() > 0){
            currentCustomer.getOrders().clear();
            for (Order o : customer.getOrders()){
                Order newOrder = new Order(o.getOrdamount(),
                                    o.getAdvanceamount(),
                                    o.getOrderdescription(),
                                    currentCustomer);
                currentCustomer.getOrders().add(newOrder);
            }
        }
        return customerrepo.save(currentCustomer);
    }


}
