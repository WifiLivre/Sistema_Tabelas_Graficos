import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class fig6 {
	public  void main(String login, String senha,String local,String diretorio,List<Integer> pracas) {
		int numeros = pracas.size();
		String pesquisa="dados_praca_por_hora.id_praca="+pracas.get(0);
		for(int k=1;k<numeros;k++){
			pesquisa=pesquisa+" or dados_praca_por_hora.id_praca="+pracas.get(k);
			
		}
		AcessaBanco Conecta = new AcessaBanco(login,senha,local); 
		Conecta.setSql( "select day(id_data_hora) as dia,month(id_data_hora) as mes,year(id_data_hora)  as ano,date_format(id_data_hora,'%Y-%m-%d') as DataCompleta,dados_praca_por_hora.id_praca,format(sum(entrada)/1024,2,'de_DE') as entradaTotal,avg(usuarios) as usu ,format(avg(usuarios)/praca.n_max_usuarios,2,'de_DE') as PorcentagemUso from dados_praca_por_hora   "+ 
		        "left Join "+
                "praca on praca.id_praca= dados_praca_por_hora.id_praca "+
                "where  "+
							"DATE_FORMAT(id_data_hora,'%H')>=8 and DATE_FORMAT(id_data_hora,'%H')<=18 and "+
                            "("+pesquisa+" ) "+
    "group by id_praca asc , dia asc , mes asc, ano asc "+
    "order by id_praca asc , ano asc , mes asc, dia asc;");
		Conecta.abrirConexao();
		ResultSet rs3 = Conecta.consulta();
		try {
			BufferedWriter StrW = null;
			try {
				StrW = new BufferedWriter(new FileWriter(diretorio+"\\fig6.csv"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StrW.write("dia ; mes ; ano ; data e hora ; praca ; entradaTotal Mbits ; MédiaUsuarios  ; porcentagem de uso \n");
			while(rs3.next()){
				
				try {
					StrW.write(rs3.getString(1)+";"+rs3.getString(2)+";"+rs3.getString(3)+";"+rs3.getString(4)+";"+rs3.getString(5)+";"+rs3.getString(6)+";"+rs3.getString(7)+";"+rs3.getString(8)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
		
				
			
			}
			StrW.close();
			System.out.println("FIM 6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}

}
