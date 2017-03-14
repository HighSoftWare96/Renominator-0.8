package en.gb.renominator;
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
import javax.swing.JOptionPane;
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

/**
 * <h1> RENOMINATOR 1.0 </h1> <br> 
 * <strong> Specs: </strong> This program allows to rename groups of file in a
 * folder with a specified extension. It works only on Windows! <br>
 * <strong> Issues: </strong> With the alphabetic mode there is a weak logic of assignment (to improve). Last
 * modification: March 2017 Made coding and with the WindowBuilder add-on for
 * Eclipse Neon.
 * 
 * @version 1.0
 * @author Giovanni Bertoncelli (HighSoftware96 on GitHub)
 * @see https://github.com/HighSoftWare96/Renominator-0.8
 * 
 */
public class Main {

	private JFrame frame;
	private JTextField folderPath;
	private JTextField ext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		frame.getContentPane().setForeground(Color.RED);
		frame.setTitle("Renominator");
		frame.setBounds(100, 100, 439, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/**
		 * Title label of the program at the top
		 */
		JLabel titleLabel = new JLabel("RENOMINATOR");
		titleLabel.setForeground(new Color(30, 144, 255));
		titleLabel.setBounds(10, 11, 406, 27);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		frame.getContentPane().add(titleLabel);

		/**
		 * Description label of the program
		 */
		JLabel descriptionLabel = new JLabel("This program helps you rename groups of files simply!");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setForeground(SystemColor.inactiveCaptionText);
		descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		descriptionLabel.setBounds(10, 38, 406, 27);
		frame.getContentPane().add(descriptionLabel);

		/**
		 * Text field for the insertion of the working path
		 */
		folderPath = new JTextField();
		folderPath.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		folderPath.setBounds(61, 95, 229, 20);
		frame.getContentPane().add(folderPath);
		folderPath.setColumns(10);

		JLabel firstLabel = new JLabel("Insert the folder path here");
		firstLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		firstLabel.setBounds(61, 76, 168, 14);
		frame.getContentPane().add(firstLabel);

		/**
		 * Text field for the insertion of the working extension
		 */
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

		/**
		 * Radio button for the first modality (alphabetical mode)
		 */
		JRadioButton radioA = new JRadioButton("a, b, c, d,...");
		radioA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		radioA.setBounds(176, 190, 109, 23);
		frame.getContentPane().add(radioA);

		/**
		 * Radio button for the second modality (numerical mode)
		 */
		JRadioButton radio01 = new JRadioButton("01, 02, 03, ...");
		radio01.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		radio01.setBounds(61, 190, 109, 23);
		frame.getContentPane().add(radio01);

		// gruppo di bottoni mutuamente esclusivi
		ButtonGroup mods = new ButtonGroup();
		mods.add(radioA);
		mods.add(radio01);

		JLabel thirdLabel = new JLabel("Rename like...");
		thirdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		thirdLabel.setBounds(61, 173, 109, 14);
		frame.getContentPane().add(thirdLabel);

		/**
		 * Browsing button that invoke the default browse window of Java Swing
		 */
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

		/**
		 * Logging text area
		 */
		JTextArea output = new JTextArea();
		output.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		output.setBackground(SystemColor.window);
		output.setText("Welcome to Renominator 1.0");
		output.setBounds(13, 325, 406, 27);
		frame.getContentPane().add(output);

		/**
		 * Button enter and entire logic of the program
		 */
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

				// GESTIONE DEGLI ERRORI
				// non è stato inserito il percorso della cartella!
				if (folderPath.getText().equals("")) {
					output.setText("Insert a path!");
					return;
				}

				if (dirList == null) {
					output.setText("Invalid path?");
					return;
				}

				if (dirList != null) {
					int index = 0;

					if (ext.getText().equals("")) { // non è stata inserita
													// un
						// estensione
						output.setText("Please insert an extension for the files!");
						return;
					}

					// selezione di una modalità non fatta
					if (!radio01.isSelected() && !radioA.isSelected()) {
						output.setText("Choose a modality!");
						return;
					}

					// finestra di conferma della transazione
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Are you sure?\n (All these files will be modified!)", "Warning",
							JOptionPane.YES_NO_OPTION);

					// risposta negativa
					if (dialogResult == JOptionPane.NO_OPTION) {
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

		// checking operative system
		System.out.println("This OS: " + System.getProperty("os.name"));
		if (!System.getProperty("os.name").contains("Windows")) {
			output.setText("This program probably doesn't work on your system. Sorry!");
			enter.setEnabled(false);
		}

		JSeparator separator = new JSeparator();
		separator.setBounds(13, 313, 395, 20);
		frame.getContentPane().add(separator);

	}

	/**
	 * Function that fork the process in a new thread of cmd.exe and use the
	 * Windows command line to rename the files chosen. NUMERICAL MODE
	 * 
	 * @param path:
	 *            the working dir
	 * @param ext:
	 *            the extension used
	 * @param i:
	 *            integer of index
	 * @return void
	 */
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

	/**
	 * Function that fork the process in a new thread of cmd.exe and use the
	 * Windows command line to rename the files chosen. ALPHABETICAL MODE
	 * 
	 * @param path:
	 *            the working dir
	 * @param ext:
	 *            the extension used
	 * @param i:
	 *            integer of index
	 * @return void
	 */
	void renameA(String path, String ext, int i) {

		int temp = i;
		String supply = "";

		// oltrepassato l'alfabeto
		while (temp > 25) {
			supply += generateChar(temp);
			temp -= 25;
		}

		// ricava un nuovo carattere
		char cr = generateChar(i);

		// aggiunge eventuali caratteri supplementari
		String c = cr + supply;
		// debugging
		System.out.println(c);

		try {

			// factoring del comando = il comando rinomina i file seguendo un
			// indice
			String command = "ren \"" + path + "\" " + c + "." + ext;

			// creazione di un nuovo processo che recupera il cmd del computer
			// ed esegue il comando di rinonima
			Process process = new ProcessBuilder("C:\\Windows\\system32\\cmd.exe", "/c", command).start();

			System.out.println("Running... ");

			// attendo la conclusione del comando (che a questo punto dovrebbe
			// essere conclusa)
			process.waitFor();

		} catch (Exception e1) {
			System.out.println("Uoops!\n");
			e1.printStackTrace();
		}
	}

	/**
	 * Function that return a char value for the alphabetical renaming
	 * 
	 * @param index:
	 *            an integer for the assignment of the name
	 * @return char
	 */
	char generateChar(int i) {
		i += 97;

		while (i >= 123) {
			i %= 123;
			i += 97;
		}

		char c = (char) i;
		return c;
	}
}
