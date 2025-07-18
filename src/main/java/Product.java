import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private double price;
    private String description;
    private String manufacturer;

    public Product(String id, String name, double price, String description, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Продукт{" +
                "идентификатор ='" + id + '\'' +
                ", Имя ='" + name + '\'' +
                ", Цена =" + price +
                ", описание ='" + description + '\'' +
                ", Производитель ='" + manufacturer + '\'' +
                '}';
    }
}
