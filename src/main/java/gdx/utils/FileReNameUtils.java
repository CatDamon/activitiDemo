package gdx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 文件移动重命名工具类
 * */
public class FileReNameUtils {
	
	public static void main(String[] args) {
		try {
			FileReNameUtils.reNameByMove("C:/Users/Administrator/Desktop/01/001-图片1-01.jpg",
					"C:/Users/Administrator/Desktop/01/002-图片2-01.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void reNameByMove (String pathPre, String pathNext) {
		File file = new File(pathPre);
		File file2 = new File(pathNext);
		String preName = file.getAbsolutePath();//001-图片1-01.jsp 
		String nextName = file2.getAbsolutePath();// 002-图片2-01.jsp
		System.out.println(preName);
		String tempName = file.getParent() + "\\xxx.jpg"; // 001-图片1-01.jsp 
		boolean renameTo = file.renameTo(new File(tempName));
		boolean renameTo2 = file2.renameTo(new File(preName));
		//重新获取临时文件
		File file3 = new File(tempName);
		boolean renameTo3 = file3.renameTo(new File(nextName));
		//boolean renameTo3 = file.renameTo(new File(nextName));
		
	}
	


}
