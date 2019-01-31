package SearchEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author simoe
 */
public class SEMainFrame extends javax.swing.JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    // Variables declaration  
    private static File currentPath;
    private String currentPathString;
    private String currentFolderName;
    private File currentFile;
    private static String currentFileString;
    private String[] fileNames;
    private JButton selectFolderButton;
    private JButton searchButton;
    private JList<String> fileNamesList;
    private File[] files;
    private JPanel mainPanel;
    private JScrollPane treeScroll;
    private JScrollPane fileScroll;
    private JTextField searchBar;
    private JTree fileFolderTree;
    // End of variables declaration                   

    

    public SEMainFrame() throws Exception {
        initComponents();
    }
                        
    private void initComponents() throws Exception{
        
        
        currentPath = new File(".");
        currentPathString = new String(currentPath.toString());
        fileNames = currentPath.list();
        currentFolderName = new String(currentPath.getCanonicalPath());
        mainPanel = new JPanel();
        treeScroll = new JScrollPane();
        fileFolderTree = new JTree(fileNames);
        fileScroll = new JScrollPane();
        fileNamesList = new JList<>();
        searchBar = new JTextField();
        searchButton = new JButton();
        selectFolderButton = new JButton();
        

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        treeScroll.setViewportView(fileFolderTree);
        
        files = currentPath.listFiles();
        
        fileNamesList.setModel(new AbstractListModel<String>() {
            private static final long serialVersionUID = 1L;
            
            public int getSize() {
                return fileNames.length;
            }
            public String getElementAt(int i) {
                return fileNames[i]; 
            }
            
        });
        
        fileScroll.setViewportView(fileNamesList);
        
        //SEARCH BUTTON
        searchButton.setText("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String s = new String(searchBar.getText());
                if(s=="\0" || s==null) {
                    try{
                       fileNames = currentPath.list();
                       fileNamesList.setModel(new AbstractListModel<String>() {
                        private static final long serialVersionUID = 1L;
                        public int getSize() { return fileNames.length; }
                        public String getElementAt(int i) { return fileNames[i]; }
                       });
                    }
                    catch(Exception e) {
                       System.out.println(e);
                    }
                }
                
                else {
                    String[] temp = new String[fileNames.length];
                    String[] filteredItemsRegex;
                    String[] filteredItemsRegexString;
                    int newLength=0;
                    int k=0;
                    for(int i=0; i<fileNames.length;i++) {
                        if (SEService.matchingFile(fileNames[i], s)==fileNames[i]){
                            temp[i]=fileNames[i];
                        }
                    }
                    
                    for(int i=0; i<temp.length;i++) {
                        if(temp[i]!=null) {
                            k++;
                        }
                    }
                    
                    filteredItemsRegex = new String[k];
                    int r=0;
                    for(int i=0; i<temp.length; i++) {
                        if(temp[i]!=null) {
                            filteredItemsRegex[r]=temp[i];
                            r++;
                        }
                    }
                    for(int i=0; i<fileNames.length;i++) {
                        if (fileNames[i].contains(s)){
                            temp[i]=fileNames[i];
                        }
                    }
                    
                    for(int i=0; i<temp.length;i++) {
                        if(temp[i]!=null) {
                            newLength++;
                        }
                    }
                    
                    filteredItemsRegexString = new String[newLength];
                    int j=0;
                    for(int i=0; i<temp.length; i++) {
                        if(temp[i]!=null) {
                            filteredItemsRegexString[j]=temp[i];
                            j++;
                        }
                    }
                    
                    fileNamesList.setModel(new AbstractListModel<String>() {
                        private static final long serialVersionUID = 1L;
                        
                        public int getSize() {
                            return filteredItemsRegexString.length;
                        }
                        public String getElementAt(int i) { 
                            return filteredItemsRegexString[i];
                        }
                        
                    });
                }
            }
                
        });
        selectFolderButton.setText(currentFolderName.substring(currentFolderName.lastIndexOf(File.separator) + 1));
        
        //FILE LIST
        fileNamesList.addListSelectionListener( new ListSelectionListener() {

            
            public void valueChanged(ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    try{
                        SEFileInMainFrame fileInMainFrame = new SEFileInMainFrame();
                        
                        String selectedFileString = fileNamesList.getSelectedValue().toString();
                        currentFileString = selectedFileString;
                        File selectedFile = new File(currentPath.toString()+"\\"+selectedFileString);
                        currentFile = selectedFile;
                        fileInMainFrame.writeInTextArea(selectedFile);
                    }
                    catch(Exception e) {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
            }
            
        }); 
        
       //SELECT FOLDER BUTTON
       selectFolderButton.addActionListener(new ActionListener() {

           public void actionPerformed(ActionEvent event) {
               JFileChooser chooser = new JFileChooser();
               chooser.setCurrentDirectory(new java.io.File("."));
               chooser.setDialogTitle("Select folder");
               chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               chooser.setAcceptAllFileFilterUsed(false);
    
               if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                   System.out.println("getCurrentDirectory(): "+ chooser.getCurrentDirectory());
                   System.out.println("getSelectedFile() : "+ chooser.getSelectedFile());
                   
                   try {
                       String newFolderName = chooser.getSelectedFile().getPath();
                       if(chooser.getSelectedFile() == null) selectFolderButton.setText(currentFolderName.substring(currentFolderName.lastIndexOf(File.separator) + 1));
                       else {
                       selectFolderButton.setText(newFolderName.substring(newFolderName.lastIndexOf(File.separator) + 1));
                       }
                       
                   } 
                   
                   catch (Exception e) {
                       System.out.println(e);
                       e.printStackTrace();
                   }
                   
                   currentPath = chooser.getSelectedFile();
                   
                   try{
                       fileNames = currentPath.list();
                       fileNamesList.setModel(new AbstractListModel<String>() {
                        private static final long serialVersionUID = 1L;
                        public int getSize() { return fileNames.length; }
                        public String getElementAt(int i) { return fileNames[i]; }
                       });
                   }
                   catch(Exception e) {
                       System.out.println(e);
                       e.printStackTrace();
                   }
               } 
               
               else {
                   System.out.println("No Selection");
               }
           }

       });
       
        //GUI SETTINGS
        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treeScroll, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                .addGroup(mainPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(fileScroll, GroupLayout.PREFERRED_SIZE, 870, GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(selectFolderButton, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(searchBar, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(searchButton)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(mainPanelLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(selectFolderButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(fileScroll))
            .addGroup(Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(0, 27, Short.MAX_VALUE)
                .addComponent(treeScroll, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }                                                      

    //CALLED WHEN UNHANDLED ACTIONS OCCUR
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static String getCurrentFileString() {
        return currentFileString;
    }
    public File getCurrentFile() {
        return this.currentFile;
    }
    public static File getCurrentPath() {
        return currentPath;
    }
    public String getCurrentPathString() {
        return this.currentPathString;
    }
    
}
