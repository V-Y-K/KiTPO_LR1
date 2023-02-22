import data_structure.BinaryTree;
import factory.Factory;
import type.UserType;

public class Main {
    private static String INTEGER_FILE = "Integer.dat";
    private static String INTEGER_ARRAY_FILE = "IntegerArray.dat";

    public static void main(String[] args) {
        //Тестирование типа Integer
        UserType userType = Factory.getBuilderByName("Integer");
        assert userType != null;
        BinaryTree binaryTree = new BinaryTree(userType.getTypeComparator());
        testTree(binaryTree, userType, INTEGER_FILE);

        //Тестирование типа IntegerArray
        userType = Factory.getBuilderByName("IntegerArray");
        assert userType != null;
        binaryTree = new BinaryTree(userType.getTypeComparator());
        testTree(binaryTree, userType, INTEGER_ARRAY_FILE);

        //Вызов графического интерфейса
        Gui gui = new Gui();
        gui.showGui();
    }

    private static void testTree(BinaryTree binaryTree, UserType userType, String fileName) {
        try {
            System.out.println("Вставка значений типа " + userType.typeName());
            binaryTree.insert(userType.create());
            binaryTree.insert(userType.create());
            binaryTree.insert(userType.create());
            binaryTree.insert(userType.create());
            binaryTree.insert(userType.create());
            binaryTree.insert(userType.create());
            binaryTree.insert(userType.create());
            binaryTree.insert(userType.create());
            binaryTree.insert(userType.create());
            binaryTree.insert(userType.create());
            binaryTree.printTree();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            binaryTree.saveTree(userType, fileName);
            System.out.println("Сохранение прошло успешно!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("Индекс 5 = " + binaryTree.findNodeByIndex(5).toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("После удаления по индексу 5:");
            binaryTree.removeByIndex(5);
            binaryTree.printTree();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            binaryTree.clearTree();
            System.out.println("Очистка дерева");
            binaryTree.printTree();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            binaryTree = binaryTree.loadTree(userType, fileName);
            System.out.println("Загрузка произошла успешно!");
            binaryTree.printTree();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            binaryTree = binaryTree.balance();
            System.out.println("Балансировка прошла успешно!");
            binaryTree.printTree();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("Поперечный обход дерева:");
            binaryTree.forEachInOrder(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}