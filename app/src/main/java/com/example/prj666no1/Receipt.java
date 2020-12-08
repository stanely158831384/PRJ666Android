package com.example.prj666no1;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Receipt {

    @SerializedName("receiptDate")
    private Date date;

    @SerializedName("user")
    private String user;

    @SerializedName("receiptDetail")
    private String receiptDetail;

    @SerializedName("paymentByCredit")
    private String paymentByCredit;

    @SerializedName("subscriptionEndDate")
    private Date endDate;

    public Receipt(Date date, String user, String receiptDetail, String paymentByCredit, Date endDate) {
        this.date = date;
        this.user = user;
        this.receiptDetail = receiptDetail;
        this.paymentByCredit = paymentByCredit;
        this.endDate = endDate;
    }

    public Date getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public String getReceiptDetail() {
        return receiptDetail;
    }

    public String getPaymentByCredit() {
        return paymentByCredit;
    }

    public Date getEndDate() {
        return endDate;
    }
}
