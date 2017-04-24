package gdx.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipInputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;


/**
 * 规划案导入离线数据包
 * */
public class FileImportForZipUtils {
	
	public static void main(String[] args) {
		try {  
			//readZipFile("E:\\20160400006268.zip",2048);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * 导入离线上传数据包
	 * @param file 解压文件路径
	 * @param buffer 缓冲大小
	 * @throws IOException 
	 * */
	public  void readZipFile(String unZipfileName, int buffer) {
		
	}
		

	
	
	 public static void readZipFile(String file) throws Exception {  
		 Charset gbk = Charset.forName("GBK");
         ZipFile zf = new ZipFile(file,"gbk");  
         InputStream in = new BufferedInputStream(new FileInputStream(file));  
         ZipInputStream zin = new ZipInputStream(in);  
         ZipEntry ze;  
         while ((ze = (ZipEntry) zin.getNextEntry()) != null) {  
             if (ze.isDirectory()) {
             } else {  
                 System.err.println("file - " + ze.getName() + " : "  
                         + ze.getSize() + " bytes");  
                 long size = ze.getSize();  
                 if (size > 0) {  
                     BufferedReader br = new BufferedReader(  
                             new InputStreamReader(zf.getInputStream(ze)));  
                     String line;  
                     while ((line = br.readLine()) != null) {  
                         System.out.println(line);  
                     }  
                     br.close();  
                 }  
                 System.out.println();  
             }  
         }  
         zin.closeEntry();  
     }  
	
}
