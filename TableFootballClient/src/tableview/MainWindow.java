package tableview;

import edit.AdditionListener;
import edit.SearchDeleteListener;
import file.ExitMenuListener;
import file.NewTableListener;
import file.OpenMenuListener;
import file.SaveMenuListener;
import tableview.PageConst;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BorderLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.Socket;

public class MainWindow {
    private Socket socket;
    private JFrame jfMainWin;

    public MainWindow(JFrame jfMainWin,Socket socket) {
        this.jfMainWin = jfMainWin;
        this.socket = socket;

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        jfMainWin.setSize(600, 600);
        JMenuBar jmbMain = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);

        JMenuItem jmiCreate = new JMenuItem("New table", KeyEvent.VK_N);
        jmiCreate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        JMenuItem jmiOpen = new JMenuItem("Open table", KeyEvent.VK_O);
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        JMenuItem jmiSave = new JMenuItem("Save table", KeyEvent.VK_S);
        JMenuItem jmiExit = new JMenuItem("Exit table", KeyEvent.VK_Q);
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

        jmFile.add(jmiCreate);
        jmFile.add(jmiOpen);
        jmFile.add(jmiSave);
        jmFile.add(jmiExit);
        jmbMain.add(jmFile);

        JMenu jmEdit = new JMenu("Edit");
        jmFile.setMnemonic(KeyEvent.VK_E);

        JMenuItem jmiSearch = new JMenuItem("Найти", KeyEvent.VK_R);
        jmiSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        JMenuItem jmiAddition = new JMenuItem("Добавить", KeyEvent.VK_A);
        jmiAddition.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        JMenuItem jmiDelete = new JMenuItem("Найти и удалить", KeyEvent.VK_D);
        jmiDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));

        jmEdit.add(jmiSearch);
        jmEdit.add(jmiAddition);
        jmEdit.add(jmiDelete);
        jmbMain.add(jmEdit);
        jfMainWin.setJMenuBar(jmbMain);

        JToolBar jtbMain = new JToolBar("Work with table", JToolBar.VERTICAL);
        ImageIcon imSearch = new ImageIcon(PageConst.SEARCH_TOOLBAR_EDIT);
        ImageIcon imAddition = new ImageIcon(PageConst.ADDITIION_TOOLBAR_EDIT);
        ImageIcon imDelete = new ImageIcon(PageConst.DELETE_TOOLBAR_EDIT);

        JButton jbtSearch = new JButton(imSearch);
        jbtSearch.setActionCommand("Найти");
        JButton jbtAddition = new JButton(imAddition);
        jbtAddition.setActionCommand("Добавить");
        JButton jbtDelete = new JButton(imDelete);
        jbtDelete.setActionCommand("Найти и удалить");

        jtbMain.add(jbtSearch);
        jtbMain.add(jbtAddition);
        jtbMain.add(jbtDelete);

        jtbMain.setFloatable(false);
        jtbMain.setVisible(false);
        jfMainWin.add(jtbMain, BorderLayout.WEST);

        JToolBar jtbFile = new JToolBar("Work with file", JToolBar.HORIZONTAL);

        ImageIcon imNew = new ImageIcon(PageConst.NEW_TOOLBAR_FILE);
        ImageIcon imOpen = new ImageIcon(PageConst.OPEN_TOOLBAR_FILE);
        ImageIcon imSave = new ImageIcon(PageConst.SAVE_TOOLBAR_FILE);

        JButton jbNew = new JButton(imNew);
        jbNew.setActionCommand("New table");
        JButton jbOpen = new JButton(imOpen);
        jbOpen.setActionCommand("Open table");
        JButton jbSave = new JButton(imSave);
        jbSave.setActionCommand("Save table");

        jtbFile.add(jbNew);
        jtbFile.add(jbOpen);
        jtbFile.add(jbSave);
        jtbFile.setFloatable(false);
        jtbFile.setVisible(true);
        jfMainWin.add(jtbFile, BorderLayout.NORTH);

        JTabbedPane tableTab = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        jfMainWin.add(tableTab, BorderLayout.CENTER);

        NewTableListener newTable = new NewTableListener(tableTab, jtbMain, socket);
        jbNew.addActionListener(newTable);
        jmiCreate.addActionListener(newTable);

        SaveMenuListener saveTable = new SaveMenuListener(tableTab);
        jbSave.addActionListener(saveTable);
        jmiSave.addActionListener(saveTable);

        OpenMenuListener openTable = new OpenMenuListener(tableTab, jtbMain);
        jbOpen.addActionListener(openTable);
        jmiOpen.addActionListener(openTable);

        jmiExit.addActionListener(new ExitMenuListener());

        AdditionListener additionStudent = new AdditionListener(jfMainWin, tableTab);
        jbtAddition.addActionListener(additionStudent);
        jmiAddition.addActionListener(additionStudent);

        SearchDeleteListener searchDeleteListener = new SearchDeleteListener(jfMainWin, tableTab);
        jbtSearch.addActionListener(searchDeleteListener);
        jmiSearch.addActionListener(searchDeleteListener);
        jbtDelete.addActionListener(searchDeleteListener);
        jmiDelete.addActionListener(searchDeleteListener);

        jfMainWin.setVisible(true);
    }
}