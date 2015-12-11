import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class fig11 {
	

public  void main(String login, String senha,String local,String diretorio,List<Integer> pracas) {
	int numeros = pracas.size();
	String pesquisa="dados_praca_por_hora.id_praca="+pracas.get(0);
	for(int k=1;k<numeros;k++){
		pesquisa=pesquisa+" or dados_praca_por_hora.id_praca="+pracas.get(k);
		
	}
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( "select dados_praca_por_hora.id_praca,count(case when (entrada/praca.n_max_usuarios)/1024 <= 0.1 then 1 else null end) as '0,1', "+
				   "count(case when (entrada/praca.n_max_usuarios)/1024 > 0.1 and (entrada/praca.n_max_usuarios)/1024 <= 0.2 then 1 else null end) as '0,2', "+
                   "count(case when (entrada/praca.n_max_usuarios)/1024 > 0.2 and (entrada/praca.n_max_usuarios)/1024 <= 0.3 then 1 else null end)as '0,3', "+
                   "count(case when (entrada/praca.n_max_usuarios)/1024 > 0.3 and (entrada/praca.n_max_usuarios)/1024 <= 0.4 then 1 else null end)as '0,4', "+
                   "count(case when (entrada/praca.n_max_usuarios)/1024 >0.4 and (entrada/praca.n_max_usuarios)/1024 <= 0.5 then 1 else null end)as '0,5', "+
                   "count(case when (entrada/praca.n_max_usuarios)/1024 >0.5 and (entrada/praca.n_max_usuarios)/1024 <= 0.6 then 1 else null end)as '0,6', "+
                   "count(case when (entrada/praca.n_max_usuarios)/1024 >0.6 and (entrada/praca.n_max_usuarios)/1024 <= 0.7 then 1 else null end)as '0,7', "+
                   "count(case when (entrada/praca.n_max_usuarios)/1024 >0.7 and (entrada/praca.n_max_usuarios)/1024 <= 0.8 then 1 else null end)as '0,8', "+
                   "count(case when (entrada/praca.n_max_usuarios)/1024 >0.8 and (entrada/praca.n_max_usuarios)/1024 <= 0.9 then 1 else null end)as '0,9', "+
                   "count(case when (entrada/praca.n_max_usuarios)/1024 >0.9 and (entrada/praca.n_max_usuarios)/1024 <= 1 then 1 else null end)as '1', "+
                   "count(case when (entrada/praca.n_max_usuarios)/1024 >1 then 1 else null end) as 'mais' "+
                   "from dados_praca_por_hora "+
                   "left Join "+
						"praca on praca.id_praca= dados_praca_por_hora.id_praca "+ 
                   "where "+
								pesquisa+ 
				   " group by id_praca;");
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			BufferedWriter StrW = null;
			try {
				StrW = new BufferedWriter(new FileWriter(diretorio+"\\fig11.csv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StrW.write("praca ; 0,1 ; 0,2 ; 0,3 ; 0,4 ; 0,5; 0,6 ; 0,7 ; 0,8 ; 0,9 ; 1; mais \n");
			while(rs3.next()){
				
				try {
					StrW.write(rs3.getString(1)+";"+rs3.getString(2)+";"+rs3.getString(3)+";"+rs3.getString(4)+";"+rs3.getString(5)+";"+rs3.getString(6)+";"+rs3.getString(7)+";"+rs3.getString(8)+";"+rs3.getString(9)+";"+rs3.getString(10)+";"+rs3.getString(11)+";"+rs3.getString(12)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
		
				
			
			}
			StrW.close();
			System.out.println("FIM 11");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}

}
