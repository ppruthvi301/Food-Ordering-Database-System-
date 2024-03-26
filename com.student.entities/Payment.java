package com.student.entities;

import javax.persistence.*;


@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentID")
    private int paymentID;

    @OneToOne
    @JoinColumn(name = "orderID")
    private Orders order;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private double amount;

    public Payment(int paymentID, Orders order, String status, double amount) {
        this.paymentID = paymentID;
        this.order = order;
        this.status = status;
        this.amount = amount;
    }

    public Payment() {
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", order=" + order +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                '}';
    }
}