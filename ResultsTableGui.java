import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class ResultsTableGui extends JPanel{
    public JTable table;
    private JScrollPane scroll;

    public ResultsTableGui(int totalProcesos){
        super();
        table = new JTable(totalProcesos+2,11);
        scroll = new JScrollPane(table);
    }

    public void setDefaults(){
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setEnabled(false);
        table.setRowHeight(50);
        setDefaultHeader();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(0,0,1500,350);
        centrar();
        this.setLayout(null);
        this.add(scroll);
    }

    public void setDefaultHeader(){
        table.getColumnModel().getColumn(0).setHeaderValue("Proceso");
        table.getColumnModel().getColumn(1).setHeaderValue("Prioridad");
        table.getColumnModel().getColumn(2).setHeaderValue("Tiempo Llegada");
        table.getColumnModel().getColumn(3).setHeaderValue("Duracion Rafaga");
        table.getColumnModel().getColumn(4).setHeaderValue("Tiempo Arranque");
        table.getColumnModel().getColumn(5).setHeaderValue("Tiempo Finalizacion");
        table.getColumnModel().getColumn(6).setHeaderValue("Tiempo Retorno");
        table.getColumnModel().getColumn(7).setHeaderValue("Tiempo Respuesta");
        table.getColumnModel().getColumn(8).setHeaderValue("Tasa Desperdicio");
        table.getColumnModel().getColumn(9).setHeaderValue("Tasa Penalizacion");
        table.getColumnModel().getColumn(10).setHeaderValue("Tiempo Espera");
        table.getTableHeader().setFont(new Font("Mono",Font.PLAIN,16));
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(Color.black);
        table.getTableHeader().setForeground(Color.white);
    }

    public void reset(){
        for(int i = 0; i < table.getColumnCount(); i++){
            for(int j = 0; j < table.getRowCount(); j++){
                table.setValueAt("",j,i);
            }
        }
    }

    public void centrar(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
