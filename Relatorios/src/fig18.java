import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class fig18 {

public  void main(String login, String senha,String local,String diretorio,List<Integer> pracas) {
	int numeros = pracas.size();
	String pesquisa="simet.id_praca="+pracas.get(0);
	String pesquisa2="dados.id_praca="+pracas.get(0);
	for(int k=1;k<numeros;k++){
		pesquisa=pesquisa+" or simet.id_praca="+pracas.get(k);
		pesquisa2=pesquisa2+" or dados.id_praca="+pracas.get(k);
	}
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( "select dados.id_praca, format(case when(avg(perda)/100)>1 then 1 else (avg(perda)/100) end,5,'de_DE') as perda,format(usuarios/n_max_usuarios,4,'de_DE') as percentual from dados_praca_por_hora as dados "+
				  "left join ( "+
							"select day(id_data_hora) as dia,month(id_data_hora) as mes,year(id_data_hora)  as ano,hour(id_data_hora) as hora,id_praca ,avg(perdaDePacotes) as perda from simetpraca as simet "+
												"where  "+
					                                pesquisa+ 
							" group by simet.id_praca  ,hora , dia  , mes , ano  "+
					        "order by simet.id_praca asc, ano asc , mes asc, dia asc ,hora asc) as pesquisa "+
							"on day(dados.id_data_hora) = dia  and   month(dados.id_data_hora) = mes and year(dados.id_data_hora) = ano and hour(dados.id_data_hora) = hora and dados.id_praca = pesquisa.id_praca "+
					   "left Join "+
					                    "praca on praca.id_praca= dados.id_praca "+
					   "where "+
					                                pesquisa2+     
					   " group by id_praca, percentual "+
					   "order by "+ 
							"dados.id_praca asc , percentual asc , perda asc;");
				
			
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			BufferedWriter StrW = null;
			try {
				StrW = new BufferedWriter(new FileWriter(diretorio+"\\fig18.csv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StrW.write("praca; pocentagem perda ; porcentagem de uso \n");
			while(rs3.next()){
				
				try {
					StrW.write(rs3.getString(1)+";"+rs3.getString(2)+";"+rs3.getString(3)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
		
				
			
			}
			StrW.close();
			System.out.println("FIM 18");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}

	
}
