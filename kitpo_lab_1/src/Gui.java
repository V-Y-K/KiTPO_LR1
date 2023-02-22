import data_structure.BinaryTree;
import factory.Factory;
import type.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Set;

public class Gui extends JPanel implements ActionListener {
    private JTextArea inputArea;
    private JComboBox dataTypes;
    private JButton insertBtn;
    private JButton saveBtn;
    private JButton balanceBtn;
    private JButton loadBtn;
    private JButton findByIdBtn;
    private JButton deleteByIDBtn;
    private JButton clearBtn;
    private JTextField findByIdField;
    private JTextField deleteByIdField;
    private JLabel findedValue;

    private UserType userType;
    private Factory factory;
    private BinaryTree binaryTree;
    private String type;

    private final String INTEGER_FILE = "Integer.dat";
    private final String INTEGER_ARRAY_FILE = "IntegerArray.dat";

    public Gui() {
        //construct preComponents
        String[] dataTypesItems = {};

        this.type = "Integer";
        this.factory = new Factory();
        this.userType = this.factory.getBuilderByName(type);
        this.binaryTree = new BinaryTree(userType.getTypeComparator());

        Set<String> types = factory.getTypeNameList();
        JComboBox<String> typeList = new JComboBox<>(Factory.getTypeNameList().toArray(new String[0]));

        //construct components
        dataTypes = typeList;
        inputArea = new JTextArea(5, 5);
        insertBtn = new JButton("Вставить");
        saveBtn = new JButton("Сохранить");
        balanceBtn = new JButton("Сбалансировать");
        loadBtn = new JButton("Загрузить");
        findByIdBtn = new JButton("Найти по ID");
        deleteByIDBtn = new JButton("Удалить по ID");
        clearBtn = new JButton("Очистить");
        findByIdField = new JTextField(5);
        deleteByIdField = new JTextField(5);
        findedValue = new JLabel("");

        //adjust size and set layout
        setPreferredSize(new Dimension(944, 669));
        setLayout(null);
        inputArea.setEnabled(false);
        inputArea.setFont(new Font("Arial", Font.BOLD, 14));
        findedValue.setFont(new Font("Arial", Font.BOLD, 14));

        //add components
        add(inputArea);
        add(dataTypes);
        add(insertBtn);
        add(saveBtn);
        add(balanceBtn);
        add(loadBtn);
        add(findByIdBtn);
        add(deleteByIDBtn);
        add(clearBtn);
        add(findByIdField);
        add(deleteByIdField);
        add(findedValue);

        //set component bounds (only needed by Absolute Positioning)
        inputArea.setBounds(10, 15, 920, 475);
        dataTypes.setBounds(10, 510, 150, 30);
        insertBtn.setBounds(235, 510, 145, 35);
        saveBtn.setBounds(235, 590, 145, 35);
        balanceBtn.setBounds(235, 550, 145, 35);
        loadBtn.setBounds(235, 630, 145, 35);
        findByIdBtn.setBounds(455, 510, 145, 35);
        deleteByIDBtn.setBounds(455, 550, 145, 35);
        clearBtn.setBounds(455, 590, 145, 35);
        findByIdField.setBounds(625, 510, 145, 35);
        deleteByIdField.setBounds(625, 550, 145, 35);
        findedValue.setBounds(805, 530, 100, 70);

        dataTypes.addActionListener(this);
        insertBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        balanceBtn.addActionListener(this);
        loadBtn.addActionListener(this);
        findByIdBtn.addActionListener(this);
        deleteByIDBtn.addActionListener(this);
        clearBtn.addActionListener(this);
    }

    public void showGui() {
        JFrame frame = new JFrame("Lab_1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Gui());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Раскрывающийся список
        if (e.getSource() == dataTypes) {
            JComboBox comboBox = (JComboBox) e.getSource();
            String item = (String) comboBox.getSelectedItem();
            if (this.type != item) {
                this.type = item;
                this.userType = this.factory.getBuilderByName(type);
                this.binaryTree = new BinaryTree(userType.getTypeComparator());
                updateTextField();
            }

        }

        //Вставка в дерево
        if (e.getSource() == insertBtn) {
            this.binaryTree.insert(userType.create());
            updateTextField();
        }

        //Балансировка дерева
        if (e.getSource() == balanceBtn) {
            this.binaryTree = this.binaryTree.balance();
            updateTextField();
        }

        //Сохранение дерева
        if (e.getSource() == saveBtn) {
            if (Objects.equals(this.type, "Integer")) {
                this.binaryTree.saveTree(this.userType, INTEGER_FILE);
                JOptionPane.showMessageDialog(null,
                        "Бинарное дерево успешно сохранено в \"" + INTEGER_FILE + "\"!");
            } else if (Objects.equals(this.type, "IntegerArray")) {
                this.binaryTree.saveTree(this.userType, INTEGER_ARRAY_FILE);
                JOptionPane.showMessageDialog(null,
                        "Бинарное дерево успешно сохранено в \"" + INTEGER_ARRAY_FILE + "\"!");
            }
        }

        //Загрузка дерева
        if (e.getSource() == loadBtn) {
            if (Objects.equals(this.type, "Integer")) {
                this.binaryTree = this.binaryTree.loadTree(this.userType, INTEGER_FILE);
                JOptionPane.showMessageDialog(null,
                        "Бинарное дерево успешно загружено!");
                updateTextField();
            } else if (Objects.equals(this.type, "IntegerArray")) {
                this.binaryTree = this.binaryTree.loadTree(this.userType, INTEGER_ARRAY_FILE);
                JOptionPane.showMessageDialog(null,
                        "Бинарное дерево успешно загружено!");
                updateTextField();
            }
        }

        //Поиск по ID
        if (e.getSource() == findByIdBtn) {
            if (findByIdField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Пустое поле! Введите значение!");
                return;
            }
            findedValue.setText(
                    this.binaryTree
                            .findNodeByIndex(Integer.parseInt(findByIdField.getText()))
                            .toString());
        }

        //Удаление по ID
        if (e.getSource() == deleteByIDBtn) {
            if (deleteByIdField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Пустое поле! Введите значение!");
                return;
            }
            this.binaryTree.removeByIndex(Integer.parseInt(deleteByIdField.getText()));
            updateTextField();
        }

        //Очистка дерева
        if (e.getSource() == clearBtn) {
            binaryTree.clearTree();
            findedValue.setText("");
            updateTextField();
        }
    }

    private void updateTextField() {
        inputArea.setText(this.binaryTree.toString());
    }
}
