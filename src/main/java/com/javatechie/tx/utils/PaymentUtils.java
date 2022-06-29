package com.javatechie.tx.utils;

import com.javatechie.tx.exception.InsufficientAmountException;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {

    private static Map<String, Double> paymentMap = new HashMap<>();

    static {
        paymentMap.put("acc1", 12000.0);
        paymentMap.put("acc2", 10000.0);
        paymentMap.put("acc3", 5000.0);
        paymentMap.put("acc4", 8000.0);
    }

    /*
     * Controlla se l'account selezionato ha sufficienti fondi. Altrimenti solleva un'eccezione. 
     * @param accNo Stringa che indica l'id dell'account. 
     * @param paidAmount Quantità da pagare. 
     * @throw InsufficientAmountException Se i fondi non sono sufficienti.
     * @return true Se i fondi nell'account con id accNo sono sufficienti. 
     */
    public static boolean validateCreditLimit(String accNo, double paidAmount) {

        if (paidAmount > paymentMap.get(accNo)) 
            throw new InsufficientAmountException("insufficient fund..!");

		return true;
    }
}
