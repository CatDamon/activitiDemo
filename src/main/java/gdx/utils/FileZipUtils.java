package gdx.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class FileZipUtils{ 
    private ZipFile        zipFile; 
    private ZipOutputStream zipOut;    //压缩Zip 
    private ZipEntry        zipEntry; 
    private static int      bufSize;    //size of bytes 
    private byte[]          buf; 
    private int            readedBytes; 
    
    public FileZipUtils(){ 
        this(512); 
    } 

    public FileZipUtils(int bufSize){ 
        this.bufSize = bufSize; 
        this.buf = new byte[this.bufSize]; 
    } 
    
    //压缩文件夹内的文件 
    public void doZip(String zipDirectory){//zipDirectoryPath:需要压缩的文件夹名 
        File file; 
        File zipDir; 

        zipDir = new File(zipDirectory); 
        String zipFileName = zipDir.getName() + ".zip";//压缩后生成的zip文件名 

        try{ 
            this.zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName))); 
            handleDir(zipDir , this.zipOut); 
            this.zipOut.close(); 
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        } 
    } 

    //由doZip调用,递归完成目录文件读取 
    private void handleDir(File dir , ZipOutputStream zipOut)throws IOException{ 
        FileInputStream fileIn; 
        File[] files; 

        files = dir.listFiles(); 
    
        if(files.length == 0){//如果目录为空,则单独创建之. 
            //ZipEntry的isDirectory()方法中,目录以"/"结尾. 
            this.zipOut.putNextEntry(new ZipEntry(dir.toString() + "/")); 
            this.zipOut.closeEntry(); 
        } 
        else{//如果目录不为空,则分别处理目录和文件. 
            for(File fileName : files){ 
                //System.out.println(fileName); 

                if(fileName.isDirectory()){ 
                    handleDir(fileName , this.zipOut); 
                } 
                else{ 
                    fileIn = new FileInputStream(fileName); 
                    this.zipOut.putNextEntry(new ZipEntry(fileName.toString())); 

                    while((this.readedBytes = fileIn.read(this.buf))>0){ 
                        this.zipOut.write(this.buf , 0 , this.readedBytes); 
                    } 

                    this.zipOut.closeEntry(); 
                } 
            } 
        } 
    } 

    //解压指定zip文件 
    public void unZip(String unZipfileName){//unZipfileName需要解压的zip文件名 
        FileOutputStream fileOut; 
        File file; 
        InputStream inputStream; 

        try{ 
            this.zipFile = new ZipFile(unZipfileName,"gbk"); 

            for(Enumeration entries = this.zipFile.getEntries(); entries.hasMoreElements();){
                ZipEntry entry = (ZipEntry)entries.nextElement(); 
                
                file = new File("E:\\" + entry.getName()); 
                

                if(entry.isDirectory()){  //仅仅是目录就创建目录
                    file.mkdirs(); 
                } 
                else{  //否则输出文件
                    //如果指定文件的目录不存在,则创建之. 
                    File parent = file.getParentFile(); 
                    if(parent != null && !parent.exists()){ 
                        parent.mkdirs(); 
                    }
                 
                    
                    if(".xls".equals(entry.getName().substring(entry.getName().indexOf("."), entry.getName().length()-1))){
                    	
                    }
                    
                    
                    
                    inputStream = zipFile.getInputStream(entry); 

                    fileOut = new FileOutputStream(file); 
                    
                    byte[] buf1 = new byte[1024]; // 设置缓存
	                int len;
	                while ((len = inputStream.read(buf1)) > 0) {
	                	fileOut.write(buf1, 0, len);
	                } 
                    fileOut.close(); 

                    inputStream.close(); 
                }    
            } 
            this.zipFile.close(); 
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        } 
    } 

    //设置缓冲区大小 
    public void setBufSize(int bufSize){ 
        this.bufSize = bufSize; 
    } 

    //测试AntZip类 
    public static void main(String[] args)throws Exception{ 
    	FileZipUtils zip = new FileZipUtils(1024); 
    	zip.unZip("E:\\20160400006268.zip");
    } 
}
