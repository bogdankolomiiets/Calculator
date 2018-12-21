package Calc;

import javax.swing.*;
import java.io.*;

public class SaveToHistory{

    //The maximal count of rows in the history file
    private final int COUNT = 100;

    public void addValuesToTxtFile(String numOneBeforeChange, String numTwo, String numOneAfterChange, char symbol){
        //Field with data of the last arithmetic operation
        String newHistoryString = numOneBeforeChange + " " + symbol + " " + numTwo + " = " + numOneAfterChange;

        try {
            File file = new File("history.txt");

            //If file history.txt does not exists - create a new file
            if (!file.exists())
            {
                file.createNewFile();
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            /*If rows count in the history.txt are less COUNT -
            then append a new row with a data of last arithmetical operation*/
                if (bufferedReader.lines().count() < COUNT) {
                    bufferedWriter.append(newHistoryString);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                    bufferedReader.close();
                }
                else {
                    /*If a rows count in the history.txt are greater COUNT -
                    then delete a first (old) row and append a new row with
                    a data of last arithmetical operation*/

                    //Create a temporary file tmp.txt
                    File tmpFile = new File("tmp.txt");
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    BufferedWriter bufferedWriterToTmp = new BufferedWriter(new FileWriter(tmpFile, true));

                    String temp;
                    for (int i = 0; i < COUNT; i++) {
                        temp = reader.readLine();

                        if (i == 0){ /*Skip the first (old) row*/}
                        else {
                            bufferedWriterToTmp.write(temp);
                            bufferedWriterToTmp.newLine();
                        }

                    }
                    /*Append the new row with a data of last arithmetical operation to temporary file tmp.txt*/
                    bufferedWriterToTmp.append(newHistoryString);
                    bufferedWriterToTmp.newLine();

                    //Clean up and close the buffers
                    bufferedReader.close();
                    reader.close();
                    bufferedWriter.close();
                    bufferedWriterToTmp.close();

                    //Delete history.txt
                    file.delete();
                    //Rename tmp.txt to history.txt
                    tmpFile.renameTo(file);
                }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Произошла ошибка", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
