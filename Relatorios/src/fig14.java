import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class fig14 {
public  void main(String login, String senha,String local,String diretorio,List<Integer> pracas){
	int numeros = pracas.size();
	String pesquisa="id_praca="+pracas.get(0);
	for(int k=1;k<numeros;k++){
		pesquisa=pesquisa+" or id_praca="+pracas.get(k);
		
	}
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( "select id_praca,format(mediaLatencia,2 ,'de_DE') , format( PorcentagemUso,4,'de_DE') from "+
				 "(select  id_praca , numeroMaxUsuarios as numeroMax,numeroUsuarios as usuarios,avg(latencia)  as mediaLatencia , (numeroUsuarios/numeroMaxUsuarios) as PorcentagemUso  "+
							"from latenciausuarios where  "+
					                                pesquisa + 
					        " group by id_praca , numeroUsuarios  "+
					        "order by id_praca asc , numeroUsuarios asc) as pesquisas "+
					        
					        "group by id_praca , PorcentagemUso "+
					        "order by id_praca asc , PorcentagemUso asc;" );
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			BufferedWriter StrW = null;
			try {
				StrW = new BufferedWriter(new FileWriter(diretorio+"\\fig14.csv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StrW.write("praca; mediaLatencia  ; porcentagem de uso \n");
			while(rs3.next()){
				
				try {
					StrW.write(rs3.getString(1)+";"+rs3.getString(2)+";"+rs3.getString(3)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
		
				
			
			}
			StrW.close();
			System.out.println("FIM 14");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}

}
