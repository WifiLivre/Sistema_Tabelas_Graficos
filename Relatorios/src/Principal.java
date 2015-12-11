import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;


public class Principal {
	static String login="",senha="",local="";
	public static void main(String[] args) {
		 
		
		login=JOptionPane.showInputDialog("Digite o login da base de dados para se conectar");
		senha=JOptionPane.showInputDialog("Digite a senha da base de dados para se conectar");
		local=JOptionPane.showInputDialog("Digite o caminho raiz de todos os banco de dados exemplo : //localhost");
		String title = "WifiLivre SP";
	    JFrame frame = new JFrame(title);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container contentpane = frame.getContentPane();
	    
	    //lista combobox
	    JComboBox<String> comboBox1 = new JComboBox();
	    contentpane.add(comboBox1, BorderLayout.NORTH);
	    
	    //botao
	    
	    JButton btnConfirma ;
		btnConfirma = new JButton("Confirmar banco de dados selecionado"); 
		contentpane.add(btnConfirma,BorderLayout.SOUTH);
	    frame.add( btnConfirma );
	    
	    //evento do botao
	    btnConfirma.addActionListener( new ActionListener() {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	if(e.getSource() == btnConfirma){
					String selecionado =comboBox1.getSelectedItem().toString();
					if(JOptionPane.showConfirmDialog(null, "Deseja utilizar o banco "+ selecionado + "?","Pergunta",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
						local= local+"/"+selecionado ;
						System.out.println(local);
						JFileChooser fc = new JFileChooser();
	                    
	                    // restringe a amostra a diretorios apenas
	                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	                    
	                    int res = fc.showOpenDialog(null);
	                    
	                    if(res == JFileChooser.APPROVE_OPTION){
	                        String diretorio = fc.getSelectedFile().getAbsolutePath();
	                        //JOptionPane.showMessageDialog(null, "Voce escolheu o diretório: " + diretorio);
	                        ListaPraca Lista = new ListaPraca();
	                        Lista.main(login,senha,local,diretorio);
	                        frame.dispose();
	                    }
	                    else{
	                        JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");
	                    }
					}
	        	}
	        }
	      } );
	    
	    //------------------------------------
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( "show databases;");
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			while (rs3.next()) {
			    comboBox1.addItem(rs3.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    frame.setSize(300, 200);
	    frame.setVisible(true);
		
	    
	}
	
}
