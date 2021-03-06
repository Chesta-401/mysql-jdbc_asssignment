package batchProcessing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utility.ConnectionFactory;


public class InsertingRecords {

	public static void main(String[] args) {
		Connection connection=ConnectionFactory.getConnection();
		
		long start1= System.currentTimeMillis();

		try {
			for(int i=1;i<=1000;i++)
			{
				String s="Chesta "+i;
				PreparedStatement pstmt=connection.prepareStatement("insert into records(name) values(?)");
				pstmt.setString(1, s);

				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		long end1= System.currentTimeMillis();
		
		System.out.println("Time taken to insert 1000 records without using batch processing: "+ (end1-start1) +"ms");


		//using Batch processing
		long start2= System.currentTimeMillis();

		try {
			connection.setAutoCommit(false);
			for(int i=1;i<=1000;i++)
			{
				String s="Chesta "+i;
				PreparedStatement pstmt=connection.prepareStatement("insert into batchprocessing(name) values(?)");
				pstmt.setString(1, s);

				pstmt.addBatch();
				
				//adding in batch of 200
				if(i%200==0)
				{
					pstmt.executeBatch();
				}
				connection.commit();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

		long end2= System.currentTimeMillis();

		System.out.println("Time taken to insert 1000 records using batch processing: "+ (end2-start2) +"ms");
	
	}

}

