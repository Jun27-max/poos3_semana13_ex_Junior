import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class JanelaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = -2897884274570344109L;

	private JPanel panel;
    
    private JLabel labelAposta;
    private JTextField fieldAposta;
    
    private JButton buttonNovo;
    private JButton buttonJogar;
    private JButton buttonLimpar;
    private JButton buttonSalvar;
    private JButton buttonRecuperar;
    
    private JTextArea areaJogo;
    private JScrollPane scrollJogo;
    
    private JogoDado jogo;
    
    public JanelaPrincipal() {
        criarComponentes();
        ajustarPropriedadesJanela();
    }
    
    private void criarComponentes() {
        panel = new JPanel();
        
        labelAposta = new JLabel("Aposta:");
        
        fieldAposta = new JTextField(4);
        fieldAposta.setEditable(false);
        
        buttonNovo = new JButton("Novo jogo");
        buttonNovo.addActionListener(this);
        
        buttonJogar = new JButton("Jogar");
        buttonJogar.addActionListener(this);
        buttonJogar.setEnabled(false);
        
        buttonLimpar = new JButton("Limpar");
        buttonLimpar.addActionListener(this);
        
        buttonSalvar = new JButton("Salvar");
        buttonSalvar.addActionListener(this);
        //buttonSalvar.setEnabled(false);
        
        buttonRecuperar = new JButton("Recuperar");
        buttonRecuperar.addActionListener(this);
        
        areaJogo = new JTextArea(30,30); //linhas e colunas
        areaJogo.setEditable(false);
        
        scrollJogo = new JScrollPane();
        scrollJogo.setViewportView(areaJogo);
        
        adicionarComponentes();
    }
    
    private void adicionarComponentes() {
        panel.add(labelAposta);
        panel.add(fieldAposta);
        panel.add(buttonNovo);
        panel.add(buttonJogar);
        panel.add(buttonLimpar);
        panel.add(buttonSalvar);
        panel.add(buttonRecuperar);
        panel.add(scrollJogo);
        add(panel);
    }
    
    private void ajustarPropriedadesJanela() {
        setVisible(true);
        setTitle("Semana 1 - Exercício");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void criarJogo() {
        jogo = new JogoDado();
        jogo.inicializarJogo();
        fieldAposta.setEditable(true);
        fieldAposta.setText(null);
        buttonJogar.setEnabled(true);
        areaJogo.append("Início de jogo\n");
    }
    
    private void jogar() {
    	String temp = fieldAposta.getText();
    	if(temp.matches("[0-9]+")) { //validando a entrada
	        int aposta = Integer.parseInt(temp);
	        boolean acertou = jogo.jogar(aposta);
	        if(acertou) {
	            areaJogo.append("Parabéns! Você acertou.\n");
	            encerrarJogo();
	        }
	        else {
	            int tentativas = jogo.obterTentativas();
	            if(tentativas > 0) {
	                areaJogo.append(String.format("Você errou. Tentativas restantes = %d\n",tentativas));
	            }
	            else {
	                areaJogo.append(String.format("Fim de jogo\n"));
	                encerrarJogo();
	            }
	        }
    	}
    }
    
    private void encerrarJogo() {
        buttonJogar.setEnabled(false);
        fieldAposta.setEditable(false);
        jogo = null;
    }
    
    private void salvarJogo() {
    	SerializadorObj sz = new SerializadorObj(new File("JogoDado.ser"));
    	sz.serializar(jogo);
    	areaJogo.append("Jogo Salvo");
    	//JOptionPane.showMessageDialog(areaJogo, "Jogo Salvo");
    }
    
   /*private void recuperarJogo() {
    	SerializadorObj s = new SerializadorObj("JogoDado.ser");
    	JogoDado j = (JogoDado)s.desseralizar();
		j.setStatus(StatusEnum.ANDAMENTO);
    }*/
    
    private void limparArea() {
        areaJogo.setText(null);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonNovo) {
            criarJogo();
        }
        else if(e.getSource() == buttonJogar) {
            jogar();
        }
        else if(e.getSource() == buttonSalvar) {
            salvarJogo();
        }
        else if(e.getSource() == buttonRecuperar) {
            //recuperarJogo();
        }
        else {
            limparArea();
        }
    }
}
