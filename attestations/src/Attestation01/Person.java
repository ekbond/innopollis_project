package Attestation01;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Person {
    
    // Поля
    // (Условие: Поля сделать private)
    private String buyer;
    private int amount_money;
    private List<Product> shopping_bag;

    // Конструктор класса Покупатель    
    public Person(String buyer, int amount_money) {
        if (!setNameBuyer(buyer) || !setAmountMoney(amount_money)) {
            System.exit(0);
        }
        this.shopping_bag = new ArrayList<>();
    }

    // Свойства (геттеры и сеттеры)
    public String getNameBuyer() {
        return buyer;
    }
    public int getAmount_money() {
        return amount_money;
    }
    public boolean setNameBuyer(String buyer) {
        if (buyer == null || buyer.trim().isEmpty()) {
            System.out.println("Имя не может быть пустым");
            return false;
        }
        if (buyer.length() < 3) {
            System.out.println("Имя не может быть короче 3 символов");
            return false;
        }
        this.buyer = buyer;
        return true;
    }
    public boolean setAmountMoney(int amount_money) {
        if (amount_money < 0) {
            System.out.println("Деньги не могут быть отрицательными");
            return false;
        }
        this.amount_money = amount_money;
        return true;
    }


    // Метод для покупки товара, product - товар для покупки
    public boolean MoneyForBuying(Product product) {
        if (product.getPriceProduct() <= amount_money) {    // Сравниваем цену продукта из класса Продкуты
            amount_money -= product.getPriceProduct();      // Вычисляем (вычитаем) цену продукта из суммарных денег
            shopping_bag.add(product);                      // Добавляем продукт в корзину
            return true;
        }
        return false;
    }

    // Переопределение методов
    @Override
    public String toString() {
        if (shopping_bag.isEmpty()) {
            return buyer + " - Ничего не куплено";
        }
        return buyer + " - " + shopping_bag.toString().replace("[", "").replace("]", "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o; // Привести Object к типу Person
        
        // Осуществляется сравнение всех полей, которые были указаны в начале кода
        return this.buyer.equals(person.buyer) && this.amount_money == person.amount_money && this.shopping_bag.equals(person.shopping_bag);
    }

    @Override
    public int hashCode() {
    return Objects.hash(buyer, amount_money, shopping_bag);
    }

}