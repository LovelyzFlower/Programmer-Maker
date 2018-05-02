package PM_game;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.sql.SQLException;

public class MemberManagementDB {
	Database db;
	public MemberManagementDB(Database db){
		this.db = db;
	}
	
	//ID�� �޾Ƽ� DB�� ���� �ִ��� �˻�
	public boolean searchID(String id, String pwd){
		boolean result = false;
		try{
			//�˻� ���Ǹ� ����
			String sql = "";
			if(pwd.equals("")){
				sql = "select * from user where db_id='"+
						id+"'";//new String(id.getBytes(),"ISO-8859-1")
			}else{
				sql = "select * from user where db_id='"+
						id+"' and db_pw='"+//new String(id.getBytes(),"ISO-8859-1")
						pwd+"'";//new String(pwd.getBytes(),"ISO-8859-1")
			}
			ResultSet searchResult = db.stmt.executeQuery(sql);
			
			if(searchResult.next()){
				ResultSetMetaData meta = searchResult.getMetaData();
				if(meta.getColumnCount()>0){
					result = true;
				}else{
					System.out.println("검색된 결과가 없습니다.");
				}
			}else{
				System.out.println("검색된 결과가 없습니다.");
			}
		//}catch (UnsupportedEncodingException e){
		//	e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
}
