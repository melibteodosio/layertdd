package br.com.packagingby.layer.account.entities;

public enum TransactionType {
    WITHDRAW(1), DEPOSIT(2);

    public final int transactionNumber;

    TransactionType(int valor) {
        this.transactionNumber = valor;
    }
}
