package com.pablodev.shared.domain.criteria;

public class Order {

    private final OrderBy orderBy;
    private final OrderType orderType;

    private Order(OrderBy orderBy, OrderType orderType) {
        this.orderBy = orderBy;
        this.orderType = orderType;
    }

    public static Order ascending(String field) {
        return new Order(new OrderBy(field), OrderType.ASCENDING);
    }

    public static Order descending(String field) {
        return new Order(new OrderBy(field), OrderType.DESCENDING);
    }

    public static Order unordered() {
        return new Order(new OrderBy("none"), OrderType.NONE);
    }

    public boolean hasOrder() {
        return !orderType.isNone();
    }

    public boolean isAscending() {
        return orderType.isAscending();
    }

    public String getOrderBy() {
        return orderBy.getValue();
    }

    public OrderType getOrderType() {
        return orderType;
    }

}
