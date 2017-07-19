import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*; //Permite a criação da interface gráfica
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



	/**	
	*
	*Seminário segundo semestre da turma ADS 48 da Uniasselvi de Capão da Canoa
	*@author Roger Monteiro
	*@author Vanessa Helena
	*@author Alexandre Souza
	 **/

@SuppressWarnings("serial") // Suprime o aviso das importações do JFrame
public class InterfaceGrafica extends JFrame {

	public JPanel painel;
	public JTextField t1, t2, t3, t4, t5;
	public JTable tabela;
	public JButton bcadastrar, bexcluir, beditar;

	// Variáveis
	public String nome = " ";
	public String endereco = " ";
	public String cpf = " ";
	public String telefone = " ";
	public String email = " ";
	public boolean interruptor = false;

	String[] colunas = new String[] { nome, endereco, cpf, telefone, email }; // Array List

	String[][] dados = new String[][] { { "Nome", "Endereço", "CPF", "Telefone", "E-mail" },

	};

	// Método de limpeza dos JText
	public void LimparCampos() {
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");

	}

	// Inicio de criação do JFrame
	public InterfaceGrafica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 300); // Tamanho da janela da aplicação
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);
		painel.setBackground(Color.lightGray);
		// Final criação do frame
		////////////////////////////////////////////////
		// Início dos rótulos com nome, endereço, cpf, etc (JLabel)
		JLabel l1 = new JLabel("Nome:");
		l1.setBounds(10, 10, 100, 15); // (Afastamento ->, Afastamento\/, Largura , Altura )
		painel.add(l1);

		JLabel l2 = new JLabel("Endereço: ");
		l2.setBounds(10, 50, 100, 15);
		painel.add(l2);

		JLabel l3 = new JLabel("CPF: ");
		l3.setBounds(10, 90, 100, 15);
		painel.add(l3);

		JLabel l4 = new JLabel("Telefone: ");
		l4.setBounds(10, 130, 100, 15);
		painel.add(l4);

		JLabel l5 = new JLabel("Email: ");
		l5.setBounds(10, 170, 100, 15);
		painel.add(l5);

		JLabel l6 = new JLabel("Cadastrados: ");
		l6.setBounds(350, 10, 100, 15);
		painel.add(l6);
		
		JLabel aviso = new JLabel("");
		aviso.setBounds(800, 250, 200, 15);
		painel.add(aviso);
		// Final dos rótulos
		////////////////////////////////////////////////
		// Inicio dos campos de preenchimento (TextField)
		t1 = new JTextField();
		t1.setBounds(100, 10, 225, 20);
		painel.add(t1);
		t1.setColumns(100);

		t2 = new JTextField();
		t2.setBounds(100, 50, 225, 20);
		painel.add(t2);
		t2.setColumns(100);

		t3 = new JTextField();
		t3.setBounds(100, 90, 225, 20);
		painel.add(t3);
		t3.setColumns(11);

		t4 = new JTextField();
		t4.setBounds(100, 130, 225, 20);
		painel.add(t4);
		t4.setColumns(100);

		t5 = new JTextField();
		t5.setBounds(100, 170, 225, 20);
		painel.add(t5);
		t5.setColumns(100);
		// Final dos campos de preenchimento
		
		// Inicio Tabela
		tabela = new JTable();
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		DefaultTableModel model = new DefaultTableModel(dados, colunas) {

			public boolean isCellEditable(int rowIndex, int vColIndex) { // Bloqueia a edição da tabela
				return interruptor;

			}

		};

		tabela.setBounds(350, 35, 620, 200);//215
		painel.add(tabela);
		tabela.setModel(model);
		// Fim Tabela

		// Inicio da criação dos botões

		// Botão CADASTRAR
		bcadastrar = new JButton("Cadastrar");
		bcadastrar.setBounds(10, 225, 100, 25);
		painel.add(bcadastrar);
		bcadastrar.addActionListener(new ActionListener() { // Comando que
															// adiciona ação ao
															// botão
			public void actionPerformed(ActionEvent e) {

				String[] dados = new String[5]; // ("^[a-z A-Z]*$") -> Expressão regular de letras e caracteres espceiais
				dados[0] = t1.getText();
				dados[1] = t2.getText();		// ^ Qualquer caracter exceto:
				dados[2] = t3.getText();
				dados[3] = t4.getText();
				dados[4] = t5.getText();

				interruptor = false; //Altera a edição da tabela para "não editável"
				
				if (dados[0].matches("^[0-9]*$")) {

					JOptionPane.showMessageDialog(null, "Nome não deve conter números.");
					return;
				}

					if (dados[1].matches("^[a-z A-Z]*$")) {

						JOptionPane.showMessageDialog(null, "Endereço deve conter letras e números.");
						return;
					}

						if (dados[2].matches("^[a-z A-Z]*$")) {

								JOptionPane.showMessageDialog(null, "CPF deve conter apenas números.");
								return;
							}

								if (dados[3].matches("^[a-z A-Z]*$")) {

										JOptionPane.showMessageDialog(null, "Telefone deve conter apenas números.");
										return;
								}

									if (dados[4].matches("^[a-z A-Z]*$")) {

										JOptionPane.showMessageDialog(null, "Email inválido.");
										return;

				} else {

					model.addRow(dados); // Adiciona linha
					aviso.setText(String.valueOf("Edição da tabela desativada."));
					
					File Arquivo = new File(t1.getText()); //Cria diretorio com o nome do Cadastrado
					Arquivo.mkdir();

					LimparCampos(); // Chama o método que limpa os campos 
				}

			}

		});

		// Botão EXCLUIR
		bexcluir = new JButton("Excluir");
		bexcluir.setBounds(225, 225, 100, 25);
		painel.add(bexcluir);
		bexcluir.addActionListener(new ActionListener() { // Adiciona ação ao botão

			public void actionPerformed(ActionEvent e) {

				if (tabela.getSelectedRow() <= 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir", "AVISO",
							JOptionPane.WARNING_MESSAGE);
										return;
				}
								
				((DefaultTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
							
			}
		});

		// Botão EDITAR
		beditar = new JButton("Editar");
		beditar.setBounds(117, 225, 100, 25);
		painel.add(beditar);
		beditar.addActionListener(new ActionListener() { // Adiciona a ação do botão
			public void actionPerformed(ActionEvent e) {

				if (tabela.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione a tabela para editar", "AVISO",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				interruptor = true;
				
				JOptionPane.showMessageDialog(null, "Edição da tabela ativada.", "AVISO", JOptionPane.WARNING_MESSAGE);
				
				aviso.setText(String.valueOf("Edição da tabela ativada."));
				
				return;
			}
		});
	}
}
