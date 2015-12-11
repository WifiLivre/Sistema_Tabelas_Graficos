import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class fig7 {
public  void main(String login, String senha,String local,String diretorio,List<Integer> pracas) {
	int numeros = pracas.size();
	String pesquisa="id_praca="+pracas.get(0);
	for(int k=1;k<numeros;k++){
		pesquisa=pesquisa+" or id_praca="+pracas.get(k);
		
	}
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( "select id_praca,count(case when entrada/1024 <= 0.1 then 1 else null end) as '0.1', "+
				   "count(case when entrada/1024 > 0.1 and entrada/1024 <= 0.3 then 1 else null end) as '0.3', "+
                   "count(case when entrada/1024 > 0.3 and entrada/1024 <= 0.6 then 1 else null end)as '0.6', "+
                   "count(case when entrada/1024 > 0.6 and entrada/1024 <= 1 then 1 else null end)as '1', "+
                   "count(case when entrada/1024 >1 and entrada/1024 <= 2 then 1 else null end)as '2', "+
                   "count(case when entrada/1024 >2 and entrada/1024 <= 3 then 1 else null end)as '3', "+
                   "count(case when entrada/1024 >3 and entrada/1024 <= 4 then 1 else null end)as '4', "+
                   "count(case when entrada/1024 >4 and entrada/1024 <= 5 then 1 else null end)as '5', "+
                   "count(case when entrada/1024 >5 and entrada/1024 <= 7.5 then 1 else null end)as '7.5', "+
                   "count(case when entrada/1024 >7.5 and entrada/1024 <= 10 then 1 else null end)as '10', "+
                   "count(case when entrada/1024 >10 and entrada/1024 <= 15 then 1 else null end)as '15', "+
                   "count(case when entrada/1024 >15 and entrada/1024 <= 30 then 1 else null end)as '30', "+
                   "count(case when entrada/1024 >30 and entrada/1024 <= 45 then 1 else null end)as '45', "+
                   "count(case when entrada/1024 >45 then 1 else null end) as 'mais' "+
                   "from dados_praca_por_hora "+
                   "where "+
								pesquisa+ 
				" group by id_praca;");
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			BufferedWriter StrW = null;
			try {
				StrW = new BufferedWriter(new FileWriter(diretorio+"\\fig7.csv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StrW.write("praca ; 0.1 ; 0.3 ; 0.6 ; 1 ; 2 ; 3 ; 4 ; 5 ; 7.5 ; 10 ; 15 ; 30 ; 45 ; mais \n");
			while(rs3.next()){
				
				try {
					StrW.write(rs3.getString(1)+";"+rs3.getString(2)+";"+rs3.getString(3)+";"+rs3.getString(4)+";"+rs3.getString(5)+";"+rs3.getString(6)+";"+rs3.getString(7)+";"+rs3.getString(8)+";"+rs3.getString(9)+";"+rs3.getString(10)+";"+rs3.getString(11)+";"+rs3.getString(12)+";"+rs3.getString(13)+";"+rs3.getString(14)+";"+rs3.getString(15)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
		
				
			
			}
			StrW.close();
			System.out.println("FIM 7");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}
}
