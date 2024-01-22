public enum OrderStatus {
    ORDER_IS_PLACED,
    PREPARING,
    DELIVERING,
    DELIVERED;

    private static OrderStatus[] list = OrderStatus.values();

    public static OrderStatus getStatus(int i) {
        return list[i];
    }
}
