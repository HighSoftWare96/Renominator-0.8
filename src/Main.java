import java.awt.EventQueue;
import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JRadioButton;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Label;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import java.io.*;
import javax.swing.JSeparator;

public class Main {

	private JFrame frame;
	private JTextField folderPath;
	private JTextField ext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		char c = 123 % 123 + 97;
		System.out.println(c);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		frame.getContentPane().setForeground(Color.RED);
		frame.setTitle("Renominator");
		frame.setBounds(100, 100, 442, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("RENOMINATOR");
		titleLabel.setForeground(SystemColor.activeCaption);
		titleLabel.setBounds(0, 11, 434, 27);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frame.getContentPane().add(titleLabel);

		JLabel descriptionLabel = new JLabel("This program helps you rename groups of files simply!");
		descriptionLabel.setForeground(SystemColor.inactiveCaptionText);
		descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		descriptionLabel.setBounds(61, 38, 286, 27);
		frame.getContentPane().add(descriptionLabel);

		folderPath = new JTextField();
		folderPath.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		folderPath.setBounds(61, 95, 229, 20);
		frame.getContentPane().add(folderPath);
		folderPath.setColumns(10);

		JLabel firstLabel = new JLabel("Insert the folder path here");
		firstLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		firstLabel.setBounds(61, 76, 168, 14);
		frame.getContentPane().add(firstLabel);

		ext = new JTextField();
		ext.setHorizontalAlignment(SwingConstants.LEFT);
		ext.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		ext.setBounds(176, 145, 79, 20);
		frame.getContentPane().add(ext);
		ext.setColumns(10);

		JLabel secondLabel = new JLabel("Insert the file extension you want to rename");
		secondLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		secondLabel.setBounds(61, 125, 323, 14);
		frame.getContentPane().add(secondLabel);

		JLabel label = new JLabel(".");
		label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		label.setBounds(166, 148, 46, 14);
		frame.getContentPane().add(label);

		JRadioButton radioA = new JRadioButton("a, b, c, d,...");
		radioA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		radioA.setBounds(61, 190, 109, 23);
		frame.getContentPane().add(radioA);

		JRadioButton radio01 = new JRadioButton("01, 02, 03, ...");
		radio01.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		radio01.setBounds(181, 190, 109, 23);
		frame.getContentPane().add(radio01);

		// gruppo di bottoni mutuamente esclusivi
		ButtonGroup mods = new ButtonGroup();
		mods.add(radioA);
		mods.add(radio01);

		JLabel thirdLabel = new JLabel("Rename like...");
		thirdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		thirdLabel.setBounds(61, 173, 109, 14);
		frame.getContentPane().add(thirdLabel);

		// BOTTONE DI BROWSE
		Button button = new Button("Browse...");
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// selettore della cartella predefinito di swing
				JFileChooser chooser = new JFileChooser();

				// imposto modalità solo CARTELLE
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				// apro una cartella di dialogo
				int returnVal = chooser.showOpenDialog(frame);

				// stampo il percorso scelto
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					folderPath.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		button.setBounds(296, 95, 70, 22);
		frame.getContentPane().add(button);

		Label alertLabel = new Label("All your file with this extension will be renamed with the method chosen!");
		alertLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		alertLabel.setAlignment(Label.CENTER);
		alertLabel.setBounds(0, 221, 419, 20);
		frame.getContentPane().add(alertLabel);

		JTextArea output = new JTextArea();
		output.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		output.setBackground(Color.WHITE);
		output.setText("Welcome to Renominator 1.0");
		output.setBounds(13, 325, 406, 27);
		frame.getContentPane().add(output);

		Button enter = new Button("PROCEED >");
		enter.setForeground(new Color(204, 0, 51));
		enter.setFont(new Font("Segoe UI", Font.BOLD, 17));
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Mi salvo l'estensione cercata
				String exOriginal = ext.getText();

				// operazioni per estrarre i files da un path
				File dir = new File(folderPath.getText());
				// lista dei file nella cartella
				File[] dirList = dir.listFiles();

				// non è stato inserito il percorso della cartella!
				if (folderPath.getText().equals("")) {
					output.setText("Insert a path!");
					return;
				}
				
				if(dirList == null){
					output.setText("Invalid path?");
					return;
				}
				
				if (dirList != null) {
					int index = 0;

					if (ext.getText().equals("")) { // non è stata inserita un
						// estensione
						output.setText("Please insert an extension for the files!");
						return;
					}

					// selezione di una modalità non fatta
					if (!radio01.isSelected() && !radioA.isSelected()) {
						output.setText("Choose a modality!");
						return;
					}

					// freeze dell'interfaccia
					button.setEnabled(false);
					enter.setLabel("Working...");
					enter.setEnabled(false);
					output.setText("Working now...");

					for (File item : dirList) {

						// recupero l'estensione del file corrente
						String ex = item.getName().substring(item.getName().lastIndexOf(".") + 1);

						// l'estensione del file è quella cercata
						if (ex.equalsIgnoreCase(exOriginal)) {

							// rinomina dei file coi numeri
							if (radio01.isSelected())
								// rename del file corrente
								rename01(item.toString(), ext.getText(), index++);
							else {
								renameA(item.toString(), ext.getText(), index++);
							}
						}
					}

					// analisi dei risultati
					if (index == 0) // trovato nulla
						output.setText("No items found!");
					else { // default
						output.setText("DONE. Found " + index + " items");
					}

					// reimpostazione dell'interfaccia grafica
					enter.setLabel("PROCEED >");
					enter.setEnabled(true);
					button.setEnabled(true);

					System.out.println("END");
				}
			}
		});

		enter.setBounds(137, 247, 137, 48);
		frame.getContentPane().add(enter);

		JSeparator separator = new JSeparator();
		separator.setBounds(13, 313, 395, 20);
		frame.getContentPane().add(separator);

	}

	void rename01(String path, String ext, int i) {

		try {

			// factoring del comando = il comando rinomina i file seguendo un
			// indice
			String command = "ren \"" + path + "\" " + i + "." + ext;

			// creazione di un nuovo processo che recupera il cmd del computer
			// ed esegue il comando di rinonima
			Process process = new ProcessBuilder("C:\\Windows\\system32\\cmd.exe", "/c", command).start();

			System.out.println("Running...");

			// attendo la conclusione del comando (che a questo punto dovrebbe
			// essere conclusa)
			process.waitFor();

		} catch (Exception e1) {
			System.out.println("Uoops!\n");
			e1.printStackTrace();
		}
	}

	void renameA(String path, String ext, int i) {
		
		//aggiungo l'offset dell'alfabeto
		i += 97;
		
		String supply = "";
		
		// siamo oltre la zeta
		if (i > 122) {
			
			//riparto dalla a
			int tempInt = (i % 123);
			char tempChar = (char) tempInt;
			supply += tempChar;
		}
		
		//TODO bisogna sistemare il ciclo dell'alfabeto...
		if(i / 123 > 0)
			i %= 123 + 97;
		
		char cr = (char) i;

		String c = cr + supply;

		try {

			// factoring del comando = il comando rinomina i file seguendo un
			// indice
			String command = "ren \"" + path + "\" " + c + "." + ext;

			// creazione di un nuovo processo che recupera il cmd del computer
			// ed esegue il comando di rinonima
			Process process = new ProcessBuilder("C:\\Windows\\system32\\cmd.exe", "/c", command).start();

			System.out.println("Running...");

			// attendo la conclusione del comando (che a questo punto dovrebbe
			// essere conclusa)
			process.waitFor();

		} catch (Exception e1) {
			System.out.println("Uoops!\n");
			e1.printStackTrace();
		}
	}
}
