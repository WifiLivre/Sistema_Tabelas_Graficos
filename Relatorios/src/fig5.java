import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class fig5 {
	public  void main(String login, String senha,String local,String diretorio,List<Integer> pracas) {
		int numeros = pracas.size();
		String pesquisa="id_praca="+pracas.get(0);
		for(int k=1;k<numeros;k++){
			pesquisa=pesquisa+" or id_praca="+pracas.get(k);
			
		}
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( "select DATE_FORMAT(id_data_hora,'%H') as hora , id_praca,CEILING(avg(usuarios)) as mediaUsuarios , format((avg(entrada)/1024),2,'de_DE') as mediaEntrada "+
				"from dados_praca_por_hora where "+
                "("+pesquisa+") "+ 
                "group by id_praca,hora;" );
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			BufferedWriter StrW = null;
			try {
				StrW = new BufferedWriter(new FileWriter(diretorio+"\\fig5.csv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StrW.write("hora ; pracas; médiaUsuarios ; MédiaEntradaMbits  \n");
			while(rs3.next()){
				
				try {
					StrW.write(rs3.getString(1)+";"+rs3.getString(2)+";"+rs3.getString(3)+";"+rs3.getString(4)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
		
				
			
			}
			StrW.close();
			System.out.println("FIM 5");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}

}
