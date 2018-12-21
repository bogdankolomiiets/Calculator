package Calc;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

/** @author Bogdan */

class CalcFrame extends JFrame {

    public JPanel panel = new JPanel();
    private static JTextField textField = new JTextField();
    private JLabel label = new JLabel();
    private JLabel labelMC = new JLabel();
    private BigDecimal numOne = new BigDecimal(0);
    private BigDecimal numOneMem = new BigDecimal(0);
    private boolean memoryClean = false;
    private BigDecimal numTwo = new BigDecimal(0);
    private char symbol;
    private boolean math = false;
    SaveToHistory saveToHistory = new SaveToHistory();

    void showMainFrame() {
        super.setTitle("Калькулятор");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //JFrame is exactly in center
        super.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2) - 120, (Toolkit.getDefaultToolkit().getScreenSize().height/2) - 170);
        super.setResizable(false);
        super.setSize(235, 345);

        //Set icon to frame
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("calc.png"));
        super.setIconImage(icon.getImage());

        //Initialize the program components
        initComponents();
        super.setVisible(true);
    }

    private void initComponents() {

        panel.setLayout(null);

        //add listener MyKeyListener on panel
        panel.addKeyListener(new MyKeyListener());

        //set focus to panel
        panel.setFocusable(true);

        //Create menu for calc
        createMenu();

        //Initialize TextField and JLabel
        initTextField();
        initJLabel();
        initJLabelForButtonMC();

        //Create buttons
        createButton();

        //add panel to JFrame
        super.add(panel);
    }

    private void initTextField() {
        textField.setEnabled(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                if (textField.getText().length() <= 10) {
                    textField.setFont(new Font("Arial", Font.PLAIN, 20));
                } else if (textField.getText().length() > 10 && textField.getText().length() < 27) {
                    textField.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            }
        });
        textField.setText("0");
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setDisabledTextColor(Color.BLACK);
        textField.setBounds(2, 45, super.getWidth() - 10, 43);
        textField.setVisible(true);
        panel.add(textField);
    }

    private void initJLabel() {
        label.setText("0");
        label.setBounds(5, 23, super.getWidth() - 20, 25);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setVisible(true);
        panel.add(label);
    }

    private void initJLabelForButtonMC() {
        labelMC.setText("");
        labelMC.setBounds(5, 23, super.getWidth() - 20, 25);
        labelMC.setHorizontalAlignment(SwingConstants.LEFT);
        labelMC.setFont(new Font("Arial", Font.PLAIN, 16));
        labelMC.setVisible(true);
        panel.add(labelMC);
    }

    private void createMenu() {
        MenuForCalc menuPanel = new MenuForCalc();
        menuPanel.createMyMenuBar();
        panel.add(menuPanel.menuBar);
    }

    private void createButton() {

        //Create buttons
        Font myFont = new Font("Arial", Font.BOLD, 16);
        Color numButtonColor = Color.decode("#005a8e");
        Color funcButtonColor = Color.decode("#e17800");

        JButton buttonMC = new JButton();
        buttonMC.setBounds(5, 90, 40, 40);
        buttonMC.setBackground(funcButtonColor);
        buttonMC.setFont(myFont);
        buttonMC.setBorder(null);
        buttonMC.setText("MC");
        buttonMC.setActionCommand("buttonMC");
        buttonMC.addActionListener(new MyActionListener());
        panel.add(buttonMC);


        JButton buttonMMinus = new JButton();
        buttonMMinus.setBounds(50, 90, 40, 40);
        buttonMMinus.setText("M-");
        buttonMMinus.setBorder(null);
        buttonMMinus.setFont(myFont);
        buttonMMinus.setBackground(funcButtonColor);
        buttonMMinus.setActionCommand("buttonSubtractFromMemory");
        buttonMMinus.addActionListener(new MyActionListener());
        panel.add(buttonMMinus);

        JButton buttonMPlus = new JButton();
        buttonMPlus.setBounds(95, 90, 40, 40);
        buttonMPlus.setText("M+");
        buttonMPlus.setFont(myFont);
        buttonMPlus.setBorder(null);
        buttonMPlus.setBackground(funcButtonColor);
        buttonMPlus.setActionCommand("buttonAddToMemory");
        buttonMPlus.addActionListener(new MyActionListener());
        panel.add(buttonMPlus);

        JButton buttonPlusMinus = new JButton();
        buttonPlusMinus.setBounds(140, 90, 40, 40);
        buttonPlusMinus.setText("+/-");
        buttonPlusMinus.setBorder(null);
        buttonPlusMinus.setFont(myFont);
        buttonPlusMinus.setBackground(funcButtonColor);
        buttonPlusMinus.setActionCommand("buttonPlusMinus");
        buttonPlusMinus.addActionListener(new MyActionListener());
        panel.add(buttonPlusMinus);

        JButton buttonCE = new JButton();
        buttonCE.setBounds(185, 90, 40, 40);
        buttonCE.setText("CE/C");
        buttonCE.setBorder(null);
        buttonCE.setFont(myFont);
        buttonCE.setBackground(funcButtonColor);
        buttonCE.setActionCommand("buttonCE");
        buttonCE.addActionListener(new MyActionListener());
        panel.add(buttonCE);

        JButton buttonSeven = new JButton();
        buttonSeven.setBounds(5, 135, 40, 40);
        buttonSeven.setText("7");
        buttonSeven.setFont(myFont);
        buttonSeven.setBackground(numButtonColor);
        buttonSeven.setBorder(null);
        buttonSeven.setActionCommand("button7");
        buttonSeven.addActionListener(new MyActionListener());
        panel.add(buttonSeven);

        JButton buttonEight = new JButton();
        buttonEight.setBounds(50, 135, 40, 40);
        buttonEight.setText("8");
        buttonEight.setFont(myFont);
        buttonEight.setBackground(numButtonColor);
        buttonEight.setBorder(null);
        buttonEight.setActionCommand("button8");
        buttonEight.addActionListener(new MyActionListener());
        panel.add(buttonEight);

        JButton buttonNine = new JButton();
        buttonNine.setBounds(95, 135, 40, 40);
        buttonNine.setText("9");
        buttonNine.setFont(myFont);
        buttonNine.setBackground(numButtonColor);
        buttonNine.setBorder(null);
        buttonNine.setActionCommand("button9");
        buttonNine.addActionListener(new MyActionListener());
        panel.add(buttonNine);

        JButton buttonPercent = new JButton();
        buttonPercent.setBounds(140, 135, 40, 40);
        buttonPercent.setText("%");
        buttonPercent.setFont(myFont);
        buttonPercent.setBackground(funcButtonColor);
        buttonPercent.setBorder(null);
        buttonPercent.setActionCommand("buttonPercent");
        buttonPercent.addActionListener(new MyActionListener());
        panel.add(buttonPercent);

        JButton buttonSQRT = new JButton();
        buttonSQRT.setBounds(185, 135, 40, 40);
        buttonSQRT.setText("√");
        buttonSQRT.setFont(myFont);
        buttonSQRT.setBackground(funcButtonColor);
        buttonSQRT.setBorder(null);
        buttonSQRT.setActionCommand("buttonSqrt");
        buttonSQRT.addActionListener(new MyActionListener());
        panel.add(buttonSQRT);

        JButton buttonFour = new JButton();
        buttonFour.setBounds(5, 180, 40, 40);
        buttonFour.setText("4");
        buttonFour.setFont(myFont);
        buttonFour.setBackground(numButtonColor);
        buttonFour.setBorder(null);
        buttonFour.setActionCommand("button4");
        buttonFour.addActionListener(new MyActionListener());
        panel.add(buttonFour);

        JButton buttonFive = new JButton();
        buttonFive.setBounds(50, 180, 40, 40);
        buttonFive.setText("5");
        buttonFive.setFont(myFont);
        buttonFive.setBackground(numButtonColor);
        buttonFive.setBorder(null);
        buttonFive.setActionCommand("button5");
        buttonFive.addActionListener(new MyActionListener());
        panel.add(buttonFive);

        JButton buttonSix = new JButton();
        buttonSix.setBounds(95, 180, 40, 40);
        buttonSix.setText("6");
        buttonSix.setFont(myFont);
        buttonSix.setBackground(numButtonColor);
        buttonSix.setBorder(null);
        buttonSix.setActionCommand("button6");
        buttonSix.addActionListener(new MyActionListener());
        panel.add(buttonSix);

        JButton buttonMultiply = new JButton();
        buttonMultiply.setBounds(140, 180, 40, 40);
        buttonMultiply.setText("*");
        buttonMultiply.setFont(myFont);
        buttonMultiply.setBackground(numButtonColor);
        buttonMultiply.setBorder(null);
        buttonMultiply.setActionCommand("buttonMultiply");
        buttonMultiply.addActionListener(new MyActionListener());
        panel.add(buttonMultiply);

        JButton buttonDivide = new JButton();
        buttonDivide.setBounds(185, 180, 40, 40);
        buttonDivide.setText("/");
        buttonDivide.setFont(myFont);
        buttonDivide.setBackground(numButtonColor);
        buttonDivide.setBorder(null);
        buttonDivide.setActionCommand("buttonDivide");
        buttonDivide.addActionListener(new MyActionListener());
        panel.add(buttonDivide);

        JButton buttonOne = new JButton();
        buttonOne.setBounds(5, 225, 40, 40);
        buttonOne.setText("1");
        buttonOne.setFont(myFont);
        buttonOne.setBackground(numButtonColor);
        buttonOne.setBorder(null);
        buttonOne.setActionCommand("button1");
        buttonOne.addActionListener(new MyActionListener());
        panel.add(buttonOne);

        JButton buttonTwo = new JButton();
        buttonTwo.setBounds(50, 225, 40, 40);
        buttonTwo.setText("2");
        buttonTwo.setFont(myFont);
        buttonTwo.setBackground(numButtonColor);
        buttonTwo.setBorder(null);
        buttonTwo.setActionCommand("button2");
        buttonTwo.addActionListener(new MyActionListener());
        panel.add(buttonTwo);

        JButton buttonThree = new JButton();
        buttonThree.setBounds(95, 225, 40, 40);
        buttonThree.setText("3");
        buttonThree.setFont(myFont);
        buttonThree.setBackground(numButtonColor);
        buttonThree.setBorder(null);
        buttonThree.setActionCommand("button3");
        buttonThree.addActionListener(new MyActionListener());
        panel.add(buttonThree);

        JButton buttonPlus = new JButton();
        buttonPlus.setBounds(140, 225, 40, 85);
        buttonPlus.setText("+");
        buttonPlus.setFont(myFont);
        buttonPlus.setBackground(numButtonColor);
        buttonPlus.setBorder(null);
        buttonPlus.setActionCommand("buttonPlus");
        buttonPlus.addActionListener(new MyActionListener());
        panel.add(buttonPlus);

        JButton buttonMinus = new JButton();
        buttonMinus.setBounds(185, 225, 40, 40);
        buttonMinus.setText("-");
        buttonMinus.setFont(myFont);
        buttonMinus.setBackground(numButtonColor);
        buttonMinus.setBorder(null);
        buttonMinus.setActionCommand("buttonMinus");
        buttonMinus.addActionListener(new MyActionListener());
        panel.add(buttonMinus);

        JButton buttonZero = new JButton();
        buttonZero.setBounds(5, 270, 40, 40);
        buttonZero.setText("0");
        buttonZero.setFont(myFont);
        buttonZero.setBackground(numButtonColor);
        buttonZero.setBorder(null);
        buttonZero.setActionCommand("buttonZero");
        buttonZero.addActionListener(new MyActionListener());
        panel.add(buttonZero);

        JButton buttonBSP = new JButton();
        buttonBSP.setBounds(50, 270, 40, 40);
        buttonBSP.setText("←");
        buttonBSP.setFont(myFont);
        buttonBSP.setBackground(funcButtonColor);
        buttonBSP.setBorder(null);
        buttonBSP.setActionCommand("buttonBSP");
        buttonBSP.addActionListener(new MyActionListener());
        panel.add(buttonBSP);

        JButton buttonDot = new JButton();
        buttonDot.setBounds(95, 270, 40, 40);
        buttonDot.setText(".");
        buttonDot.setFont(myFont);
        buttonDot.setBackground(numButtonColor);
        buttonDot.setBorder(null);
        buttonDot.setActionCommand("buttonDot");
        buttonDot.addActionListener(new MyActionListener());
        panel.add(buttonDot);

        JButton buttonEqual = new JButton();
        buttonEqual.setBounds(185, 270, 40, 40);
        buttonEqual.setText("=");
        buttonEqual.setFont(myFont);
        buttonEqual.setBackground(numButtonColor);
        buttonEqual.setBorder(null);
        buttonEqual.setActionCommand("buttonEqual");
        buttonEqual.addActionListener(new MyActionListener());
        panel.add(buttonEqual);
    }

    private void editTextfield(double i) {
        if (math == true) {
            textField.setText("0");
            math = false;
        }
        if (math == false) {

            String temp = textField.getText();
            if (temp.equals("0") || temp.equals("-0")) {
                textField.setText(String.valueOf(i).substring(0, 1));
            } else if (temp.length() <= 16) {
                textField.setText(temp + String.valueOf(i).substring(0, 1));
            }
        }
    }

    private void buttonBSP() {
        if (textField.getText().length() > 1) {
            textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
        }
        else {
            textField.setText("0");
        }
    }

    //add char '-' in textField
    private void buttonPlusMinus() {
        if (!textField.getText().contains("-")) {
            textField.setText("-" + textField.getText());
        } else textField.setText(textField.getText().substring(1, textField.getText().length()));
    }

    private void buttonDot() {
        String temp = textField.getText();
        if (!temp.contains(".") && temp.length() < 16) {
            textField.setText(temp + ".");
        }
    }

    private void addChar(char c) {
        String temp = label.getText();

        /*If press the arithmetic button, but the previous action has not been perform,
        the perform the action and then add a new symbol of arithmetic*/
        if (temp.length() > 3 && !textField.getText().equals("0")) {
            if (temp.substring(temp.length() - 3, temp.length() - 1).equals("  ")) {
                math = false;
                buttonEqual();
                label.setText((numOne.stripTrailingZeros().toPlainString()) + "  " + c);
                textField.setText("0");
            }
        }
        //change symbol +, -, *, or / on new symbol
        else if (!temp.equals("0") && textField.getText().equals("0")) {
            label.setText(temp.substring(0, temp.length() - 1) + c);
        }

        symbol = c;

        //if text in label equals 0, then transfer text from textField into label
        if (temp.equals("0") && !textField.getText().equals("0")){
            label.setText(textField.getText() + "  " + c);
            textField.setText("0");
        }
    }

    private void buttonEqual() {

        if (label.getText().length() > 3 && math == false) {
            arithmetic();
            math = true;
        }

        //if previous action was successful - repeat the action if it's needed
        else if (math == true && !numTwo.toPlainString().equals("0")) {
            repeatArithmetic();
        }

        label.setText("0");
        //Delete zeros to the right of the floating point, if there are no other digits
        //Assign a value to the textField. If the length of the value is greater than 26, the value is rounded

        if (numOne.stripTrailingZeros().toPlainString().length() >= 27) {
            try {
                numOne = new BigDecimal(numOne.stripTrailingZeros().toString());
                textField.setText(BigDecimal.valueOf(numOne.stripTrailingZeros().doubleValue()).toString());
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Число слишком большое, или идет к бесконечности!!!\nВсе значения будут обнулены..", "Ошибка обработки", JOptionPane.ERROR_MESSAGE, null);
                clear();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.toString(), "Ошибка обработки!!!", JOptionPane.ERROR_MESSAGE, null);
                clear();
            }
        } else {
            textField.setText(numOne.stripTrailingZeros().toPlainString());
        }
    }

    //Method for transfer values to the SaveToHistory class
    //append the math action to the history
    public void transferValuesForSaveToHistory(BigDecimal numOneBeforeChange) {

        //if value numOneBeforeChange is greater then 26 - transform a value to E+ format
        if (numOneBeforeChange.stripTrailingZeros().toPlainString().length() >= 27) {
            saveToHistory.addValuesToTxtFile(BigDecimal.valueOf(numOneBeforeChange.stripTrailingZeros().doubleValue()).toString(), BigDecimal.valueOf(numTwo.stripTrailingZeros().doubleValue()).toString(), BigDecimal.valueOf(numOne.stripTrailingZeros().doubleValue()).toString(), symbol);
        }
        //if a value numOneBeforeChange less 27, then numOneBeforeChange keep without change
        else {
            //if result of the math action is greater 26 - then field numOne transform to E+ format
            if (numOne.stripTrailingZeros().toPlainString().length() >= 27)
            saveToHistory.addValuesToTxtFile(numOneBeforeChange.stripTrailingZeros().toPlainString(), numTwo.stripTrailingZeros().toPlainString(), BigDecimal.valueOf(numOne.stripTrailingZeros().doubleValue()).toString(), symbol);
            //a value without transform to E+ format
            else saveToHistory.addValuesToTxtFile(numOneBeforeChange.stripTrailingZeros().toPlainString(), numTwo.stripTrailingZeros().toPlainString(), numOne.stripTrailingZeros().toPlainString(), symbol);

        }
    }

    private void clear(){
        numOne = new BigDecimal(0);
        numTwo = new BigDecimal(0);
        label.setText("0");
        textField.setText("0");
        math = false;
    }

    private void buttonMC(){
        if (memoryClean == false) {
        numOneMem = numOneMem.setScale(15, RoundingMode.HALF_UP);
        textField.setText(numOneMem.stripTrailingZeros().toPlainString());

            memoryClean = true;
        }
        else {
            labelMC.setText("");
            numOneMem = new BigDecimal(0);
            textField.setText("0");
        }

    }

    private void buttonAddToMemory(){
        Double s = Double.valueOf(textField.getText());
        numOneMem = numOneMem.add(BigDecimal.valueOf(s));
        labelMC.setText("M+");
        textField.setText("0");
    }

    private void buttonSubtractFromMemory(){
        Double s = Double.valueOf(textField.getText());
        numOneMem = numOneMem.subtract(BigDecimal.valueOf(s));
        labelMC.setText("M-");
        textField.setText("0");
    }

    private void percent(){

        Double s = Double.valueOf(textField.getText());
        numTwo = new BigDecimal(label.getText().substring(0, label.getText().length() - 3)).divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(s));
        textField.setText(numTwo.stripTrailingZeros().toPlainString());
    }

    private void sqrt(){
        numTwo = new BigDecimal(Math.sqrt(Double.valueOf(textField.getText())));
        numTwo = numTwo.setScale(15, RoundingMode.HALF_UP);
        textField.setText(numTwo.stripTrailingZeros().toPlainString());
    }

    private void arithmetic() {

        try {
        numOne = new BigDecimal(label.getText().substring(0, label.getText().length() - 3));
        numTwo = new BigDecimal(textField.getText());

        //Assign the initial value of the numOne field of the temporary numOneBeforeChange field
        BigDecimal numOneBeforeChange = new BigDecimal(label.getText().substring(0, label.getText().length() - 3));

        numOne = numOne.setScale(15, RoundingMode.HALF_UP);
        numTwo = numTwo.setScale(15, RoundingMode.HALF_UP);

        if (symbol == '+') {
            numOne = numOne.add(numTwo);
        }
        if (symbol == '-') {
            numOne = numOne.subtract(numTwo);
        }
        if (symbol == '*') {
            numOne = numOne.multiply(numTwo);
        }
        if (symbol == '/') {
            try {
                numOne = numOne.divide(numTwo, RoundingMode.HALF_UP);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Деление на 0 запрещено", "Ошибка!!!", JOptionPane.ERROR_MESSAGE, null);
                clear();
            }
        }

        try{
            //call the method for append math action to a history
            transferValuesForSaveToHistory(numOneBeforeChange);
        } catch (Exception e){

        }

        //the memory for RC button
        memoryClean = false;
        numOneMem = numOne;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Повидимому Вы вставили НЕ ЧИСЛО", "Ошибка!!!", JOptionPane.ERROR_MESSAGE, null);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка!!!", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    private void repeatArithmetic() {
        //Assign the initial value of the numOne field of the temporary numOneBeforeChange field
        BigDecimal numOneBeforeChange = new BigDecimal(numOne.toString());

        if (symbol == '+') {
            numOne = numOne.add(numTwo);
        }
        if (symbol == '-') {
            numOne = numOne.subtract(numTwo);
        }
        if (symbol == '*') {
            numOne = numOne.multiply(numTwo);
        }
        if (symbol == '/') {
            try {
                numOne = numOne.divide(numTwo, RoundingMode.HALF_UP);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Деление на 0 запрещено", "Ошибка!!!", JOptionPane.ERROR_MESSAGE, null);
                clear();
            }

        }

        try{
            //call the method for append math action to a history
            transferValuesForSaveToHistory(numOneBeforeChange);
        } catch (Exception e){

        }


        //the memory for RC button
        memoryClean = false;
        numOneMem = numOne;
    }


    public String getTextFieldText(){
        return textField.getText();
    }

    public void setTextFieldText(String textFieldText){
        textField.setText(textFieldText);
    }

    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("button1"))
            {
                editTextfield(1);
            }
            if (e.getActionCommand().equals("button2"))
            {
                editTextfield(2);
            }
            if (e.getActionCommand().equals("button3"))
            {
                editTextfield(3);
            }
            if (e.getActionCommand().equals("button4"))
            {
                editTextfield(4);
            }
            if (e.getActionCommand().equals("button5"))
            {
                editTextfield(5);
            }
            if (e.getActionCommand().equals("button6"))
            {
                editTextfield(6);
            }
            if (e.getActionCommand().equals("button7"))
            {
                editTextfield(7);
            }
            if (e.getActionCommand().equals("button8"))
            {
                editTextfield(8);
            }
            if (e.getActionCommand().equals("button9"))
            {
                editTextfield(9);
            }
            if (e.getActionCommand().equals("buttonZero"))
            {
                editTextfield(0);
            }
            if (e.getActionCommand().equals("buttonBSP"))
            {
                buttonBSP();
            }
            if (e.getActionCommand().equals("buttonPlusMinus"))
            {
                buttonPlusMinus();
            }
            if (e.getActionCommand().equals("buttonDot"))
            {
                buttonDot();
            }
            if (e.getActionCommand().equals("buttonPlus")) {
                addChar('+');
            }
            if (e.getActionCommand().equals("buttonMultiply")){
                addChar('*');
            }
            if (e.getActionCommand().equals("buttonDivide")){
                addChar('/');
            }
            if (e.getActionCommand().equals("buttonMinus")){
                addChar('-');
            }
            if (e.getActionCommand().equals("buttonEqual")){
                buttonEqual();
            }
            if (e.getActionCommand().equals("buttonCE"))
            {
                clear();
            }
            if (e.getActionCommand().equals("buttonPercent")){
                percent();
            }
            if (e.getActionCommand().equals("buttonSqrt")){
                sqrt();
            }
            if (e.getActionCommand().equals("buttonMC")){
                buttonMC();
            }
            if (e.getActionCommand().equals("buttonAddToMemory")){
                buttonAddToMemory();
            }
            if (e.getActionCommand().equals("buttonSubtractFromMemory")){
                buttonSubtractFromMemory();
            }

            panel.requestFocus();
        }
    }

    class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == '1'){
                editTextfield(1);
            }
            if (e.getKeyChar() == '2') {
                editTextfield(2);
            }
            if (e.getKeyChar() == '3'){
                editTextfield(3);
            }
            if (e.getKeyChar() == '4') {
                editTextfield(4);
            }
            if (e.getKeyChar() == '5') {
                editTextfield(5);
            }
            if (e.getKeyChar() == '6') {
                editTextfield(6);
            }
            if (e.getKeyChar() == '7') {
                editTextfield(7);
            }
            if (e.getKeyChar() == '8'){
                editTextfield(8);
            }
            if (e.getKeyChar() == '9') {
                editTextfield(9);
            }
            if (e.getKeyChar() == '0') {
                editTextfield(0);
            }
            if (e.getKeyChar() == '.' || e.getKeyChar() == ','){
                buttonDot();
            }
            if (e.getKeyChar() == '+'){
                addChar('+');
            }
            if (e.getKeyChar() == '-'){
                addChar('-');
            }
            if (e.getKeyChar() == '*'){
                addChar('*');
            }
            if (e.getKeyChar() == '/'){
                addChar('/');
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 10){
                buttonEqual();
            }
            if (e.getKeyCode() == 8){
                buttonBSP();
            }
            if (e.getKeyCode() == 67 || e.getKeyCode() == 127){
                clear();
            }
            if (e.getKeyCode() == 80){
                percent();
            }
            if (e.getKeyCode() == 83){
                sqrt();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

}