package platformDependent;

public class Platform {
	private static String OS = null;
	
	public static String getOsName() {
		if(OS == null) {
			OS = System.getProperty("os.name");
		}
		
		return OS;
	}
	
	private static boolean check(String os) {
		return getOsName().startsWith(os);
	}
	
	public static boolean isWindows() {
		return check("Windows");
	}
	
	public static boolean isLinux() {
		return check("Linux");
	}
	
	public static boolean isMacOs() {
		return check("MacOs");
	}
}