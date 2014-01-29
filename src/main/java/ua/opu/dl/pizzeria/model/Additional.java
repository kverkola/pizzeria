package ua.opu.dl.pizzeria.model;

public class Additional extends Product {

	private long orderId;
    private String logo;

    public Additional() {
	}

	public Additional( long id, long orderId, String name, double price, String logo) {

        super(id, name, 1, price);
        this.orderId = orderId;
        this.logo = logo;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Additional that = (Additional) o;

        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        return result;
    }
}
