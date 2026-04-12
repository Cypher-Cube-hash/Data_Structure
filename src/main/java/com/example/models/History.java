package com.example.models;

import com.example.enums.TypeOrderStatus;
import com.example.utils.IDGenerator;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "history")
public class History {
    // - historyId: String
    @Id
    private String historyId;
    // - orderId: string
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    // - orderStatus: Enum(Status)
    @Enumerated(EnumType.STRING)
    private TypeOrderStatus orderStatus;

    // - createdAt(): LocalDate
    private LocalDate createdAt;

    // - updatedAt(): LocalDate
    private LocalDate updatedAt;

    // =================================
    // + History(String, String, Status, LocalDate, LocalDate): void
        public History(String historyId, Order orderId, TypeOrderStatus orderStatus, LocalDate createdAt, LocalDate updatedAt) {
            this.historyId = IDGenerator.idGenerator(10);
            this.orderId = orderId;
            this.orderStatus = orderStatus;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

    // + History(): void
    public History() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // + History(History): void
    public History(History history) {
        this.historyId = history.historyId;
        this.orderId = history.orderId;
        this.orderStatus = history.orderStatus;
        this.createdAt = history.createdAt;
        this.updatedAt = history.updatedAt;
    }

    // + getHistoryId(): String
    public String getHistoryId() {
        return historyId;
    }

    // + getOrderId(): String
    public Order getOrderId() {
        return orderId;
    }

    // + getOrderStatus(): Status
    public TypeOrderStatus getOrderStatus() {
        return orderStatus;
    }

    // + getCreatedAt(): LocalDate
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    // + getUpdatedAt(): LocalDate
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    // + setHistoryId(String): void
    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    // + setOrderId(String): void
    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    // + setOrderStatus(Status): void
    public void setOrderStatus(TypeOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    // + setCreatedAt(LocalDate): void
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    // + setUpdatedAt(LocalDate): void
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    // + withHistoryId(String): History
    public History withHistoryId(String historyId) {
        History newHistory = new History(this);
        newHistory.historyId = historyId;
        return newHistory;
    }

    // + withOrderId(String): History
    public History withOrderId(Order orderId) {
        History newHistory = new History(this);
        newHistory.orderId = orderId;
        return newHistory;
    }

    // + withOrderStatus(Status): History
    public History withOrderStatus(TypeOrderStatus orderStatus) {
        History newHistory = new History(this);
        newHistory.orderStatus = orderStatus;
        return newHistory;
    }

    // + withDateCreated(LocalDate): History
    public History withDateCreated(LocalDate createdAt) {
        History newHistory = new History(this);
        newHistory.createdAt = createdAt;
        return newHistory;
    }

    // + withDateUpdated(LocalDate): History
    public History withDateUpdated(LocalDate updatedAt) {
        History newHistory = new History(this);
        newHistory.updatedAt = updatedAt;
        return newHistory;
    }
}
