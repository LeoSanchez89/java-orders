package com.example.lambda.school.javaorders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties(value = {"hasordamount", "hasadvamount"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long ordnum;

    private double ordamount;
    private double advanceamount;
    private String orderdescription;

    @Transient
    public boolean hasordamount = false;
    @Transient
    public boolean hasadvamount = false;



//    ORDERSPAYMENTS JOINTABLE
//    *DOES NOT go in constructor, DOES add setters + getters*
    @ManyToMany()
    @JoinTable(name = "orderspayments",
            joinColumns = @JoinColumn(name = "ordnum"),
            inverseJoinColumns = @JoinColumn(name = "paymentid")
    )
//    always omit the field that you're in to avoid double values
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Set<Payment> payments = new HashSet<>();
// END JOINTABLE


//    connects orders to customer
    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
//    always omit the field that you're in to avoid double values
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Customer customer;

    public Order() {
    }

    public Order(double ordamount, double advanceamount, String orderdescription, Customer customer) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.orderdescription = orderdescription;
        this.customer = customer;
    }

    public long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(long ordnum) {
        this.ordnum = ordnum;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        this.advanceamount = advanceamount;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
