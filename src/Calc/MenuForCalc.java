package Calc;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

public class MenuForCalc extends CalcFrame {
    JMenuBar menuBar;
    JMenu jmenuFile;
    JMenu jmenuEdit;
    JMenu jmenuAbout;
    JMenuItem jMenuItem;

    public void createMyMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setEnabled(true);
        menuBar.setBounds(0, 0, 240, 25);
        createMenu();
        menuBar.setVisible(true);
    }

    private void createMenu() {
        jmenuFile = new JMenu("Файл");
        jmenuEdit = new JMenu("Правка");
        jmenuAbout = new JMenu("Справка");
        menuBar.add(jmenuFile);
        menuBar.add(jmenuEdit);
        menuBar.add(jmenuAbout);

        createMenuItem();
    }

    private void createMenuItem() {
        jMenuItem = new JMenuItem("История");
        jMenuItem.setActionCommand("history");
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        jMenuItem.addActionListener(new MyActionListener());
        jmenuFile.add(jMenuItem);

        jMenuItem = new JMenuItem("Выход");
        jMenuItem.setActionCommand("exit");
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        jMenuItem.addActionListener(new MyActionListener());
        jmenuFile.add(jMenuItem);

        jMenuItem = new JMenuItem("Копировать");
        jMenuItem.setActionCommand("copy");
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        jMenuItem.addActionListener(new MyActionListener());
        jmenuEdit.add(jMenuItem);

        jMenuItem = new JMenuItem("Вставить");
        jMenuItem.setActionCommand("paste");
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        jMenuItem.addActionListener(new MyActionListener());
        jmenuEdit.add(jMenuItem);

        jMenuItem = new JMenuItem("О программе");
        jMenuItem.setActionCommand("about");
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        jMenuItem.addActionListener(new MyActionListener());
        jmenuAbout.add(jMenuItem);
    }

    class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("history")){
                HistoryFrame history = new HistoryFrame();
                history.createHistoryFrame();
            }
            if (e.getActionCommand().equals("exit")){
                System.exit(0);
            }
            //Copy text from textField to clipboard
            if (e.getActionCommand().equals("copy")){
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(getTextFieldText()), null);
            }
            //Paste text from textField to clipboard
            if (e.getActionCommand().equals("paste")){
                try {
                    //Get text from Clipboard
                    String temp = Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
                    String result = "";
                    for (char c : temp.toCharArray()) {
                        if (Character.isDigit(c) || c == '+' || c == '-' || c == 'E' || c == '.' || c == ',') {
                            result += c;
                        }
                    }
                    setTextFieldText(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString(), "Произошла ошибка", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            if (e.getActionCommand().equals("about")){
                AboutFrame aboutFrame = new AboutFrame();
                aboutFrame.createAboutFrame();
            }
        }
    }
}
