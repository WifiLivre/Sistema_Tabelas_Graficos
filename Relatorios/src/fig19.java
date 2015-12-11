import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class fig19 {
	

public  void main(String login, String senha,String local,String diretorio,List<Integer> pracas){
	int numeros = pracas.size();
	String pesquisa="id_praca="+pracas.get(0);
	for(int k=1;k<numeros;k++){
		pesquisa=pesquisa+" or id_praca="+pracas.get(k);
		
	}
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( 
				"select id_praca,count(case when perdaDePacotes/100 = 0 then 1 else null end) as '0', "+
				"count(case when perdaDePacotes/100 > 0 and perdaDePacotes/100 <= 0.01 then 1 else null end) as '1', "+
                "count(case when perdaDePacotes/100 > 0.01 and perdaDePacotes/100 <= 0.02 then 1 else null end)as '2', "+
                "count(case when perdaDePacotes/100 > 0.02 and perdaDePacotes/100 <= 0.04 then 1 else null end)as '4', "+
                "count(case when perdaDePacotes/100 > 0.04 and perdaDePacotes/100 <= 0.08 then 1 else null end)as '8', "+
                "count(case when perdaDePacotes/100 > 0.08 and perdaDePacotes/100 <= 0.16 then 1 else null end)as '16', "+
                "count(case when perdaDePacotes/100 > 0.16 and perdaDePacotes/100 <= 0.32 then 1 else null end)as '32', "+
                "count(case when perdaDePacotes/100 > 0.32 and perdaDePacotes/100 <= 0.64 then 1 else null end)as '64', "+
                "count(case when perdaDePacotes/100 >0.64 then 1 else null end) as 'mais' "+
                "from simetpraca "+
                "where "+
							 pesquisa+
                             " group by id_praca;");
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			BufferedWriter StrW = null;
			try {
				StrW = new BufferedWriter(new FileWriter(diretorio+"\\fig19.csv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StrW.write("praca ; 0 ; 1 ; 2 ; 4 ; 8; 16 ; 32 ; 64 ; mais \n");
			while(rs3.next()){
				
				try {
					StrW.write(rs3.getString(1)+";"+rs3.getString(2)+";"+rs3.getString(3)+";"+rs3.getString(4)+";"+rs3.getString(5)+";"+rs3.getString(6)+";"+rs3.getString(7)+";"+rs3.getString(8)+";"+rs3.getString(9)+";"+rs3.getString(10)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
		
				
			
			}
			StrW.close();
			System.out.println("FIM 19");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}
}
