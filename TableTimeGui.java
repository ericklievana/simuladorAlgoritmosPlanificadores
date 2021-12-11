import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class TableTimeGui extends JPanel{
    public JTable table;
    private JScrollPane scroll;
    private JLabel L;
    private JLabel U;
    private JLabel E;

    public TableTimeGui(int totalTime){
        super();
        table = new JTable(3,(totalTime+2)*5);
        scroll = new JScrollPane(table);
        L = new JLabel("L:");
        U = new JLabel("U:");
        E = new JLabel("E:");
    }

    public void setDefaults(){
        this.setBackground(Color.black);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setEnabled(false);
        table.setRowHeight(30);
        table.setTableHeader(null);
        enumerar();
        centrar();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        L.setHorizontalAlignment(JLabel.CENTER);
        L.setVerticalAlignment(JLabel.CENTER);
        U.setHorizontalAlignment(JLabel.CENTER);
        U.setVerticalAlignment(JLabel.CENTER);
        E.setHorizontalAlignment(JLabel.CENTER);
        E.setVerticalAlignment(JLabel.CENTER);
        L.setBounds(0,0,50,30);
        U.setBounds(0,30,50,30);
        E.setBounds(0,60,50,30);
        L.setForeground(Color.white);
        U.setForeground(Color.white);
        E.setForeground(Color.white);
        scroll.setBounds(50,0,700,108);
        this.setLayout(null);
        this.add(scroll);
        this.add(L);
        this.add(U);
        this.add(E);
    }

    public void enumerar(){
        for(int i = 0; i < table.getColumnCount(); i++){
            if(i % 2 == 0){
                table.setValueAt(i/2,1,i);
            }
        }
    }

    public void reset(){
        for(int i = 0; i < table.getColumnCount(); i++){
            table.setValueAt("",0,i);
            table.setValueAt("",2,i);
        }
    }

    public void centrar(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            //table.getColumnModel().getColumn(i).setMinWidth(50);
            //table.getColumnModel().getColumn(i).setMaxWidth(50);
            //table.getColumnModel().getColumn(i).setPreferredWidth(50);
        }
    }
}
