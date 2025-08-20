package Attestation01;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class DiscountProduct extends Product { // Указываем наследие из класса Продуктов
    private int discount;   // Размер скидки
    private LocalDate validDate;   // Дата, до которой действует скидка

    // Создаем конструктор для класса Скидок
    public DiscountProduct(String name_product, int price_product, int discount, String validDate) {
        super(name_product, price_product); //Вызов конструктора родительского класса
        // Конструктор класса скидок
        if (!setDiscount(discount) || !setValidDate(validDate)) {
            System.exit(0);
        }
    }

    // Свойства (геттеры и сеттеры)
    public int getDiscount() {
        return discount;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public boolean setDiscount(int discount) {
        if (discount < 0 || discount > 100) {
            System.out.println("Скидка должна быть в диапазоне от 0 до 100%. Запустите программу снова и введите корректные данные.");
            return false;
        }
        this.discount = discount;
        return true;
    }


    // Метод, который проверяет дату истечения срока скидки
    public boolean setValidDate(String validDate) {
        try {
            this.validDate = LocalDate.parse(validDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты. Используйте формат yyyy-MM-dd. Запустите программу снова и введите корректные данные.");
            return false;
        }
    }


// Переопределение методов

    @Override
    public int getPriceProduct() {
        if (LocalDate.now().isAfter(validDate)) {
            System.out.println("Скидка на " + getNameProduct() + " истекла " + validDate);
            return super.getPriceProduct();
        }
        
        int discountedPrice = super.getPriceProduct() * (100 - discount) / 100;
        return discountedPrice;
    }

    @Override
    public String toString() {
        String base = super.toString();
        if (LocalDate.now().isAfter(validDate)) {
            return base + " (скидка истекла " + validDate + ")";
        }
        return base + " (скидка " + discount + "% до " + validDate + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountProduct that = (DiscountProduct) o;
        return discount == that.discount && validDate.equals(that.validDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discount, validDate);
    }
}