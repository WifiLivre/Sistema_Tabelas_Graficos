import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaPraca {
	 public  void main(String login, String senha,String local,String diretorio)  {
		 	List<Integer> Pracas = new ArrayList<Integer>();
		    DefaultTableModel model = new DefaultTableModel(null, new String [] {"Código", "Nome Praça","Utilizar"}) {
		                                public Class getColumnClass(int c) {
		                                  switch (c) {
		                                    case 2: return Boolean.class;
		                                    default: return String.class;
		                                  }   
		                                } };
		    JTable table = new JTable(model);
		    JScrollPane scrollPane = new JScrollPane(table);
		    JFrame frame = new JFrame("Selecionar praças");
		    table.setSize(500,400);
		    //frame.add(table,BorderLayout.NORTH);
		    frame.add(scrollPane,BorderLayout.NORTH);
		    
		    AcessaBanco Conecta = new AcessaBanco(); 
			Conecta.setSql( "SELECT id_praca,nomePref FROM `praca`;");
			Conecta.abrirConexao();
			ResultSet rs3 = Conecta.consulta();
			try {
				while(rs3.next()){
					
					
						model.addRow(new Object [] {rs3.getString(1),rs3.getString(2),false});
						
					
									

					
				
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		    
		    
		    JButton btnConfirma = new JButton("Confirmar praças");
		    btnConfirma.setSize(new Dimension(50, 50));
		    btnConfirma.setLocation(500, 350);
		    frame.add(btnConfirma,BorderLayout.SOUTH);
		    
		    btnConfirma.addActionListener( new ActionListener() {
		        @Override
		        public void actionPerformed( ActionEvent e ) {
		        	if(e.getSource() == btnConfirma){
		        		if(JOptionPane.showConfirmDialog(null, "Deseja utilizar as praças selecionadas?","Pergunta",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
		        			int x=table.getRowCount();
		        			for(int k=0;k<x;k++){
		        				if((boolean)table.getValueAt(k, 2)==true){
		        					System.out.println(table.getModel().getValueAt(k, 0)+"  "+table.getModel().getValueAt(k, 1));
		        					Pracas.add(Integer.parseInt((String) table.getModel().getValueAt(k, 0)));
		        					
		        				}
		        			
		        			}
		        			if(Pracas.size()>0){
		        			fig4 class4 = new fig4();
	                        class4.main(login,senha,local,diretorio,Pracas);
	                        fig5 class5 = new fig5();
	                        class5.main(login,senha,local,diretorio,Pracas);
	                        fig6 class6 = new fig6();
	                        class6.main(login,senha,local,diretorio,Pracas);
	                        fig7 class7 = new fig7();
	                        class7.main(login,senha,local,diretorio,Pracas);
	                        fig8 class8 = new fig8();
	                        class8.main(login,senha,local,diretorio,Pracas);
	                        fig9 class9 = new fig9();
	                        class9.main(login,senha,local,diretorio,Pracas);
	                        fig10 class10 = new fig10();
	                        class10.main(login,senha,local,diretorio,Pracas);
	                        fig11 class11 = new fig11();
	                        class11.main(login,senha,local,diretorio,Pracas);
	                        fig12 class12 = new fig12();
	                        class12.main(login,senha,local,diretorio,Pracas);
	                        fig13 class13 = new fig13();
	                        class13.main(login,senha,local,diretorio,Pracas);
	                        fig14 class14 = new fig14();
	                        class14.main(login,senha,local,diretorio,Pracas);
	                        fig15 class15 = new fig15();
	                        class15.main(login,senha,local,diretorio,Pracas);
	                        fig16 class16 = new fig16();
	                        class16.main(login,senha,local,diretorio,Pracas);
	                        fig17 class17 = new fig17();
	                        class17.main(login,senha,local,diretorio,Pracas);
	                        fig18 class18 = new fig18();
	                        class18.main(login,senha,local,diretorio,Pracas);
	                        fig19 class19 = new fig19();
	                        class19.main(login,senha,local,diretorio,Pracas);
		        			frame.dispose();
		        			}else{
		        				JOptionPane.showMessageDialog(frame, "Selecione alguma praça para executar análise!");
		        				Pracas.clear();
		        			}
		        		}
		        	}
						
		        }
		      } );
		    
		    
		    frame.pack(); frame.validate();
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(500,500);
		    frame.setVisible(true);
		  }
	
	 
	
}
