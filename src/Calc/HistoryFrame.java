package Calc;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class HistoryFrame extends JFrame {
    /**Create HistoryFrame*/
    void createHistoryFrame(){
        super.setTitle("История рассчетов");
        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        super.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-200, 100);
        super.setResizable(true);
        super.setLayout(new GridLayout());

        //Set icon to frame
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("calc.png"));
        super.setIconImage(icon.getImage());

        //Initialize a components
        initComponents();
        super.pack();
        super.setVisible(true);
    }

    private void initComponents(){
        //Menu for mouse button
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem copyToClipboard = new JMenuItem("Копировать");;

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(440, 600));
        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setFocusable(true);

        //New panel for OkButton
        JPanel panelForOKButton = new JPanel();
        panelForOKButton.setLayout(new FlowLayout());

        //Create scrollPane for jTextArea
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Create the button OkButton
        JButton OkButton = new JButton("OK");
        OkButton.setToolTipText("Enter button");
        OkButton.setVisible(true);

        //Add action listener for buttonOk
        OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryFrame.super.setVisible(false);
            }
        });

        //Add Key listener for jTextArea
        jTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10){
                    HistoryFrame.super.setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        copyToClipboard.addMouseListener(new MouseListener() {
                                             @Override
                                             public void mouseClicked(MouseEvent e) {
                                                 try {
                                                     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(jTextArea.getSelectedText()), null);
                                                 } catch (Exception ex) {
                                                     JOptionPane.showMessageDialog(null, ex.getMessage(), "Произошла ошибка. Текст не скопирован", JOptionPane.ERROR_MESSAGE, null);
                                                 } finally {
                                                     popupMenu.setVisible(false);
                                                 }
                                             }

                                             @Override
                                             public void mousePressed(MouseEvent e) {

                                             }

                                             @Override
                                             public void mouseReleased(MouseEvent e) {

                                             }

                                             @Override
                                             public void mouseEntered(MouseEvent e) {
                                                 copyToClipboard.setBackground(Color.decode("#87b8c1"));
                                             }

                                             @Override
                                             public void mouseExited(MouseEvent e) {
                                                 copyToClipboard.setBackground(Color.decode("#e1e2e3"));
                                             }
                                         });

        jTextArea.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == 3) {
                            popupMenu.setBorder(null);
                            popupMenu.add(copyToClipboard);
                            popupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
                            popupMenu.setVisible(true);
                        }
                        if(e.getButton()==1 && popupMenu.isVisible()){
                            popupMenu.setVisible(false);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });

        //Hide the popup menu if History frame lost the focus
        super.addWindowFocusListener(new WindowFocusListener() {
                                         @Override
                                         public void windowGainedFocus(WindowEvent e) {

                                         }

                                         @Override
                                         public void windowLostFocus(WindowEvent e) {
                                             if (popupMenu.isVisible()){
                                                 popupMenu.setVisible(false);
                                             }
                                         }
                                     });

        /**Get the data of arithmetic operations from the history.txt file and pass them to jTextArea*/
        try {
            File file = new File("history.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp;
            int i = 1;
            while ((temp = reader.readLine()) != null){
                jTextArea.append(i + ":  " + temp + "\n");
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Произошла ошибка!!!", JOptionPane.ERROR_MESSAGE, null);
        }

        panelForOKButton.add(OkButton);
        //Add scrollPane on the panel
        panel.add(scrollPane, BorderLayout.CENTER);
        //Add panelForOKButton on the panel
        panel.add(panelForOKButton, BorderLayout.SOUTH);
        //Add panel with elements in HistoryFrame
        super.add(panel);
    }
}
