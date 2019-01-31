
package SearchEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author simoe
 */
public class SEFileInMainFrame extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    
    // Variables declaration                   
    private JButton closeWindowButton;
    private JButton searchButton;
    private JButton openInNewWindowButton;
    private JCheckBox INFOCheckbox;
    private JCheckBox WARNCheckbox;
    private JCheckBox ERRCheckbox;
    private JCheckBox DEBGCheckbox;
    private JLabel fileNameLabel;
    private JScrollPane textAreaScroll;
    private JTextArea textArea;
    private JTextField searchBar;
    // End of variables declaration
    
    public SEFileInMainFrame() {
        initComponents();
    }
                      
    private void initComponents() {

        textAreaScroll = new JScrollPane();
        textArea = new JTextArea();
        closeWindowButton = new JButton();
        fileNameLabel = new JLabel();
        searchBar = new JTextField();
        searchButton = new JButton();
        INFOCheckbox = new JCheckBox();
        WARNCheckbox = new JCheckBox();
        ERRCheckbox = new JCheckBox();
        DEBGCheckbox = new JCheckBox();
        openInNewWindowButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        textArea.setColumns(20);
        textArea.setRows(5);
        textAreaScroll.setViewportView(textArea);
        
        
        closeWindowButton.setText("X");
        closeWindowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                setVisible(false);
            }
        });
        
        //ACTION ON CLICK
        searchButton.setText("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                File file =new File(SEMainFrame.getCurrentPath().toString()+"\\"+SEMainFrame.getCurrentFileString());
                Scanner in = null;
                try {
                    textArea.setText("");
                    in = new Scanner(file);
                    while(in.hasNext())
                    {
                        String line=in.nextLine();
                        if(line.contains(searchBar.getText()))
                            textArea.append(line+"\n");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                
            }
        });
        
      //ACTION ON CHECK
        INFOCheckbox.setText("INFO");
        INFOCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(INFOCheckbox.isSelected()) {
                    File file =new File(SEMainFrame.getCurrentPath().toString()+"\\"+SEMainFrame.getCurrentFileString());
                    Scanner in = null;
                    try {
                        textArea.setText("");
                        in = new Scanner(file);
                        while(in.hasNext())
                        {
                            String line=in.nextLine();
                            if(line.contains("INFO"))
                                textArea.append(line+"\n");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                
                else {
                    File file =new File(SEMainFrame.getCurrentPath().toString()+"\\"+SEMainFrame.getCurrentFileString());
                    textArea.setText("");
                    writeInTextArea(file);
                }
            }
        });
        
        //ACTION ON CHECK
        WARNCheckbox.setText("WARN");
        WARNCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(WARNCheckbox.isSelected()) {
                    File file =new File(SEMainFrame.getCurrentPath().toString()+"\\"+SEMainFrame.getCurrentFileString());
                    Scanner in = null;
                    try {
                        textArea.setText("");
                        in = new Scanner(file);
                        while(in.hasNext())
                        {
                            String line=in.nextLine();
                            if(line.contains("WARN"))
                                textArea.append(line+"\n");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    File file =new File(SEMainFrame.getCurrentPath().toString()+"\\"+SEMainFrame.getCurrentFileString());
                    textArea.setText("");
                    writeInTextArea(file);
                }
            }
        });
        
        //ACTION ON CHECK
        ERRCheckbox.setText("ERR");
        ERRCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(ERRCheckbox.isSelected()) {
                    File file =new File(SEMainFrame.getCurrentPath().toString()+"\\"+SEMainFrame.getCurrentFileString());
                    Scanner in = null;
                    try {
                        textArea.setText("");
                        in = new Scanner(file);
                        while(in.hasNext())
                        {
                            String line=in.nextLine();
                            if(line.contains("ERR"))
                                textArea.append(line+"\n");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                
                else {
                    File file =new File(SEMainFrame.getCurrentPath().toString()+"\\"+SEMainFrame.getCurrentFileString());
                    textArea.setText("");
                    writeInTextArea(file);
                }
            }
        });
        
        //ACTION ON CHECK
        DEBGCheckbox.setText("DEBG");
        DEBGCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(DEBGCheckbox.isSelected()) {
                    File file =new File(SEMainFrame.getCurrentPath().toString()+"\\"+SEMainFrame.getCurrentFileString());
                    Scanner in = null;
                    try {
                        textArea.setText("");
                        in = new Scanner(file);
                        while(in.hasNext())
                        {
                            String line=in.nextLine();
                            if(line.contains("DEBG"))
                                textArea.append(line+"\n");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                
                else {
                    File file =new File(SEMainFrame.getCurrentPath().toString()+"\\"+SEMainFrame.getCurrentFileString());
                    textArea.setText("");
                    writeInTextArea(file);
                }
            }
        });
        
        //ACTION ON CLICK
        /*openInNewWindowButton.setText("W");
        openInNewWindowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
            }
        });*/
        
        //GUI SETUP
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(textAreaScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(closeWindowButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fileNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(INFOCheckbox)
                .addGap(18, 18, 18)
                .addComponent(WARNCheckbox)
                .addGap(18, 18, 18)
                .addComponent(ERRCheckbox)
                .addGap(18, 18, 18)
                .addComponent(DEBGCheckbox)
                .addGap(18, 18, 18)
                .addComponent(openInNewWindowButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeWindowButton)
                    .addComponent(fileNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(searchButton)
                    .addComponent(INFOCheckbox)
                    .addComponent(WARNCheckbox)
                    .addComponent(ERRCheckbox)
                    .addComponent(DEBGCheckbox)
                    .addComponent(openInNewWindowButton))
                .addGap(13, 13, 13)
                .addComponent(textAreaScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    
    public void writeInTextArea(File file) {
        String fileName = new String(file.toString().substring(file.toString().lastIndexOf(File.separator) + 1));
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fileNameLabel.setText(fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                textArea.append(sCurrentLine+"\n");
            }
        }
        catch(IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
            }
            catch(IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
    
    public void INFOFilteredFile(File file) {
        
    }
                 
}
