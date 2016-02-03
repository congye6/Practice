package nju.sec.yz.ExpressSystem.data.fileUtility.sql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class SerializetHelper {

	static public Object deSerialize(Blob blob){
		
		ByteArrayInputStream bais;  
        ObjectInputStream in = null;  
        try{ 
        	byte[] bytes=blob.getBytes(1, (int)blob.length());
            bais = new ByteArrayInputStream(bytes);  
            in = new ObjectInputStream(bais);  
            return in.readObject();  
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{  
            if(in != null){  
                try {  
                    in.close();  
                } catch (IOException e) {  
                    e.printStackTrace(); 
                }  
            }  
        }
        return null;
	}
	
	static public byte[] serialize(Object obj){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        ObjectOutputStream out = null;  
        try {  
            out = new ObjectOutputStream(baos);  
            out.writeObject(obj);        
        } catch (IOException e) {  
            e.printStackTrace();
        }finally{  
            try {  
                out.close();  
            } catch (IOException e) {  
                e.printStackTrace(); 
            }  
        }  
          
        return baos.toByteArray();
	}
	
	
	
}
