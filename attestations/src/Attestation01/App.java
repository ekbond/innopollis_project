package Attestation01;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        // Информация о Покупателе и продуктах будет вводится с клавиатуры        
        Scanner scanner = new Scanner(System.in, "cp866");

        // Списки для хранения покупателей и их денег, продуктов и их цена
        ArrayList<Person> people_money = new ArrayList<>();
        ArrayList<Product> product_price = new ArrayList<>();

        System.out.println("\nПриложение по обработке списка покупателей и продуктов");
        
        // Вводится информация о покупателях с клавиатуры 
        System.out.println("\nВведите информацию о покупателях(-е): " );
        System.out.println("Формат: Имя = Сумма. Вводить следующие данные через ;");

        String input_info  = scanner.nextLine();            // Запомнить данные, которые введены с клавиатуры

        String[] paths = input_info.split(";");         // Цикл указан без явного использования индекса
        for (String i : paths) {
            try {
                String[] words = i.trim().split("=");     // Парсинг входящий строки по символу =
                String name_person = words[0].trim();              // Убрать пробелы у имени покупателя
                int money_person = Integer.parseInt(words[1].trim());   // Преобразовать второе значение списка (деньги) в число
                people_money.add(new Person(name_person, money_person));   // Добавить в список значение [ (Имя, деньги), (Имя, деньги), ...]
            } catch (Exception ex) {
                System.exit(0);
            }
        }

        // Вводится информация о продуктах с клавиатуры 
        System.out.println("\nВведите информацию о продуктах: ");
        System.out.println("Формат: Название = Цена.  Вводить следующие данные через ;");
        
        String input_product_list = scanner.nextLine();            
        
        String[] paths_prod = input_product_list.split(";");
        
        for (String k : paths_prod) {
            try{
                String[] prod = k.trim().split("=");
                String name = prod[0].trim();
                int prod_price = Integer.parseInt(prod[1].trim());
                product_price.add(new Product(name, prod_price));
            } catch (Exception ex) {
                System.exit(0);
            }
        }
        // Тестирование промежуточного результата
        //System.out.println(people_money);
        //System.out.println(product_price);

        // Вводится информация о покупках с клавиатуры и обработка
        System.out.println("\nВведите покупки каждого покупателя: " ); 
        System.out.println("Формат: Имя - Товар (для завершения введите END)");
        while (true) {
            String input_person_product = scanner.nextLine();
            if (input_person_product.equalsIgnoreCase("END"))
            break;
            
            String[] PerPro = input_person_product.split("-");
            String personName = PerPro[0].trim();
            String productName = PerPro[1].trim();
            
            // Перебор по совпадениям для имен
            Person person = null;
            for (Person p : people_money) {
                if (p.getNameBuyer().equals(personName)) {
                    person = p;
                    break;
                }
            }

            // Перебор по совпадениям дли продуктов
            Product product = null;
            for (Product pr : product_price) {
                if (pr.getNameProduct().equals(productName)) {
                    product = pr;
                    break;
                }
            }            

            // Пытаемся купить продукт
            if (person.MoneyForBuying(product)) {
                System.out.println(person.getNameBuyer() + " купил " + product.getNameProduct());
            } else {
                System.out.println(person.getNameBuyer() + " не может позволить себе " + product.getNameProduct());

            }
        }

        System.out.println("\nИтоговые покупки:");
        for (Person person : people_money) {
            System.out.println(person);
        }
    }
}
