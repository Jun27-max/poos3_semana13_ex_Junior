import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//SwingUtilities.invokeAndWait(JanelaPrincipal::new);
			SwingUtilities.invokeAndWait(() -> new JanelaPrincipal());
		}
		catch(Exception erro) {
			System.out.println("Erro na execução da janela gráfica");
		}
	}

}
