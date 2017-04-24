package gdx.IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
	public static void main(String[] args) {
		String[] str = {"b.*\\.txt"};
		File file = new File("E:\\test");
		String[] list;
		if(str.length == 0){
			list = file.list();
		}else{
			list = file.list(new DirFilter(str[0]));
			Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
			for (String string : list) {
				System.out.println(string);
			}
		}
	}
}

/**
 * 目录列表器
 * 通过文件名去查询该目录下文件
 * */
class DirFilter implements FilenameFilter{
	private Pattern pattern;
	public DirFilter (String regex) {
		pattern = Pattern.compile(regex);
	}
	
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
	
	
}


