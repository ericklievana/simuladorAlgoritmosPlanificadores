import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements ActionListener{
    private JFrame window;
    private JLabel title;
    private ResultsTableGui results;
    private TableTimeGui time;
    private InformationGui info;
    private JComboBox<String> selector;
    private JButton start;
    private Simulator simulador;
    private JTextField quantum;
    private JLabel mensajeQuantum;
    private JLabel mensajeMejor;
    private JLabel mejor;

    public Window(Simulator simulador){
        this.simulador = simulador;
        window = new JFrame();
        title = new JLabel();
        results = new ResultsTableGui(simulador.numeroProcesos());
        time = new TableTimeGui(simulador.totalTime());
        info = new InformationGui();
        selector = new JComboBox<String>();
        start = new JButton("Iniciar");
        quantum = new JTextField("1");
        mensajeQuantum = new JLabel("Quantum: ");
        mensajeMejor = new JLabel("<html>El mejor algoritmo es:</html>");
        mejor = new JLabel();
    }

    private void setDefaultWindowsProperties(){
        window.setSize(1600,900);
        window.setLayout(null);
        window.setVisible(true);
        window.getContentPane().setBackground(Color.black);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setDefaultTitleProperties(){
        title.setText("Simulador de Algoritmos Planificadores");
        title.setBounds(0,0,1600,100);
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Mono", Font.BOLD, 36));
    }

    private void setDefaultSelectorProperties(){
        selector.setBounds(50,200,200,30);
        selector.addItem(Nombres.fcfs);
        selector.addItem(Nombres.sjf);
        selector.addItem(Nombres.srt);
        selector.addItem(Nombres.roundRobin);
        selector.addItem(Nombres.prioridades);
    }

    private void setDefaultStartProperties(){
        start.setBounds(50,400,200,30);
        start.addActionListener(this);
        quantum.setBounds(150,300,200,30);
        mensajeQuantum.setForeground(Color.white);
        mensajeQuantum.setFont(new Font("Mono", Font.BOLD, 14));
        mensajeQuantum.setBounds(50,300,200,30);
        mensajeMejor.setBounds(400,200,200,30);
        mensajeMejor.setForeground(Color.white);
        mejor.setBounds(400,250,200,30);
        mejor.setForeground(Color.white);
        mejor.setFont(new Font("Mono", Font.BOLD, 24));
        mensajeMejor.setFont(new Font("Mono", Font.BOLD, 14));
    }

    public void actionPerformed(ActionEvent e) {
        simulador.setInfo(info);
        simulador.setTimeTable(time);
        simulador.setResultados(results);
        simulador.reset();
        simulador.setQuantum(Integer.parseInt(quantum.getText()));
        simulador.start(selector.getSelectedItem().toString());
        String tmp = simulador.mejor();
        mejor.setText(tmp);
    }

    private void setDefaultResultsTableProperties(){
        results.setDefaults();
        results.setBounds(50,500,1500,350);
    }

    private void setDefaultTimeProperties(){
        time.setDefaults();
        time.setBounds(850,125,700,108);
    }

    private void setDefaultInfoProperties(){
        info.setDefaults();
        info.setBounds(850,300,700,108);
    }

    private void addWindowsElements(){
        window.add(title);
        window.add(results);
        window.add(time);
        window.add(info);
        window.add(selector);
        window.add(start);
        window.add(quantum);
        window.add(mensajeQuantum);
        window.add(mensajeMejor);
        window.add(mejor);
    }

    public void createWindow(){
        setDefaultWindowsProperties();
        setDefaultTitleProperties();
        setDefaultResultsTableProperties();
        setDefaultTimeProperties();
        setDefaultInfoProperties();
        setDefaultStartProperties();
        setDefaultSelectorProperties();
        addWindowsElements();
    }
}
