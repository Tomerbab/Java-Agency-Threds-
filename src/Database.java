import java.sql.*;
public class Database {
	private Connection conn;
	private Statement stmt;
	ResultSet result=null;

	public Database(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/test";
			conn=DriverManager.getConnection(url, "root", "root");
			stmt=conn.createStatement();
		}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public Strategy extractFromTable(String table){//extractFromTable the strategy with the lowest time
		ResultSet result = null;
		Strategy s= null;
		String extractDetails = "SELECT * FROM "+table+" ORDER BY Time ASC LIMIT 1";
		try{
			result = stmt.executeQuery(extractDetails);
			if(result.next()){
				int id = result.getInt("ID");
				String Code =result.getString("Code");
				int client = result.getInt("client");
				int Severity = result.getInt("Severity");
				long Time = result.getLong("Time");
				s = new Strategy(id,Code,client,Severity,Time);
				deleteRecord(table,id);
			}
		}
		catch(SQLException e) { 
			System.out.println(e.getStackTrace());
		}
		return s;
	}


	void addTable(String table) {
		try{
			String sql = "CREATE TABLE " +table+ "(ID int, Code varchar(50),Client int, Severity int, Time long)";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	void insert(String table, Strategy s) {
		try{
			int ID = s.getId(); 
			String Code =s.getName();
			int client = s.getcType();
			int Severity = s.getLevel();
			long Time = s.getOpTime();
			stmt.executeUpdate("INSERT INTO "+table+" VALUES("+ID+", '"+ Code+"', '"+ Severity+"', '"+ client+"', '"+ Time+"')");
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	void deleteRecord(String tableN, int ID){
		try{
			stmt.executeUpdate("DELETE FROM "+tableN+" WHERE ID="+ID);// not sure, maybe return AreaCode ???
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	void dropTable(String tableN){
		try{
			stmt.executeUpdate("Drop TABLE "+tableN);
		}
		catch(SQLException ex){
		}
	}

	public void queryTest(){
		if(conn==null) return;
		String query="select * from PN";
		try{
			result=stmt.executeQuery(query);
			print();

		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	public void print(){
		try{
			System.out.println("# "+"ID"+"\t"+" Code"+"\t"+" Severity"+"\t"+"client"+"\t"+"Time"+"\t");
			System.out.println("- ---------"+"\t"+" ------"+"\t" + " -------"+"\t"+" -------"+"\t");
			while(result.next()){
				int ID=result.getInt("ID");
				String Code=result.getString("Code");
				int Severity=result.getInt("Severity");
				int client=result.getInt("client");
				int Time=result.getInt("Time");
				System.out.println(result.getRow()+ " "+ID+"\t "+Code+"\t" +Severity+"\t"+client+"\t" +Time+"\t" );
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Database x=new Database();
		Strategy s = new Strategy (38883, "Description1",1,2,3);
		Strategy s1 = new Strategy (38882, "Description2",4,6,30);
		x.dropTable("PN");
		x.addTable("PN");
		x.insert("PN",s);
		x.insert("PN",s1);
		x.queryTest();
		x.deleteRecord("PN", 38883);
		x.queryTest();
		x.dropTable("PN");
	}
}
