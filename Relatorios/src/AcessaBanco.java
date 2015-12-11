import java.sql.*;

public class AcessaBanco {

	private String sql;
	private Connection conexao;
	private String usuario; 		//nome usuario banco
	private String senha;				//senha do usuario no banco
	private String banco;	//Local e Servidor do banco
		
	public AcessaBanco(){
		this.usuario = "root";
		this.senha = "";
		this.banco = "//localhost/novonovembro";
		//Construtor de Carregar o drive MySql de Acesso ao Banco
		this.carregarDriver();
	}
	
	public AcessaBanco(String usuarioBanco,String senhaBanco,String caminhoBanco){
		this.usuario = usuarioBanco;
		this.senha = senhaBanco;
		this.banco = caminhoBanco;
		//Construtor de Carregar o drive MySql de Acesso ao Banco
		this.carregarDriver();
	}
	
	public void setSql(String _sql){
		this.sql = _sql;
	}
	
	public String getSql(){
		return(sql);
	}
	public void carregarDriver(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//JOptionPane.showMessageDialog(null, "Driver carregado com sucesso!");
			System.out.println("Driver carregado com sucesso!");
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null,"Driver n�o carregado, verifique importa��o do driver\n"+e);
			System.out.println("Driver n�o carregado, verifique importa��o do driver\n"+e);
		}
	}
	
	public void abrirConexao(){
		try{
			conexao = DriverManager.getConnection("jdbc:mysql:"+ banco,usuario,senha);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Conex�o n�o inicializada :(");
		}
	}
	
	public void fecharConexao(){
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("N�o foi possivel fechar conex�o ... continua aberta :(");
		}
	}
	
	public ResultSet consulta(){
		try {
			PreparedStatement psmt = conexao.prepareStatement(sql);
			ResultSet consulta = psmt.executeQuery();
			return consulta;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("N�o realiza a consulta :(");
			return null;
		}
	}
	
	public void executa(){
		try {
			PreparedStatement psmt = conexao.prepareStatement(sql);
			psmt.execute();					
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro de execu��o Sql");
		}
	}

}
