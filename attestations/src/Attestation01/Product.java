package Attestation01;
import java.util.ArrayList;
import java.util.Objects;

public class Product {

    // Поля
    // (Условие: Поля сделать private)
    private String name_product;
    private int price_product;
    
    // Конструктор класса Продукты
    public Product(String name_product, int price_product) {
        if (!setNameProduct(name_product) || !setPriceProduct(price_product)) {
            System.exit(0);
        }
    }

    // Свойства (геттеры и сеттеры)
    public String getNameProduct() {
        return name_product;
    }
    public int getPriceProduct() {
        return price_product;
    }
    public boolean setNameProduct(String name_product) {
        if (name_product == null || name_product.trim().isEmpty()) {
            System.out.println("Название продукта не может быть пустым. Запустите программу снова и введите корретные данные.");
            return false;
        }
        this.name_product = name_product;
        return true;
    }
    public boolean setPriceProduct(int price_product) {
        if (price_product < 0) {
            System.out.println("Стоимость продукта не может быть отрицательной. Запустите программу снова и введите корретные данные.");
            return false;
        }
        this.price_product = price_product;
        return true;
    }

    // Переопределение методов
    @Override
    public String toString() {
        return name_product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o; // Привести Object к типу Product
        
        // Осуществляется сравнение всех полей, которые были указаны в начале кода
        return price_product == product.price_product && name_product.equals(product.name_product);
    }

    @Override
    public int hashCode() {
    return Objects.hash(name_product, price_product);
    }
}