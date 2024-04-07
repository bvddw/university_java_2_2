import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws Exception {
    // a) Отримати об'єкт Class трьома різними способами
        // Спосіб 1: метод getClass() об'єкта
        Class<?> stringClass1 = "test".getClass();
        System.out.println(stringClass1);

        // Спосіб 2: ключове слово .class
        Class<?> stringClass2 = String.class;
        System.out.println(stringClass2);

        // Спосіб 3: статичний метод forName() класу Class
        Class<?> stringClass3 = Class.forName("java.lang.String");
        System.out.println(stringClass3);

    // b) Вивести усі модифікатори класу String
        System.out.println();
        int modifiers = stringClass1.getModifiers();
        System.out.println("Modifiers of String class: " + Modifier.toString(modifiers));

    // c) Вивести інформацію про всі public конструктори класу String
        System.out.println();
        Constructor<?>[] constructors = stringClass1.getConstructors();
        System.out.println("Public constructors of String class:");
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

    // d) Вивести інформацію про всі поля класу String
        System.out.println();
        Field[] fields = stringClass1.getDeclaredFields();
        System.out.println("Fields of String class:");
        for (Field field : fields) {
            System.out.println(field);
        }

    // e) Вивести інформацію про всі public методи класу String
        System.out.println();
        Method[] methods = stringClass1.getMethods();
        System.out.println("Public methods of String class:");
        for (Method method : methods) {
            System.out.println(method);
        }

    // f) Викликати будь-який із методів та роздрукувати результат
        System.out.println();
        String str = "Hello, World!".concat("\nSuch a good day!");
        System.out.println(str);
    }
}