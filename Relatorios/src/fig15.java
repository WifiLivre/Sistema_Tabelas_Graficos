import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class fig15 {

public  void main(String login, String senha,String local,String diretorio,List<Integer> pracas) {
	int numeros = pracas.size();
	String pesquisa="id_praca="+pracas.get(0);
	for(int k=1;k<numeros;k++){
		pesquisa=pesquisa+" or id_praca="+pracas.get(k);
		
	}
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( 
				"select id_praca,count(case when latencia <= 1 then 1 else null end) as '1', "+
				   "count(case when latencia > 1 and latencia <= 2 then 1 else null end) as '2', "+
                "count(case when latencia > 2 and latencia <= 4 then 1 else null end)as '4', "+
                "count(case when latencia > 4 and latencia <= 8 then 1 else null end)as '8', "+
                "count(case when latencia >8 and latencia <= 16 then 1 else null end)as '16', "+
                "count(case when latencia >16 and latencia <= 32 then 1 else null end)as '32', "+
                "count(case when latencia >32 and latencia <= 64 then 1 else null end)as '64', "+
                "count(case when latencia >64 and latencia <= 128 then 1 else null end)as '128', "+
                "count(case when latencia >128 and latencia <= 256 then 1 else null end)as '256', "+
                "count(case when latencia >256 and latencia <= 512 then 1 else null end)as '512', "+
                "count(case when latencia >512 and latencia <= 1024 then 1 else null end)as '1024', "+
                "count(case when latencia >1024 and latencia <= 2048 then 1 else null end)as '2048', "+
                "count(case when latencia >2048 and latencia <= 4096 then 1 else null end)as '4096', "+
                "count(case when latencia >4096 then 1 else null end) as '8192' "+
                "from latenciausuarios "+
                "where "+
							 pesquisa +
                             " group by id_praca;");
				
			
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			BufferedWriter StrW = null;
			try {
				StrW = new BufferedWriter(new FileWriter(diretorio+"\\fig15.csv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StrW.write("praca ; 1 ; 2 ; 4 ; 8 ; 16; 32 ; 64 ; 128 ; 256 ; 512; 1024;2048;4096;8192 \n");
			while(rs3.next()){
				
				try {
					StrW.write(rs3.getString(1)+";"+rs3.getString(2)+";"+rs3.getString(3)+";"+rs3.getString(4)+";"+rs3.getString(5)+";"+rs3.getString(6)+";"+rs3.getString(7)+";"+rs3.getString(8)+";"+rs3.getString(9)+";"+rs3.getString(10)+";"+rs3.getString(11)+";"+rs3.getString(12)+";"+rs3.getString(13)+";"+rs3.getString(14)+";"+rs3.getString(15)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
		
				
			
			}
			StrW.close();
			System.out.println("FIM 15");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}
}
