import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class fig17 {


public  void main(String login, String senha,String local,String diretorio,List<Integer> pracas) {
	int numeros = pracas.size();
	String pesquisa="simet.id_praca="+pracas.get(0);
	String pesquisa2="praca.id_praca="+pracas.get(0);
	for(int k=1;k<numeros;k++){
		pesquisa=pesquisa+" or simet.id_praca="+pracas.get(k);
		pesquisa2=pesquisa2+" or praca.id_praca="+pracas.get(k);
	}
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( "select praca.id_praca , hour(praca.id_data_hora) as hora, ceil(avg(praca.usuarios)) as usuarios,perda from dados_praca_por_hora as praca "+
				  "left join "+
				  "(select hour(simet.id_data_hora) as hora,simet.id_data_hora,simet.id_praca ,format(case when(avg(PerdaDePacotes)/100)>1 then 1 else (avg(PerdaDePacotes)/100) end,4 ,'de_DE')  as perda from simetpraca as simet "+
										"where  "+
				                                pesquisa + 
						" group by simet.id_praca  ,hora  "+
				        "order by simet.id_praca asc,hora asc) as pesquisa "+
				        "on hour(praca.id_data_hora) = hour(pesquisa.id_data_hora) and praca.id_praca= pesquisa.id_praca "+
				        "where  "+
				                                pesquisa2 +     
				   
				   " group by "+ 
						"praca.id_praca  , hora "+ 
				   "order by "+
						"praca.id_praca asc , hora asc;");
				
			
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			BufferedWriter StrW = null;
			try {
				StrW = new BufferedWriter(new FileWriter(diretorio+"\\fig17.csv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StrW.write(" praca; hora; ;media usuarios  ; pocentagem perda \n");
			while(rs3.next()){
				
				try {
					StrW.write(rs3.getString(1)+";"+rs3.getString(2)+";"+rs3.getString(3)+";"+rs3.getString(4)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
		
				
			
			}
			StrW.close(); 
			System.out.println("FIM 17");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}

	
}
