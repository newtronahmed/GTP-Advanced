package org.springboot.dayfive;
class Order {
    private final int id;
    private final String item;

    public Order(int id, String item) {
        this.id = id;
        this.item = item;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", item='" + item + "'}";
    }
}
