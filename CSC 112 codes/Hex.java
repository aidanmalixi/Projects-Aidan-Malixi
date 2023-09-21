
public class Hex {
    public static int parseHex(String hexStr) {
        int decimal = 0;
        int power = 0;
        for (int i = hexStr.length() - 1; i >= 0; i--) {
            char hexChar = hexStr.charAt(i);
            int num = convertHexToDec(hexChar);
            decimal += num * (int) Math.pow(16, power);
            power++;
        }
        return decimal;
    }
    public static int convertHexToDec(char ch) throws NumberFormatException {
        if (ch >= '0' && ch <= '9') {
             return ch - '0';
        }
        else if (ch >= 'A' && ch <= 'F') {
            return ch - 'A' + 10;
        }
        else {
            throw new NumberFormatException("Invalid String");
        }
    }
    
	public static void main (String[] args) {
        try {
	        System.out.println(parseHex("A5"));
	        System.out.println(parseHex("FFA"));
	        System.out.println(parseHex("ABC"));
	        System.out.println(parseHex("10A"));
	        System.out.println(parseHex("T10"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
	    
	}
}


